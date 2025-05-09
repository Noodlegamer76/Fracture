package com.noodlegamer76.fracture.block;

import com.noodlegamer76.fracture.item.VoidCrystal;
import com.noodlegamer76.fracture.network.payloads.nihilportalstate.NihilPortalStatePayload;
import com.noodlegamer76.fracture.tile.InitBlockEntities;
import com.noodlegamer76.fracture.tile.NihilPortalTile;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

public class NihilPortal extends Block implements EntityBlock {
    public NihilPortal(Properties properties) {
        super(properties);
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new NihilPortalTile(pos, state);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (stack.getItem() instanceof VoidCrystal) {
            NihilPortalTile blockEntity = level.getBlockEntity(pos) instanceof NihilPortalTile ? (NihilPortalTile) level.getBlockEntity(pos) : null;

            if (blockEntity != null && level instanceof ServerLevel serverLevel) {
                if (blockEntity.portalState != NihilPortalTile.PortalState.CLOSED) {
                    return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
                }

                blockEntity.portalState = NihilPortalTile.PortalState.ACTIVATING;
                PacketDistributor.sendToPlayersTrackingChunk(serverLevel, new ChunkPos(pos),
                        new NihilPortalStatePayload(BlockPos.asLong(pos.getX(), pos.getY(), pos.getZ()), NihilPortalTile.PortalState.ACTIVATING.ordinal()));
                blockEntity.transitionTicks = NihilPortalTile.ACTIVATION_TIME;

                if (!player.hasInfiniteMaterials()) {
                    stack.shrink(1);
                }

                return ItemInteractionResult.SUCCESS;
            }
        }

        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return type == InitBlockEntities.NIHIL_PORTAL.get() ? NihilPortalTile::tick : null;
    }

    @Override
    protected RenderShape getRenderShape(BlockState state) {
        return RenderShape.ENTITYBLOCK_ANIMATED;
    }
}
