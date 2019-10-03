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
    boolean visited;
    
    InitialRoom (int number, String name, String shortDes, String longDes,
            int north, int south, int east, int west, boolean visited) {
        this.roomNumber = number;
        this.roomName = name;
        this.shortDescription = shortDes;
        this.longDescription = longDes;
        this.roomToNorth = north;
        this.roomToSouth = south;
        this.roomToEast = east;
        this.roomToWest = west;
        this.visited = visited;
    }
}

public class Room {
    
    /**
     * Room Class (base class for all locations)
     * 
     * initialRoomList[] details of all locations indexed by roomNumber
     */
    private static final InitialRoom[] initialRoomList = {
        new InitialRoom(0, "Gatehouse", "Gatehouse\n",
                "You are at the Gatehouse, there is a road to the North\n",
                1, 1, 0, 0, false),
        new InitialRoom(1, "North Road", "North Road\n",
                "You are on a paved road running north & south\n",
                1, 1, 0, 1, false)
    };
    
    /** roomObjects initialized with derived class rooms
     *  room logic in derived classes
     *  roomNumber = instance variable with roomNumber for each derived class
     */
    
    protected static Room[] roomObjects = { new GateHouse0(0), new NorthRoad(1) };
    protected int roomNumber;
    
    private static int STARTING_ROOM_NUM = 0;
    
    /**
     * Constructor
     * creates a new room object, and initializes the roomNumber instance var
     */
    Room(int roomNum) {
        roomNumber = roomNum;
    }
    
    /**
     * startingRoom - initial game location
     * @return - returns pointer to derived object of starting location
     */
    public static Room startingRoom() {
        return roomObjects[STARTING_ROOM_NUM];
    }
    
    public Room processUserRequest(PlayerInput playerInput) {
        /**
         *     Base Class - should never get here - probably should assert
         */
        printConsole("BASE CLASS: processUserRequest of " + playerInput.toString());  
        
        // back to go
        return roomObjects[STARTING_ROOM_NUM];
    }
    
    protected int moveDirection(int roomNum, PlayerInput.Direction dir) {
        switch (dir) {
            case N:
                return initialRoomList[roomNum].roomToNorth;
            case S:
                return initialRoomList[roomNum].roomToSouth;
            case E:
                return initialRoomList[roomNum].roomToEast;
            case W:
                return initialRoomList[roomNum].roomToWest;
            default:
                return initialRoomList[roomNum].roomNumber;
        }
    }
    
    
    /**
     * getShortDescription for current room
     * @return - String containing short description of current room
     */
    public String getShortDescription() {
        return (initialRoomList[roomNumber].shortDescription);
    }
    
    /**
     * getLongDescription for current room
     * @return - String containing long description of current room
     */
    public String getLongDescription() {
        return (initialRoomList[roomNumber].longDescription);
    }
    
    /**
     * getRoomVisited()
     * @return Boolean - true if previously visited room
     */
    public Boolean getRoomVisited() {
        return (initialRoomList[roomNumber].visited);
    }
    
    public void setRoomVisited(Boolean visited) {
        initialRoomList[roomNumber].visited = visited;
    }
    
    //
    public void printCurrentRoom() {
        String printString = new String();
        printString = "Room #: " + initialRoomList[roomNumber].roomNumber + "\n";
        printString += "Room name: " + initialRoomList[roomNumber].roomName + "\n";
        printString += "Short Desc: " + initialRoomList[roomNumber].shortDescription;
        printString += "Long Desc: " + initialRoomList[roomNumber].longDescription;
        printString += "Visited: " + initialRoomList[roomNumber].visited + "\n";
        printConsole(printString); 
    }
}
