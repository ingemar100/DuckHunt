package duckhunt.Model;

import duckhunt.Boundary.AnimationPanel;
import duckhunt.Boundary.InputContainer;
import duckhunt.Boundary.ShootInput;
import duckhunt.Control.Game;
import duckhunt.Control.Sound;
import duckhunt.Control.UnitManager;
import duckhunt.Model.BaseLevelState;
import duckhunt.Model.Unit;
import java.applet.AudioClip;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Level1 extends BaseLevelState {

    private final AnimationPanel panel;
    private double time = 0;
    private final int MAX_SPAWN_TIME = 5; //seconds
    private int nextUnitTime = MAX_SPAWN_TIME * 1000 * 1000; //microseconds
    private final int TIME_FOR_SPECIAL = 1000;
    private int scoreUntilSpecial = TIME_FOR_SPECIAL;
    private static final int SCORE_TO_WIN = 2500;

    private UnitManager unitManager;
    private final UnitFactory factory = new UnitFactory();

    public Level1() {
        super();
        unitManager = new UnitManager(this);
        panel = new AnimationPanel(unitManager, "../Images/background.png");
        openingScene();
    }

    @Override
    public void update(double dt) {
        if (score > SCORE_TO_WIN){
            changeLevel();
        }
        
        time += dt;

        unitManager.update(dt);
        addDucks();
    }

    @Override
    public void move() {
        unitManager.move();
    }

    @Override
    public void collide(List<ShootInput> shots) {
        unitManager.collide(shots);
    }

    @Override
    public void render() {
        //panel calls draw on units
        panel.repaint();
    }

    public void addScore(int addedScore) {
        super.addScore(addedScore);
        panel.setScore(this.score);
        scoreUntilSpecial -= addedScore;
    }

    /*
     * Adds a unit if certain conditions are met 
     */
    private void addDucks() {
        if (scoreUntilSpecial <= 0) {
            scoreUntilSpecial = TIME_FOR_SPECIAL;

            Thread soundThreadDelay = new Thread() {
                public void run() {
                    Sound.BACKGROUND.stop();
                    Sound.BONUS.play();
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(duckhunt.Model.Level1.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Unit unit = factory.create("Special");
                    unitManager.addUnit(unit);
                    System.out.println(unit);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(duckhunt.Model.Level1.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Sound.BACKGROUND.loop();
                }
            };
            soundThreadDelay.start();
        } else if (time > nextUnitTime) {
            Unit unit = factory.create("Bird");

            //random spawntijd tussen 1-5 sec
            Random rn = new Random();
            int secs = rn.nextInt(MAX_SPAWN_TIME) + 1;
            nextUnitTime = secs * 1000 * 1000;
            time = 0;

            unitManager.addUnit(unit);
        }
    }

    private void openingScene() {
        Unit dog = factory.create("Chase");
        unitManager.addUnit(dog, 0);
    }

    public JPanel getPanel() {
        return panel;
    }

    @Override
    protected AudioClip getMusic() {
        return Sound.BACKGROUND;
    }

    public ArrayList<Unit> getUnits() {
        return unitManager.getUnits();
    }
}
