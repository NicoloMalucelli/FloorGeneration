package roomGenerator;

public enum CardinalPoint {
    NORTH(0),
    EAST(1),
    SOUTH(2),
    WEST(3);
    
    private int value;
    
    CardinalPoint(int value) {
        this.value = value;
    }
    
    int getValue() {
        return this.value;
    }
    
    
}
