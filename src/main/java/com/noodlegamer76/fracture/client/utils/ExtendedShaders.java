package com.noodlegamer76.fracture.client.utils;

import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.RenderTargets;
import com.noodlegamer76.fracture.events.RegisterShaders;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceProvider;
import org.joml.Matrix4f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class

ExtendedShaders {
    public static final int BRANCHING_TEXTURE = ExtendedShaders.getIdFromTexture(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/noise/branching.png"));
    private static final List<ExtendedShaderInstance> shaders = new ArrayList<>();

    public static void setSamplers() {
        for (ExtendedShaderInstance shader : shaders) {
            shader.setSampler("Color", Minecraft.getInstance().getMainRenderTarget().getColorTextureId());

            shader.setSampler("Depth", Minecraft.getInstance().getMainRenderTarget().getDepthTextureId());

            shader.setSampler("Grainy", getIdFromTexture(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/noise/grainy.png")));
            shader.setSampler("Manifold", getIdFromTexture(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/noise/manifold.png")));
            shader.setSampler("Milky", getIdFromTexture(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/noise/milky.png")));
            shader.setSampler("Swirl", getIdFromTexture(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/noise/swirl.png")));
            shader.setSampler("Branching", BRANCHING_TEXTURE);

            shader.setSampler("SpaceSkybox", RenderTargets.spaceTarget.getColorTextureId());
        }
    }

    public static int getIdFromTexture(ResourceLocation texture) {
        TextureManager texturemanager = Minecraft.getInstance().getTextureManager();
        AbstractTexture abstracttexture = texturemanager.getTexture(texture);
        return abstracttexture.getId();
    }

    public static void setUniforms() {
        for (ExtendedShaderInstance shader : shaders) {
            Uniform inverseProjMat = shader.getUniform("InverseProjMat");
            if (inverseProjMat != null) {
                inverseProjMat.set(new Matrix4f(RenderSystem.getProjectionMatrix()).invert());
            }

            Uniform inverseModelViewMat = shader.getUniform("InverseModelViewMat");
            if (inverseModelViewMat != null) {
                inverseModelViewMat.set(new Matrix4f(RenderSystem.getModelViewMatrix()).invert());
            }

            Uniform cameraPos = shader.getUniform("CameraPos");
            if (cameraPos != null) {
                cameraPos.set(Minecraft.getInstance().gameRenderer.getMainCamera().getPosition().toVector3f());
            }

            Uniform farPlane = shader.getUniform("FarPlane");
            if (farPlane != null) {
                farPlane.set(Minecraft.getInstance().gameRenderer.getRenderDistance());
            }
        }
    }

    public static List<ExtendedShaderInstance> getShaders() {
        return shaders;
    }
}
