package com.noodlegamer76.fracture.block;

import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class StrangeStem extends DirectionalBlock {
    private static final VoxelShape NORTH = Block.box(4, 4, 0, 12, 12, 12);
    private static final VoxelShape SOUTH = Block.box(4, 4, 4, 12, 12, 16);
    private static final VoxelShape EAST = Block.box(4, 4, 4, 16, 12, 12);
    private static final VoxelShape WEST = Block.box(0, 4, 4, 12, 12, 12);
    private static final VoxelShape UP = Block.box(4, 4, 4, 12, 16, 12);
    private static final VoxelShape DOWN = Block.box(4, 0, 4, 12, 12, 12);

    public static final MapCodec<StrangeStem> CODEC = simpleCodec(StrangeStem::new);

    protected StrangeStem(Properties p_52591_) {
        super(p_52591_);
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
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        if (true) return true;
        return switch (state.getValue(FACING)) {
            case UP -> level.getBlockState(pos.below()).isSolid();
            case DOWN -> level.getBlockState(pos.above()).isSolid();
            case SOUTH -> level.getBlockState(pos.north()).isSolid();
            case NORTH -> level.getBlockState(pos.south()).isSolid();
            case EAST -> level.getBlockState(pos.east()).isSolid();
            case WEST -> level.getBlockState(pos.west()).isSolid();
        };
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case NORTH -> NORTH;
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
            case UP -> UP;
            case DOWN -> DOWN;
        };
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }
}
