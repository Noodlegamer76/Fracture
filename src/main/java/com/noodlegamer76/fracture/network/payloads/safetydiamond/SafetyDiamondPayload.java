package com.noodlegamer76.fracture.network.payloads.safetydiamond;

import com.noodlegamer76.fracture.FractureMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;

public record SafetyDiamondPayload(int diamond, long pos) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<SafetyDiamondPayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "safety_diamond_payload"));

    public static final StreamCodec<ByteBuf, SafetyDiamondPayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.INT,
            SafetyDiamondPayload::diamond,
            ByteBufCodecs.VAR_LONG,
            SafetyDiamondPayload::pos,
            SafetyDiamondPayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
