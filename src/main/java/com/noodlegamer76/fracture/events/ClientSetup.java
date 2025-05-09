package com.noodlegamer76.fracture.events;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.block.fluid.InitFluids;
import com.noodlegamer76.fracture.client.utils.ModRenderTypes;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.ClientHooks;

@EventBusSubscriber(modid = FractureMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void clientSetupEvent(FMLClientSetupEvent event) {
    }
}
