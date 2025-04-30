package com.noodlegamer76.fracture.worldgen.feature;

import com.mojang.serialization.Codec;
import com.noodlegamer76.fracture.worldgen.dungeon.DungeonChunkPipeline;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class VoidCityRoomPlacementFeature extends Feature<NoneFeatureConfiguration> {

    public VoidCityRoomPlacementFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        DungeonChunkPipeline pipeline = new DungeonChunkPipeline(context);
        pipeline.start();

        return true;
    }
}
