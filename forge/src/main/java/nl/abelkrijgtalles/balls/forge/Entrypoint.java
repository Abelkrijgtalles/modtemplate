package nl.abelkrijgtalles.balls.forge;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
#if MC_1_19_4 || MC_1_19_3
import net.minecraftforge.event.CreativeModeTabEvent;
#elif MC_1_19_2 || MC_1_18_2
#else
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
#endif
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import nl.abelkrijgtalles.balls.Balls;
import nl.abelkrijgtalles.balls.forge.specialentrypoints.EntrypointClient;
import nl.abelkrijgtalles.balls.forge.specialentrypoints.EntrypointDedicatedServer;
import nl.abelkrijgtalles.balls.forge.util.multiloader.ForgeItemHandler;

@Mod("balls")
public class Entrypoint {
    public Entrypoint() {
        // Code here will run on both physical client and server.
        // Client classes may or may not be available - be careful!
        Balls.LOGGER.info("Running on Forge.");

        // some forge startup stuff
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);



        Balls.init();

        ForgeItemHandler forgeItemHandler = new ForgeItemHandler();
        forgeItemHandler.register(modEventBus);


        // more forge stuff
        #if MC_1_18_2 || MC_1_19_2
        #else
        modEventBus.addListener(this::addCreative);
        #endif
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, builder.build());

        // Initialize client and dedicated server entrypoints.
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> EntrypointClient::init);
        DistExecutor.safeRunWhenOn(Dist.DEDICATED_SERVER, () -> EntrypointDedicatedServer::init);
    }

    #if MC_1_18_2 || MC_1_19_2
    #else

    private void addCreative(
            #if MC_1_19_4 || MC_1_19_3
            CreativeModeTabEvent.BuildContents event
            #else
            BuildCreativeModeTabContentsEvent event
            #endif
                    ) {

        ForgeItemHandler.itemInventoryGroupMap.forEach((itemRegistryObject, creativeModeTabResourceKey) -> {
            if (
                    #if MC_1_19_4 || MC_1_19_3
                    event.getTab()
                    #else
                    event.getTabKey()
                    #endif
                    == creativeModeTabResourceKey) {
                event.accept(itemRegistryObject);
            }
        });

    }

    #endif

    private void commonSetup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = Balls.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {

        }
    }

}