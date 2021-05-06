
package shipboneyard;


import static shipboneyard.ShipBoneYardUI.printGameOutput;  // save typing
import static shipboneyard.ShipBoneYardUI.printConsole;
import static shipboneyard.ShipBoneYardUI.getGameInput2; 
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
    static final String VERSION = "Version 0.3\n";    // current version #
    
    static final String INTRO_1 = "\t\tWelcome to the Ship Boneyard Game\n\n"; 
    static final String INTRO_2 = "game text area - Wreck of the Edmund Fitzgerald\n";
    static final String DESCRIPTION = 
            "You are entering a graveyard of old ships.\n"
            + "Your objective is to explore (and survive)\n"
            + "You can move around with simple commands (e.g. north)\n"
            + "Please close the window or type <exit> to quit the game\n";
    static final String PLAYER_NAME_REQ = "Enter player name> ";
    static final String QUIT_MESSAGE = "Game Over - Hope to see you again!\n";

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
        log(INTRO_1);
        log(VERSION);
        

        // Initialize UI and test output
        // UI output methods are static, enabling access from anywhere in game objects
        Drop drop = new Drop();
        ShipBoneYardUI ui = new ShipBoneYardUI(drop);  // setup game window, ui unused
        ui.printGameOutput(INTRO_1);
        ui.printGameOutput(VERSION);
        ui.printGameOutput(DESCRIPTION);
        log("Ship Boneyard - This is the console output\n");
 
        //          Player Initialization
        // Request user to enter player name
        //   if no player name entered, player class will use a default name
        //   if player name entered - print welcome message to game window
        Player player = new Player(); 
        ui.printGameOutput(PLAYER_NAME_REQ);
        ui.setPrompt(PLAYER_NAME_REQ);
        String playerName = drop.take();
        if (!playerName.equals("")) {
            player.setPlayerName(playerName);
            player.printPlayerName();  // prints new player name to log
        }
        ui.printGameOutput("Welcome " + player.getPlayerName() + "\n");
        
        //     Starting location & input parser initialization
        Room room = Room.startingRoom();
        ParseInput parser = new ParseInput();
        
        //                 Main loop
        //   
        //   while player hasn't quit
        //      player's input (ie typing) - drop.take blocks until input avail
        //      send input to room logic (derived object of Room base class)
        //      move to new room (if applicable)
        //
        // drop = message passing from GUI - see Drop class
        //
        // ToDo:
        //   Consider moving description print elsewhere (and using short des if visited)
        
        while (true) {
            String input = drop.take();  // receive user input from GUI thread
            log(String.format("MESSAGE RECEIVED: %s\n", input));
            
            // parse input, UI echos to game screen
            PlayerInput playerInput = parser.parseInput(input);

            // check if user is tired of this and wants to quit
            if (playerInput.getInputType() == InputType.EXIT) {
                ui.printGameOutput(QUIT_MESSAGE);
                try {             // sleep 5 seconds & exit
                    Thread.sleep(5000);
                } catch (Exception e) {
                    System.out.println(e);
                }
                System.exit (0);
            }
            
            // switch on type of input - e.g. move in a direction
            // process method returns pointer to next (or same) room
            room = room.processUserRequest(playerInput);
            ui.printGameOutput(room.getLongDescription());
               // log room info to console - maybe do this in room class
            room.printCurrentRoom();

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {}
        }  // end main game loop to read input & process move
            
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
