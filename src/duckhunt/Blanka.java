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
public class Blanka {

    private int xPos = 900;
    private int yPos = 400;

    private Image blanka;

    public void update() {
        
    }
    
    protected void draw(Graphics g) {
        blanka = new ImageIcon(getClass().getResource("blanka.gif")).getImage();

        g.drawImage(blanka, xPos, yPos, 200, 200, null);

    }
}
