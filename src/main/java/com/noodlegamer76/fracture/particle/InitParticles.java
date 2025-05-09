package com.noodlegamer76.fracture.particle;

import com.noodlegamer76.fracture.FractureMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class InitParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister.create(BuiltInRegistries.PARTICLE_TYPE, FractureMod.MODID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> TAINTED_SPORES = PARTICLE_TYPES.register("tainted_spores",
            () -> new SimpleParticleType(false));
}
