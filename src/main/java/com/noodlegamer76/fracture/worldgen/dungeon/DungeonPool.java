package com.noodlegamer76.fracture.worldgen.dungeon;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.worldgen.dungeon.voidcity.pools.TestPool;
import com.noodlegamer76.fracture.worldgen.dungeon.voidcity.pools.rooms.Room1;
import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonPool {

    public static final DungeonRoom STONE_CUBE = new DungeonRoom(
            ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "stone_cube"),
            0.2, 0.8,
            1, 4);

    public static final DungeonRoom TEST_ROOM = new DungeonRoom(
            ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "test_room"),
            0, 1,
            -1, 1);

    public static final DungeonRoom TEST_WALL_EXTERIOR = new DungeonRoom(
            ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "void_city/temp/exterior"),
            0, 1,
            -1, 1);

    public static final DungeonRoom BLANK = new DungeonRoom(
            ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "void_city/blank"),
            0, 1,
            -1, 1);

    public static final DungeonRoom ROOM1 = new Room1();

    public static final DungeonRoomPool ROOM1_POOL = new TestPool();
}
