package com.noodlegamer76.fracture.worldgen.dungeon;

import net.minecraft.core.Direction;

public class RoomSettings {
    private final DungeonRoom room;
    private Direction direction = Direction.NORTH;
    private boolean isBlockedFromEmpty = false;

    public RoomSettings(DungeonRoom room) {
        this.room = room;
    }

    public DungeonRoom getRoom() {
        return room;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setBlockedFromEmpty(boolean blockedFromEmpty) {
        isBlockedFromEmpty = blockedFromEmpty;
    }

    public boolean isBlockedFromEmpty() {
        return isBlockedFromEmpty;
    }
}
