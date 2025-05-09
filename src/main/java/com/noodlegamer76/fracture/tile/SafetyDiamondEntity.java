package com.noodlegamer76.fracture.tile;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.AnimatableManager;
import software.bernie.geckolib.util.GeckoLibUtil;

public class SafetyDiamondEntity extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    private SAFETY_DIAMOND_NUMBER blue = SAFETY_DIAMOND_NUMBER.ZERO;
    private SAFETY_DIAMOND_NUMBER red = SAFETY_DIAMOND_NUMBER.ZERO;
    private SAFETY_DIAMOND_NUMBER yellow = SAFETY_DIAMOND_NUMBER.ZERO;
    private SAFETY_DIAMOND_HAZARD hazard = SAFETY_DIAMOND_HAZARD.NONE;

    public SafetyDiamondEntity(BlockPos pos, BlockState blockState) {
        super(InitBlockEntities.SAFETY_DIAMOND.get(), pos, blockState);
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        tag.putByte("blue", (byte) blue.ordinal());
        tag.putByte("red", (byte) red.ordinal());
        tag.putByte("yellow", (byte) yellow.ordinal());
        tag.putByte("hazard", (byte) hazard.ordinal());
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        blue = SAFETY_DIAMOND_NUMBER.values()[tag.getByte("blue")];
        red = SAFETY_DIAMOND_NUMBER.values()[tag.getByte("red")];
        yellow = SAFETY_DIAMOND_NUMBER.values()[tag.getByte("yellow")];
        hazard = SAFETY_DIAMOND_HAZARD.values()[tag.getByte("hazard")];
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag, registries);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    //ran on the server side to send the diamond to clients
    public void setDiamondUpdated() {
        if (level instanceof ServerLevel serverLevel)
            serverLevel.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
    }

    public SAFETY_DIAMOND_NUMBER getBlue() {
        return blue;
    }

    public void setBlue(SAFETY_DIAMOND_NUMBER blue) {
        this.blue = blue;
        setChanged();
    }

    public SAFETY_DIAMOND_NUMBER getRed() {
        return red;
    }

    public void setRed(SAFETY_DIAMOND_NUMBER red) {
        this.red = red;
        setChanged();
    }

    public SAFETY_DIAMOND_NUMBER getYellow() {
        return yellow;
    }

    public void setYellow(SAFETY_DIAMOND_NUMBER yellow) {
        this.yellow = yellow;
        setChanged();
    }

    public SAFETY_DIAMOND_HAZARD getHazard() {
        return hazard;
    }

    public void setHazard(SAFETY_DIAMOND_HAZARD hazard) {
        this.hazard = hazard;
        setChanged();
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllerRegistrar) {

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public enum SAFETY_DIAMOND_NUMBER {
        ZERO,
        ONE,
        TWO,
        THREE,
        FOUR
    }

    public enum SAFETY_DIAMOND_HAZARD {
        NONE,
        ACID,
        ALK,
        COR,
        OX,
        SA,
        RADIOACTIVE,
        W
    }
}
