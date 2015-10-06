package duckhunt;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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
import java.awt.Image;
import java.awt.Toolkit;

@SuppressWarnings("serial")
public class AnimationPanel extends JPanel {

    private JLabel scoreLabel = new JLabel("0");
    private UnitManager dm;

    private Image backgroundImage;

    public AnimationPanel(InputContainer inputCont) {
        backgroundImage = new ImageIcon(getClass().getResource("Images/background.png")).getImage();
                
        Image im = Toolkit.getDefaultToolkit().createImage("src/duckhunt/Images/crosshair.png");
        Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(16,16),"custom cursor");
        setCursor(c);
        
        this.addMouseListener(inputCont);
        this.add(new JLabel("Score: "));
        this.add(scoreLabel);
    }

    public void setManager(UnitManager dm) {
        this.dm = dm;
    }

    public void setScore(int score) {
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
        while (it.hasNext()) {
            Unit u = it.next();
            u.draw(g);
        }
    }
}
