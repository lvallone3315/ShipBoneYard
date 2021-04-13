
package shipboneyard;


import static shipboneyard.ShipBoneYardUI.printGameOutput;  // save typing
import static shipboneyard.ShipBoneYardUI.printConsole;  // save typing
import static shipboneyard.ShipBoneYardUI.getGameInput; 
import static shipboneyard.PlayerInput.InputType;
import static shipboneyard.LogToConsole.log;

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
    
    // Version #, should be in separate file, e.g. version.txt
    //   update with every GIT commit
    static final String VERSION = "Version 0.2 Inv\n";    // current version #
    
    static final int STARTING_ROOM = 0;  // ToDo: switch to enum
    static final String INTRO_1 = "\t\tWelcome to the Ship Boneyard Game\n\n"; 
    static final String INTRO_2 = "game text area - Wreck of the Edmund Fitzgerald\n";
    static final String DESCRIPTION = 
            "You are entering a graveyard of old ships.\n"
            + "Your objective is to explore (and survive)\n"
            + "You can move around with simple commands (e.g. north)\n"
            + "Please close the window or type <exit> to quit the game\n";
    static final String PLAYER_NAME_REQ = "Enter player name> ";

    /**
     * @param args the command line arguments (none at this time)
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
        // Print game intro on console
        System.out.println(INTRO_1);
        System.out.println(VERSION);
        
        // Initialize UI and test output
        ShipBoneYardUI ui = new ShipBoneYardUI();  // setup game window, ui unused
        printGameOutput(INTRO_1);
        printGameOutput(VERSION);
        printGameOutput(DESCRIPTION);
        log("This is the console output\n");
        
        
        // Initial player
        Player player = new Player();  // prints welcome message too
 
        // Request user to enter player name
        //   if no player name entered, player class will use a default name
        //   if player name entered - print welcome message to game window
        String playerName = getGameInput(PLAYER_NAME_REQ);
        if (!playerName.equals("")) {
            player.setPlayerName(playerName);
            player.printPlayerName();  // prints new player name to log
        }
        ShipBoneYardUI.printGameOutput("Welcome " + player.getPlayerName() + "\n");
        
        // initialize starting location
        Room room = Room.startingRoom();
        
        // Initialize parser
        ParseInput parser = new ParseInput();
        
        // Main loop
        //   get first player input - maybe use do While instead
        //   while player hasn't quit
        //      get player's input (ie typing)
        //      send input to room logic (derived object of Room base class)
        //      move to new room (if applicable)
        //
        // ToDo:
        //   Move prompts to constants
        //   Consider moving description print elsewhere (and using short des if visited)
        //   Define an exit type in the parser (ie accept done, quit, exit...)
        String input = getGameInput("What's up?> ");
        PlayerInput playerInput = parser.parseInput(input);
        while (playerInput.getInputType() != InputType.EXIT) {
  
            // echo player's input to game screen
            printGameOutput(playerInput.getPlayerText()+"\n");
            
            // switch on type of input - e.g. move in a direction
            // process method returns pointer to next (or same) room
            room = room.processUserRequest(playerInput);
            printGameOutput(room.getLongDescription());
               // log room info to console - maybe do this in room class
            room.printCurrentRoom();  
            
            // next input line
            input = getGameInput("What's up?> ");
            playerInput = parser.parseInput(input);
        }
        
        printGameOutput("All done!\n");
        
        // sleep 5 seconds & exit
        //   *** redo this later - maybe ask for key to exit
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            System.out.println(e);
        }
        System.exit(0);
            
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
