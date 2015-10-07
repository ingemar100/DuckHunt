package duckhunt.Boundary;

import duckhunt.Boundary.InputContainer;
import duckhunt.Control.UnitManager;
import duckhunt.Model.Unit;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.Iterator;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AnimationPanel extends JPanel {

    private JLabel scoreLabel = new JLabel("0");
    private UnitManager unitManager;
    private Image backgroundImage;

    public AnimationPanel(UnitManager um, String backGround) {
        this.unitManager = um;
        
        this.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        backgroundImage = new ImageIcon(getClass().getResource(backGround)).getImage();

        Image im = Toolkit.getDefaultToolkit().createImage("src/duckhunt/Images/crosshair_64px.png");
        Cursor c = Toolkit.getDefaultToolkit().createCustomCursor(im, new Point(16, 16), "custom cursor");
        setCursor(c);

        this.addMouseListener(InputContainer.getInstance());
        this.add(new JLabel("Score: "));
        this.add(scoreLabel);
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
        List<Unit> units = (List<Unit>) unitManager.getUnits();
        Iterator<Unit> it = units.iterator();
        while (it.hasNext()) {
            Unit u = it.next();
            u.draw(g);
        }
    }
}
