package nl.abelkrijgtalles.balls;

import nl.abelkrijgtalles.balls.item.ItemRegisterer;
import nl.abelkrijgtalles.balls.util.ModLoaderStatistics;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Balls {
    public static final String MOD_ID = "balls";
    public static Logger LOGGER = LogManager.getLogger(MOD_ID);

    public static void init() {

        ModLoaderStatistics.printStats();

        ItemRegisterer itemRegisterer = new ItemRegisterer();
        itemRegisterer.registerModItems();

    }
}
