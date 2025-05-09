package com.noodlegamer76.fracture.worldgen.feature;

import com.mojang.serialization.Codec;
import com.noodlegamer76.fracture.block.InitBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RandomScrapFeature extends Feature<NoneFeatureConfiguration> {
    public RandomScrapFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        var level = context.level();
        var origin = context.origin();
        var random = context.random();

        int radius = random.nextInt(7) + 4;
        int height = random.nextInt(3);

        for (int dx = -radius; dx <= radius; dx++) {
            for (int dz = -radius; dz <= radius; dz++) {
                double distance = Math.sqrt(dx * dx + dz * dz);
                double noise = random.nextDouble() * 1.5;

                if (distance < radius - noise) {
                    int pileHeight = height - (int) (distance * 0.5) + random.nextInt(2);

                    for (int dy = -6; dy < pileHeight; dy++) {
                        var pos = origin.offset(dx, dy, dz);

                        if (level.getBlockState(pos).isAir()) {
                            level.setBlock(pos, InitBlocks.SCRAP.get().defaultBlockState(), 2);
                        }
                    }
                }
            }
        }
        return true;
    }
}
