package roomGenerator;

import java.awt.Point;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class ConnectedMapGenerator {
    private final boolean[][] map; //output map
    private final boolean[][] visited; //true if the node has been visited
    private final double density;
    private final int size;
    private final PointWithNeighbour center;
    private final Random rnd = new Random();
    
    public ConnectedMapGenerator(final int radius, final double density) {
        this.size = radius*2+1;
        visited = new boolean[size][size];
        map = new boolean[size][size];
        this.density = density;
        this.center = new PointWithNeighbour(radius, radius);
        this.generate();
    }
    
    public boolean[][] getMap(){
        return this.map;
    }
    
    private void generate() {
        final Queue<PointWithNeighbour> generationQueue = new LinkedList<>();
        this.setMapped(generationQueue, center);
        this.setMapped(generationQueue, center.getEast());
        this.setMapped(generationQueue, center.getNorth());
        this.setMapped(generationQueue, center.getSouth());
        //this.setMapped(center.getWest());
        while(generationQueue.size() > 0) {
            final PointWithNeighbour actualPoint = generationQueue.remove();
            chooseIfAdd(generationQueue, actualPoint.getNorth());
            chooseIfAdd(generationQueue, actualPoint.getEast());
            chooseIfAdd(generationQueue, actualPoint.getSouth());
            chooseIfAdd(generationQueue, actualPoint.getWest());
        }
    }
    
    private void setMapped(Queue<PointWithNeighbour> queue, PointWithNeighbour p) {
        queue.add(p);
        map[p.x][p.y] = true;
        visited[p.x][p.y] = true;
    }
    
    private void chooseIfAdd(Queue<PointWithNeighbour> queue, PointWithNeighbour p) {
        if(!validPosition(p) || visited[p.x][p.y]) {
            return;
        }
        if(rnd.nextDouble() % 1 < density) {
            setMapped(queue, p);
        }
        else {
            visited[p.x][p.y] = true;
        }
    }
    
    private boolean validPosition(Point p) {
        return p.x >= 0 && p.y >= 0 && p.x <= size-1 && p.y <= size-1;
    }
    
    
}
