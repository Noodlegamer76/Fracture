package com.noodlegamer76.fracture.network.payloads.nihilportalstate;

import com.noodlegamer76.fracture.tile.NihilPortalTile;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public class NihilPortalStateHandler {

    public static void handle(NihilPortalStatePayload payload, IPayloadContext context) {
        Level level = context.player().level();

        NihilPortalTile portal = level.getBlockEntity(BlockPos.of(payload.pos())) instanceof NihilPortalTile
                ? (NihilPortalTile) level.getBlockEntity(BlockPos.of(payload.pos())) : null;

        if (portal != null) {
            portal.portalState = NihilPortalTile.PortalState.values()[payload.state()];
        }
    }
}
