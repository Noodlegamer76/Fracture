package com.noodlegamer76.fracture.network.payloads.nihilportalstate;

import com.noodlegamer76.fracture.FractureMod;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.ResourceLocation;


public record NihilPortalStatePayload(long pos, int state) implements CustomPacketPayload {

    public static final CustomPacketPayload.Type<NihilPortalStatePayload> TYPE = new CustomPacketPayload.Type<>(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "nihil_portal_state"));

    public static final StreamCodec<ByteBuf, NihilPortalStatePayload> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.VAR_LONG,
            NihilPortalStatePayload::pos,
            ByteBufCodecs.VAR_INT,
            NihilPortalStatePayload::state,
            NihilPortalStatePayload::new
    );

    @Override
    public CustomPacketPayload.Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
