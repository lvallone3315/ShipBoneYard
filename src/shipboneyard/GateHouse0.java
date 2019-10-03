/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import static shipboneyard.ShipBoneYardUI.printConsole;
import static shipboneyard.PlayerInput.InputType;
import static shipboneyard.PlayerInput.Direction;

/**
 * GateHouse0 - derived room class
 *   implements logic for gatehouse (starting) location
 * 
 * @args roomNumber = instance variable (declared in base class)
 *   tracks room number for this location
 * 
 * moveDirection() defined in baseclass & uses initial room definitions
 *
 * @author leev
 */
public class GateHouse0 extends Room {
    
    GateHouse0(int roomNum) {
        super(roomNum);
    }

    @Override
    public Room processUserRequest(PlayerInput playerInput) {
        printConsole("GateHouse0: Input " + playerInput.toString());
        setRoomVisited(true);
        if (playerInput.getInputType() == PlayerInput.InputType.DIRECTION) {
            int newRoom = moveDirection(roomNumber, playerInput.getDirection());
            LogToConsole.log("GateHouse0: new room = " + newRoom + "\n");
            return (roomObjects[newRoom]);
        }
        return (roomObjects[roomNumber]);
    }
}
