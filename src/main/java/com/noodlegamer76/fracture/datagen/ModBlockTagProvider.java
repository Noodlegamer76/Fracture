package com.noodlegamer76.fracture.datagen;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.block.InitBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FractureMod.MODID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                InitBlocks.WIRES.value(),
                InitBlocks.VERTICAL_WIRES.value(),
                InitBlocks.WIRE_PANEL.value(),
                InitBlocks.WIRE_PANEL_HAZARD.value(),
                InitBlocks.SIDEWAYS_VERTICAL_WIRES.value(),
                InitBlocks.SUPPORT_BEAM.value(),
                InitBlocks.VERTICAL_SUPPORT_BEAM.value(),
                InitBlocks.CRUMBLING_REALITY.value(),
                InitBlocks.SCRAP.value(),
                InitBlocks.RUSTED_METAL.value(),
                InitBlocks.RUSTED_BARREL.value(),
                InitBlocks.SAFETY_DIAMOND.value()
        );

        tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                InitBlocks.CORRODED_DUST.value(),
                InitBlocks.TOXIC_DIRT.value()
        );

        tag(BlockTags.MINEABLE_WITH_HOE).add(
                InitBlocks.TAINTED_FUNGUS.value(),
                InitBlocks.TAINTED_FUNGUS_STEM.value()
        );
    }
}