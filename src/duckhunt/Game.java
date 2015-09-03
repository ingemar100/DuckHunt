package duckhunt;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;

public class Game {

    private static final Dimension SIZE = new Dimension(600, 600);
    static AnimationPanel panel = new AnimationPanel();

    private static void createAndShowUI() {
        JFrame frame = new JFrame("SimpleSwingAnimation");
        frame.getContentPane().add(panel);
        frame.getContentPane().setPreferredSize(SIZE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        Thread threadForInitGame = new Thread() {
            public void run() {
                createAndShowUI();
                while (true) {
//                    processInput();
                    update();
                    render();

                    try {
                        Thread.sleep(20);
                    } catch (Exception e) {

                    }
                }
            }
        };
        threadForInitGame.start();
    }

    public static void update() {
        panel.increment();
    }

    public static void render() {
        panel.repaint();
    }
}

@SuppressWarnings("serial")
class AnimationPanel extends JPanel {

    private static final int RADIUS = 20;
    private static final int TIMER_DELAY = 20;
    private int xPos = 10;
    private int yPos = 100;
    private ArrayList<Duck> ducks = new ArrayList();
    private int time = 0;
    private boolean shooting = false;
    private Point shotLocation;

    public AnimationPanel() {
        setBackground(Color.blue);
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                shooting = true;
                shotLocation = me.getPoint();
            }
        });
    }

    public void increment() {
        xPos++;
        yPos--;
    }

    @Override
    // override a Swing JComponent's paintComponent, not the paint method
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // again to repaint the screen
        // set antialiasing rendering hints to smooth out the circle
        // this is not necessary but makes for prettier animation
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

//        g.setColor(Color.red);
//        g.fillOval(xPos - RADIUS, yPos - RADIUS, 2 * RADIUS, 2 * RADIUS);
        if (time > 100) {
            Duck d = new Duck();
            ducks.add(d);
            Random rn = new Random();
            int n = 200 - 50 + 1;
            int i = rn.nextInt() % n;
            time = 50 + i;
        }
        time++;

        for (Duck duck : ducks) {
            duck.update();
            duck.draw(g);
            if (shooting) {
//                System.out.println("Shooting: " + shotLocation.x + ", " + shotLocation.y);
//                System.out.println("Duck: " + duck.getXPos() + ", " + duck.getYPos());
                if (shotLocation.x > duck.getXPos() && shotLocation.x < (duck.getXPos() + 2 * duck.getRadius()) 
                        && shotLocation.y > duck.getYPos() && shotLocation.y < (duck.getYPos() + 2 * duck.getRadius())){
                    System.out.println("geraakt");
                    duck.hit();
                }
            }
        }
        shooting = false;
    }
}
