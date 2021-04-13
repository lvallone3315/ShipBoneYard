package shipboneyard;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lvall
 */
public class ShipBoneYardInventoryTest {
    
    public ShipBoneYardInventoryTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // Test Item Methods
    // 1st test is checking if item valid - ie in list
    // 2nd test is converting between string & enum
    @Test
    public void testItemValidity() {
        Item item = new Item();
        System.out.println("testing if axe is a valid item ...");
        assertEquals(item.isItemValid("axe"), true);
        System.out.println("Verifying mace is not a valid item ...");
        assertEquals(item.isItemValid("mace"), false);
    }
    
    @Test
    public void testItemMapping() {
        Item item = new Item();
        
        System.out.println("convert axe string to Enum & then back to String");
        assertEquals(item.itemStringToEnum("axe"), Item.ItemType.BATTLE_AXE);
        assertEquals(item.itemEnumToString(Item.ItemType.BATTLE_AXE), "axe");
        
        System.out.println("verify null is returned if an invalid item, e.g. lance, passed");
        assertEquals(item.itemStringToEnum("lance"), null);
    }
    
    // Test displaying inventory, adding axe, displaying inventory,
    //   removing axe, displaying inventory
    @Test
    public void testInventorySunnyDay() {
        Inventory inventory = new Inventory();
        
        System.out.println("Verify initial backpack is empty" + "***" + inventory.inventory()+"***");
        assertEquals(inventory.inventory(), "");
        assertEquals(inventory.takeItem("axe"), true);
        System.out.println("Verify backpack now has an axe" + "***" + inventory.inventory()+"***");
        assertEquals(inventory.inventory(), "axe");
        assertEquals(inventory.dropItem("axe"), true);
        System.out.println("Verify backpack is again empty" + "***" + inventory.inventory()+"***");
    }
    
    // Test rainy day scenarios
    //   adding an invalid item - e.g. lance
    //   dropping an item not in the backpack
    
    @Test
    public void testInventoryRainyDay() {
        Inventory inventory = new Inventory();
        
        System.out.println("*** Rainy Day Scenarios ***");
        System.out.println("Adding an invalid item (e.g. Lance) to backpack - passed test case = null return & no change in inventory");
        // try to add a lance - save the before inventory - confirm after inventory = before inventory
        String tempInventory = inventory.inventory();
        assertEquals(inventory.takeItem("lance"), false);
        assertEquals(inventory.inventory(), tempInventory);
        
        System.out.println("Removing a valid item (e.g. axe) that isn't in the backpack");
        tempInventory = inventory.inventory();
        assertEquals(inventory.dropItem("axe"), false);
        assertEquals(inventory.inventory(), tempInventory);
    }

}
