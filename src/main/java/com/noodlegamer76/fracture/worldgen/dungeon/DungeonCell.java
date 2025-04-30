package com.noodlegamer76.fracture.worldgen.dungeon;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.worldgen.dungeon.voidcity.RoomKeys;
import com.noodlegamer76.fracture.worldgen.dungeon.voidcity.VoidCityRoomGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.structure.templatesystem.JigsawReplacementProcessor;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;

import java.util.Optional;

public class DungeonCell {
    private final DungeonPos pos;
    private RoomSettings roomSettings;
    private final DungeonChunkPipeline pipeline;
    private static final ResourceKey<Level> VOID_CITY = ResourceKey.create(Registries.DIMENSION, ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "void_city"));

    public DungeonCell(DungeonPos pos, DungeonChunkPipeline pipeline) {
        this.pos = pos;
        this.pipeline = pipeline;
    }

    public void init(RandomSource random) {
        if (pipeline.getLevel().getLevel().dimension() == VOID_CITY) {
            VoidCityRoomGenerator.generate(this, random);
        }
    }

    public void generate(WorldGenLevel level) {
        if (roomSettings == null) {
            generateAirRoom(level);
            return;
        }

        BlockPos origin = pos.toBlockPos();
        generate(roomSettings, level, origin);

        generateRecursive(roomSettings, level, origin, level.getRandom());
    }

    public void generateRecursive(RoomSettings room, WorldGenLevel level, BlockPos pos, RandomSource random) {
        for (RoomSettings roomSettings : room.getRoom().getSubRooms().values()) {

            if (roomSettings.isBlockedFromEmpty()) {
                Direction dir = roomSettings.getDirection();
                DungeonPos neighborPos = switch (dir) {
                    case NORTH -> new DungeonPos(pos.north(16));
                    case SOUTH -> new DungeonPos(pos.south(16));
                    case WEST  -> new DungeonPos(pos.west(16));
                    case EAST  -> new DungeonPos(pos.east(16));
                    default    -> null;
                };

                AreaData data = getPipeline().getData(neighborPos);
                if (data == null) {
                    System.out.println(dir);
                }
                else if (neighborPos != null && !DungeonRoomPool.isRoom(data, roomSettings)) {
                    continue;
                }
            }

            generate(roomSettings, level, pos);
            generateRecursive(roomSettings, level, pos.above(), random);
        }
    }

    public void generate(RoomSettings roomSettings, WorldGenLevel level, BlockPos pos) {
        Optional<StructureTemplate> structure = level.getServer().getStructureManager().get(roomSettings.getRoom().getRoom());

        structure.ifPresent(template -> {
            StructurePlaceSettings settings = new StructurePlaceSettings();
            settings.addProcessor(JigsawReplacementProcessor.INSTANCE);

            settings.setRotation(directionToRotation(roomSettings.getDirection()));
            BlockPos finalPos = calculateAttachmentPos(pos, roomSettings.getDirection());

            template.placeInWorld(level, finalPos, finalPos, settings, level.getRandom(), 2);
        });
    }

    private Rotation directionToRotation(Direction direction) {
        return switch (direction) {
            case SOUTH -> Rotation.CLOCKWISE_180;
            case WEST -> Rotation.COUNTERCLOCKWISE_90;
            case EAST -> Rotation.CLOCKWISE_90;
            default -> Rotation.NONE;
        };
    }

    private BlockPos calculateAttachmentPos(BlockPos origin, Direction direction) {
        int size = 16; // Room size

        return switch (direction) {
            case SOUTH -> origin.east(15).south(15);
            case WEST -> origin.south(15);
            case EAST -> origin.east(15);
            default -> origin;
        };
    }

    public void generateAirRoom(WorldGenLevel level) {

    }

    public RoomSettings getRoomSettings() {
        return roomSettings;
    }

    public DungeonChunkPipeline getPipeline() {
        return pipeline;
    }

    public DungeonPos getPos() {
        return pos;
    }

    public void setRoomSettings(RoomSettings roomSettings) {
        this.roomSettings = roomSettings;
    }
}
