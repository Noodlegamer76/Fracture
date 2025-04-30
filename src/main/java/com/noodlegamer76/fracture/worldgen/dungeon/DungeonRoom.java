package com.noodlegamer76.fracture.worldgen.dungeon;

import net.minecraft.resources.ResourceLocation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonRoom {
    public final ResourceLocation room;
    public double minDepth, maxDepth;
    public double minTemp, maxTemp;
    private final Map<String, RoomSettings> subRooms = new HashMap<>();

    public DungeonRoom(ResourceLocation room, double minDepth, double maxDepth, double minTemp, double maxTemp) {
        this.room = room;
        this.minDepth = minDepth;
        this.maxDepth = maxDepth;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
    }

    public Map<String, RoomSettings> getSubRooms() {
        return subRooms;
    }

    public void addSubRoom(String name, RoomSettings room) {
        subRooms.put(name, room);
    }

    public ResourceLocation getRoom() {
        return room;
    }

    public void setMaxDepth(double maxDepth) {
        this.maxDepth = maxDepth;
    }

    public void setMaxTemp(double maxTemp) {
        this.maxTemp = maxTemp;
    }

    public void setMinDepth(double minDepth) {
        this.minDepth = minDepth;
    }

    public void setMinTemp(double minTemp) {
        this.minTemp = minTemp;
    }

    public double getMaxDepth() {
        return maxDepth;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getMinDepth() {
        return minDepth;
    }

    public double getMinTemp() {
        return minTemp;
    }
}
