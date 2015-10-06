package duckhunt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import javax.swing.*;

public class GameMenu extends JPanel {

    Image menuImage;
    Image startButton;

    public GameMenu() {
        startButton = new ImageIcon(getClass().getResource("Images/GameMenu.png")).getImage();
        
    }
    
    public void render() {
        this.repaint();
    }
    
    @Override
    // override a Swing JComponent's paintComponent, not the paint method
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(startButton, 0, 0, 400, 500, null);
    }
}
