package com.noodlegamer76.fracture.worldgen.carver;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.longs.Long2DoubleMap;
import it.unimi.dsi.fastutil.longs.Long2DoubleOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.CarvingMask;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.levelgen.Aquifer;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.carver.*;
import org.apache.commons.lang3.mutable.MutableBoolean;

import javax.annotation.Nullable;
import java.util.*;
import java.util.function.Function;

public class VoidTrenches<C extends CanyonCarverConfiguration> extends WorldCarver<CanyonCarverConfiguration> {

    public VoidTrenches(Codec<CanyonCarverConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean isStartChunk(CanyonCarverConfiguration config, RandomSource random) {
        return random.nextFloat() < config.probability;
    }

    @Override
    public boolean carve(
            CarvingContext context,
            CanyonCarverConfiguration config,
            ChunkAccess chunk,
            Function<BlockPos, Holder<Biome>> biomeAccessor,
            RandomSource random,
            Aquifer aquifer,
            ChunkPos chunkPos,
            CarvingMask carvingMask
    ) {
        //I had so many issues I had to go through to make this method
        if (!isStartChunk(config, random) || !chunk.getPos().equals(chunkPos)) {
            return false;
        }

        int i = (this.getRange() * 2 - 1) * 16;

        int x = chunk.getPos().getBlockX(0);
        int y = config.y.sample(random, context);
        int z = chunk.getPos().getBlockZ(0);

        float yaw = random.nextFloat() * (float) (Math.PI * 2);
        float pitch = config.verticalRotation.sample(random);

        double horizontalVerticalRatio = config.yScale.sample(random);

        float thickness = config.shape.thickness.sample(random);
        int branchCount = (int)((float)i * config.shape.distanceFactor.sample(random));

        this.doCarve(context, config, chunk, biomeAccessor, random.nextLong(), aquifer, x, y, z, thickness, yaw, pitch, 0, branchCount, horizontalVerticalRatio, carvingMask);
        return true;
    }

    protected void doCarve(
            CarvingContext context,
            CanyonCarverConfiguration config,
            ChunkAccess chunk,
            Function<BlockPos, Holder<Biome>> biomeAccessor,
            long seed,
            Aquifer aquifer,
            int xStart, int yStart, int zStart,
            float baseThickness,
            float yaw, float pitch,
            int branchIndex, int branchCount,
            double horizontalVerticalRatio,
            CarvingMask carvingMask
    ) {
        RandomSource random = RandomSource.create(seed);
        MutableBoolean reachedSurface = new MutableBoolean(false);
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

        int xEnd = xStart + 15;
        int zEnd = zStart + 15;
        int maxY = Math.min(context.getMinGenY() + context.getGenDepth(), 320);
        int minY = context.getMinGenY();

        // Erosion and density functions
        DensityFunction erosionDensity = context.randomState().router().erosion();
        DensityFunction finalDensity = context.randomState().router().finalDensity();

        // Precompute erosion values using xz key
        Long2DoubleMap erosionValues = new Long2DoubleOpenHashMap();
        for (int x = xStart; x <= xEnd; x++) {
            for (int z = zStart; z <= zEnd; z++) {
                long key = (((long) x) & 0xFFFFFFFFL) | ((((long) z) & 0xFFFFFFFFL) << 32);
                double erosion = erosionDensity.compute(new DensityFunction.SinglePointContext(x, yStart, z));
                erosionValues.put(key, erosion);
            }
        }

        // Pass 1: Carve based on erosion >= 0
        for (int x = xStart; x <= xEnd; x++) {
            for (int z = zStart; z <= zEnd; z++) {
                long key = (((long) x) & 0xFFFFFFFFL) | ((((long) z) & 0xFFFFFFFFL) << 32);
                if (erosionValues.get(key) > -0.3) continue;

                pos.set(x, yStart, z);
                for (int y = yStart; y <= maxY; y++) {
                    pos.setY(y);

                    if (carvingMask.get(pos.getX(), pos.getY(), pos.getZ())) {
                        continue;
                    }

                    if (!chunk.getBlockState(pos).isAir()) {
                        if (carveBlock(context, config, chunk, biomeAccessor, carvingMask, pos, pos, aquifer, reachedSurface)) {
                            continue;
                        }
                    }

                    // Stop carving up if air is hit
                    if (chunk.getBlockState(pos).isAir()) break;
                }
            }
        }

        // Pass 2: Carve based on finalDensity
        for (int x = xStart; x <= xEnd; x++) {
            for (int z = zStart; z <= zEnd; z++) {
                float continueFactor = random.nextFloat();
                pos.set(x, yStart, z);
                for (int y = yStart; y <= maxY; y++) {
                    pos.setY(y);

                    if (carvingMask.get(pos.getX(), pos.getY(), pos.getZ())) {
                        continue;
                    }

                    BlockState state = chunk.getBlockState(pos);
                    double density = finalDensity.compute(new DensityFunction.SinglePointContext(x, y, z));

                    if (state.isAir() || density > 0.05) {
                        if (continueFactor < 0.75f) {
                            continueFactor = random.nextFloat();
                        } else {
                            break;
                        }
                    }

                    carveBlock(context, config, chunk, biomeAccessor, carvingMask, pos, pos, aquifer, reachedSurface);
                }
            }
        }

        BlockState state = Blocks.AIR.defaultBlockState();
        if (true) {
            for (int x = xStart; x <= xEnd; x++) {
                for (int z = zStart; z <= zEnd; z++) {
                    for (int y = yStart; y >= minY; y--) {
                        pos.set(x, y, z);
                        chunk.setBlockState(pos, state, false);
                    }
                }
            }

            return;
        }

        // Pass 3: Carve downward based on erosion (reversed)
        for (int x = xStart; x <= xEnd; x++) {
            for (int z = zStart; z <= zEnd; z++) {
                long key = (((long) x) & 0xFFFFFFFFL) | ((((long) z) & 0xFFFFFFFFL) << 32);
                double erosion = 1.0 - erosionValues.get(key);
                int carveHeight = (int) (erosion * 15);

                for (int y = yStart - 1; y >= Math.max(yStart - carveHeight, minY); y--) {
                    pos.set(x, y, z);

                    if (carvingMask.get(pos.getX(), pos.getY(), pos.getZ())) {
                        continue;
                    }

                    carveBlock(context, config, chunk, biomeAccessor, carvingMask, pos, pos, aquifer, reachedSurface);
                }
            }
        }
    }

    //this doesnt work at all
    public static List<BlockPos> GetWalls(CarvingContext context, CanyonCarverConfiguration config, ChunkAccess chunk, BlockPos min, BlockPos max, RandomSource random, CarvingMask carvingMask) {
        List<BlockPos> walls = new ArrayList<>();
        int minY = config.y.sample(random, context);

        BlockPos.MutableBlockPos currentPos = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos north = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos south = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos east = new BlockPos.MutableBlockPos();
        BlockPos.MutableBlockPos west = new BlockPos.MutableBlockPos();

        for (int x = min.getX(); x <= max.getX(); x++) {
            for (int z = min.getZ(); z <= max.getZ(); z++) {
                currentPos.set(x & 15, minY, z & 15);
                north.set(x & 15, minY, z & 15 - 1);
                south.set(x & 15, minY, z & 15 + 1);
                east.set(x & 15 + 1, minY, z & 15);
                west.set(x & 15 - 1, minY, z & 15);

                if (!carvingMask.get(currentPos.getX(), currentPos.getY(), currentPos.getZ()) && (
                        carvingMask.get(north.getX(), north.getY(), north.getZ()) ||
                                carvingMask.get(south.getX(), south.getY(), south.getZ()) ||
                                carvingMask.get(east.getX(), east.getY(), east.getZ()) ||
                                carvingMask.get(west.getX(), west.getY(), west.getZ()))
                ) {
                    walls.add(currentPos.immutable());
                }
            }
        }

        return walls;
    }

    public static BlockPos getMaxInChunkRadius(BlockPos center, int radius, ChunkPos pos, CarvingContext context) {
        BlockPos max = getMaxInRadius(center, radius, context);
        int blockChunkX = pos.getBlockX(15);
        int blockChunkZ = pos.getBlockZ(15);

        return new BlockPos(
                Math.min(max.getX(), blockChunkX),
                max.getY(),
                Math.min(max.getZ(), blockChunkZ)
        );
    }

    public static BlockPos getMinInChunkRadius(BlockPos center, int radius, ChunkPos pos, CarvingContext context) {
        BlockPos min = getMinInRadius(center, radius, context);
        int blockChunkX = pos.getBlockX(0);
        int blockChunkZ = pos.getBlockZ(0);

        return new BlockPos(
                Math.max(min.getX(), blockChunkX),
                context.getMinGenY(),
                Math.max(min.getZ(), blockChunkZ)
        );
    }

    public static BlockPos getMinInRadius(BlockPos center, int radius, CarvingContext context) {
        return new BlockPos(
                center.getX() - radius,
                Math.max(center.getY() - radius, context.getMinGenY()),
                center.getZ() - radius
        );
    }

    public static BlockPos getMaxInRadius(BlockPos center, int radius, CarvingContext context) {
        int maxY = context.getMinGenY() + context.getGenDepth() - 1;
        return new BlockPos(
                center.getX() + radius,
                Math.min(center.getY() + radius, maxY),
                center.getZ() + radius
        );
    }
}
