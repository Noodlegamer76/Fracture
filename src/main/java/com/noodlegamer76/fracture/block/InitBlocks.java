package com.noodlegamer76.fracture.block;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.block.fluid.InitFluids;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.awt.*;

public class InitBlocks {
        public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FractureMod.MODID);

        public static final DeferredBlock<NihilPortal> NIHIL_PORTAL = BLOCKS.register("nihil_portal",
            () -> new NihilPortal(BlockBehaviour.Properties.of().noOcclusion()));

    public static final DeferredBlock<Wires> WIRES = BLOCKS.register("wires",
            () -> new Wires(BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<WirePanel> WIRE_PANEL = BLOCKS.register("wire_panel",
            () -> new WirePanel(BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<WirePanel> WIRE_PANEL_HAZARD = BLOCKS.register("wire_panel_hazard",
            () -> new WirePanel(BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<Wires> VERTICAL_WIRES = BLOCKS.register("vertical_wires",
            () -> new VerticleWires(BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<Wires> SIDEWAYS_VERTICAL_WIRES = BLOCKS.register("sideways_vertical_wires",
            () -> new VerticleWires(BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<SupportBeam> SUPPORT_BEAM = BLOCKS.register("support_beam",
            () -> new SupportBeam(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));
    public static final DeferredBlock<VerticalSupportBeam> VERTICAL_SUPPORT_BEAM = BLOCKS.register("vertical_support_beam",
            () -> new VerticalSupportBeam(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final DeferredBlock<Block> CRUMBLING_REALITY = BLOCKS.register("crumbling_reality",
            () -> new Block(BlockBehaviour.Properties.of().mapColor(DyeColor.BLACK)));

    public static final DeferredBlock<Block> CORRODED_DUST = BLOCKS.register("corroded_dust",
            () -> new ColoredFallingBlock(new ColorRGBA(new Color(199, 84, 0).getRGB()),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)));
    public static final DeferredBlock<Block> SCRAP = BLOCKS.register("scrap",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).mapColor(DyeColor.BLACK)));
    public static final DeferredBlock<Block> RUSTED_METAL = BLOCKS.register("rusted_metal",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).mapColor(DyeColor.BROWN)));

    public static final DeferredBlock<Block> TAINTED_FUNGUS = BLOCKS.register("tainted_fungus",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).mapColor(DyeColor.RED)));
    public static final DeferredBlock<Block> TAINTED_FUNGUS_STEM = BLOCKS.register("tainted_fungus_stem",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.MOSS_BLOCK).instabreak().mapColor(DyeColor.RED)));
    public static final DeferredBlock<Block> TAINTED_MUSHROOM = BLOCKS.register("tainted_mushroom",
            () -> new TaintedMushroom(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SAPLING).mapColor(DyeColor.RED)));
    public static final DeferredBlock<Block> CHEMSHROOM = BLOCKS.register("chemshroom",
            () -> new Chemshroom(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SAPLING).mapColor(DyeColor.BLACK)));
    public static final DeferredBlock<Block> STRANGE_STEM = BLOCKS.register("strange_stem",
            () -> new StrangeStem(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_SAPLING).mapColor(DyeColor.RED)));
    public static final DeferredBlock<Block> TOXIC_DIRT = BLOCKS.register("toxic_dirt",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIRT).mapColor(DyeColor.GREEN)));

    public static final DeferredBlock<Block> RUSTED_BARREL = BLOCKS.register("rusted_barrel",
            () -> new RotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.IRON_BLOCK).mapColor(DyeColor.BROWN)));

    public static final DeferredBlock<ToxicChemicalsBlock> TOXIC_CHEMICALS = BLOCKS.register("toxic_chemicals",
            () -> new ToxicChemicalsBlock(InitFluids.SOURCE_TOXIC_CHEMICALS.get(), BlockBehaviour.Properties.ofFullCopy(Blocks.WATER).mapColor(DyeColor.GREEN)));

    public static final DeferredBlock<SafetyDiamond> SAFETY_DIAMOND = BLOCKS.register("safety_diamond",
            () -> new SafetyDiamond(BlockBehaviour.Properties.ofFullCopy(Blocks.STONE).noCollission()));
}