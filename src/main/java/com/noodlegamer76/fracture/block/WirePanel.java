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

public class WirePanel extends DirectionalBlock {
    public static final MapCodec<WirePanel> CODEC = simpleCodec(WirePanel::new);
    private static final VoxelShape DOWN = Block.box(0, 0, 0, 16, 4, 16);
    private static final VoxelShape UP = Block.box(0, 12, 0, 16, 16, 16);
    private static final VoxelShape NORTH = Block.box(0, 0, 0, 16, 16, 4);
    private static final VoxelShape SOUTH = Block.box(0, 0, 12, 16, 16, 16);
    private static final VoxelShape WEST = Block.box(0, 0, 0, 4, 16, 16);
    private static final VoxelShape EAST = Block.box(12, 0, 0, 16, 16, 16);

    protected WirePanel(Properties p_52591_) {
        super(p_52591_);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        switch (state.getValue(FACING)) {
            case DOWN:
                return DOWN;
            case UP:
                return UP;
            case NORTH:
                return NORTH;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            case EAST:
                return EAST;
        }
        return DOWN;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getClickedFace().getOpposite());
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }
}
