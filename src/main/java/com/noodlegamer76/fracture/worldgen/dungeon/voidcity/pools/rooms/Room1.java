package com.noodlegamer76.fracture.worldgen.dungeon.voidcity.pools.rooms;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.worldgen.dungeon.DungeonRoom;
import com.noodlegamer76.fracture.worldgen.dungeon.DungeonRoomPool;
import com.noodlegamer76.fracture.worldgen.dungeon.RoomSettings;
import com.noodlegamer76.fracture.worldgen.dungeon.voidcity.RoomKeys;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;

public class Room1 extends DungeonRoom {
    public static final DungeonRoom ROOM1_WALL = new DungeonRoom(
            ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "void_city/temp/room1_wall"),
            0, 1,
            -1, 1);

    public Room1() {
        super(ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "void_city/temp/room1"),
                0, 1,
                -1, 1);

        {
            RoomSettings settings = new RoomSettings(ROOM1_WALL);
            settings.setDirection(Direction.NORTH);
            settings.setBlockedFromEmpty(true);
            addSubRoom(RoomKeys.NORTH_DOOR, settings);
        }
        {
            RoomSettings settings = new RoomSettings(ROOM1_WALL);
            settings.setDirection(Direction.EAST);
            settings.setBlockedFromEmpty(true);
            addSubRoom(RoomKeys.EAST_DOOR, settings);
        }
        {
            RoomSettings settings = new RoomSettings(ROOM1_WALL);
            settings.setDirection(Direction.SOUTH);
            settings.setBlockedFromEmpty(true);
            addSubRoom(RoomKeys.SOUTH_DOOR, settings);
        }
        {
            RoomSettings settings = new RoomSettings(ROOM1_WALL);
            settings.setDirection(Direction.WEST);
            settings.setBlockedFromEmpty(true);
            addSubRoom(RoomKeys.WEST_DOOR, settings);
        }
    }
}
