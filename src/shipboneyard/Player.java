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
        LogToConsole.log(printPlayerName());
    }
    
    Player() {
        this(DEFAULT_PLAYER);  // "this" refers to constructor
    }
    
    public String printPlayerName() {
        return ("Player name: " + getPlayerName() + "\n");
    }
    
    public String getPlayerName() {
        return playerName;
    }
    
    public void setPlayerName(String name) {
        if (!name.equals("")) {   // don't allow null player names
            playerName = name;
        }
        else {
            LogToConsole.log("Null player name entered\n");
        }
    }

}
