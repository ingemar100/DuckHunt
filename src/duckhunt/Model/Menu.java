package duckhunt.Model;

import duckhunt.Boundary.InputContainer;
import duckhunt.Boundary.InputContainer;
import duckhunt.Boundary.ShootInput;
import duckhunt.Control.Game;
import duckhunt.Control.Sound;
import duckhunt.Model.Button;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Menu extends BaseLevelState{

    private Image mainMenuTitle;
    private Button start;
    private Button options;
    private Button credits;
    private MenuPanel panel;
    private InputContainer inputCont;

    public Menu() {
        super();
        this.inputCont = InputContainer.getInstance();
        panel = new MenuPanel();
        panel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.addMouseListener(inputCont);

        mainMenuTitle = new ImageIcon(getClass().getResource("../Images/MainMenuTitle.jpg")).getImage();
        start = new Button("../Images/StartButton.png", panel.getWidth() / 3, panel.getHeight() / 2, 300, 100);
        options = new Button("../Images/OptionButton.png", panel.getWidth() / 3 - 42, panel.getHeight() / 2 + 80, 300, 100);
        credits = new Button("../Images/CreditButton.png", panel.getWidth() / 3 - 42, panel.getHeight() / 2 + 160, 300, 100);
    }

    public void collide(List<ShootInput> inputs) {
        Iterator<ShootInput> it = inputs.iterator();

        while (it.hasNext()) {
            ShootInput i = it.next();

            if (start.testClick(i.getPoint())) {
                changeLevel();
            }
        }
    }

    @Override
    public void move() {

    }

    @Override
    public void update(double dt) {
//        System.out.println("update menu");
    }

    @Override
    public void render() {
        panel.repaint();
    }

    public void button() {
        BufferedImage image = null;
        try {
            URL file = getClass().getResource("../Images/StartButton.png");
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
        panel.add(quitButton);

        // add the listener to the jbutton to handle the "pressed" event
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                //actie die uitgevoerd meot worden voor de game

            }
        });
    }
    
    public MenuPanel getPanel(){
        return panel;
    }

    @Override
    protected AudioClip getMusic() {
        return Sound.MAINMENU;
    }

    private class MenuPanel extends JPanel {

        public MenuPanel() {
        }

        @Override
        // override a Swing JComponent's paintComponent, not the paint method
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            setBackground(Color.BLACK);

            g.drawImage(mainMenuTitle, this.getWidth() / 3, 0, 600, 300, null);
            start.draw(g);
            options.draw(g);
            credits.draw(g);
        }
    }
}