package com.noodlegamer76.fracture.block.fluid;

import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.fluids.FluidType;

import java.util.function.Consumer;

public class ToxicChemicalsFluidType extends FluidType {

    public ToxicChemicalsFluidType(Properties properties) {
        super(properties);
    }

    @Override
    public void initializeClient(Consumer<IClientFluidTypeExtensions> consumer) {
        super.initializeClient(consumer);
    }
}
