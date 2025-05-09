package com.noodlegamer76.fracture.worldgen.carver;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.valueproviders.FloatProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.level.levelgen.carver.CarverDebugSettings;
import net.minecraft.world.level.levelgen.heightproviders.HeightProvider;

public class VoidTrenchesConfiguration extends CanyonCarverConfiguration {
    public static final Codec<VoidTrenchesConfiguration> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                    CarverConfiguration.CODEC.forGetter(voidTrenches -> voidTrenches),
                    FloatProvider.CODEC.fieldOf("vertical_rotation").forGetter(voidTrenches -> voidTrenches.verticalRotation),
                    VoidTrenchesConfiguration.CanyonShapeConfiguration.CODEC.fieldOf("shape").forGetter(voidTrenches -> voidTrenches.shape)
                    )
                    .apply(instance, VoidTrenchesConfiguration::new));

    public final FloatProvider verticalRotation;
    public final CanyonCarverConfiguration.CanyonShapeConfiguration shape;

    public VoidTrenchesConfiguration(float probability, HeightProvider y, FloatProvider yScale, VerticalAnchor lavaLevel, CarverDebugSettings debugSettings, HolderSet<Block> replaceable, FloatProvider verticalRotation, CanyonShapeConfiguration shape) {
        super(probability, y, yScale, lavaLevel, debugSettings, replaceable, verticalRotation, shape);
        this.verticalRotation = verticalRotation;
        this.shape = shape;
    }

    public VoidTrenchesConfiguration(CarverConfiguration config, FloatProvider verticalRotation, CanyonShapeConfiguration shape) {
        this(config.probability, config.y, config.yScale, config.lavaLevel, config.debugSettings, config.replaceable, verticalRotation, shape);
    }
}
