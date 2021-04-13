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

    
    // Test displaying inventory, adding axe, displaying inventory,
    //   removing axe, displaying inventory
    @Test
    public void testInventorySunnyDay() {
        Inventory inventory = new Inventory();
        
        System.out.println("\n*** Testing Sunny Day Scenarios ***");
        System.out.println("Verify initial backpack is empty" + "***" + inventory.getInventory()+"***");
        assertEquals("Initial inventory is NOT empty", inventory.getInventory(), "");
        assertTrue("axe not added to inventory", inventory.takeItem("axe"));
        System.out.println("Verify backpack now has an axe" + "***" + inventory.getInventory()+"***");
        assertEquals("axe is NOT in the inventory after adding", inventory.getInventory(), "axe\n");
        assertTrue("Not able to drop the axe", inventory.dropItem("axe"));
        System.out.println("Verify backpack is again empty" + "***" + inventory.getInventory()+"***");
        assertEquals("Inventory is NOT empty, but should be, after removing axe", inventory.getInventory(), "");
    }
    
    // Test rainy day scenarios
    //   adding an invalid item - e.g. lance
    //   dropping an item not in the backpack
    
    @Test
    public void testInventoryRainyDay() {
        Inventory inventory = new Inventory();
        
        System.out.println("\n*** Testing Rainy Day Scenarios ***");
        System.out.println("Adding an invalid item (e.g. Lance) to backpack - passed test case = null return & no change in inventory");
        // try to add a lance - save the before inventory - confirm after inventory = before inventory
        String tempInventory = inventory.getInventory();
        assertFalse("Able to add lance to inventory", inventory.takeItem("lance"));
        assertEquals(inventory.getInventory(), tempInventory);
        
        System.out.println("Removing a valid item (e.g. axe) that isn't in the backpack");
        tempInventory = inventory.getInventory();
        assertFalse("Able to Remove Axe from empty inventory", inventory.dropItem("axe"));
        assertEquals("After NOT removing item, inventory changed", inventory.getInventory(), tempInventory);
    }

}
