package com.noodlegamer76.fracture.tile;

import com.noodlegamer76.fracture.network.payloads.nihilportalstate.NihilPortalStatePayload;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;
import software.bernie.geckolib.animatable.GeoBlockEntity;
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animation.*;
import software.bernie.geckolib.util.GeckoLibUtil;

public class NihilPortalTile extends BlockEntity implements GeoBlockEntity {
    private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
    public static final RawAnimation OPENING = RawAnimation.begin().thenPlay("opening");
    public static final RawAnimation OPEN_IDLE = RawAnimation.begin().thenPlay("open_idle");
    public static final RawAnimation CLOSING = RawAnimation.begin().thenPlay("closing");
    public PortalState portalState = PortalState.CLOSED;
    public int transitionTicks;
    public static final int ACTIVE_TIME = 6000;
    public static final int ACTIVATION_TIME = 75;
    public static final int CLOSING_TIME = 61;

    public NihilPortalTile(BlockPos pos, BlockState blockState) {
        super(InitBlockEntities.NIHIL_PORTAL.get(), pos, blockState);
    }

    public static <T extends BlockEntity> void tick(Level level, BlockPos pos, BlockState blockState, T t) {

        if (level instanceof ServerLevel serverLevel) {
            NihilPortalTile blockEntity = (NihilPortalTile) t;

            if (blockEntity.transitionTicks > 0) {
                blockEntity.transitionTicks--;
            }

            else if (blockEntity.transitionTicks == 0) {

                if (blockEntity.portalState == PortalState.ACTIVATING) {
                    blockEntity.portalState = PortalState.ACTIVATED;
                    blockEntity.transitionTicks = ACTIVE_TIME;

                    PacketDistributor.sendToPlayersTrackingChunk(serverLevel, new ChunkPos(pos),
                            new NihilPortalStatePayload(BlockPos.asLong(pos.getX(), pos.getY(), pos.getZ()), PortalState.ACTIVATED.ordinal()));
                }

                else if (blockEntity.portalState == PortalState.ACTIVATED) {
                    blockEntity.portalState = PortalState.CLOSING;
                    blockEntity.transitionTicks = CLOSING_TIME;

                    PacketDistributor.sendToPlayersTrackingChunk(serverLevel, new ChunkPos(pos),
                            new NihilPortalStatePayload(BlockPos.asLong(pos.getX(), pos.getY(), pos.getZ()), PortalState.CLOSING.ordinal()));
                }

                else if (blockEntity.portalState == PortalState.CLOSING) {
                    blockEntity.portalState = PortalState.CLOSED;

                    PacketDistributor.sendToPlayersTrackingChunk(serverLevel, new ChunkPos(pos),
                            new NihilPortalStatePayload(BlockPos.asLong(pos.getX(), pos.getY(), pos.getZ()), PortalState.CLOSED.ordinal()));
                }
            }
        }
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {
        controllers.add(new AnimationController<>(this, this::state));
    }

    private PlayState state(AnimationState<NihilPortalTile> state) {

        return switch (this.portalState) {
            case CLOSED -> {
                state.setAnimation(null);
                yield PlayState.STOP;
            }
            case CLOSING -> {
                if (state.getController().hasAnimationFinished()) {
                    state.setAnimation(null);
                    yield PlayState.STOP;
                }
                yield state.setAndContinue(CLOSING);
            }
            case ACTIVATING -> {
                if (state.getController().hasAnimationFinished() && state.isCurrentAnimation(OPENING)) {
                    yield state.setAndContinue(OPEN_IDLE);
                }
                if (state.isCurrentAnimation(OPEN_IDLE)) {
                    yield PlayState.CONTINUE;
                }
                yield state.setAndContinue(OPENING);
            }
            case ACTIVATED -> {
                if (state.getController().hasAnimationFinished()) {
                    state.resetCurrentAnimation();
                }
                yield state.setAndContinue(OPEN_IDLE);
            }
            default -> PlayState.STOP;
        };
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

    public enum PortalState {
        CLOSING,
        CLOSED,
        ACTIVATING,
        ACTIVATED
    }
}
