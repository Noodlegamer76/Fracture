package com.noodlegamer76.fracture.block;

import com.noodlegamer76.fracture.FractureMod;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.apache.http.impl.conn.Wire;

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
            () -> new SupportBeam(BlockBehaviour.Properties.of().noOcclusion()));
    public static final DeferredBlock<VerticalSupportBeam> VERTICAL_SUPPORT_BEAM = BLOCKS.register("vertical_support_beam",
            () -> new VerticalSupportBeam(BlockBehaviour.Properties.of().noOcclusion()));
}