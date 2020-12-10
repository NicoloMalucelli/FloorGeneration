package roomGenerator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.2;
    private final JButton[][] btnFloor;
    Floor floor;
    
    public GUI(final Floor floor) {
        super();
        this.floor = floor;
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getWidth() * HEIGHT_PERC));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        final JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(floor.getSize(), floor.getSize()));
        this.setContentPane(mainPanel);
        
        btnFloor = new JButton[floor.getSize()][floor.getSize()];
        for(int i = floor.getSize() - 1; i >= 0; i--) {
            for(int j = 0; j < floor.getSize(); j++) {
                btnFloor[i][j] = new JButton(Integer.toString(i) + ":" + Integer.toString(j));
                if(floor.getRooms()[i][j] == null) {
                    btnFloor[i][j].setBackground(new Color(255, 255, 255));
                }
                else {
                    if(floor.getRooms()[i][j] == floor.getActualRoom()) {
                        btnFloor[i][j].setBackground(new Color(255, 0, 0));
                    }else {
                        btnFloor[i][j].setBackground(new Color(0, 0, 0));
                    }
                }
                mainPanel.add(btnFloor[i][j], i, j);
            }
        }
        
        Agent agent = new Agent();
        new Thread(agent).start();
        
        this.setVisible(true);
    }
    
    private class Agent implements Runnable{

        public void run() {
            Room actualRoom = floor.getActualRoom();
            while(true) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(floor.getActualRoom() != actualRoom) {
                    PointWithNeighbour newP = floor.getPosition(floor.getActualRoom());
                    PointWithNeighbour oldP = floor.getPosition(actualRoom);
                    btnFloor[newP.x][newP.y].setBackground(new Color(255, 0, 0));
                    btnFloor[oldP.x][oldP.y].setBackground(new Color(0, 0, 0));
                    actualRoom = floor.getRoom(newP);
                }
            }
            
        }
    
    }
}
