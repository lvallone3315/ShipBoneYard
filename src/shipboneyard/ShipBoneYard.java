/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;


import static shipboneyard.ShipBoneYardUI.printGameOutput;  // save typing
import static shipboneyard.ShipBoneYardUI.printConsole;  // save typing
import static shipboneyard.ShipBoneYardUI.getGameInput; 

/**
 *
 * Ship Bone Yard adventure game
 * Description: Colossal Cave-like text based adventure game that runs on 
 * modern platforms featuring a new adventure scenario optimized for 
 * short user engagement times.
 * <P>
 * Initial phase - setup main loop & call stubs
 * 
 * @author leev
 */
public class ShipBoneYard {

    /**
     * @param args the command line arguments
     * 
     * Initialize player & initial room objects
     * Main loop:
     *   while user input
     *    parse user input
     *    switch on type of user input:
     *     move - try to move in specified direction
     *     other - TBD
     * <P>
     * Note on UI:
     *   two interfaces:
     *    game interface - player interaction
     *    console interface - debugging & other info
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Welcome to the Ship Boneyard Game");
        
        // Initialize UI and test output
        ShipBoneYardUI ui = new ShipBoneYardUI();  // setup game window, ui unused
        printGameOutput("game text area - Wreck of the Edmund Fitzgerald\n");
        printConsole("console output\n");
        
        // Initial player next
        Player player = new Player();  // prints welcome message too
        Room room = new Room(0); // initialize to starting location
        room.printCurrentRoom();
        
        // Get Player name
        String playerName = getGameInput("Enter player name> ");
        if (!playerName.equals("")) {
            player.setPlayerName(playerName);
            player.printPlayerName();
        }
        
        // Initialize parser
        ParseInput parser = new ParseInput();
        // get first player input - maybe use do While instead
        String input = getGameInput("What's up?> ");
        PlayerInput playerInput = parser.parseInput(input);
        while (!playerInput.getPlayerText().equals("exit")) {
  
            printGameOutput(playerInput.getPlayerText()+"\n");
            
            // switch on type of input - e.g. move in a direction
            // create room class and first room (derived from room class)
            room = room.processUserRequest(playerInput);
            room.printCurrentRoom();
            
            // next input line
            input = getGameInput("What's up?> ");
            playerInput = parser.parseInput(input);
        }
        printGameOutput("All done!\n");
            
    }
}

/**
 * UI Class
 *   Setup console output
 *      printConsole(<string>)
 *   Setup game output
 *      printGameOutput(<string>)
 *   Get input from game window
 *      String getGameInput()
 * 
 * Issues:
 *   Initially - string arg only, formatting done prior to calling
 *     equivalent to sprintf() only
 *   Assuming static class - so no need to instantiate object to access
 *   For jar version, IDE console not avail, map console to window &
 *     map gameoutput to separate window
 *     
 * Consider:
 *   separate logging class, consider extending args
 *   destination totally within logging class
 * 
 * Note: if switch to multi-player, need static -> instance
 */

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
 */

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
 */

/**
 * Room Class
 *   initial stub - constructor only
 *   next - create helper class w/ static room info (1st gatehouse)
 *    static room info - short room name (enum?), short description
 *      long description, NSEW rooms
 *    ? how to design movable things in room - e.g. in derived room logic?
 *      poss create a room object class tracking which room & description
 *    create object w/ gate house room, define room info
 *      add visited
 * 
 * Issues:
 *   how to initialize "static final" room info
 *    objective: all rooms in one place, easy to visualize & change
 *   propose overloading base room class with room derived class w/ logic
 *    for specific room (Extends)
 * 
 * Design assumption:
 *   when moving to a new location, check NSEW room in object
 *   if room is NULL (Java way to do this?), 
 *    instantiate room, store pointer in current room object
 *   Note: this works well in multi-player, since object pointers associated
 *    w/ room & not associated with the player
 *    (e.g. items moved around by one player, are persistent)
 * 
 * Future:
 *   additional directions incl NE, SW, etc., up, down
 *   
 */
