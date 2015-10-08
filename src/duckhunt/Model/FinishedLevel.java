/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Model;

import duckhunt.Boundary.AnimationPanel;
import duckhunt.Boundary.InputContainer;
import duckhunt.Boundary.ShootInput;
import duckhunt.Control.Sound;
import duckhunt.Control.UnitManager;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Ingemar
 */
public class FinishedLevel extends BaseLevelState{
    private JPanel panel;
    private Image gameOver;
    
    public FinishedLevel(){
        panel = new FinishedPanel();
        panel.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        gameOver = new ImageIcon(getClass().getResource("../Images/gameover.jpg")).getImage();
        panel.addMouseListener(InputContainer.getInstance());
    }
    
    @Override
    public void move() {
    }

    @Override
    public void collide(List<ShootInput> inputs) {
        Iterator<ShootInput> it = inputs.iterator();

        while (it.hasNext()) {
            changeLevel();
            break;
        }
    }

    @Override
    public void update(double dt) {
    }

    @Override
    public void render() {
        panel.repaint();
    }

    @Override
    protected AudioClip getMusic() {
        return Sound.MAINMENU;
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }
    
    private class FinishedPanel extends JPanel {

        public FinishedPanel() {
        }

        @Override
        // override a Swing JComponent's paintComponent, not the paint method
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            setBackground(Color.BLACK);

            g.drawImage(gameOver, 0, 0, this.getWidth(), this.getHeight(), null);
        }
    }
}
