package com.noodlegamer76.fracture.gui.safetydiamond;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.network.chat.Component;

public class DiamondZoneButton extends Button {
    public DiamondZoneButton(int x, int y, int width, int height, String tag, OnPress onPress) {
        super(x, y, width, height, Component.empty(), onPress, DEFAULT_NARRATION);
        this.setTooltip(Tooltip.create(Component.literal("Click to change " + tag)));
    }
}