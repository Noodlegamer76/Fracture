package com.noodlegamer76.fracture.client.utils.puddle;

import com.mojang.blaze3d.shaders.Uniform;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.noodlegamer76.fracture.client.utils.CreateCube;
import com.noodlegamer76.fracture.client.utils.ExtendedShaderInstance;
import com.noodlegamer76.fracture.client.utils.ExtendedShaders;
import com.noodlegamer76.fracture.events.RegisterShaders;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL44;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PuddleRenderer {
    public static final List<PuddleInfo> PUDDLES = new ArrayList<>();

    public static void  render(PoseStack poseStack) {

        BufferBuilder vertexConsumer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);

        ExtendedShaders.setSamplers();
        ExtendedShaders.setUniforms();

        for (PuddleInfo puddle: PUDDLES) {
            RenderSystem.setShader(() -> RegisterShaders.puddle);
            RenderSystem.disableDepthTest();
            RenderSystem.disableBlend();
            RenderSystem.depthMask(false);

            ExtendedShaderInstance shader = (ExtendedShaderInstance) RenderSystem.getShader();
            Uniform inverseModelViewMat = shader.getUniform("InverseModelViewMat");

            if (inverseModelViewMat != null) {
                inverseModelViewMat.set(new Matrix4f(RenderSystem.getModelViewMatrix()).invert());
            }

            CreateCube.createCubeAroundPlayer(poseStack, vertexConsumer, puddle);


            MeshData meshData = vertexConsumer.build();

            if (meshData != null) {
                BufferUploader.drawWithShader(meshData);
            }

            RenderSystem.enableDepthTest();
            RenderSystem.depthMask(true);

            GL44.glMemoryBarrier(GL44.GL_FRAMEBUFFER_BARRIER_BIT);
        }
    }
}
