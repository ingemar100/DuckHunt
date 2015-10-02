/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author Selman
 */
public class Dog {
    private int xPos = -1700;
    private int yPos = 500;
    private static final int RIGHT_SPEED = 15;
    private int xSpeed = RIGHT_SPEED;

    private Image running;

    public void update() {
        xPos += xSpeed;
    }

    protected void draw(Graphics g) {
        running = new ImageIcon(getClass().getResource("Images/dog_with_duck_running.gif")).getImage();

        g.drawImage(running, xPos, yPos, 200, 200, null);

    }
}
