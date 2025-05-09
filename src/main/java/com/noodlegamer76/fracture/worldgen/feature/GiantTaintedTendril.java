package com.noodlegamer76.fracture.worldgen.feature;

import com.mojang.serialization.Codec;
import com.noodlegamer76.fracture.block.InitBlocks;
import com.noodlegamer76.fracture.block.StrangeStem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.joml.Vector3f;

public class GiantTaintedTendril extends Feature<NoneFeatureConfiguration> {
    public GiantTaintedTendril(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        BlockPos.MutableBlockPos raisedOrigin = new BlockPos.MutableBlockPos(origin.getX(), origin.getY(), origin.getZ());
        int initialLength = random.nextInt(6) + 12;

        int radius = 1;
        BlockPos.MutableBlockPos boxPos = new BlockPos.MutableBlockPos();

        for (int i = 0; i < initialLength; i++) {
            for (int x = -radius; x <= radius; x++) {
                for (int z = -radius; z <= radius; z++) {
                    if (random.nextDouble() < 0.2) {
                        continue;
                    }
                    boxPos.set(raisedOrigin.getX() + x, raisedOrigin.getY(), raisedOrigin.getZ() + z);
                    setBlock(level, boxPos, InitBlocks.TAINTED_FUNGUS_STEM.get().defaultBlockState());
                }
            }
            raisedOrigin.move(0, 1, 0);
        }

        BlockPos.MutableBlockPos current = new BlockPos.MutableBlockPos();
        int tendrilCount = random.nextInt(6) + 8;
        for (int i = 0; i < tendrilCount; i++) {
            Vector3f direction = new Vector3f(random.nextFloat() - 0.5f, random.nextFloat() - 0.5f, random.nextFloat() - 0.5f).normalize();
            Vector3f position = new Vector3f(raisedOrigin.getX(), raisedOrigin.getY(), raisedOrigin.getZ());
            int length = random.nextInt(8) + 7;

            for (int j = 0; j < length; j++) {
                position.add(direction);
                current.set(position.x, position.y, position.z);

                for (int x = -radius; x <= radius; x++) {
                    for (int y = -radius; y <= radius; y++) {
                        for (int z = -radius; z <= radius; z++) {

                            if (random.nextBoolean()) {
                                continue;
                            }

                            boxPos.set(current.getX() + x, current.getY() + y, current.getZ() + z);
                            setBlock(level, boxPos, InitBlocks.TAINTED_FUNGUS.get().defaultBlockState());

                            if (random.nextDouble() < 0.25) {
                                Direction randomDir = Direction.values()[random.nextInt(Direction.values().length)];

                                BlockPos observerPos = boxPos.relative(randomDir);
                                Direction facing = randomDir.getOpposite();

                                if (level.getBlockState(observerPos).isAir()) {
                                    setBlock(level, observerPos, InitBlocks.STRANGE_STEM.get().defaultBlockState().setValue(StrangeStem.FACING, randomDir.getOpposite()));
                                }
                            }
                        }
                    }
                }
            }
        }

        return true;
    }
}
