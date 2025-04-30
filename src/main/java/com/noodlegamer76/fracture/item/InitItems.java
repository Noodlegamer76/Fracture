package com.noodlegamer76.fracture.item;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.block.InitBlocks;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FractureMod.MODID);

    public static final DeferredItem<Item> TEST_ITEM = ITEMS.registerSimpleItem("test_item", new Item.Properties());

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
}
