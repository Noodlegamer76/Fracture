package com.noodlegamer76.fracture.block.fluid;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.block.InitBlocks;
import com.noodlegamer76.fracture.item.InitItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.neoforge.fluids.BaseFlowingFluid;
import net.neoforged.neoforge.fluids.FluidType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class InitFluids {

    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(Registries.FLUID, FractureMod.MODID);

    public static final DeferredHolder<Fluid, BaseFlowingFluid.Source> SOURCE_TOXIC_CHEMICALS = FLUIDS.register("toxic_chemicals",
            () -> new BaseFlowingFluid.Source(InitFluids.TOXIC_CHEMICAL_PROPERTIES));
    public static final DeferredHolder<Fluid, BaseFlowingFluid.Flowing> FLOWING_TOXIC_CHEMICALS = FLUIDS.register("toxic_chemicals_flowing",
            () -> new BaseFlowingFluid.Flowing(InitFluids.TOXIC_CHEMICAL_PROPERTIES));

    public static final BaseFlowingFluid.Properties TOXIC_CHEMICAL_PROPERTIES =
            new BaseFlowingFluid.Properties(
                    InitFluidTypes.TOXIC_CHEMICALS,
                    SOURCE_TOXIC_CHEMICALS,
                    FLOWING_TOXIC_CHEMICALS
            )
                    .bucket(InitItems.TOXIC_CHEMICAL_BUCKET)
                    .block(InitBlocks.TOXIC_CHEMICALS);
}
