package com.noodlegamer76.fracture.tile;

import com.noodlegamer76.fracture.FractureMod;
import com.noodlegamer76.fracture.block.InitBlocks;
import com.noodlegamer76.fracture.block.SafetyDiamond;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class InitBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, FractureMod.MODID);

        public static final Supplier<BlockEntityType<NihilPortalTile>> NIHIL_PORTAL = BLOCK_ENTITIES.register("nihil_portal",
            () -> BlockEntityType.Builder.of(NihilPortalTile::new, InitBlocks.NIHIL_PORTAL.get()).build(null));

    public static final Supplier<BlockEntityType<SafetyDiamondEntity>> SAFETY_DIAMOND = BLOCK_ENTITIES.register("safety_diamond",
            () -> BlockEntityType.Builder.of(SafetyDiamondEntity::new, InitBlocks.SAFETY_DIAMOND.get()).build(null));
}
