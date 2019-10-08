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
 * ToDo:
 *   add exit type
 *   either accept verbs such as move, go, etc. or search for directions in string
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
        String actionString = string;
        Boolean error = false;
        // remove leading and trailing whitespace (string.trim)
        // normalize string to lower case
        //    save normalized string to playerInput structure
        normalizedString = normalizedString.trim();
        normalizedString = normalizedString.toLowerCase();
        playerInput.setPlayerText(normalizedString);
        LogToConsole.log("Parser: " + string + " ->" + normalizedString + "\n");
        
        // break into words separated by white space
        String[] stringWords = normalizedString.split("\\s+",2);
        
        // if two (or more words), parse first word
        //   
        switch (stringWords[0]) {
            case "go":
            case "move":
            case "run":
                if (stringWords.length < 2) {
                    playerInput.setInputType(InputType.DIRECTIONERR);
                    playerInput.setDirection(Direction.NA);
                    error = true;
                }
                else {
                    actionString = stringWords[1];
                }
                break;
            default:
                actionString = stringWords[0];
        }

        // check for direction
        if (!error) switch (actionString) {
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
            case "exit":
            case "quit":
            case "done":
            case "bye":
                playerInput.setInputType(InputType.EXIT);
                playerInput.setDirection(Direction.W);
                break;
            default:
                playerInput.setInputType(InputType.OTHER);
                playerInput.setDirection(Direction.NA);
        }

        playerInput.printPlayerInput(); // prints to console, should use log
        return playerInput;
    }
    
}