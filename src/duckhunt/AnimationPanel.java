package duckhunt;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Cursor;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AnimationPanel extends JPanel {

    private Point shotLocation;
    private JLabel scoreLabel = new JLabel("0");
    private List<ShootingListener> shootingListeners = new ArrayList();
    private UnitManager dm;

    private Image backgroundImage;

    public AnimationPanel() {
        backgroundImage = new ImageIcon(getClass().getResource("Images/background.png")).getImage();
       
        this.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
        
//       this.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
//            new ImageIcon("pokeball.jpg").getImage(),
//            new Point(0,0),"custom cursor"));
        
        
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {
                
                shotLocation = me.getPoint();
                for (ShootingListener sl : shootingListeners) {
                    sl.shoot(shotLocation);
                }
            }
        });
        this.add(new JLabel("Score: "));
        this.add(scoreLabel);
    }
    
    public void addShootingListener(ShootingListener sl){
        shootingListeners.add(sl);
    }

    public void setManager(UnitManager dm) {
        this.dm = dm;
    }
    
    public void setScore(int score){
        scoreLabel.setText("" + score);
    }

    @Override
    // override a Swing JComponent's paintComponent, not the paint method
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);

        List<Unit> units = dm.getUnits();
        Iterator<Unit> it = units.iterator();
        while(it.hasNext()){
            Unit u = it.next();
            u.draw(g);
        }
    }
}
