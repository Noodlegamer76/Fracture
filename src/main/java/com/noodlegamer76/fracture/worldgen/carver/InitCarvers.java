package com.noodlegamer76.fracture.worldgen.carver;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.worldgen.feature.VoidCityRoomPlacementFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.carver.CanyonCarverConfiguration;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitCarvers {

    public static final DeferredRegister<WorldCarver<?>> CARVERS = DeferredRegister.create(Registries.CARVER, FractureMod.MODID);

    public static final DeferredHolder<WorldCarver<?>, VoidTrenches<CanyonCarverConfiguration>> VOID_TRENCHES_CARVER =
            CARVERS.register("void_trenches", () -> new VoidTrenches<>(CanyonCarverConfiguration.CODEC));
}
