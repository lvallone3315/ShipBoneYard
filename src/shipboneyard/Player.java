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
 *
 * Updates: 
 *   1-October - added Doc comments & toString() method
 * 
 * @author leev
 */

public class Player {
    static final private String DEFAULT_PLAYER = "Ima Pirate";
    
    private String playerName;
    
    /**
     * Player Constructors
     *   two variants - no parameter -> uses DEFAULT_PLAYER name
     *     if parameter passed -> store String as player name
     *   prints player name to console log
     * @param name 
     */
    Player(String name) {
        playerName = name;   // note: not checking null player name
        printPlayerName();
    }
    
    Player() {
        this(DEFAULT_PLAYER);  // "this" refers to constructor
    }
    
    /**
     * getPlayerName()
     * @return player name associated with player object
     */
    public String getPlayerName() {
        return playerName;
    }
    
    /**
     *  setPlayerName()
     *     if blank player name entered, log error to console
     * @param name - player name associated with player instance
     */
    public void setPlayerName(String name) {
        if (!name.equals("")) {   // don't allow null player names
            playerName = name;
        }
        else {
            LogToConsole.log("ERROR: Null player name entered\n");
        }
    }
    
    /**
     * toString() 
     * @return returns formatted player instance information
     */
    public String toString() {
        String returnString = "Player Name: " + getPlayerName() + "\n";
        return (returnString);
    }
    
    /**
     * printPlayerName() to console log <br>
     *   uses toString() method
     */
    public void printPlayerName() {
        LogToConsole.log(this.toString());
    }
    
}
