package com.noodlegamer76.fracture;

import com.mojang.logging.LogUtils;
import com.noodlegamer76.fracture.block.InitBlocks;
import com.noodlegamer76.fracture.block.fluid.InitFluidTypes;
import com.noodlegamer76.fracture.block.fluid.InitFluids;
import com.noodlegamer76.fracture.creativetab.InitCreativeTabs;
import com.noodlegamer76.fracture.item.InitItems;
import com.noodlegamer76.fracture.particle.InitParticles;
import com.noodlegamer76.fracture.tile.InitBlockEntities;
import com.noodlegamer76.fracture.worldgen.carver.InitCarvers;
import com.noodlegamer76.fracture.worldgen.feature.InitFeatures;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(FractureMod.MODID)
public class FractureMod {
    public static final String MODID = "fracture";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FractureMod(IEventBus modEventBus, ModContainer modContainer) {
        InitBlocks.BLOCKS.register(modEventBus);
        InitItems.ITEMS.register(modEventBus);
        InitFluids.FLUIDS.register(modEventBus);
        InitFluidTypes.FLUID_TYPES.register(modEventBus);
        InitCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        InitBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        InitFeatures.FEATURES.register(modEventBus);
        InitParticles.PARTICLE_TYPES.register(modEventBus);
        InitCarvers.CARVERS.register(modEventBus);

        //NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}
