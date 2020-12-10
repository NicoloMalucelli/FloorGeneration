package roomGenerator;

public class RoomImpl implements Room {
    private final static int MAX_NEIGHBOUR = 4;
    private final Room[] connectedRooms = new Room[MAX_NEIGHBOUR];
    
    public void setRoom(CardinalPoint cp, Room room) {
        connectedRooms[cp.getValue()] = room;
    }

    public Room getRoom(CardinalPoint cp) {
        return connectedRooms[cp.getValue()];
    }

    public boolean hasRoom(CardinalPoint cp) {
        return this.getRoom(cp) == null ? false : true;
    }
}    

 