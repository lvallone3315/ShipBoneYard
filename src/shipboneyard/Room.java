/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import static shipboneyard.ShipBoneYardUI.printConsole;

/**
 *  Room Class
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
 * @author leev
 */
class InitialRoom {
    int roomNumber;
    String roomName;
    
    InitialRoom (int number, String name) {
        this.roomNumber = number;
        this.roomName = name;
    }
}

class CurrentRoom {
    int roomNumber;
    String roomName;
    String shortDescription;
    String longDescription;
    boolean visited = false;
}

public class Room {
    private static final InitialRoom[] initialRoomList = {
        new InitialRoom(0, "Gatehouse"),
        new InitialRoom(1, "North Road")
    };
    private CurrentRoom currentRoom;
    
    // Contructor
    // creates a new room object, and defines it as the current room
    //   note - will need to make sure old room pointers are maintained
    // copies initial room information to current room object
    Room(int roomNum) {
        currentRoom = new CurrentRoom();
        currentRoom.roomNumber = initialRoomList[roomNum].roomNumber;
        currentRoom.roomName = initialRoomList[roomNum].roomName;
        currentRoom.visited = true;
    }
    
    public Room processUserRequest(PlayerInput playerInput) {
        /**
         * Directional changes
         *   check if room object already created, if not create it
         *     alt: create all room objects at startup
         *   either way: need array of room object pointers
         * 
         *  challenging part - how to execute derived class method
         *  
         *  To start - big switch statement based on room #
         *    call appropriate room method with playerInput
         *    method returns room pointer which becomes the new current room
         */
        return (new Room(1));
    }
    
    //
    public void printCurrentRoom() {
        String printString = new String();
        printString = "Room #: " + currentRoom.roomNumber + "\n";
        printString += "Room name: " + currentRoom.roomName + "\n";
        printString += "Visited: " + currentRoom.visited + "\n";
        printConsole(printString); 
    }
}
