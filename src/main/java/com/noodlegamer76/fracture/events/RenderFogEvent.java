package com.noodlegamer76.fracture.events;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.block.fluid.InitFluidTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(modid = FractureMod.MODID, value = Dist.CLIENT)
public class RenderFogEvent {

    @SubscribeEvent
    public static void renderFog(ViewportEvent.RenderFog event) {
        if (Minecraft.getInstance().player.isEyeInFluidType(InitFluidTypes.TOXIC_CHEMICALS.value())) {
            event.setNearPlaneDistance(0);
            event.setFarPlaneDistance(5);
            event.setCanceled(true);
            return;
        }
        event.setCanceled(false);
    }
}
