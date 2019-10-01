/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

// PlayerInput enums
import static shipboneyard.PlayerInput.InputType;
import static shipboneyard.PlayerInput.Direction;

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
 * Future:
 *    Change from switch statement to final structure (or populate from file)
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
        String normalizedString = string;
        // remove leading and trailing whitespace (string.trim)
        // standardize string to lower case
        normalizedString = normalizedString.trim();
        normalizedString = normalizedString.toLowerCase();
        LogToConsole.log(string + " ->" + normalizedString + "\n");
        
        // check for direction
        switch (normalizedString) {
            case "north":
            case "n":
                playerInput.setInputType(InputType.DIRECTION);
                playerInput.setDirection(Direction.N);
                break;
            case "south":
            case "s":
                playerInput.setInputType(InputType.DIRECTION);
                playerInput.setDirection(Direction.S);
                break;
            case "east":
            case "e":
                playerInput.setInputType(InputType.DIRECTION);
                playerInput.setDirection(Direction.E);
                break;
            case "west":
            case "w":
                playerInput.setInputType(InputType.DIRECTION);
                playerInput.setDirection(Direction.W);
                break;
            default:
                playerInput.setInputType(InputType.OTHER);
                playerInput.setDirection(Direction.NA);
        }
        playerInput.setPlayerText(normalizedString);
        playerInput.printPlayerInput(); // prints to console, should use log
        return playerInput;
    }
}