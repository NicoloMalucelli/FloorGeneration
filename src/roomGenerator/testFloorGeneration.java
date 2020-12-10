package roomGenerator;

public class testFloorGeneration{
    
    private final static int RADIUS = 3;
    private final static double DENSITY = 0.5;

    public static void main(String[] args) {
        Floor floor = new Floor(RADIUS, DENSITY);
        new GUI(floor);
        new InteractiveGUI(floor);
    }
}
