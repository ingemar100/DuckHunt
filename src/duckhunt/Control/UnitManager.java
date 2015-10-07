package duckhunt.Control;

import duckhunt.Boundary.AnimationPanel;
import duckhunt.Boundary.ShootInput;
import duckhunt.Model.Unit;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnitManager {

    private ArrayList<Unit> units = new ArrayList();
    private final AnimationPanel panel;
    private final Game game;
    private double time = 0;
    private final int MAX_SPAWN_TIME = 5; //seconds
    private int nextUnitTime = MAX_SPAWN_TIME * 1000 * 1000; //microseconds
    private final int TIME_FOR_SPECIAL = 1000;
    private int scoreUntilSpecial = TIME_FOR_SPECIAL;

    private final UnitFactory factory = new UnitFactory();

    public UnitManager(AnimationPanel panelParam, Game game) {
        this.game = game;
        panel = panelParam;
        openingScene();
    }
    
    public void update(double dt){
        time += dt;
        Iterator<Unit> it = units.iterator();

        while (it.hasNext()) {
            Unit unit = it.next();
            if (isOffscreen(unit.getXPos(), unit.getYPos())) {
                it.remove();
                Sound.OFFSCREEN.play();
            }
            else {
                unit.update(dt);
            }
        }
        addDucks();
    }

    public void move() {
        Iterator<Unit> it = units.iterator();

        while (it.hasNext()) {
            Unit unit = it.next();
            unit.move();
        }
    }

    public void collide(List<ShootInput> shots) {
        Iterator<ShootInput> it = shots.iterator();

        while (it.hasNext()) {
            ShootInput shot = it.next();
            Iterator<Unit> unitIter = units.iterator();

            while (unitIter.hasNext()) {
                Unit unit = unitIter.next();
                Point p = shot.getPoint();

                if (p.x > (unit.getXPos() - unit.getRadius()) && p.x < unit.getXPos() + unit.getRadius()
                        && p.y > (unit.getYPos() - unit.getRadius()) && p.y < unit.getYPos() + unit.getRadius()) {
                    unit.hit();
                    unitIter.remove();

                    addScore(unit.getKillPoints());
                }
            }
        }
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void render() {
        //panel calls draw on units
        panel.repaint();
    }

    private void addScore(int score) {
        game.addScore(score);
        scoreUntilSpecial -= score;
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
                        Logger.getLogger(duckhunt.Control.UnitManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    Unit unit = factory.create("Special");
                    addUnit(unit);
                    System.out.println(unit);
                    
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(duckhunt.Control.UnitManager.class.getName()).log(Level.SEVERE, null, ex);
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

            addUnit(unit);
        }
    }

    private void addUnit(Unit unit) {
        Random r = new Random();

        int minX = (int) (panel.getWidth() * 0.4);
        int xMargin = (int) (panel.getWidth() * 0.2);
        int x = (r.nextInt(xMargin) + minX);

        unit.setXPos(x);
        unit.setYPos(getSpawnHeight());
        units.add(unit);
    }

    private boolean isOffscreen(int x, int y) {
        return x < 0 || x > panel.getWidth() || y < 0 || y > panel.getHeight();
    }

    private void openingScene() {
        Unit dog = factory.create("Chase");
        dog.setXPos(0);
        dog.setYPos(getSpawnHeight());
        units.add(dog);
    }
    
    private int getSpawnHeight(){
        return (int) (panel.getHeight() * 0.7);
    }
}
