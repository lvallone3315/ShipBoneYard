/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lvall
 */
public class ShipBoneYardItemTest {
    
    public ShipBoneYardItemTest() {
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
        assertTrue("Axe is NOT valid",item.isItemValid("axe"));
        System.out.println("Verifying mace is not a valid item ...");
        assertFalse("Mace IS valid", item.isItemValid("mace"));
    }
    
    @Test
    public void testItemMapping() {
        Item item = new Item();
        
        System.out.println("convert axe string to Enum & then back to String");
        assertEquals("Axe != BATTLE_AXE enum", item.itemStringToEnum("axe"), Item.ItemType.BATTLE_AXE);
        assertEquals("BATTLE_AXE enum != axe", item.itemEnumToString(Item.ItemType.BATTLE_AXE), "axe");
        
        System.out.println("verify null is returned if an invalid item, e.g. lance, passed");
        assertNull("Lance is accpepted as valid item", item.itemStringToEnum("lance"));
    }
}
