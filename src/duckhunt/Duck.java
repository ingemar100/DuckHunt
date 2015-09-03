/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;

/**
 *
 * @author Ingemar
 */
public class Duck {

    private int xPos = 10;
    private int yPos = 500;
    private static final int RADIUS = 20;
    private int xSpeed = 5;
    private int ySpeed = -5;
    private int lastChange = 10;
    private boolean hit = false;

    public int getXPos(){
        return xPos;
    }
    
    public int getYPos(){
        return yPos;
    }
    
    public int getRadius(){
        return RADIUS;
    }
    
    public void update() {
        if ((xPos - lastChange) > 400){
            changeDirection();
            lastChange = xPos;
        }
        xPos += xSpeed;
        yPos += ySpeed;
    }
    
    private void changeDirection(){
        Random rn = new Random();
        int n = 10;
        int i = rn.nextInt(n) + 1;
        System.out.println(i);
        if (i < 5){
            //verander x richting
            if (xSpeed> 0){
                xSpeed = -5;
            }
            else {
                xSpeed = 5;
            }
        }
        else if (i > 4){
            //verander richting y
            if (ySpeed > 0){
                ySpeed = -5;
            }
            else {
                ySpeed = 5;
            }
        }
    }
    
    public void hit(){
        hit = true;
    }

    protected void draw(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if (hit){
            g.setColor(Color.GREEN);
        }
        else {
            g.setColor(Color.red);
        }
        g.fillOval(xPos - RADIUS, yPos - RADIUS, 2 * RADIUS, 2 * RADIUS);
    }
}
