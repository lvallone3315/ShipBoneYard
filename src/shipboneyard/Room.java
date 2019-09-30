/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shipboneyard;

import static shipboneyard.ShipBoneYardUI.printConsole;
import static shipboneyard.PlayerInput.InputType;
import static shipboneyard.PlayerInput.Direction;

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
    String shortDescription;
    String longDescription;
    int roomToNorth;
    int roomToSouth;
    int roomToEast;
    int roomToWest;
    
    InitialRoom (int number, String name, String shortDes, String longDes,
            int north, int south, int east, int west) {
        this.roomNumber = number;
        this.roomName = name;
        this.shortDescription = shortDes;
        this.longDescription = longDes;
        this.roomToNorth = north;
        this.roomToSouth = south;
        this.roomToEast = east;
        this.roomToWest = west;
    }
}

class CurrentRoom {
    int roomNumber;
    String roomName;
    String shortDescription;
    String longDescription;
    int roomToNorth;
    int roomToSouth;
    int roomToEast;
    int roomToWest;
    boolean visited = false;
}

public class Room {
    private static final InitialRoom[] initialRoomList = {
        new InitialRoom(0, "Gatehouse", "Gatehouse\n",
                "You are at the Gatehouse, there is a road to the North\n",
                1, 1, 0, 0),
        new InitialRoom(1, "North Road", "North Road\n",
                "You are on a paved road running north & south\n",
                1, 1, 0, 1)
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
        currentRoom.shortDescription = initialRoomList[roomNum].shortDescription;
        currentRoom.longDescription = initialRoomList[roomNum].longDescription;
        currentRoom.roomToNorth = initialRoomList[roomNum].roomToNorth;
        currentRoom.roomToSouth = initialRoomList[roomNum].roomToSouth;
        currentRoom.roomToEast = initialRoomList[roomNum].roomToEast;
        currentRoom.roomToWest = initialRoomList[roomNum].roomToWest;
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
        switch (currentRoom.roomNumber) {
            case 0:
                if (playerInput.getInputType() == PlayerInput.InputType.DIRECTION) {
                    int newRoom = moveDirection(playerInput.getDirection());
                    LogToConsole.log("new room = " + newRoom);
                    return (new Room(newRoom));
                }
            case 1:
                if (playerInput.getInputType() == PlayerInput.InputType.DIRECTION) {
                    int newRoom = moveDirection(playerInput.getDirection());
                    LogToConsole.log("new room = " + newRoom);
                    return (new Room(newRoom));
                }
            default:                
                return (new Room(1));
        }
    }
    
    public int moveDirection(PlayerInput.Direction dir) {
        switch (dir) {
            case N:
                return currentRoom.roomToNorth;
            case S:
                return currentRoom.roomToSouth;
            case E:
                return currentRoom.roomToEast;
            case W:
                return currentRoom.roomToWest;
            default:
                return currentRoom.roomNumber;
        }
    }
    
    
    /**
     * getShortDescription for current room
     * @return - String containing short description of current room
     */
    public String getShortDescription() {
        return currentRoom.shortDescription;
    }
    
    /**
     * getLongDescription for current room
     * @return - String containing long description of current room
     */
    public String getLongDescription() {
        return currentRoom.longDescription;
    }
    
    //
    public void printCurrentRoom() {
        String printString = new String();
        printString = "Room #: " + currentRoom.roomNumber + "\n";
        printString += "Room name: " + currentRoom.roomName + "\n";
        printString += "Short Desc: " + currentRoom.shortDescription + "\n";
        printString += "Long Desc: " + currentRoom.longDescription + "\n";
        printString += "Visited: " + currentRoom.visited + "\n";
        printConsole(printString); 
    }
}
