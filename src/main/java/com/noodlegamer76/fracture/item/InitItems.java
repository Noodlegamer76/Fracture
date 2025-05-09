package com.noodlegamer76.fracture.item;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.block.InitBlocks;
import com.noodlegamer76.fracture.block.fluid.InitFluids;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FractureMod.MODID);

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.register("test_item",
            () -> new TestItem(new Item.Properties()));

    public static final DeferredItem<Item> VOID_CRYSTAL = ITEMS.register("void_crystal",
            () -> new VoidCrystal(new Item.Properties()));

    public static final DeferredItem<BlockItem> NIHIL_PORTAL = ITEMS.register("nihil_portal",
            () -> new NihilPortalItem(InitBlocks.NIHIL_PORTAL.get(), new Item.Properties()));

    public static final DeferredItem<BlockItem> WIRES = ITEMS.register("wires",
            () -> new BlockItem(InitBlocks.WIRES.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> WIRE_PANEL = ITEMS.register("wire_panel",
            () -> new BlockItem(InitBlocks.WIRE_PANEL.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> WIRE_PANEL_HAZARD = ITEMS.register("wire_panel_hazard",
            () -> new BlockItem(InitBlocks.WIRE_PANEL_HAZARD.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> VERTICAL_WIRES = ITEMS.register("vertical_wires",
            () -> new BlockItem(InitBlocks.VERTICAL_WIRES.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> SIDEWAYS_VERTICAL_WIRES = ITEMS.register("sideways_vertical_wires",
            () -> new BlockItem(InitBlocks.SIDEWAYS_VERTICAL_WIRES.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> SUPPORT_BEAM = ITEMS.register("support_beam",
            () -> new BlockItem(InitBlocks.SUPPORT_BEAM.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> VERTICAL_SUPPORT_BEAM = ITEMS.register("vertical_support_beam",
            () -> new BlockItem(InitBlocks.VERTICAL_SUPPORT_BEAM.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> CRUMBLING_REALITY = ITEMS.register("crumbling_reality",
            () -> new BlockItem(InitBlocks.CRUMBLING_REALITY.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> CORRODED_DUST = ITEMS.register("corroded_dust",
            () -> new BlockItem(InitBlocks.CORRODED_DUST.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> SCRAP = ITEMS.register("scrap",
            () -> new BlockItem(InitBlocks.SCRAP.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> RUSTED_METAL = ITEMS.register("rusted_metal",
            () -> new BlockItem(InitBlocks.RUSTED_METAL.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> TAINTED_MUSHROOM = ITEMS.register("tainted_mushroom",
            () -> new BlockItem(InitBlocks.TAINTED_MUSHROOM.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> TAINTED_FUNGUS = ITEMS.register("tainted_fungus",
            () -> new BlockItem(InitBlocks.TAINTED_FUNGUS.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> TAINTED_FUNGUS_STEM = ITEMS.register("tainted_fungus_stem",
            () -> new BlockItem(InitBlocks.TAINTED_FUNGUS_STEM.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> CHEMSHROOM = ITEMS.register("chemshroom",
            () -> new BlockItem(InitBlocks.CHEMSHROOM.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> STRANGE_STEM = ITEMS.register("strange_stem",
            () -> new BlockItem(InitBlocks.STRANGE_STEM.get(), new Item.Properties().food(Foods.STRANGE_STEM)));
    public static final DeferredItem<BlockItem> TOXIC_DIRT = ITEMS.register("toxic_dirt",
            () -> new BlockItem(InitBlocks.TOXIC_DIRT.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> RUSTED_BARREL = ITEMS.register("rusted_barrel",
            () -> new BlockItem(InitBlocks.RUSTED_BARREL.get(), new Item.Properties()));
    public static final DeferredItem<BlockItem> SAFETY_DIAMOND = ITEMS.register("safety_diamond",
            () -> new BlockItem(InitBlocks.SAFETY_DIAMOND.get(), new Item.Properties()));

    public static final DeferredItem<BucketItem> TOXIC_CHEMICAL_BUCKET = ITEMS.register("toxic_chemical_bucket",
            () -> new BucketItem(InitFluids.SOURCE_TOXIC_CHEMICALS.get(), new Item.Properties().stacksTo(1)));
}
