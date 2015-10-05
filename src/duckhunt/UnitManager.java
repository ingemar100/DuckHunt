package duckhunt;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class UnitManager implements ShootingListener{
    
    private static ArrayList<Unit> units = new ArrayList();
    private static final int KILL_POINTS = 100;
    private static boolean shooting;
    private static Point shotLocation;
    private AnimationPanel panel;
    private Game game;
    private static double time = 0;
    private static final int MAX_SPAWN_TIME = 5; //seconds
    private static int nextUnitTime = MAX_SPAWN_TIME * 1000 * 1000;
    
    private UnitFactory factory = new UnitFactory();
    
    public UnitManager(AnimationPanel panelParam, Game game){
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
                Sound.OFFSCREEN.play();
            } else {
                if (shooting) {
                    if (shotLocation.x > (unit.getXPos() - unit.getRadius()) && shotLocation.x < unit.getXPos() + unit.getRadius()
                            && shotLocation.y > (unit.getYPos() - unit.getRadius()) && shotLocation.y < unit.getYPos() + unit.getRadius()) {
                        unit.hit();
                        deadUnits.add(unit);
                        game.addScore(KILL_POINTS);
                    }
                }
                unit.update();
            }
        }
        units.removeAll(deadUnits);
        shooting = false;
    }

    public ArrayList<Unit> getUnits(){
        return units;
    }
    
    public void render() {  
        panel.repaint();
    }

    private void addDucks() {
//        System.out.println("Time: " + time);
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
    
    private void openingScene(){
        Unit dog = factory.create("Chase");
        dog.xPos = 0;
        dog.yPos = 400;
        units.add(dog);
    }
}
