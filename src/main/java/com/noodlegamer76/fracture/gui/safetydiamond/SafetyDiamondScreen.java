package com.noodlegamer76.fracture.gui.safetydiamond;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.network.payloads.safetydiamond.SafetyDiamondHandler;
import com.noodlegamer76.fracture.network.payloads.safetydiamond.SafetyDiamondPayload;
import com.noodlegamer76.fracture.tile.SafetyDiamondEntity;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.network.PacketDistributor;

public class SafetyDiamondScreen extends Screen {
    private final SafetyDiamondEntity entity;
    private boolean changed = false;
    private final int SCALE = 7;

    SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER blue;
    SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER red;
    SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER yellow;
    SafetyDiamondEntity.SAFETY_DIAMOND_HAZARD hazard;

    private static final ResourceLocation DIAMOND     = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/safety_diamond.png");
    private static final ResourceLocation ZERO        = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/zero.png");
    private static final ResourceLocation ONE         = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/one.png");
    private static final ResourceLocation TWO         = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/two.png");
    private static final ResourceLocation THREE       = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/three.png");
    private static final ResourceLocation FOUR        = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/four.png");
    private static final ResourceLocation ACID        = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/acid.png");
    private static final ResourceLocation ALK         = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/alk.png");
    private static final ResourceLocation COR         = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/cor.png");
    private static final ResourceLocation OX          = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/ox.png");
    private static final ResourceLocation RADIOACTIVE = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/radioactive.png");
    private static final ResourceLocation SA          = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/sa.png");
    private static final ResourceLocation W           = ResourceLocation.fromNamespaceAndPath(FractureMod.MODID, "textures/block/safety_diamond/w.png");

    public SafetyDiamondScreen(SafetyDiamondEntity entity) {
        super(Component.literal("Safety Diamond"));
        this.entity = entity;

        blue   = entity.getBlue();
        red    = entity.getRed();
        yellow = entity.getYellow();
        hazard = entity.getHazard();
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);


        int halfSize  = (int)(SCALE * 8);
        int fullSize  = halfSize * 2;

        int centerX = width  / 2;
        int centerY = height / 2 - (height / 10);
        int dx = centerX - halfSize;
        int dy = centerY - halfSize;

        guiGraphics.blit(DIAMOND, dx, dy, 0, 0, fullSize, fullSize, fullSize, fullSize);

        float fxLeft   = 0.23f, fxRight  = 0.77f;
        float fyTop    = 0.23f, fyBottom = 0.77f;
        float fyMid    = 0.50f, fxMid    = 0.50f;

        drawNumber(guiGraphics, blue,   dx + (int)(fullSize * fxLeft ),  dy + (int)(fullSize * fyMid),   SCALE);
        drawNumber(guiGraphics, red,    dx + (int)(fullSize * fxMid ),   dy + (int)(fullSize * fyTop),   SCALE);
        drawNumber(guiGraphics, yellow, dx + (int)(fullSize * fxRight),  dy + (int)(fullSize * fyMid),   SCALE);
        drawHazard(guiGraphics, hazard, dx + (int)(fullSize * fxMid),    dy + (int)(fullSize * fyBottom),SCALE);

    }

    @Override
    protected void init() {
        super.init();

        float scale   = 7f;
        int halfSize  = (int)(scale * 8);
        int fullSize  = halfSize * 2;

        int centerX = width  / 2;
        int centerY = height / 2 - (height / 10);
        int dx = centerX - halfSize;
        int dy = centerY - halfSize;

        int widgetSize = 28; // size of the invisible buttons or actual interactable widgets

        addDiamondButton(centerX, centerY, 0.23f, 0.5f, widgetSize, "blue");   // Left
        addDiamondButton(centerX, centerY, 0.5f,  0.23f, widgetSize, "red");    // Top
        addDiamondButton(centerX, centerY, 0.77f, 0.5f, widgetSize, "yellow"); // Right
        addDiamondButton(centerX, centerY, 0.5f,  0.77f, widgetSize, "hazard"); // Bottom
    }

    private void addDiamondButton(int cx, int cy, float fx, float fy, int size, String tag) {
        int x = (int)(cx + (fx - 0.5f) * (SCALE * 16)) - size / 2;
        int y = (int)(cy + (fy - 0.5f) * (SCALE * 16)) - size / 2;

        addRenderableWidget(new DiamondZoneButton(x, y, size, size, tag, button -> {
            switch (tag) {
                case "blue"   -> blue = (SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER.values()[(blue.ordinal() + 1) % 5]);
                case "red"    -> red = (SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER.values()[(red.ordinal() + 1) % 5]);
                case "yellow" -> yellow = (SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER.values()[(yellow.ordinal() + 1) % 5]);
                case "hazard" -> hazard = (SafetyDiamondEntity.SAFETY_DIAMOND_HAZARD.values()[(hazard.ordinal() + 1) % SafetyDiamondEntity.SAFETY_DIAMOND_HAZARD.values().length]);
                default -> throw new IllegalStateException("Unexpected value: " + tag);
            }

            changed = true;
        }));
    }

    private void drawNumber(GuiGraphics g, SafetyDiamondEntity.SAFETY_DIAMOND_NUMBER num, int x, int y, float scale) {
        if (num == null) return;
        ResourceLocation tex = switch (num) {
            case ZERO   -> ZERO;
            case ONE    -> ONE;
            case TWO    -> TWO;
            case THREE  -> THREE;
            case FOUR   -> FOUR;
        };
        int sz = (int)(16 * scale / 2.5);
        g.blit(tex, x - sz/2, y - sz/2, 0, 0, sz, sz, sz, sz);
    }

    private void drawHazard(GuiGraphics g, SafetyDiamondEntity.SAFETY_DIAMOND_HAZARD h, int x, int y, float scale) {
        if (h == null || h == SafetyDiamondEntity.SAFETY_DIAMOND_HAZARD.NONE) return;
        ResourceLocation tex = switch (h) {
            case ACID        -> ACID;
            case ALK         -> ALK;
            case COR         -> COR;
            case OX          -> OX;
            case RADIOACTIVE -> RADIOACTIVE;
            case SA          -> SA;
            case W           -> W;
            default -> throw new IllegalStateException("Unexpected value: " + h);
        };
        int sz = (int)(16 * scale / 2.5);
        g.blit(tex, x - sz/2, y - sz/2, 0, 0, sz, sz, sz, sz);
    }

    @Override
    public void onClose() {
        super.onClose();

        if (changed) {
            byte[] bytes = {(byte) blue.ordinal(), (byte) red.ordinal(), (byte) yellow.ordinal(), (byte) hazard.ordinal()};
            PacketDistributor.sendToServer(new SafetyDiamondPayload(SafetyDiamondHandler.encodeDiamond(bytes), entity.getBlockPos().asLong()));
        }
    }
}
