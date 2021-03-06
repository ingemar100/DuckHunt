package duckhunt.Model;

import duckhunt.Boundary.InputContainer;
import duckhunt.Boundary.ShootInput;
import duckhunt.Control.Sound;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import javax.swing.*;

public class Menu extends BaseLevelState{

    private Image mainMenuTitle;
    private Button start;
    private Button credits;
    private Button exit;
    private MenuPanel panel;
    private InputContainer inputCont;

    public Menu() {
        super();
        this.inputCont = InputContainer.getInstance();
        panel = new MenuPanel();
        panel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        panel.addMouseListener(inputCont);

        mainMenuTitle = new ImageIcon(getClass().getResource("../Images/MainMenuTitle.jpg")).getImage();
        start = new Button("../Images/StartGameButton.png", panel.getWidth() / 3, panel.getHeight() / 2, 300, 100);
        credits = new Button("../Images/CreditsButton.png", panel.getWidth() / 3, panel.getHeight() / 2 + 80, 300, 100);
        exit = new Button("../Images/ExitGame.png", panel.getWidth() / 3, panel.getHeight() / 2 + 160, 300, 100);
    }

    public void collide(List<ShootInput> inputs) {
        Iterator<ShootInput> it = inputs.iterator();

        while (it.hasNext()) {
            ShootInput i = it.next();

            if (start.testClick(i.getPoint())) {
                changeLevel();
            }
            else if (credits.testClick(i.getPoint())) {
                JOptionPane.showMessageDialog(null, "DuckDuckHunt™,\nMade by Selman Karasu and Ingemar Kok\n®2015 DuckDuckHunt productions");
            }
            else if (exit.testClick(i.getPoint())) {
                //exit
                game.stop();
            }
        }
    }

    @Override
    public void move() {
        //buttons dont move
    }

    @Override
    public void update(double dt) {
//        System.out.println("update menu");
    }

    @Override
    public void render() {
        panel.repaint();
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
            credits.draw(g);
            exit.draw(g);
        }
    }
}
