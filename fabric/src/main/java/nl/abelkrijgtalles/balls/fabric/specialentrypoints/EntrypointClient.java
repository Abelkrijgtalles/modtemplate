package nl.abelkrijgtalles.balls.fabric.specialentrypoints;

import net.fabricmc.api.ClientModInitializer;
import nl.abelkrijgtalles.balls.InitBallsClient;

public class EntrypointClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        InitBallsClient.init();
    }
}
