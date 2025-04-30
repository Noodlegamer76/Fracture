package com.noodlegamer76.fracture.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class VerticalSupportBeam extends DirectionalBlock {
    public static final MapCodec<VerticalSupportBeam> CODEC = simpleCodec(VerticalSupportBeam::new);
    private static final VoxelShape NS = Block.box(4, 0, 2, 12, 16, 14);
    private static final VoxelShape WE = Block.box(2, 0, 4, 14, 16, 12);

    protected VerticalSupportBeam(Properties p_52591_) {
        super(p_52591_);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH, SOUTH -> NS;
            case WEST, EAST -> WE;
            default -> NS;
        };
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection());
    }
}
