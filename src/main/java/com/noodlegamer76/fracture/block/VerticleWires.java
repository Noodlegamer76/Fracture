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

public class VerticleWires extends Wires {
    private static final VoxelShape NORTH = Block.box(0, 0, 0, 16, 16, 3);
    private static final VoxelShape SOUTH = Block.box(0, 0, 13, 16, 16, 16);
    private static final VoxelShape EAST = Block.box(13, 0, 0, 16, 16, 16);
    private static final VoxelShape WEST = Block.box(0, 0, 0, 3, 16, 16);

    protected VerticleWires(Properties properties) {
        super(properties);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return switch (state.getValue(FACING)) {
            case SOUTH -> SOUTH;
            case EAST -> EAST;
            case WEST -> WEST;
            default -> NORTH;
        };
    }
}
