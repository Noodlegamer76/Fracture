package com.noodlegamer76.fracture.client.renderer.tile;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.utils.CreateCube;
import com.noodlegamer76.fracture.client.utils.ModRenderTypes;
import com.noodlegamer76.fracture.client.utils.RenderPostEffects;
import com.noodlegamer76.fracture.client.utils.RenderSkyboxes;
import com.noodlegamer76.fracture.events.RegisterShaders;
import com.noodlegamer76.fracture.tile.NihilPortalTile;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.BakedGeoModel;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoBlockRenderer;
import software.bernie.geckolib.renderer.specialty.DynamicGeoBlockRenderer;

public class NihilPortalRenderer extends DynamicGeoBlockRenderer<NihilPortalTile> {

    public NihilPortalRenderer(BlockEntityRendererProvider.Context context) {
        super(new DefaultedBlockGeoModel<>(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "nihil_portal")));
    }

    @Override
    public void actuallyRender(PoseStack poseStack, NihilPortalTile animatable, BakedGeoModel model, @Nullable RenderType renderType, MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, int colour) {
        super.actuallyRender(poseStack, animatable, model, renderType, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, colour);

        CreateCube.createCubeAroundPlayer(poseStack, bufferSource, ModRenderTypes.TEST_RENDER_TYPE);
    }

    @Override
    protected @Nullable RenderType getRenderTypeOverrideForBone(GeoBone bone, NihilPortalTile animatable, ResourceLocation texturePath, MultiBufferSource bufferSource, float partialTick) {
        if (bone.getName().equals("shadered_cube_folder")) {
            return ModRenderTypes.SPACE_RENDER_TYPE;
        }

        return super.getRenderTypeOverrideForBone(bone, animatable, texturePath, bufferSource, partialTick);
    }
}
