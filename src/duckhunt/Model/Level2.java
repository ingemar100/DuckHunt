package duckhunt.Model;

import duckhunt.Boundary.AnimationPanel;
import duckhunt.Boundary.ShootInput;
import duckhunt.Control.Sound;
import duckhunt.Control.UnitManager;
import java.applet.AudioClip;
import java.util.List;
import javax.swing.JPanel;

public class Level2 extends BaseLevelState {

    private AnimationPanel panel;
    private UnitManager unitManager;
    private static int TIME_FOR_GOKU = 200 * 1000;
    private int timeSinceGoku = 0;
    private int time = 0;
    private final int SCORE_TO_WIN = 1 * 1000 * 1000;

    public Level2() {
        super();
        unitManager = new UnitManager(this);
        panel = new AnimationPanel(unitManager, "../Images/background3.png");

    }

    @Override
    public void move() {
        unitManager.move();
    }
    
    public void addScore(int addedScore){
        super.addScore(addedScore);
        panel.setScore(this.score);
    }

    @Override
    public void collide(List<ShootInput> inputs) {
        unitManager.collide(inputs);
    }

    @Override
    public void update(double dt) {
        if (score > SCORE_TO_WIN){
            changeLevel();
        }
        
        time += dt;
        unitManager.update(dt);

        if ((time - timeSinceGoku) > TIME_FOR_GOKU) {
            Unit goku = UnitFactory.create("Chaser");
            unitManager.addUnit(goku);
            timeSinceGoku = time;
        }
    }

    @Override
    public void render() {
        panel.repaint();
    }

    @Override
    protected AudioClip getMusic() {
        return Sound.BACKGROUND2;
    }

    @Override
    public JPanel getPanel() {
        return panel;
    }

}
