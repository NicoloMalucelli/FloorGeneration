package roomGenerator;

public interface Room {
    void setRoom(CardinalPoint cp, Room room);
	
	Room getRoom(CardinalPoint cp);
	
	boolean hasRoom(CardinalPoint cp);
}
