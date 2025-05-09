package com.noodlegamer76.fracture.block.fluid;

import com.noodlegamer76.fracture.FractureMod;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class InitFluidTypes {
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(NeoForgeRegistries.Keys.FLUID_TYPES, FractureMod.MODID);

    public static final DeferredHolder<FluidType, FluidType> TOXIC_CHEMICALS = FLUID_TYPES.register("toxic_chemicals",
            () -> new FluidType(FluidType.Properties.create()));
}
