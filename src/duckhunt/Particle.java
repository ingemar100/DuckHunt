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
import java.awt.Window;
import java.util.ArrayList;
import java.util.Random;
import java.util.Vector;

/**
 *
 * @author Ingemar
 */
public class Particle {

    double x, y, xVel, yVel;
    int endTime;
    Color color;

    public Particle(double x, double y, double xVel,
            double yVel, int life, Color color) {
        this.x = x;
        this.y = y;
        this.xVel = xVel;
        this.yVel = yVel;
        endTime = life;//ticks + life
        color = color;
    }

    void move() {
        x += xVel;
        y += yVel;
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
//        if (x > Window.WIDTH) {
//            x = Window.WIDTH - 1;
//        }
//        if (y > Window.HEIGHT) {
//            y = Window.HEIGHT - 1;
//        }
    }

    void show(Graphics g) {
        int pixel = 500;
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.setColor(color);
        
        System.out.println("X: " + x + " y: " + y);
        g.fillOval((int)x, (int)y, 5, 5);
        
        //set pixel to color
    }

    boolean isDead() {
        return x == 0 || y == 0 || x == Window.WIDTH - 1 || y == Window.HEIGHT - 1;
    }
}
