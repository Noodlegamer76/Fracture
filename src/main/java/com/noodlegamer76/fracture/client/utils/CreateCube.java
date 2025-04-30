package com.noodlegamer76.fracture.client.utils;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Axis;
import com.noodlegamer76.fracture.client.utils.puddle.PuddleInfo;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix4f;

public class CreateCube {

    public static void createTexturedCubeAroundPlayer(PoseStack poseStack, ResourceLocation texture) {

        poseStack.pushPose();

        Matrix4f cameraRot = Minecraft.getInstance().gameRenderer.getMainCamera().rotation().get(new Matrix4f());
        poseStack.mulPose(cameraRot.invert());

        float far = Minecraft.getInstance().gameRenderer.getRenderDistance();

        for(int i = 0; i < 6; ++i) {
            BufferBuilder vertexConsumer = Tesselator.getInstance().begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);

            poseStack.pushPose();
            if (i == 0) {
                RenderSystem.setShaderTexture(0, texture.withSuffix("/front.png"));
                poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 1) {
                RenderSystem.setShaderTexture(0, texture.withSuffix("/right.png"));
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(-90));
            }

            if (i == 2) {
                RenderSystem.setShaderTexture(0, texture.withSuffix("/left.png"));
                poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(90));
            }

            if (i == 3) {
                RenderSystem.setShaderTexture(0, texture.withSuffix("/back.png"));
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
            }

            if (i == 4) {
                RenderSystem.setShaderTexture(0, texture.withSuffix("/bottom.png"));
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 5) {
                RenderSystem.setShaderTexture(0, texture.withSuffix("/top.png"));
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }
            Matrix4f matrix4f = poseStack.last().pose();
            vertexConsumer.addVertex(matrix4f, -far, -far, -far).setUv(0.0F, 0.0F);
            vertexConsumer.addVertex(matrix4f, -far, -far, far).setUv(0.0F, 1.0F);
            vertexConsumer.addVertex(matrix4f, far, -far, far).setUv(1.0F, 1.0F);
            vertexConsumer.addVertex(matrix4f, far, -far, -far).setUv(1.0F, 0.0F);
            poseStack.popPose();

            MeshData meshData = vertexConsumer.build();

            if (meshData != null) {
                BufferUploader.drawWithShader(meshData);
            }
        }
        poseStack.popPose();
    }

    public static void createCubeAroundPlayer(PoseStack poseStack, VertexConsumer vertexConsumer, PuddleInfo info) {
        for(int i = 0; i < 6; ++i) {

            poseStack.pushPose();
            if (i == 0) {
                poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 1) {
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(-90));
            }

            if (i == 2) {
                poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(90));
            }

            if (i == 3) {
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
            }

            if (i == 4) {
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 5) {
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }
            Matrix4f matrix4f = poseStack.last().pose();
            vertexConsumer.addVertex(matrix4f, -5, -5, -5).setColor(info.getColor().getRGB());
            vertexConsumer.addVertex(matrix4f, -5, -5, 5).setColor(info.getColor().getRGB());
            vertexConsumer.addVertex(matrix4f, 5, -5, 5).setColor(info.getColor().getRGB());
            vertexConsumer.addVertex(matrix4f, 5, -5, -5).setColor(info.getColor().getRGB());
            poseStack.popPose();
        }
    }

    public static void createCubeAroundPlayer(PoseStack poseStack, MultiBufferSource bufferSource, RenderType renderType) {
        VertexConsumer vertexConsumer = bufferSource.getBuffer(renderType);
        for(int i = 0; i < 6; ++i) {

            poseStack.pushPose();
            if (i == 0) {
                poseStack.mulPose(Axis.XP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 1) {
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(-90.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(-90));
            }

            if (i == 2) {
                poseStack.mulPose(Axis.ZP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(90));
            }

            if (i == 3) {
                poseStack.mulPose(Axis.XP.rotationDegrees(90.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
            }

            if (i == 4) {
                poseStack.mulPose(Axis.XP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }

            if (i == 5) {
                poseStack.mulPose(Axis.XP.rotationDegrees(180.0F));
                poseStack.mulPose(Axis.ZP.rotationDegrees(0.0F));
                poseStack.mulPose(Axis.YN.rotationDegrees(180));
            }
            Matrix4f matrix4f = poseStack.last().pose();
            vertexConsumer.addVertex(matrix4f, -5, -5, -5);
            vertexConsumer.addVertex(matrix4f, -5, -5, 5);
            vertexConsumer.addVertex(matrix4f, 5, -5, 5);
            vertexConsumer.addVertex(matrix4f, 5, -5, -5);
            poseStack.popPose();
        }
    }
}
