package nl.abelkrijgtalles.balls.item;

import java.util.ArrayList;
import java.util.List;
import nl.abelkrijgtalles.balls.Balls;
import nl.abelkrijgtalles.balls.util.multiloader.item.InventoryGroups;
import nl.abelkrijgtalles.balls.util.multiloader.item.ItemPropertyType;

public class ItemRegisterer {

    public static List<BallsItem> items = new ArrayList<>();

    public void registerModItems() {

        Balls.LOGGER.info("Registering items.");
        items.add(new BallsItem(InventoryGroups.FOOD_AND_DRINKS, "frikandel_broodje", ItemPropertyType.DEFAULT));

    }

}
