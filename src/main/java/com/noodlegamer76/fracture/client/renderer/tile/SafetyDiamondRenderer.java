package com.noodlegamer76.fracture.client.renderer.tile;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.tile.NihilPortalTile;
import com.noodlegamer76.fracture.tile.SafetyDiamondEntity;
import com.noodlegamer76.fracture.network.payloads.safetydiamond.SafetyDiamondHandler;
import com.noodlegamer76.fracture.network.payloads.safetydiamond.SafetyDiamondPayload;
import com.noodlegamer76.fracture.gui.safetydiamond.SafetyDiamondScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.AABB;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;

public class SafetyDiamondRenderer extends GeoBlockRenderer<SafetyDiamondEntity> {
    private static final ResourceLocation MODEL_LOCATION =
            ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "safety_diamond");

    private static final ResourceLocation ZERO        = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/zero.png");
    private static final ResourceLocation ONE         = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/one.png");
    private static final ResourceLocation TWO         = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/two.png");
    private static final ResourceLocation THREE       = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/three.png");
    private static final ResourceLocation FOUR        = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/four.png");
    private static final ResourceLocation ACID        = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/acid.png");
    private static final ResourceLocation ALK         = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/alk.png");
    private static final ResourceLocation COR         = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/cor.png");
    private static final ResourceLocation OX          = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/ox.png");
    private static final ResourceLocation RADIOACTIVE = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/radioactive.png");
    private static final ResourceLocation SA          = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/sa.png");
    private static final ResourceLocation W           = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/w.png");

    public SafetyDiamondRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultedBlockGeoModel<>(MODEL_LOCATION));
    }

    @Override
    public void actuallyRender(PoseStack poseStack,
                               SafetyDiamondEntity entity,
                               BakedGeoModel model,
                               RenderType renderType,
                               MultiBufferSource bufferSource,
                               VertexConsumer buffer,
                               boolean isReRender,
                               float partialTick,
                               int packedLight,
                               int packedOverlay,
                               int color) {
        super.actuallyRender(poseStack, entity, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, color);

        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(180));
        renderOverlays(poseStack, bufferSource, entity, packedLight, true);  // front
        poseStack.popPose();

        renderOverlays(poseStack, bufferSource, entity, packedLight, false); // back
    }

    private void renderOverlays(PoseStack poseStack,
                                MultiBufferSource bufferSource,
                                SafetyDiamondEntity entity,
                                int packedLight,
                                boolean front) {
        // fx/fy from your screen, and invert Y for block‐space
        float fxLeft   = -0.27f, fxMid    = 0, fxRight = 0.27f;
        float fyTop    = 1 - 0.25f, fyMid   = 1 - 0.50f, fyBottom = 1 - 0.77f;
        float quadSize = 0.32f;   // covers about 30% of block face
        float zOffset  = front ? -0.49f : 0.51f;

        // Gather your four overlays
        ResourceLocation[] numberTex = new ResourceLocation[] {
                pickNumberTexture(entity.getBlue()),
                pickNumberTexture(entity.getRed()),
                pickNumberTexture(entity.getYellow())
        };
        float[] posX = new float[] { fxLeft, fxMid, fxRight };
        float[] posY = new float[] { fyMid,  fyTop,  fyMid  };
        ResourceLocation hazardTex = pickHazardTexture(entity.getHazard());
        float hzX = fxMid, hzY = fyBottom;

        // Draw the three number quads
        for (int i = 0; i < 3; i++) {
            drawTexturedQuad(poseStack, bufferSource, numberTex[i],
                    posX[i], posY[i], quadSize, zOffset, packedLight);
        }
        // Draw the hazard quad (skip if NONE)
        if (hazardTex != null) {
            drawTexturedQuad(poseStack, bufferSource, hazardTex,
                    hzX, hzY, quadSize, zOffset, packedLight);
        }
    }

    private void drawTexturedQuad(PoseStack poseStack,
                                  MultiBufferSource buffers,
                                  ResourceLocation tex,
                                  float centerX, float centerY, float size,
                                  float z, int light) {
        VertexConsumer vb = buffers.getBuffer(RenderType.entityCutoutNoCull(tex));
        var mat  = poseStack.last().pose();
        int overlay = OverlayTexture.NO_OVERLAY;

        float half = size * 0.5f;
        // texture coordinates
        float u0 = 0f, v0 = 1f, u1 = 1f, v1 = 0f;
        // color
        float r=1f, g=1f, b=1f, a=1f;

        // bottom-left
        vb.addVertex(mat, centerX - half, centerY - half, z)
                .setColor(r, g, b, a)
                .setUv(u0, v0)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(poseStack.last(), 0f, 0f, -1f);

        // top-left
        vb.addVertex(mat, centerX - half, centerY + half, z)
                .setColor(r, g, b, a)
                .setUv(u0, v1)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(poseStack.last(), 0f, 0f, -1f);

        // top-right
        vb.addVertex(mat, centerX + half, centerY + half, z)
                .setColor(r, g, b, a)
                .setUv(u1, v1)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(poseStack.last(), 0f, 0f, -1f);

        // bottom-right
        vb.addVertex(mat, centerX + half, centerY - half, z)
                .setColor(r, g, b, a)
                .setUv(u1, v0)
                .setOverlay(overlay)
                .setLight(light)
                .setNormal(poseStack.last(), 0f, 0f, -1f);
    }


    private ResourceLocation pickNumberTexture(SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER num) {
        return switch (num) {
            case ZERO  -> ZERO;
            case ONE   -> ONE;
            case TWO   -> TWO;
            case THREE -> THREE;
            case FOUR  -> FOUR;
        };
    }

    private ResourceLocation pickHazardTexture(SafetyDiamondEntity.SAFETY_DIAMOND_HAZARD h) {
        return switch (h) {
            case ACID        -> ACID;
            case ALK         -> ALK;
            case COR         -> COR;
            case OX          -> OX;
            case RADIOACTIVE -> RADIOACTIVE;
            case SA          -> SA;
            case W           -> W;
            case NONE        -> null;
        };
    }

    @Override
    public AABB getRenderBoundingBox(SafetyDiamondEntity blockEntity) {
        return super.getRenderBoundingBox(blockEntity);
    }
}
