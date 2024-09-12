package nl.abelkrijgtalles.balls.forge;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.loading.FMLEnvironment;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;
import nl.abelkrijgtalles.balls.Balls;
import nl.abelkrijgtalles.balls.forge.specialentrypoints.EntrypointClient;
import nl.abelkrijgtalles.balls.forge.specialentrypoints.EntrypointDedicatedServer;
import nl.abelkrijgtalles.balls.forge.util.multiloader.NeoForgeItemHandler;

@Mod("balls")
public class Entrypoint {
    public Entrypoint(IEventBus modEventBus, ModContainer modContainer) {
        // Code here will run on both physical client and server.
        // Client classes may or may not be available - be careful!
        Balls.LOGGER.info("Running on Forge.");

        // some forge startup stuff
        modEventBus.addListener(this::commonSetup);
        NeoForge.EVENT_BUS.register(this);



        Balls.init();

        NeoForgeItemHandler neoForgeItemHandler = new NeoForgeItemHandler();
        neoForgeItemHandler.register(modEventBus);


        // more forge stuff
        modEventBus.addListener(this::addCreative);
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        modContainer.registerConfig(ModConfig.Type.COMMON, builder.build());

        // Initialize client and dedicated server entrypoints.
        if (FMLEnvironment.dist == Dist.CLIENT) {
            EntrypointClient.init();
        } else {
            EntrypointDedicatedServer.init();
        }
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {

        NeoForgeItemHandler.itemInventoryGroupMap.forEach((itemRegistryObject, creativeModeTabResourceKey) -> {
            if (event.getTabKey() == creativeModeTabResourceKey) {
                event.accept(itemRegistryObject);
            }
        });

    }

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = Balls.MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }

}