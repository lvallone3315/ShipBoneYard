/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import java.util.EnumMap;

/**
 *
 * @author lvall
 */

public class Item {
    public enum ItemType {BATTLE_AXE};
    EnumMap<ItemType, String> itemMap
            = new EnumMap<>(ItemType.class);

    Item() {
        itemMap.put(ItemType.BATTLE_AXE, "axe");

        // for (ItemType item : ItemType.values()) {
    }
    
    /**
     * Boolean isItemValid (String)
     * @parm string representation of item being queried
     * @return True if item valid, False if item invalid
     */

    public Boolean isItemValid (String itemString) {
        for (EnumMap.Entry<ItemType,String> entry : itemMap.entrySet()) {
            if (itemString.equals(entry.getValue())) {
                return(true);
            }
        }
        return(false);
    }
    
    
    /**
     * itemStringToEnum maps string argument to Enumeration representation
     * @param itemString - string to be searched
     * @return Enumeration of item or null if item not found
     * 
     * Note: assumes isItemValid() called 1st, so should never return null
     * ToDo: throw exception if item string not found
     */
  
    public ItemType itemStringToEnum(String itemString) {

        for (EnumMap.Entry<ItemType,String> entry : itemMap.entrySet()) {
            if (itemString.equals(entry.getValue())) {
                return(entry.getKey());
            }
        }
        return (null);
    }
    
    /**
     * itemEnumToString maps item Enum to string representation
     * @param item - enum to be searched
     * @return String representation of item or null if item not found
     * 
     * Note: assumes isItemValid() called 1st, so should never return null
     * ToDo: throw exception if item string not found
     */
    public String itemEnumToString(ItemType item) {

        for (EnumMap.Entry<ItemType,String> entry : itemMap.entrySet()) {
            if (entry.getKey() == item) {
                return(entry.getValue());
            }
        }
        return (null);
    }
    
}
