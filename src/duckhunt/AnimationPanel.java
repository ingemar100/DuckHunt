package duckhunt;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


@SuppressWarnings("serial")
class AnimationPanel extends JPanel {
    private Point shotLocation;
    private JLabel scoreLabel = new JLabel("0");
    private int score = 0;
    private List<ShootingListener> shootingListeners = new ArrayList();
    
    private ParticleEngine pe;
    private Image backgroundImage;

    public AnimationPanel() {
        backgroundImage = new ImageIcon(getClass().getResource("background.png")).getImage();

        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                shotLocation = me.getPoint();
                for(ShootingListener sl : shootingListeners){
                    sl.shoot(shotLocation);
                }
            }
        });
        this.add(new JLabel("Score: "));
        this.add(scoreLabel);

        pe = new ParticleEngine(1000, this.getBounds().width / 2, this.getBounds().height / 2);
    }
    
    public void addScore(int amount){
        score += amount;
        scoreLabel.setText(score + "");
    }

    @Override
    // override a Swing JComponent's paintComponent, not the paint method
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        
        pe.refresh(g);
    }
}