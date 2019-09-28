/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

/**
 * Player Class
 *   initial stub - accept player name (default name = "Ima Pirate")
 *    setPlayerName(<string>)
 *    getPlayerName()
 *    printPlayerName (on console)
 * 
 *   future: add additional player fields such as:
 *    inventory list (unsorted)
 *    current room
 *    # moves, move refreshed food/light, 
 * 
 * Design considerations:
 *   In future may create multi-player version
 *     possible issue w/ required items
 *  *
 * @author leev
 */

public class Player {
    static final private String DEFAULT_PLAYER = "Ima Pirate";
    
    private String playerName;
    
    Player(String name) {
        playerName = name;   // note: not checking null player name
        this.printPlayerName();
    }
    
    Player() {
        this(DEFAULT_PLAYER);  // "this" refers to constructor
    }
    
    public void printPlayerName() {
        ShipBoneYardUI.printGameOutput("Welcome " + playerName + "\n");
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String name) {
        if (name != "") {   // don't allow null player names
            playerName = name;
        }
        else {
            ShipBoneYardUI.printConsole("Null player name entered\n");
        }
    }

}
