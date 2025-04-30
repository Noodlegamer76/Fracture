package com.noodlegamer76.fracture.client.renderer.item;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.utils.ModRenderTypes;
import com.noodlegamer76.fracture.item.VoidCrystal;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib.cache.object.GeoBone;
import software.bernie.geckolib.model.DefaultedItemGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;
import software.bernie.geckolib.renderer.specialty.DynamicGeoBlockRenderer;
import software.bernie.geckolib.renderer.specialty.DynamicGeoItemRenderer;

public class VoidCrystalItemRenderer extends DynamicGeoItemRenderer<VoidCrystal> {
    public VoidCrystalItemRenderer() {
        super(new DefaultedItemGeoModel<>(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "void_crystal")));
    }

    @Override
    protected @Nullable RenderType getRenderTypeOverrideForBone(GeoBone bone, VoidCrystal animatable, ResourceLocation texturePath, MultiBufferSource bufferSource, float partialTick) {
        if (bone.getName().equals("space_skybox")) {
            return ModRenderTypes.SPACE_RENDER_TYPE;
        }

        return super.getRenderTypeOverrideForBone(bone, animatable, texturePath, bufferSource, partialTick);
    }
}
