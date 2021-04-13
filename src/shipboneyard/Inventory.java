/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import java.util.ArrayList;

/**
 *
 * @author lvall
 */
public class Inventory {
    private ArrayList<Item.ItemType> inventory = new ArrayList<Item.ItemType>();
    private Item itemList = new Item();
    
    Inventory() {
        // initial inventory is null - so nothing to do here
    }
    
    public Boolean takeItem(String itemString) {
        if (itemList.isItemValid(itemString)) {
            if (!inventory.contains(itemList.itemStringToEnum(itemString))) {
                inventory.add(itemList.itemStringToEnum(itemString));
                return(true);
            }
        } // item already in inventory or item not valid
        return(false);
    }
    
    public Boolean dropItem(String itemString) {
        if (itemList.isItemValid(itemString)) {
            if (inventory.contains(itemList.itemStringToEnum(itemString))) {
                inventory.remove(itemList.itemStringToEnum(itemString));
                return(true); 
            }
        }  // item not valid or item not in inventory
        return(false);
    }
    
    public String inventory() {
        String inventoryString = "";
        for (Item.ItemType item : inventory) {
            inventoryString += itemList.itemEnumToString(item);
        }
        return (inventoryString);
    }
}
