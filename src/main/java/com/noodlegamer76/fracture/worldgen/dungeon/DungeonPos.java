package com.noodlegamer76.fracture.worldgen.dungeon;

import net.minecraft.core.BlockPos;

import java.util.Objects;

public class DungeonPos {
    public final int cellX, cellY, cellZ;

    public DungeonPos(int cellX, int cellY, int cellZ) {
        this.cellX = cellX;
        this.cellY = cellY;
        this.cellZ = cellZ;
    }

    public DungeonPos(BlockPos pos) {
        this(pos.getX() >> 4, pos.getY() >> 4, pos.getZ() >> 4);
    }

    public String toString() {
        return "DungeonPos{" + "cellX=" + cellX + ", cellY=" + cellY + ", cellZ=" + cellZ + '}';
    }

    public BlockPos toBlockPos() {
        return new BlockPos(cellX * 16, cellY * 16, cellZ * 16);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DungeonPos that)) return false;
        return cellX == that.cellX && cellY == that.cellY && cellZ == that.cellZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cellX, cellY, cellZ);
    }

    public int getCellX() {
        return cellX;
    }

    public int getCellY() {
        return cellY;
    }

    public int getCellZ() {
        return cellZ;
    }
}
