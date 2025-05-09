package com.noodlegamer76.fracture.events;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.client.utils.ExtendedShaderInstance;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterShadersEvent;

import java.io.IOException;

@EventBusSubscriber(modid = FractureMod.MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class RegisterShaders {
    public static ExtendedShaderInstance spaceSkybox;
    public static ExtendedShaderInstance puddle;
    public static ExtendedShaderInstance test;

    @SubscribeEvent
    public static void registerShaders(RegisterShadersEvent event) {

        try {
            event.registerShader(new ExtendedShaderInstance(event.getResourceProvider(),
                            ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "skybox"),
                            DefaultVertexFormat.POSITION),
                    (e) -> spaceSkybox = (ExtendedShaderInstance) e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            event.registerShader(new ExtendedShaderInstance(event.getResourceProvider(),
                            ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "puddle"),
                            DefaultVertexFormat.POSITION_COLOR),
                    (e) -> puddle = (ExtendedShaderInstance) e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        try {
            event.registerShader(new ExtendedShaderInstance(event.getResourceProvider(),
                            ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "test"),
                            DefaultVertexFormat.POSITION),
                    (e) -> test = (ExtendedShaderInstance) e);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
