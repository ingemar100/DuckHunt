package duckhunt;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import javafx.application.Application;
import javax.swing.*;

public class Game {

    private static final Dimension SIZE = new Dimension(600, 600);
    static AnimationPanel panel = new AnimationPanel();
    private static final int TIMER_DELAY = 20;

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
                        Thread.sleep(TIMER_DELAY);
                    } catch (Exception e) {

                    }
                }
            }
        };
        threadForInitGame.start();
    }

    public static void update() {
        
    }

    public static void render() {
        panel.repaint();
    }
}

@SuppressWarnings("serial")
class AnimationPanel extends JPanel {

    private ArrayList<Duck> ducks = new ArrayList();
    private int time = 0;
    private boolean shooting = false;
    private Point shotLocation;
    private JLabel scoreLabel = new JLabel("0");
    private int score = 0;

    public AnimationPanel() {
        setBackground(Color.blue);
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                shooting = true;
                shotLocation = me.getPoint();
            }
        });
        this.add(new JLabel("Score: "));
        this.add(scoreLabel);
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

        if (time > 200) {
            Duck d = new Duck();
            ducks.add(d);
            Random rn = new Random();
            int n = 200 - 50 + 1;
            int i = rn.nextInt() % n;
            time = 50 + i;
        }
        time++;

        Rectangle bounds = this.getBounds();
        int h = bounds.height;
        int w = bounds.width;
        
        ArrayList<Duck> deadDucks = new ArrayList();
        for (Duck duck : ducks) {
            //controleer of eend buiten scherm is
            if (duck.getXPos() < 0 || duck.getXPos() > w || duck.getYPos() < 0 || duck.getYPos() > h){
                deadDucks.add(duck);
            }
            else {
                if (shooting) {
                    if (shotLocation.x > (duck.getXPos() - duck.getRadius()) && shotLocation.x < duck.getXPos() + duck.getRadius()
                            && shotLocation.y > (duck.getYPos() - duck.getRadius()) && shotLocation.y < duck.getYPos() + duck.getRadius()) {
                        System.out.println("geraakt");
                        duck.hit();
                    }
                }
                duck.update();
                duck.draw(g);
            }
        }
        ducks.removeAll(deadDucks);
        shooting = false;
        System.out.println("Aantal eenden actief: " + ducks.size());
    }
}