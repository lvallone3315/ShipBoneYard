/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import static shipboneyard.PlayerInput.InputType;

/**
 * ParseInput Class
 *   returns -> structure w/ move type, string (standardized format)
 *    move type in (MOVE, ACTION (e.g. pickup, drop), OTHER (e.g. tbd))
 *    string in standardized format (lower case, leading/trailing white space removed)
 * 
 *   initial stub - constructor only - print to console
 *   next - return structure with MOVE & actual string 
 * 
 * Issues:
 *   need to create structure & verify can access in main class, incl ENUM
 *   
 * @author leev
 */
public class ParseInput {
    PlayerInput playerInput = new PlayerInput();
    
    // null constructor
    ParseInput() {
        ;        
    }
    
    PlayerInput parseInput(String string) {
        if (string.equals("N")) {
            playerInput.setInputType(InputType.DIRECTION);
        }
        else
            playerInput.setInputType(InputType.OTHER);
        playerInput.setPlayerText(string);
        playerInput.printPlayerInput();
        return playerInput;
    }
}