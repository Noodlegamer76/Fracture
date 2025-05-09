package com.noodlegamer76.fracture.worldgen.feature;

import com.noodlegamer76.fracture.FractureMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(Registries.FEATURE, FractureMod.MODID);

    public static final DeferredHolder<Feature<?>, VoidCityRoomPlacementFeature> VOID_CITY_ROOM_PLACEMENT =
            FEATURES.register("void_city_room", () -> new VoidCityRoomPlacementFeature(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, RandomScrapFeature> RANDOM_SCRAP =
            FEATURES.register("random_scrap", () -> new RandomScrapFeature(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, TaintedTendril> TAINTED_TENDRIL =
            FEATURES.register("tainted_tendril", () -> new TaintedTendril(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, GiantTaintedTendril> GIANT_TAINTED_TENDRIL =
            FEATURES.register("giant_tainted_tendril", () -> new GiantTaintedTendril(NoneFeatureConfiguration.CODEC));
}
