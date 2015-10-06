package duckhunt;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class UnitManager {

    private ArrayList<Unit> units = new ArrayList();
    private final AnimationPanel panel;
    private final Game game;
    private double time = 0;
    private final int MAX_SPAWN_TIME = 5; //seconds
    private int nextUnitTime = MAX_SPAWN_TIME * 1000 * 1000;
    private final int TIME_FOR_SPECIAL = 1000;
    private int scoreUntilSpecial = TIME_FOR_SPECIAL;

    private final UnitFactory factory = new UnitFactory();

    public UnitManager(AnimationPanel panelParam, Game game) {
        this.game = game;
        panel = panelParam;
        openingScene();
    }

    public void update(double dt) {
        time += dt;
        Iterator<Unit> it = units.iterator();
        
        while(it.hasNext()) {
            Unit unit = it.next();
            if (isOffscreen(unit.getXPos(), unit.getYPos())) {
                it.remove();
                Sound.OFFSCREEN.play();
            } else {
                unit.update();
            }
        }
        addDucks();
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

    private void addDucks() {
        if (scoreUntilSpecial <= 0) {
            scoreUntilSpecial = TIME_FOR_SPECIAL;
            Unit spec = factory.create("Special");
            spec.xPos = panel.getWidth() / 2;
            spec.yPos = (int) (panel.getHeight() * 0.7);
            units.add(spec);
            System.out.println(spec);
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

    private void openingScene() {
        Unit dog = factory.create("Chase");
        dog.xPos = 0;
        dog.yPos = 400;
        units.add(dog);
    }
}
