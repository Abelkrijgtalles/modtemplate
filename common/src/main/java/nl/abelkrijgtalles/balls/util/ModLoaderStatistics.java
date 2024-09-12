package nl.abelkrijgtalles.balls.util;

import nl.abelkrijgtalles.balls.Balls;

public class ModLoaderStatistics {

    public static void printStats() {

        #if MC_1_18_2
        Balls.LOGGER.info("Current version is 1.18.2.");
        #elif MC_1_19_2
        Balls.LOGGER.info("Current version is 1.19.2.");
        #elif MC_1_19_3
        Balls.LOGGER.info("Current version is 1.19.3.");
        #elif MC_1_19_4
        Balls.LOGGER.info("Current version is 1.19.4.");
        #elif MC_1_20_1
        Balls.LOGGER.info("Current version is 1.20.1.");
        #elif MC_1_20_2
        Balls.LOGGER.info("Current version is 1.20.2.");
        #elif MC_1_20_3
        Balls.LOGGER.info("Current version is 1.20.3.");
        #elif MC_1_20_4
        Balls.LOGGER.info("Current version is 1.20.4.");
        #elif MC_1_20_6
        Balls.LOGGER.info("Current version is 1.20.6.");
        #elif MC_1_21
        Balls.LOGGER.info("Current version is 1.21.");
        #endif

    }

}
