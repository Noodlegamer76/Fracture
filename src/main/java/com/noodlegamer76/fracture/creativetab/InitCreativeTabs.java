package com.noodlegamer76.fracture.creativetab;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.item.InitItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FractureMod.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> FRACTURE_TAB = CREATIVE_MODE_TABS.register("fracture_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.fracture"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> InitItems.CRUMBLING_REALITY.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(InitItems.TEST_ITEM.get());
                output.accept(InitItems.NIHIL_PORTAL.get());
                output.accept(InitItems.VOID_CRYSTAL.get());
                output.accept(InitItems.WIRES.get());
                output.accept(InitItems.VERTICAL_WIRES.get());
                output.accept(InitItems.SIDEWAYS_VERTICAL_WIRES.get());
                output.accept(InitItems.WIRE_PANEL.get());
                output.accept(InitItems.WIRE_PANEL_HAZARD.get());
                output.accept(InitItems.SUPPORT_BEAM.get());
                output.accept(InitItems.VERTICAL_SUPPORT_BEAM.get());
                output.accept(InitItems.CRUMBLING_REALITY.get());
                output.accept(InitItems.CORRODED_DUST.get());
                output.accept(InitItems.SCRAP.get());
                output.accept(InitItems.RUSTED_METAL.get());
                output.accept(InitItems.TAINTED_MUSHROOM.get());
                output.accept(InitItems.TAINTED_FUNGUS.get());
                output.accept(InitItems.TAINTED_FUNGUS_STEM.get());
                output.accept(InitItems.CHEMSHROOM.get());
                output.accept(InitItems.STRANGE_STEM.get());
                output.accept(InitItems.TOXIC_DIRT.get());
                output.accept(InitItems.TOXIC_CHEMICAL_BUCKET.get());
                output.accept(InitItems.RUSTED_BARREL.get());
                output.accept(InitItems.SAFETY_DIAMOND.get());
            }).build());
}
