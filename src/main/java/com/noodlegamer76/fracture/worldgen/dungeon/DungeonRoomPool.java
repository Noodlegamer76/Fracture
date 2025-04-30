package com.noodlegamer76.fracture.worldgen.dungeon;

import net.minecraft.util.RandomSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DungeonRoomPool {
    public List<RoomSettings> pool = new ArrayList<>();

    public DungeonRoomPool(RoomSettings... rooms) {
        pool.addAll(Arrays.asList(rooms));
    }

    public List<RoomSettings> getPool() {
        return pool;
    }

    public void addRoom(RoomSettings room) {
        pool.add(room);
    }

    public RoomSettings getRandomRoom(RandomSource random, DungeonChunkPipeline pipeline, DungeonPos pos) {
        List<RoomSettings> availableRooms = new ArrayList<>();
        AreaData data = pipeline.getData(pos);

        if (data == null) {
            return null;
        }

        for (RoomSettings room : pool) {
            if (isRoom(data, room)) {
                availableRooms.add(room);
            }
        }

        if (availableRooms.isEmpty()) {
            return null;
        }

        return availableRooms.get(random.nextInt(availableRooms.size()));
    }

    public static boolean isRoom(AreaData data, RoomSettings room) {
        return data.getTemperature() >= room.getRoom().minTemp && data.getTemperature() <= room.getRoom().maxTemp &&
                data.getDepth() >= room.getRoom().minDepth && data.getDepth() <= room.getRoom().maxDepth;
    }
}
