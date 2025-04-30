package com.noodlegamer76.fracture.client.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.RenderTargets;
import com.noodlegamer76.fracture.events.RegisterShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.client.renderer.blockentity.TheEndPortalRenderer;
import net.minecraft.resources.ResourceLocation;

public class ModRenderTypes {

    public static final RenderType SPACE_RENDER_TYPE = RenderType.create(
            "space",
            DefaultVertexFormat.POSITION_TEX,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RegisterShaders.spaceSkybox))
                    .setTextureState(new RenderStateShard.EmptyTextureStateShard(
                            () -> RegisterShaders.spaceSkybox.setSampler("Skybox", RenderTargets.spaceTarget.getColorTextureId()),
                            () -> {}))
                    .createCompositeState(true)
    );

    public static final RenderType TEST_RENDER_TYPE = RenderType.create(
            "test",
            DefaultVertexFormat.POSITION,
            VertexFormat.Mode.QUADS,
            256,
            false,
            false,
            RenderType.CompositeState.builder()
                    .setShaderState(new RenderStateShard.ShaderStateShard(() -> RegisterShaders.test))
                    .setDepthTestState(RenderStateShard.DepthTestStateShard.NO_DEPTH_TEST)
                    .setTransparencyState(RenderStateShard.TRANSLUCENT_TRANSPARENCY)
                    .setOverlayState(RenderStateShard.OVERLAY)
                    .createCompositeState(true)
    );
}
