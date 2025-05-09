package com.noodlegamer76.fracture.datagen;

import com.noodlegamer76.fracture.block.InitBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        dropSelf(InitBlocks.WIRES.value());
        dropSelf(InitBlocks.VERTICAL_WIRES.value());
        dropSelf(InitBlocks.WIRE_PANEL.value());
        dropSelf(InitBlocks.WIRE_PANEL_HAZARD.value());
        dropSelf(InitBlocks.SIDEWAYS_VERTICAL_WIRES.value());
        dropSelf(InitBlocks.SUPPORT_BEAM.value());
        dropSelf(InitBlocks.VERTICAL_SUPPORT_BEAM.value());
        dropSelf(InitBlocks.CRUMBLING_REALITY.value());
        dropSelf(InitBlocks.CORRODED_DUST.value());
        dropSelf(InitBlocks.SCRAP.value());
        dropSelf(InitBlocks.RUSTED_METAL.value());
        dropSelf(InitBlocks.TAINTED_FUNGUS.value());
        dropSelf(InitBlocks.TAINTED_FUNGUS_STEM.value());
        dropSelf(InitBlocks.TOXIC_DIRT.value());
        dropSelf(InitBlocks.RUSTED_BARREL.value());
        dropSelf(InitBlocks.SAFETY_DIAMOND.value());
        dropSelf(InitBlocks.STRANGE_STEM.value());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        List<Block> blocks = new ArrayList<>();

        blocks.add(InitBlocks.WIRES.value());
        blocks.add(InitBlocks.VERTICAL_WIRES.value());
        blocks.add(InitBlocks.WIRE_PANEL.value());
        blocks.add(InitBlocks.WIRE_PANEL_HAZARD.value());
        blocks.add(InitBlocks.SIDEWAYS_VERTICAL_WIRES.value());
        blocks.add(InitBlocks.SUPPORT_BEAM.value());
        blocks.add(InitBlocks.VERTICAL_SUPPORT_BEAM.value());
        blocks.add(InitBlocks.CRUMBLING_REALITY.value());
        blocks.add(InitBlocks.CORRODED_DUST.value());
        blocks.add(InitBlocks.SCRAP.value());
        blocks.add(InitBlocks.RUSTED_METAL.value());
        blocks.add(InitBlocks.TAINTED_FUNGUS.value());
        blocks.add(InitBlocks.TAINTED_FUNGUS_STEM.value());
        blocks.add(InitBlocks.TOXIC_DIRT.value());
        blocks.add(InitBlocks.RUSTED_BARREL.value());
        blocks.add(InitBlocks.SAFETY_DIAMOND.value());
        blocks.add(InitBlocks.STRANGE_STEM.value());

        return blocks;
    }
}