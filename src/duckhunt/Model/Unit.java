package duckhunt.Model;

import duckhunt.Control.Sound;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

abstract public class Unit extends Component {

    protected int xPos;
    protected int yPos = 500;
    private static final int RADIUS = 80;
    public static final int DEF_LEFT_SPEED = -10;
    public static final int DEF_RIGHT_SPEED = 10;
    public static final int DEF_UP_SPEED = -4;
    public static final int DEF_DOWN_SPEED = 4;

    protected int xSpeed = DEF_RIGHT_SPEED;
    protected int ySpeed = DEF_UP_SPEED;
    private int lastChange = 10;

    protected Image image;
    protected Behavior behavior;

    public Unit(int initialX, int initialY) {

        xPos = initialX;
        yPos = initialY;
        lastChange = initialX;

        move();
        loadImage();
    }
    
    public Unit(){
        loadImage();
        setBehavior();
        move();
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }

    public int getXSpeed() {
        return xSpeed;
    }

    public int getYSpeed() {
        return ySpeed;
    }

    public void setXSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setYSpeed(int ySpeed) {
        this.ySpeed = ySpeed;
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
                    Logger.getLogger(Unit.class.getName()).log(Level.SEVERE, null, ex);
                }
                Sound.COCK.play();
            }
        };
        soundThread.start();
    }

    public void draw(Graphics g) {
        if (xSpeed == DEF_RIGHT_SPEED) {
            g.drawImage(image, xPos - RADIUS, yPos - RADIUS, 2 * RADIUS, 2 * RADIUS, null);
        } else if (xSpeed == DEF_LEFT_SPEED) {
            g.drawImage(image, xPos, yPos - RADIUS, -2 * RADIUS, 2 * RADIUS, null);
        }
    }
    
    private void move(){
        behavior.move(this);
    }
    
    protected void loadImage(){
        String loc = getImageLocation();
        image = new ImageIcon(getClass().getResource(loc)).getImage();
    }
    
    abstract protected String getImageLocation();
    
    abstract public int getKillPoints();

    abstract protected void setBehavior();
}
