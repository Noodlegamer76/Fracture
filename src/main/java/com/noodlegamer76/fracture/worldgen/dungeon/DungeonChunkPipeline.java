package com.noodlegamer76.fracture.worldgen.dungeon;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.chunk.status.WorldGenContext;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DungeonChunkPipeline {
    private final List<DungeonCell> cells = new ArrayList<>();
    private final WorldGenLevel level;
    private final FeaturePlaceContext<NoneFeatureConfiguration> context;
    private final Map<DungeonPos, AreaData> dataMap = new HashMap<>();

    public DungeonChunkPipeline(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        this.level = context.level();
        this.context = context;
    }

    public void start() {
        scanArea(2);
        createCells();
        initCells();
        generateCells();
    }

    public void createCells() {
        int size = level.getHeight() / 16;
        BlockPos origin = context.origin();

        for (int i = 0; i < size; i++) {
            DungeonCell cell = new DungeonCell(new DungeonPos(origin.offset(0, i * 16, 0)), this);
            addCell(cell);
        }
    }

    public void scanArea(int radius) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                int height = level.getHeight() / 16;
                for (int y = 0; y < height; y++) {
                    createData(new DungeonPos(context.origin().offset(x * 16, y * 16, z * 16)));
                }
            }
        }
    }

    public void createData(DungeonPos pos) {
        DensityFunction.SinglePointContext context = new DensityFunction.SinglePointContext(pos.toBlockPos().getX(), pos.toBlockPos().getY(), pos.toBlockPos().getZ());
        Climate.Sampler sampler = level.getLevel().getChunkSource().randomState().sampler();

        AreaData data = new AreaData();
        data.setDepth(sampler.depth().compute(context));
        data.setTemperature(sampler.temperature().compute(context));

        dataMap.put(pos, data);
    }

    public void initCells() {
        for (DungeonCell cell: cells) {
            cell.init(level.getRandom());
        }
    }

    public void generateCells() {
        for (DungeonCell cell: cells) {
            cell.generate(level);
        }
    }

    public void clear() {
        cells.clear();
    }

    public void addCell(DungeonCell cell) {
        cells.add(cell);
    }

    public void removeCell(DungeonCell cell) {
        cells.remove(cell);
    }

    public List<DungeonCell> getCells() {
        return cells;
    }

    public WorldGenLevel getLevel() {
        return level;
    }

    public Map<DungeonPos, AreaData> getDataMap() {
        return dataMap;
    }

    public AreaData getData(DungeonPos pos) {
        return dataMap.get(pos);
    }
}
