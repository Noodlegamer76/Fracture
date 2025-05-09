package com.noodlegamer76.fracture.network.payloads.safetydiamond;

import com.noodlegamer76.fracture.tile.SafetyDiamondEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.network.handling.IPayloadContext;


public class SafetyDiamondHandler {

    public static void handle(SafetyDiamondPayload payload, IPayloadContext context) {
        Level level = context.player().level();

        if (level.getBlockEntity(BlockPos.of(payload.pos())) instanceof SafetyDiamondEntity entity) {
            byte[] diamond = decodeDiamond(payload.diamond());
            entity.setBlue(SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER.values()[diamond[0]]);
            entity.setRed(SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER.values()[diamond[1]]);
            entity.setYellow(SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER.values()[diamond[2]]);
            entity.setHazard(SafetyDiamondEntity.SAFETY_DIAMOND_HAZARD.values()[diamond[3]]);
            entity.setDiamondUpdated();
        }
    }

    public static int encodeDiamond(byte[] bytes) {
        if (bytes.length != 4) throw new IllegalArgumentException("Must provide exactly 4 diamond");
        return ((bytes[0] & 0xFF) << 24) |
                ((bytes[1] & 0xFF) << 16) |
                ((bytes[2] & 0xFF) << 8)  |
                (bytes[3] & 0xFF);
    }

    public static byte[] decodeDiamond(int value) {
        return new byte[] {
                (byte) ((value >> 24) & 0xFF),
                (byte) ((value >> 16) & 0xFF),
                (byte) ((value >> 8) & 0xFF),
                (byte) (value & 0xFF)
        };
    }
}
