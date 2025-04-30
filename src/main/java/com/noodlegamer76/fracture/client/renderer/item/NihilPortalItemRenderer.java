package com.noodlegamer76.fracture.client.renderer.item;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.item.NihilPortalItem;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.DefaultedBlockGeoModel;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.renderer.GeoItemRenderer;

public class NihilPortalItemRenderer extends GeoItemRenderer<NihilPortalItem> {
    public NihilPortalItemRenderer() {
        super(new DefaultedBlockGeoModel<>(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "nihil_portal")));
    }
}
