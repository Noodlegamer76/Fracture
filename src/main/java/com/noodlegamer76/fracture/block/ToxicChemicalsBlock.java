package com.noodlegamer76.fracture.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;

public class ToxicChemicalsBlock extends LiquidBlock {
    public ToxicChemicalsBlock(FlowingFluid fluid, Properties properties) {
        super(fluid, properties);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        super.entityInside(state, level, pos, entity);
        if (entity instanceof LivingEntity livingEntity) {
            if (livingEntity.isInvulnerable() || livingEntity.isSpectator() || (livingEntity instanceof Player player && player.isCreative())) {
                return;
            }

            MobEffectInstance poison = livingEntity.getEffect(MobEffects.POISON);

            if (poison == null) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, 3, 0));
                poison = livingEntity.getEffect(MobEffects.POISON);
            }

            if (poison != null && poison.getDuration() < 600) {
                livingEntity.addEffect(new MobEffectInstance(MobEffects.POISON, poison.getDuration() + 1, poison.getAmplifier()));
            }
        }
    }
}
