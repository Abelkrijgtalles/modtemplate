package nl.abelkrijgtalles.balls.neoforge.util.multiloader;

import net.minecraft.world.item.CreativeModeTab;
import java.util.HashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import nl.abelkrijgtalles.balls.Balls;
import nl.abelkrijgtalles.balls.item.BallsItem;
import nl.abelkrijgtalles.balls.item.ItemRegisterer;
import nl.abelkrijgtalles.balls.util.multiloader.item.InventoryGroups;
import nl.abelkrijgtalles.balls.util.multiloader.item.ItemPropertyType;

public class NeoForgeItemHandler {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Balls.MOD_ID);
    public static HashMap<DeferredItem<Item>, ResourceKey<CreativeModeTab>> itemInventoryGroupMap = new HashMap<>();

    public void register(IEventBus eventBus) {

        for (BallsItem item : ItemRegisterer.items) {
            DeferredItem<Item> registeredItem = ITEMS.register(item.name(), () -> itemFromItemPropertyType(item.itemProperty()));
            itemInventoryGroupMap.put(registeredItem, homemadeInventoryGroupToMojangStandard(item.homemadeGroup()));
        }

        ITEMS.register(eventBus);
    }

    private static ResourceKey<CreativeModeTab> homemadeInventoryGroupToMojangStandard(InventoryGroups group) {
        switch (group) {
            case COMBAT -> {
                return CreativeModeTabs.COMBAT;
            }
            case HOTBAR -> {
                return CreativeModeTabs.HOTBAR;
            }
            case SEARCH -> {
                return CreativeModeTabs.SEARCH;
            }
            case INVENTORY -> {
                return CreativeModeTabs.INVENTORY;
            }
            case OP_BLOCKS -> {
                return CreativeModeTabs.OP_BLOCKS;
            }
            case SPAWN_EGGS -> {
                return CreativeModeTabs.SPAWN_EGGS;
            }
            case INGREDIENTS -> {
                return CreativeModeTabs.INGREDIENTS;
            }
            case COLORED_BLOCKS -> {
                return CreativeModeTabs.COLORED_BLOCKS;
            }
            case NATURAL_BLOCKS -> {
                return CreativeModeTabs.NATURAL_BLOCKS;
            }
            case BUILDING_BLOCKS -> {
                return CreativeModeTabs.BUILDING_BLOCKS;
            }
            case FOOD_AND_DRINKS -> {
                return CreativeModeTabs.FOOD_AND_DRINKS;
            }
            case REDSTONE_BLOCKS -> {
                return CreativeModeTabs.REDSTONE_BLOCKS;
            }
            case FUNCTIONAL_BLOCKS -> {
                return CreativeModeTabs.FUNCTIONAL_BLOCKS;
            }
            case TOOLS_AND_UTILITIES -> {
                return CreativeModeTabs.TOOLS_AND_UTILITIES;
            }
        }
        Balls.LOGGER.error("I've somehow passed an homemade inventory group that's not part of the enum. Returning null.");
        return null;

    }

    private static Item itemFromItemPropertyType(ItemPropertyType itemPropertyType) {

        switch (itemPropertyType) {
            case DEFAULT -> {
                return new Item(new Item.Properties());
            }
        }
        Balls.LOGGER.error("I've somehow passed an item property type that's not part of the enum. Returning null.");
        return null;

    }

}
