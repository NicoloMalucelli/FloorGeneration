package roomGenerator;

import java.awt.Point;

public class PointWithNeighbour extends Point {

    private static final long serialVersionUID = 1L;

    public PointWithNeighbour() {
        // TODO Auto-generated constructor stub
    }

    public PointWithNeighbour(Point p) {
        super(p);
        // TODO Auto-generated constructor stub
    }

    public PointWithNeighbour(int x, int y) {
        super(x, y);
        // TODO Auto-generated constructor stub
    }
    
    public PointWithNeighbour getWest() {
        return new PointWithNeighbour(this.x, this.y - 1);
    }
    
    public PointWithNeighbour getEast() {
        return new PointWithNeighbour(this.x, this.y + 1);
    }
    
    public PointWithNeighbour getNorth() {
        return new PointWithNeighbour(this.x - 1, this.y);
    }
    
    public PointWithNeighbour getSouth() {
        return new PointWithNeighbour(this.x + 1, this.y);
    }

}
