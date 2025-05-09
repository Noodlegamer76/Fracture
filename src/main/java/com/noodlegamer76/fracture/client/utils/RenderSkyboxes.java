package com.noodlegamer76.fracture.client.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.RenderTargets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;

public class RenderSkyboxes {
    public static final ResourceLocation SPACE = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/environment/nebula");


    public static void renderAllSkyboxes(PoseStack poseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexColorShader);

        RenderTargets.spaceTarget.bindWrite(true);
        CreateCube.createTexturedCubeAroundPlayer(poseStack, SPACE, Color.WHITE);
        RenderTargets.spaceTarget.unbindWrite();
        Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
    }

    public static void renderSkybox(PoseStack poseStack, ShaderInstance shader, Color color, ResourceLocation texture) {
        RenderSystem.setShader(() -> shader);

        CreateCube.createTexturedCubeAroundPlayer(poseStack, texture, color);
    }
}
