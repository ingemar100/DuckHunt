/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import javax.swing.ImageIcon;

/**
 *
 * @author Ingemar
 */
public class Button {

    private Image image;
    private int x, y, width, height;

    public Button(String imageLoc, int x, int y, int width, int height) {
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        image = new ImageIcon(getClass().getResource(imageLoc)).getImage();
    }

    protected String getImageLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }

    public boolean testClick(Point p) {
        if (p.x > x && p.x < x + width && p.y > y && p.y < y + height) {
            return true;
        }
        else {
            return false;
        }
    }
}