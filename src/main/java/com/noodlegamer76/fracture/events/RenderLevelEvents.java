package com.noodlegamer76.fracture.events;


import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.RenderTargets;
import com.noodlegamer76.fracture.client.util.BiomeBlendUtils;
import com.noodlegamer76.fracture.client.utils.ExtendedShaders;
import com.noodlegamer76.fracture.client.utils.RenderPostEffects;
import com.noodlegamer76.fracture.client.utils.RenderSkyboxes;
import com.noodlegamer76.fracture.client.utils.puddle.PuddleInfo;
import com.noodlegamer76.fracture.client.utils.puddle.PuddleRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

import java.awt.*;

@EventBusSubscriber(modid = FractureMod.MODID, value = Dist.CLIENT)
public class RenderLevelEvents {
    private static float previousBiomePresenceFactor = 0;
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

            RenderSkyboxes.renderAllSkyboxes(event.getPoseStack());

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

        //for blending skyboxes
        if (event.getStage() == RenderLevelStageEvent.Stage.AFTER_SKY) {
            Biome rupture = BiomeBlendUtils.getTargetBiome(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "rupture"));
            float spaceBlend = BiomeBlendUtils.getBiomePresenceFactor(Minecraft.getInstance().level, event.getCamera().getPosition(), rupture, 10, 2);
            spaceBlend *= 255;
            spaceBlend = Mth.lerp(event.getPartialTick().getGameTimeDeltaPartialTick(true) * 0.01F, previousBiomePresenceFactor, spaceBlend);

            if (spaceBlend > 0) {
                RenderSystem.enableBlend();
                RenderSkyboxes.renderSkybox(event.getPoseStack(), GameRenderer.getPositionTexColorShader(), new Color(255, 255, 255, (int) spaceBlend), RenderSkyboxes.SPACE);
                RenderSystem.disableBlend();
            }

            previousBiomePresenceFactor = spaceBlend;
        }
    }
}