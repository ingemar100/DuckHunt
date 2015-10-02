package duckhunt;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class Unit extends Component {

    protected int xPos;
    protected int yPos = 500;
    private static final int RADIUS = 80;
    protected static final int LEFT_SPEED = -10;
    protected static final int RIGHT_SPEED = 10;
    protected static final int UP_SPEED = -4;
    protected static final int DOWN_SPEED = 4;

    protected int xSpeed = RIGHT_SPEED;
    protected int ySpeed = UP_SPEED;
    protected int maxY;
    private int lastChange = 10;

    protected Image image;

    public Unit(int initialX, int initialY) {

        xPos = initialX;
        yPos = initialY;
        lastChange = initialX;
        maxY = initialY;

        move();
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public int getRadius() {
        return RADIUS;
    }

    public void update() {
        if ((xPos - lastChange) > 400) {
            move();
            lastChange = xPos;
        }
        xPos += xSpeed;
        yPos += ySpeed;
    }

    public void hit() {
        Thread soundThread = new Thread() {
            public void run() {
                Sound.SHOT.play();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Duck.class.getName()).log(Level.SEVERE, null, ex);
                }
                Sound.COCK.play();
            }
        };
        soundThread.start();
    }

    protected void draw(Graphics g) {
        if (xSpeed == RIGHT_SPEED) {
            g.drawImage(image, xPos - RADIUS, yPos - RADIUS, 2 * RADIUS, 2 * RADIUS, null);
        } else if (xSpeed == LEFT_SPEED) {
            g.drawImage(image, xPos - RADIUS, yPos - RADIUS, -2 * RADIUS, 2 * RADIUS, null);
        }
    }
    
    abstract protected void move();

}
