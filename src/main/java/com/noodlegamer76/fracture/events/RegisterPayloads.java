package com.noodlegamer76.fracture.events;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.network.payloads.nihilportalstate.NihilPortalStateHandler;
import com.noodlegamer76.fracture.network.payloads.nihilportalstate.NihilPortalStatePayload;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

@Mod(value = FractureMod.MODID, dist = Dist.CLIENT)
public class RegisterPayloads {

    @SubscribeEvent
    public static void register(final RegisterPayloadHandlersEvent event) {
        final PayloadRegistrar registrar = event.registrar("1");

        registrar.playToClient(
                NihilPortalStatePayload.TYPE,
                NihilPortalStatePayload.STREAM_CODEC,
                NihilPortalStateHandler::handle
        );

    }
}
