package duckhunt.Model;

import duckhunt.Control.Sound;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

abstract public class Unit {

    protected int xPos;
    protected int yPos = 500;
    private static final int RADIUS = 80;

    protected Image image;
    protected MoveBehavior behavior;

    
    public Unit(){
        loadImage();
        setBehavior();
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
    
    public int getRadius() {
        return RADIUS;
    }
    
    public void update(double dt){
        behavior.update(dt);
    }
    
    public void move(){
        behavior.move();
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
        if (behavior.xSpeed >= 0) {
            g.drawImage(image, xPos - RADIUS, yPos - RADIUS, 2 * RADIUS, 2 * RADIUS, null);
        } else if (behavior.xSpeed < 0) {
            g.drawImage(image, xPos, yPos - RADIUS, -2 * RADIUS, 2 * RADIUS, null);
        }
    }
    
    protected void loadImage(){
        String loc = getImageLocation();
        image = new ImageIcon(getClass().getResource(loc)).getImage();
    }
    
    abstract protected String getImageLocation();
    
    abstract public int getKillPoints();

    abstract protected void setBehavior();
}
