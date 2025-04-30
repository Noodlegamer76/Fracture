package com.noodlegamer76.fracture;

import com.mojang.logging.LogUtils;
import com.noodlegamer76.fracture.block.InitBlocks;
import com.noodlegamer76.fracture.client.renderer.tile.NihilPortalRenderer;
import com.noodlegamer76.fracture.creativetab.InitCreativeTabs;
import com.noodlegamer76.fracture.events.RegisterPayloads;
import com.noodlegamer76.fracture.events.RegisterShaders;
import com.noodlegamer76.fracture.events.RenderLevelEvents;
import com.noodlegamer76.fracture.item.InitItems;
import com.noodlegamer76.fracture.tile.InitBlockEntities;
import com.noodlegamer76.fracture.worldgen.feature.InitFeatures;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.IModBusEvent;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

@Mod(FractureMod.MODID)
public class FractureMod
{
    public static final String MODID = "fracture";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FractureMod(IEventBus modEventBus, ModContainer modContainer)
    {
        modEventBus.addListener(this::commonSetup);
        modEventBus.addListener(RegisterShaders::registerShaders);
        modEventBus.addListener(RegisterPayloads::register);
        NeoForge.EVENT_BUS.register(RenderLevelEvents.class);

        InitBlocks.BLOCKS.register(modEventBus);
        InitItems.ITEMS.register(modEventBus);
        InitCreativeTabs.CREATIVE_MODE_TABS.register(modEventBus);
        InitBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        InitFeatures.FEATURES.register(modEventBus);

        //doing this so java loads the fields
        //new Rooms();
        //for(Room room : RoomRegistry.getRooms()) {
        //    room.init();
        //}

        NeoForge.EVENT_BUS.register(this);

        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {

    }

    @EventBusSubscriber(modid = MODID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }

        @SubscribeEvent
        public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
            event.registerBlockEntityRenderer(InitBlockEntities.NIHIL_PORTAL.get(), NihilPortalRenderer::new);
        }
    }
}
