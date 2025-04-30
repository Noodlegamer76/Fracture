package com.noodlegamer76.fracture.client;

import com.mojang.blaze3d.pipeline.RenderTarget;
import com.mojang.blaze3d.pipeline.TextureTarget;
import net.minecraft.client.Minecraft;

public class RenderTargets {
    public static RenderTarget spaceTarget;

    public static void createRenderTargets() {
        int width = Minecraft.getInstance().getWindow().getWidth();
        int height = Minecraft.getInstance().getWindow().getHeight();

        spaceTarget = new TextureTarget(width, height, false, false);
    }
}