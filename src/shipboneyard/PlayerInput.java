/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import static shipboneyard.PlayerInput.InputType.DIRECTION;
import static shipboneyard.ShipBoneYardUI.printConsole;

/**
 * PlayerInput class
 *   holds parsed output of player input
 *      assuming instantiate once per player (single persistent object)
 * 
 *   inputType = type of command (enum DIRECTION, VERB, OTHER)
 *   playerText = string w/ standardized input from user
 * 
 *   Possible changes:
 *     for DIRECTION - code in one letter direction & add to separate field
 *       possibly use enum for direction as well
 *     for VERB - clean up verb, and store in separate field
 * 
 *   standard methods supported
 *     setPlayerText(), getPlayerText()
 *     setInputType(), getInputType()
 *     printPlayerInput()
 *
 * @author leev
 */
public class PlayerInput {
    public enum InputType {DIRECTION,VERB,OTHER};
    public enum Direction {N, S, E, W, NA};
    private InputType inputType;
    private Direction direction;
    private String playerText;  // player text
        
    public void setPlayerText(String text) {
        this.playerText = text;
    }
    
    public String getPlayerText() {
        return this.playerText;
    }
    public void setInputType (InputType type) {
        this.inputType = type;
    }
    public InputType getInputType () {
        return this.inputType;
    }
    public void setDirection (Direction dir) {
        this.direction = dir;
    }
    public Direction getDirection () {
        return this.direction;
    }
    public String toString() {
        String printString;
        
        switch (this.inputType) {
            case DIRECTION:
            case VERB:
            case OTHER:
                printString = " " + this.inputType;
                break;
            default:
                printString = "unknown";
        }
        if (this.inputType == DIRECTION) {
            switch (this.direction) {
            case N:
            case S:
            case E:
            case W:
                printString += " " + this.direction;
                break;
            default:
                printString += "ERROR: invalid direction" + this.direction;
            }
                
        } // end if DIRECTION
        printString += "\tplayerText: " + this.playerText + "\n";
        return(printString);
    }
    public void printPlayerInput() {
        printConsole(this.toString());
    }
}