package duckhunt;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UnitManager implements ShootingListener {

    private ArrayList<Unit> units = new ArrayList();
    private final int KILL_POINTS = 100;
    private boolean shooting;
    private Point shotLocation;
    private AnimationPanel panel;
    private Game game;
    private double time = 0;
    private final int MAX_SPAWN_TIME = 5; //seconds
    private int nextUnitTime = MAX_SPAWN_TIME * 1000 * 1000;
    private final int TIME_FOR_SPECIAL = 1000;
    private int scoreUntilSpecial = TIME_FOR_SPECIAL;

    private UnitFactory factory = new UnitFactory();

    public UnitManager(AnimationPanel panelParam, Game game) {
        this.game = game;
        panel = panelParam;
        panel.addShootingListener(this);
        openingScene();
    }

    public void update(double dt) {
//        System.out.println("Dt: " + dt);
        time += dt;
        addDucks();
        ArrayList<Unit> deadUnits = new ArrayList();
        for (Unit unit : units) {
            //controleer of eend buiten scherm is
            if (isOffscreen(unit.getXPos(), unit.getYPos())) {
                deadUnits.add(unit);
                //Sound.OFFSCREEN.play();
            } else {
                if (shooting) {
                    if (shotLocation.x > (unit.getXPos() - unit.getRadius()) && shotLocation.x < unit.getXPos() + unit.getRadius()
                            && shotLocation.y > (unit.getYPos() - unit.getRadius()) && shotLocation.y < unit.getYPos() + unit.getRadius()) {
                        unit.hit();
                        deadUnits.add(unit);

                        addScore(KILL_POINTS);
                    }
                }
                unit.update();
            }
        }
        units.removeAll(deadUnits);
        shooting = false;
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void render() {
        panel.repaint();
    }

    private void addScore(int score) {
        game.addScore(score);
        scoreUntilSpecial -= score;
    }

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
                        Logger.getLogger(UnitManager.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Unit spec = factory.create("Special");
                    spec.xPos = panel.getWidth() / 2;
                    spec.yPos = (int) (panel.getHeight() * 0.7);
                    units.add(spec);
                    System.out.println(spec);

                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(UnitManager.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Sound.BACKGROUND.loop();
                }
            };
            soundThreadDelay.start();
        }

        if (time > nextUnitTime) {
            Unit duck = factory.create("Bird");
            duck.xPos = panel.getWidth() / 2;
            duck.yPos = (int) (panel.getHeight() * 0.7);
            units.add(duck);

            //random spawntijd tussen 1-5 sec
            Random rn = new Random();
            int secs = rn.nextInt(MAX_SPAWN_TIME) + 1;
            nextUnitTime = secs * 1000 * 1000;
//            System.out.println("create duck");
            time = 0;
        }

    }

    private boolean isOffscreen(int x, int y) {
        return x < 0 || x > panel.getWidth() || y < 0 || y > panel.getHeight();
    }

    @Override
    public void shoot(Point p) {
        shooting = true;
        shotLocation = p;
    }

    private void openingScene() {
        Unit dog = factory.create("Chase");
        dog.xPos = 0;
        dog.yPos = 400;
        units.add(dog);
    }
}
