package com.noodlegamer76.fracture.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.Optional;

public class Foods {
    public static final FoodProperties STRANGE_STEM = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(2)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 30, 2), 0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.HUNGER, 60, 2), 0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 60, 2), 0.1F)
            .effect(() -> new MobEffectInstance(MobEffects.ABSORPTION, 600, 0), 0.1F)
            .fast()
            .alwaysEdible()
            .build();

    public static final FoodProperties TOXIC_CHEMICALS = new FoodProperties.Builder()
            .nutrition(20)
            .saturationModifier(1)
            .effect(() -> new MobEffectInstance(MobEffects.POISON, 150, 3), 1F)
            .effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 250, 3), 1F)
            .usingConvertsTo(Items.BUCKET)
            .fast()
            .alwaysEdible()
            .build();
}
