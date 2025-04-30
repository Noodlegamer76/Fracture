package com.noodlegamer76.fracture.creativetab;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.item.InitItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitCreativeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FractureMod.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> EXAMPLE_TAB = CREATIVE_MODE_TABS.register("example_tab", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.fracture"))
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> InitItems.TEST_ITEM.get().getDefaultInstance())
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
            }).build());
}
