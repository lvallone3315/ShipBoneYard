/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import static shipboneyard.PlayerInput.InputType;
import static shipboneyard.PlayerInput.Direction;

/**
 *
 * @author leev
 */
public class GateHouse0 extends Room {
    
    GateHouse0(int roomNumber) {
        super(roomNumber);
    }
    public Room gateHouse0(PlayerInput playerInput) {
        if (playerInput.getInputType() == PlayerInput.InputType.DIRECTION) {
            int newRoom = moveDirection(playerInput.getDirection());
            LogToConsole.log("new room = " + newRoom);
            return (new Room(newRoom));
        }
        return (new Room(0));
    }
}
