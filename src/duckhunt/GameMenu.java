package duckhunt;

import duckhunt.Boundary.InputContainer;
import duckhunt.Control.Game;
import duckhunt.Control.Sound;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GameMenu extends JPanel {

    private Image MainMenuTitle;
    private Image StartButton;
    private Image OptionButton;
    private Image CreditButton;
    private Game game;

    public GameMenu(InputContainer inputCont) {
        this.addMouseListener(inputCont);
        MainMenuTitle = new ImageIcon(getClass().getResource("Images/MainMenuTitle.jpg")).getImage();
        StartButton = new ImageIcon(getClass().getResource("Images/StartButton.png")).getImage();
        OptionButton = new ImageIcon(getClass().getResource("Images/OptionButton.png")).getImage();
        CreditButton = new ImageIcon(getClass().getResource("Images/CreditButton.png")).getImage();
        button();
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
        setBackground(Color.BLACK);

        g.drawImage(MainMenuTitle, this.getWidth() / 3, 0, 600, 300, null);
        g.drawImage(StartButton, this.getWidth() / 3, getHeight() / 2, 300, 100, null);
        g.drawImage(OptionButton, this.getWidth() / 3 - 42, getHeight() / 2 + 80, 300, 100, null);
        g.drawImage(CreditButton, this.getWidth() / 3 - 42, getHeight() / 2 + 160, 300, 100, null);
    }

    public void button() {
        BufferedImage image = null;
        try {
            URL file = getClass().getResource("Images/StartButton.png");
            image = ImageIO.read(file);
        } catch (IOException ioex) {
            System.err.println("load error: " + ioex.getMessage());
        }
        ImageIcon icon = new ImageIcon(image);
        JButton quitButton = new JButton(icon);
        quitButton.setOpaque(false);
        quitButton.setContentAreaFilled(false);
        quitButton.setBorderPainted(false);
        quitButton.setBorder(null);
        this.add(quitButton);

        // add the listener to the jbutton to handle the "pressed" event
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            
                
                System.out.println("Ingemar HELP!!!");
                //actie die uitgevoerd meot worden voor de game

            }
        });
    }
}
