package roomGenerator;

public class Floor {

    private final ConnectedMapGenerator mapGenerator;
    private final Room[][] floor;
    private Room actualRoom;
    private final int size;
    
    public Floor(final int radius, final double density) {
        mapGenerator = new ConnectedMapGenerator(radius, density);
        floor = new RoomImpl[radius*2+1][radius*2+1];
        this.size = radius*2+1;
        this.generateFloor();
        actualRoom = floor[radius][radius];
    }
    
    public int getSize() {
        return this.size;
    }
    
    public Room getActualRoom() {
        return actualRoom;
    }
    
    public Room getRoom(PointWithNeighbour p) {
        return floor[p.x][p.y];
    }
    
    public boolean[][] getSimpleFloor(){
        return mapGenerator.getMap();
    }
    
    public Room[][] getRooms(){
        return this.floor;
    }
    
    public PointWithNeighbour getPosition(Room r) {
        for(int i = 0; i < this.size; i++) {
            for(int j = 0; j < this.size; j++) {
                if(this.floor[i][j] == r) {
                    return new PointWithNeighbour(i, j);
                }
            }
        }
        return null;
    }
    
    public void setActualRoom(Room r) {
        actualRoom = r;
    }
    
    public void setActualRoom(PointWithNeighbour p) {
        actualRoom = floor[p.x][p.y];
    }
    
    private void generateFloor() {
        boolean[][] map = mapGenerator.getMap();
        for(int i = 0; i < floor.length; i++) {
            for(int j = 0; j < floor.length; j++) {
                if(map[i][j]) {
                    floor[i][j] = new RoomImpl();
                }
            }
        }
        
        for(int i = 0; i < floor.length; i++) {
            for(int j = 0; j < floor.length; j++) {
                if(map[i][j]) {
                    connectRoom(i, j);
                }
            }
        }
    }
    
    private void connectRoom(final int row, final int column) {
        PointWithNeighbour p = new PointWithNeighbour(row, column);
        if(row < size-1) {
            floor[p.x][p.y].setRoom(CardinalPoint.SOUTH, floor[p.getSouth().x][p.getSouth().y]);
        }
        if(row > 0) {
            floor[p.x][p.y].setRoom(CardinalPoint.NORTH, floor[p.getNorth().x][p.getNorth().y]);
        }
        if(column > 0) {
            floor[p.x][p.y].setRoom(CardinalPoint.WEST, floor[p.getWest().x][p.getWest().y]);
        }
        if(column < size-1) {
            floor[p.x][p.y].setRoom(CardinalPoint.EAST, floor[p.getEast().x][p.getEast().y]);
        }
    }
}
