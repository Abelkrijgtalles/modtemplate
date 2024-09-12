package nl.abelkrijgtalles.balls.forge.util.multiloader;

import net.minecraft.world.item.CreativeModeTab;
#if MC_1_18_2 || MC_1_19_2
#else
import java.util.HashMap;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTabs;
#endif
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import nl.abelkrijgtalles.balls.Balls;
import nl.abelkrijgtalles.balls.item.BallsItem;
import nl.abelkrijgtalles.balls.item.ItemRegisterer;
import nl.abelkrijgtalles.balls.util.multiloader.item.InventoryGroups;
import nl.abelkrijgtalles.balls.util.multiloader.item.ItemPropertyType;

public class ForgeItemHandler{

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, Balls.MOD_ID);

    #if MC_1_19_4 || MC_1_19_3
    public static HashMap<RegistryObject<Item>, CreativeModeTab> itemInventoryGroupMap = new HashMap<>();
    #elif MC_1_19_2 || MC_1_18_2
    #else
    public static HashMap<RegistryObject<Item>, ResourceKey<CreativeModeTab>> itemInventoryGroupMap = new HashMap<>();
    #endif

    public void register(IEventBus eventBus) {

        for (BallsItem item : ItemRegisterer.items) {
            RegistryObject<Item> registeredItem = ITEMS.register(item.name(), () -> itemFromItemPropertyType(item.itemProperty()
            #if MC_1_19_2 || MC_1_18_2
            , homemadeInventoryGroupToMojangStandard(item.homemadeGroup())
            #endif));
            #if MC_1_18_2 || MC_1_19_2
            #else
            itemInventoryGroupMap.put(registeredItem, homemadeInventoryGroupToMojangStandard(item.homemadeGroup()));
            #endif
        }

        ITEMS.register(eventBus);
    }

    #if MC_1_19_4 || MC_1_19_3 || MC_1_19_2 || MC_1_18_2
    private static CreativeModeTab homemadeInventoryGroupToMojangStandard(InventoryGroups group) {
    #else
    private static ResourceKey<CreativeModeTab> homemadeInventoryGroupToMojangStandard(InventoryGroups group) {
    #endif
        switch (group) {
            case COMBAT -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_COMBAT;
                #else
                return CreativeModeTabs.COMBAT;
                #endif
            }
            case HOTBAR -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_HOTBAR;
                #else
                return CreativeModeTabs.HOTBAR;
                #endif
            }
            case SEARCH -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_SEARCH;
                #else
                return CreativeModeTabs.SEARCH;
                #endif
            }
            case INVENTORY -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_INVENTORY;
                #else
                return CreativeModeTabs.INVENTORY;
                #endif
            }
            case OP_BLOCKS -> {
                #if MC_1_19_2 || MC_1_18_2
                Balls.LOGGER.error("The OP_BLOCKS tab isn't supported on 1.19.2 and below.");
                return null;
                #else
                return CreativeModeTabs.OP_BLOCKS;
                #endif
            }
            case SPAWN_EGGS -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_MISC;
                #else
                return CreativeModeTabs.SPAWN_EGGS;
                #endif
            }
            case INGREDIENTS -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_MISC;
                #else
                return CreativeModeTabs.INGREDIENTS;
                #endif
            }
            case COLORED_BLOCKS -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_DECORATIONS;
                #else
                return CreativeModeTabs.COLORED_BLOCKS;
                #endif
            }
            case NATURAL_BLOCKS -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_BUILDING_BLOCKS;
                #else
                return CreativeModeTabs.NATURAL_BLOCKS;
                #endif
            }
            case BUILDING_BLOCKS -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_BUILDING_BLOCKS;
                #else
                return CreativeModeTabs.BUILDING_BLOCKS;
                #endif
            }
            case FOOD_AND_DRINKS -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_FOOD;
                #else
                return CreativeModeTabs.FOOD_AND_DRINKS;
                #endif
            }
            case REDSTONE_BLOCKS -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_REDSTONE;
                #else
                return CreativeModeTabs.REDSTONE_BLOCKS;
                #endif
            }
            case FUNCTIONAL_BLOCKS -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_MISC;
                #else
                return CreativeModeTabs.FUNCTIONAL_BLOCKS;
                #endif
            }
            case TOOLS_AND_UTILITIES -> {
                #if MC_1_19_2 || MC_1_18_2
                return CreativeModeTab.TAB_TOOLS;
                #else
                return CreativeModeTabs.TOOLS_AND_UTILITIES;
                #endif
            }
        }
        Balls.LOGGER.error("I've somehow passed an homemade inventory group that's not part of the enum. Returning null.");
        return null;

    }

    #if MC_1_19_2 || MC_1_18_2
    private static Item itemFromItemPropertyType(ItemPropertyType itemPropertyType, CreativeModeTab creativeModeTab) {
    #else
    private static Item itemFromItemPropertyType(ItemPropertyType itemPropertyType) {
    #endif

        switch (itemPropertyType) {
            case DEFAULT -> {
                #if MC_1_19_2 || MC_1_18_2
                return new Item(new Item.Properties().tab(creativeModeTab));
                #else
                return new Item(new Item.Properties());
                #endif
            }
        }
        Balls.LOGGER.error("I've somehow passed an item property type that's not part of the enum. Returning null.");
        return null;

    }

}
