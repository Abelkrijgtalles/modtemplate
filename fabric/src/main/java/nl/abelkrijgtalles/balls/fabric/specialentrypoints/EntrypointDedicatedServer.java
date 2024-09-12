package nl.abelkrijgtalles.balls.fabric.specialentrypoints;

import net.fabricmc.api.DedicatedServerModInitializer;
import nl.abelkrijgtalles.balls.InitBallsDedicatedServer;

public class EntrypointDedicatedServer implements DedicatedServerModInitializer {
    @Override
    public void onInitializeServer() {
        InitBallsDedicatedServer.init();
    }
}
