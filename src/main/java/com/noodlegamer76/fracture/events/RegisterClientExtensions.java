package com.noodlegamer76.fracture.events;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.block.fluid.InitFluidTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.material.FluidState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientFluidTypeExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import java.awt.*;

@EventBusSubscriber(modid = FractureMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterClientExtensions {

    @SubscribeEvent
    public static void registerClientExtensions(RegisterClientExtensionsEvent event) {
        event.registerFluidType(new IClientFluidTypeExtensions() {
            private static final ResourceLocation UNDERWATER_LOCATION = ResourceLocation.fromNamespaceAndPath("minecraft", "textures/misc/underwater.png");
            private static final ResourceLocation TOXIC_CHEMICALS_STILL = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "block/toxic_chemicals_still");
            private static final ResourceLocation TOXIC_CHEMICAL_FLOW = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "block/toxic_chemicals_flowing");
            private static final ResourceLocation CHEMICAL_TOXIC_OVERLAY = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "block/water_overlay");
            private static final int color = new Color(1, 38, 0, 255).getRGB();

            public ResourceLocation getStillTexture() {
                return TOXIC_CHEMICALS_STILL;
            }

            public ResourceLocation getFlowingTexture() {
                return TOXIC_CHEMICAL_FLOW;
            }

            public int getTintColor() {
                return color;
            }

            public int getTintColor(FluidState state, BlockAndTintGetter getter, BlockPos pos) {
                return color;
            }
        },  InitFluidTypes.TOXIC_CHEMICALS.value());
    }
}
