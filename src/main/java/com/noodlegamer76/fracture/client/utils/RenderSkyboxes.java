package com.noodlegamer76.fracture.client.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.RenderTargets;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

public class RenderSkyboxes {
    public static final ResourceLocation SPACE = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/environment/nebula");


    public static void renderSkyboxes(PoseStack poseStack) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);

        RenderTargets.spaceTarget.bindWrite(true);
        CreateCube.createTexturedCubeAroundPlayer(poseStack, SPACE);
        RenderTargets.spaceTarget.unbindWrite();
        Minecraft.getInstance().getMainRenderTarget().bindWrite(true);
    }
}
