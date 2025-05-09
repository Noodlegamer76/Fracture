package com.noodlegamer76.fracture.events;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.renderer.tile.NihilPortalRenderer;
import com.noodlegamer76.fracture.client.renderer.tile.SafetyDiamondRenderer;
import com.noodlegamer76.fracture.tile.InitBlockEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = FractureMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterRenderers {

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(InitBlockEntities.NIHIL_PORTAL.get(), NihilPortalRenderer::new);
        event.registerBlockEntityRenderer(InitBlockEntities.SAFETY_DIAMOND.get(), SafetyDiamondRenderer::new);
    }
}
