package com.noodlegamer76.fracture.worldgen.dungeon.voidcity;

import com.noodlegamer76.fracture.worldgen.dungeon.*;
import com.noodlegamer76.fracture.worldgen.dungeon.voidcity.pools.TestPool;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;

public class VoidCityRoomGenerator {
    public static void generate(DungeonCell cell, RandomSource random) {
        RoomSettings room = new TestPool().getRandomRoom(random, cell.getPipeline(), cell.getPos());

        if (room == null) {
            cell.setRoomSettings(getEmptyRoomSettings(cell, random));
        }
        else {
            cell.setRoomSettings(room);
        }
    }

    public static RoomSettings getEmptyRoomSettings(DungeonCell cell, RandomSource random) {
        RoomSettings settings = new RoomSettings(DungeonPool.BLANK);
        BlockPos pos = cell.getPos().toBlockPos();

        for (Direction dir : Direction.values()) {
            DungeonPos neighborPos = switch (dir) {
                case NORTH -> new DungeonPos(pos.north(16));
                case SOUTH -> new DungeonPos(pos.south(16));
                case WEST  -> new DungeonPos(pos.west(16));
                case EAST  -> new DungeonPos(pos.east(16));
                default    -> null;
            };

            AreaData data = cell.getPipeline().getData(neighborPos);
            if (data == null) {
            }
            else if (neighborPos != null && DungeonRoomPool.isRoom(data, settings)) {
                RoomSettings room = new RoomSettings(DungeonPool.TEST_WALL_EXTERIOR);
                room.setDirection(dir);
                switch (dir) {
                    case NORTH -> settings.getRoom().addSubRoom(RoomKeys.NORTH_WALL, room);
                    case SOUTH -> settings.getRoom().addSubRoom(RoomKeys.SOUTH_WALL, room);
                    case WEST  -> settings.getRoom().addSubRoom(RoomKeys.WEST_WALL, room);
                    case EAST  -> settings.getRoom().addSubRoom(RoomKeys.EAST_WALL, room);
                    default    -> {
                    }
                }

            }
        }

        return settings;
    }
}
