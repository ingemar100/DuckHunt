/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

/**
 *
 * @author Ingemar
 */
public class Duck {

    private int xPos;
    private int yPos = 500;
    private static final int RADIUS = 80;
    private static final int LEFT_SPEED = -10;
    private static final int RIGHT_SPEED = 10;
    private static final int UP_SPEED = -4;
    private static final int DOWN_SPEED = 4;

    private int xSpeed = RIGHT_SPEED;
    private int ySpeed = UP_SPEED;
    private int maxY;
    private int lastChange = 10;
    private boolean hit = false;

    private Image duck;

    public Duck(int initialX, int initialY) {
        xPos = initialX;
        yPos = initialY;
        lastChange = initialX;
        maxY = initialY;

        changeDirection();
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
            changeDirection();
            lastChange = xPos;
        }
        xPos += xSpeed;
        yPos += ySpeed;
    }
 

    private void changeDirection() {
        Random rn = new Random();
        int n = 10;
        int i = rn.nextInt(n) + 1;
        System.out.println(i);
        if (i < 5) {
            //verander x richting
            if (xSpeed == RIGHT_SPEED) {
                xSpeed = LEFT_SPEED;

            } else {
                xSpeed = RIGHT_SPEED;
            }
        } else if (i > 4) {
            //verander richting y
            if (ySpeed == DOWN_SPEED) {
                ySpeed = UP_SPEED;
            } else {
                ySpeed = DOWN_SPEED;
            }
        }
        if (yPos >= maxY) {
            ySpeed = UP_SPEED;
        }
    }

    public void hit() {
        hit = true;
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
//        Graphics2D g2 = (Graphics2D) g;
//        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//                RenderingHints.VALUE_ANTIALIAS_ON);
//
//        if (hit){
//            g.setColor(Color.GREEN);
//        }
//        else {
//            g.setColor(Color.red);
//        }
//        g.fillOval(xPos - RADIUS, yPos - RADIUS, 2 * RADIUS, 2 * RADIUS);
       // duck = new ImageIcon(getClass().getResource("FlyingEagle.gif-c200")).getImage();
        duck = new ImageIcon(getClass().getResource("psyduck.gif")).getImage();
        
        if (xSpeed == RIGHT_SPEED) {
            g.drawImage(duck, xPos - RADIUS, yPos - RADIUS, 2 * RADIUS, 2 * RADIUS, null);
        } else if (xSpeed == LEFT_SPEED) {
            g.drawImage(duck, xPos - RADIUS, yPos - RADIUS, -2 * RADIUS, 2 * RADIUS, null);
        }
    }
}
