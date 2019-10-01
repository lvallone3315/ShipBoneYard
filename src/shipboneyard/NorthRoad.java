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
 *
 * @author leev
 */
public class NorthRoad extends Room {
    NorthRoad(int roomNum) {
        super(roomNum);
    }
      
    @Override
    public Room processUserRequest(PlayerInput playerInput) {
        printConsole("North Road " + playerInput.toString());
        if (playerInput.getInputType() == PlayerInput.InputType.DIRECTION) {
            int newRoom = moveDirection(roomNumber, playerInput.getDirection());
            LogToConsole.log("North Road: new room = " + newRoom + "\n");
            return (roomObjects[newRoom]);
        }
        return (roomObjects[roomNumber]);
    }
}
