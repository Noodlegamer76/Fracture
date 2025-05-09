package com.noodlegamer76.fracture.worldgen.feature;

import com.mojang.serialization.Codec;
import com.noodlegamer76.fracture.block.InitBlocks;
import com.noodlegamer76.fracture.block.StrangeStem;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ObserverBlock;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import org.joml.Vector3f;

public class TaintedTendril extends Feature<NoneFeatureConfiguration> {
    public TaintedTendril(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        WorldGenLevel level = context.level();
        BlockPos origin = context.origin();
        RandomSource random = context.random();

        BlockPos.MutableBlockPos raisedOrigin = new BlockPos.MutableBlockPos(origin.getX(), origin.getY(), origin.getZ());
        int initialLength = random.nextInt(3) + 3;

        for (int i = 0; i < initialLength; i++) {
            setBlock(level, raisedOrigin, InitBlocks.TAINTED_FUNGUS_STEM.get().defaultBlockState());
            raisedOrigin.move(0, 1, 0);
        }

        BlockPos.MutableBlockPos current = new BlockPos.MutableBlockPos();
        int tendrilCount = random.nextInt(4) + 4;
        for (int i = 0; i < tendrilCount; i++) {
            Vector3f direction = new Vector3f(random.nextFloat() - 0.5f, random.nextFloat() * 1.5f, random.nextFloat() - 0.5f).normalize();
            Vector3f position = new Vector3f(raisedOrigin.getX(), raisedOrigin.getY(), raisedOrigin.getZ());
            int length = random.nextInt(8) + 4;

            for (int j = 0; j < length; j++) {
                position.add(direction);
                current.set(position.x, position.y, position.z);
                setBlock(level, current, InitBlocks.TAINTED_FUNGUS.get().defaultBlockState());

                if (random.nextDouble() < 0.25) {
                    Direction[] directions = Direction.values();
                    Direction randomDir = directions[random.nextInt(directions.length)];

                    current.move(randomDir);
                    if (level.getBlockState(current).isAir()) {
                        setBlock(level, current, InitBlocks.STRANGE_STEM.get().defaultBlockState().setValue(StrangeStem.FACING, randomDir.getOpposite()));
                    }
                }
            }
        }

        return true;
    }
}
