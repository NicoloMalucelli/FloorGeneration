package roomGenerator;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class InteractiveGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private static final double WIDTH_PERC = 0.2;
    private static final double HEIGHT_PERC = 0.2;
    private final JButton goNorth = new JButton("NORTH");
    private final JButton goEast = new JButton("EAST");
    private final JButton goSouth = new JButton("SOUTH");
    private final JButton goWest = new JButton("WEST");
    private final Floor floor;
    
    public InteractiveGUI(final Floor floor) {
        super();
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize((int) (screenSize.getWidth() * WIDTH_PERC), (int) (screenSize.getWidth() * HEIGHT_PERC));
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.floor = floor;
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        this.setContentPane(mainPanel);
        
        mainPanel.add(goNorth, BorderLayout.NORTH);
        mainPanel.add(goEast, BorderLayout.EAST);
        mainPanel.add(goWest, BorderLayout.WEST);
        mainPanel.add(goSouth, BorderLayout.SOUTH);
        
        Agent agent = new Agent();
        new Thread(agent).start();
        
        goWest.addActionListener((e) -> agent.goWest());
        goEast.addActionListener((e) -> agent.goEast());
        goNorth.addActionListener((e) -> agent.goNorth());
        goSouth.addActionListener((e) -> agent.goSouth());
        
        this.setFocusable(true);
        this.addKeyListener(new KeyListener() {
            
            public void keyTyped(KeyEvent e) {
                
            }
            
            public void keyReleased(KeyEvent e) {
                
            }
            
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) { 
                    case KeyEvent.VK_UP:
                        if(goNorth.isEnabled()) {
                            agent.goNorth(); 
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if(goSouth.isEnabled()) {
                            agent.goSouth(); 
                        }break;
                    case KeyEvent.VK_LEFT:
                        if(goWest.isEnabled()) {
                            agent.goWest();
                        }
                        break;
                    case KeyEvent.VK_RIGHT :
                        if(goEast.isEnabled()) {
                            agent.goEast();
                        }
                        break;
                 }
            }
        });
        
        
        this.setVisible(true);
    }
    
    private class Agent implements Runnable{

        public void goNorth() {
            floor.setActualRoom(floor.getActualRoom().getRoom(CardinalPoint.NORTH));
            setButtons();
        }
        
        public void goSouth() {
            floor.setActualRoom(floor.getActualRoom().getRoom(CardinalPoint.SOUTH));
            setButtons();
        }
        
        public void goEast() {
            floor.setActualRoom(floor.getActualRoom().getRoom(CardinalPoint.EAST));
            setButtons();
        }
        
        public void goWest() {
            floor.setActualRoom(floor.getActualRoom().getRoom(CardinalPoint.WEST));
            setButtons();
        }
        
        public void run() {
            setButtons();
        }
        
        private void setButtons() {
            if(floor.getActualRoom().hasRoom(CardinalPoint.NORTH)) {
                goNorth.setEnabled(true);
            }else {
                goNorth.setEnabled(false);
            }
            if(floor.getActualRoom().hasRoom(CardinalPoint.EAST)) {
                goEast.setEnabled(true);
            }else {
                goEast.setEnabled(false);
            }
            if(floor.getActualRoom().hasRoom(CardinalPoint.SOUTH)) {
                goSouth.setEnabled(true);
            }else {
                goSouth.setEnabled(false);
            }
            if(floor.getActualRoom().hasRoom(CardinalPoint.WEST)) {
                goWest.setEnabled(true);
            }else {
                goWest.setEnabled(false);
            }
        }
        
    }

}
