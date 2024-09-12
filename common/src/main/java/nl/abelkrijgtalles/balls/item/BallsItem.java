package nl.abelkrijgtalles.balls.item;

import nl.abelkrijgtalles.balls.util.multiloader.item.InventoryGroups;
import nl.abelkrijgtalles.balls.util.multiloader.item.ItemPropertyType;

public record BallsItem(InventoryGroups homemadeGroup, String name, ItemPropertyType itemProperty) {

}
