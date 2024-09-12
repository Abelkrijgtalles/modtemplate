package nl.abelkrijgtalles.balls.fabric;

import net.fabricmc.api.ModInitializer;
import nl.abelkrijgtalles.balls.Balls;
import nl.abelkrijgtalles.balls.fabric.util.multiloader.FabricItemHandler;

public class Entrypoint implements ModInitializer {
    @Override
    public void onInitialize() {
        // Code here will run on both physical client and server.
        // Client classes may or may not be available - be careful!
        Balls.LOGGER.info("Running on Fabric.");
        Balls.init();

        FabricItemHandler.register();
    }
}
