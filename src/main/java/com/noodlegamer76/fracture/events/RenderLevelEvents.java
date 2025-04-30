package com.noodlegamer76.fracture.events;


import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.RenderTargets;
import com.noodlegamer76.fracture.client.utils.ExtendedShaders;
import com.noodlegamer76.fracture.client.utils.RenderPostEffects;
import com.noodlegamer76.fracture.client.utils.RenderSkyboxes;
import com.noodlegamer76.fracture.client.utils.puddle.PuddleInfo;
import com.noodlegamer76.fracture.client.utils.puddle.PuddleRenderer;
import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.awt.*;

@Mod(value = FractureMod.MODID, dist = Dist.CLIENT)
public class RenderLevelEvents {
    private static int previousWidth;
    private static int previousHeight;

    @SubscribeEvent
    public static void renderLevelEvent(RenderLevelStageEvent event) {
        int width = Minecraft.getInstance().getWindow().getWidth();
        int height = Minecraft.getInstance().getWindow().getHeight();

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            if (width != previousWidth || height != previousHeight) {

                RenderTargets.createRenderTargets();

                previousWidth = width;
                previousHeight = height;
            }

            RenderSkyboxes.renderSkyboxes(event.getPoseStack());

            ExtendedShaders.setSamplers();
            ExtendedShaders.setUniforms();
        }

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_WEATHER) {
            //PuddleRenderer.PUDDLES.clear();
            //PuddleInfo puddle = new PuddleInfo();
            //Color color = new Color(112, 0, 0, 255);
            //puddle.setColor(color);
            //PuddleRenderer.PUDDLES.add(puddle);

            PuddleRenderer.render(event.getPoseStack());
        }

        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_LEVEL) {
            RenderPostEffects.renderPostEffects(event.getPoseStack());
        }
    }
}