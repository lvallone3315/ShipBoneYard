package shipboneyard;

import java.util.EnumMap;

/**
 * Item class
 *   Maintains a list of valid items in the game, and
 *   Maps internal item representation to external string (for displaying to user)
 * <P>
 * Game logic expected to use internal representation for all logic except user displays
 * User input for items is checked against String versions stored in map
 * 
 * ToDo: future enhancement - allow different input versions to map to single item
 *   e.g. "battle axe" & "axe" both map to BATTLE_AXE
 * 
 * @author lvall
 */

public class Item {
    
    // ItemType maintains internal rep of all allowable items
    //   New items in game must be added to this enum
    // itemMap[] maps internal item rep to external display String
    //
    // To add new items to game:
    //   create new ENUM and append to ItemType
    //   define text version, e.g. "xyz", create map entry - enum, text
    public enum ItemType {BATTLE_AXE, MACE};
    EnumMap<ItemType, String> itemMap
            = new EnumMap<>(ItemType.class);

    // constructor - add all allowable items & String versions to map
    Item() {
        itemMap.put(ItemType.BATTLE_AXE, "axe");
        itemMap.put(ItemType.MACE, "mace");
    }
    
    /**
     * Boolean isItemValid (String)
     * @parm itemString - string representation of item being checked
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
