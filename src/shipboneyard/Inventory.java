package shipboneyard;

import java.util.ArrayList;

/**
 * Generic inventory class - can be used as a player inventory, game inventory, etc.
 * 
 * Initial implementation supports adding items (takeItem)
 *   removing items (dropItem)
 *   & retrieving text version of inventory (getInventory)
 * 
 * ToDo: getInventory() should put newlines between items - not currently done
 * @author lvall
 */
public class Inventory {
    
    // inventory is current items in backpack, etc. - starts empty
    // itemList is the list of items known to the game in enum & String versions
    private ArrayList<Item.ItemType> inventory = new ArrayList<Item.ItemType>();
    private Item itemList = new Item();
    
    Inventory() {
        // initial inventory is null - so nothing to do here
    }
    
    /**
     * 
     * @param itemString - text version of item to be added to backpack
     * @return - true = item added, otherwise false
     * 
     * validates item is known to game & not already in backpack - ie no dups
     */
    public Boolean takeItem(String itemString) {
        if (itemList.isItemValid(itemString)) {
            if (!inventory.contains(itemList.itemStringToEnum(itemString))) {
                inventory.add(itemList.itemStringToEnum(itemString));
                return(true);
            }
        } // item already in inventory or item not valid
        return(false);
    }
    
    /**
     * 
     * @param itemString - text version of item to be removed from backpack
     * @return - true if item removed, otherwise false
     * 
     * validates item is known to game & in backpack - ie can't drop what you don't have
     */
    public Boolean dropItem(String itemString) {
        if (itemList.isItemValid(itemString)) {
            if (inventory.contains(itemList.itemStringToEnum(itemString))) {
                inventory.remove(itemList.itemStringToEnum(itemString));
                return(true); 
            }
        }  // item not valid or item not in inventory
        return(false);
    }
    
    /**
     * Returns a text version of all items in the inventory
     * @return 
     */
    public String getInventory() {
        String inventoryString = "";
        for (Item.ItemType item : inventory) {
            inventoryString += itemList.itemEnumToString(item)+"\n";
        }
        return (inventoryString);
    }
}
