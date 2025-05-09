package com.noodlegamer76.fracture.client.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.phys.Vec3;

public class BiomeBlendUtils {

    public static float getBiomePresenceFactor(ClientLevel level, Vec3 cameraPos, Biome targetBiome, int radius, int step) {
        BlockPos center = new BlockPos((int) cameraPos.x(), (int) cameraPos.y, (int) cameraPos.z);
        int matchCount = 0;
        int totalSamples = 0;

        for (int dx = -radius; dx <= radius; dx += step) {
            for (int dz = -radius; dz <= radius; dz += step) {
                for (int dy = -radius; dy <= radius; dy += step) {
                    BlockPos samplePos = center.offset(dx, 0, dz);
                    Biome sampleBiome = level.getBiome(samplePos).value();
                    totalSamples++;

                    if (sampleBiome == targetBiome) {
                        matchCount++;
                    }
                }
            }
        }

        return (float) matchCount / totalSamples;
    }

    public static float getBiomePresenceFactor(ClientLevel level, Vec3 cameraPos, Biome targetBiome) {
        return getBiomePresenceFactor(level, cameraPos, targetBiome, 2, 1);
    }

    public static Biome getTargetBiome(ResourceLocation biomeId) {
        Registry<Biome> biomeRegistry = Minecraft.getInstance().level.registryAccess().registryOrThrow(Registries.BIOME);
        return biomeRegistry.get(biomeId);
    }
}
