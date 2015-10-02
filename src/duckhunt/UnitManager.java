package duckhunt;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

public class UnitManager implements ShootingListener{
    
    private static ArrayList<Unit> units = new ArrayList();
    private static int time = 0;
    private static final int KILL_POINTS = 100;
    private static boolean shooting;
    private static Point shotLocation;
    private AnimationPanel panel;
    
    private UnitFactory factory = new UnitFactory();
    
    public UnitManager(AnimationPanel panelParam){
        panel = panelParam;
        panel.addShootingListener(this);
        openingScene();
    }

    public void update() {
        addDucks();
        ArrayList<Unit> deadUnits = new ArrayList();
        for (Unit unit : units) {
            //controleer of eend buiten scherm is
            if (isOffscreen(unit.getXPos(), unit.getYPos())) {
                deadUnits.add(unit);
                Sound.OFFSCREEN.play();
            } else {
                if (shooting) {
                    System.out.println("shooting");
                    if (shotLocation.x > (unit.getXPos() - unit.getRadius()) && shotLocation.x < unit.getXPos() + unit.getRadius()
                            && shotLocation.y > (unit.getYPos() - unit.getRadius()) && shotLocation.y < unit.getYPos() + unit.getRadius()) {
                        System.out.println("geraakt");
                        unit.hit();
                        deadUnits.add(unit);
                        panel.addScore(KILL_POINTS);
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
        if (time > 200) {
            Unit duck = factory.create("Bird"); 
            duck.xPos = panel.getWidth() / 2;
            duck.yPos = (int) (panel.getHeight() * 0.7);
            units.add(duck);
            Random rn = new Random();
            int n = 200 - 50 + 1;
            int i = rn.nextInt() % n;
            time = 50 + i;
        }
        time++;
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
