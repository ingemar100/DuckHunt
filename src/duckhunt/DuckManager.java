/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JPanel;

/**
 *
 * @author Ingemar
 */
public class DuckManager implements ShootingListener{
    
    private static ArrayList<Duck> ducks = new ArrayList();
    private static int time = 0;
    private static final int KILL_POINTS = 100;
    private static boolean shooting;
    private static Point shotLocation;
    private AnimationPanel panel;
    
    public DuckManager(AnimationPanel panelParam){
        panel = panelParam;
    }

    public void update() {
        addDucks();
        ArrayList<Duck> deadDucks = new ArrayList();
        for (Duck duck : ducks) {
            //controleer of eend buiten scherm is
            if (isOffscreen(duck.getXPos(), duck.getYPos())) {
                deadDucks.add(duck);
                Sound.OFFSCREEN.play();
            } else {
                if (shooting) {
                    if (shotLocation.x > (duck.getXPos() - duck.getRadius()) && shotLocation.x < duck.getXPos() + duck.getRadius()
                            && shotLocation.y > (duck.getYPos() - duck.getRadius()) && shotLocation.y < duck.getYPos() + duck.getRadius()) {
                        System.out.println("geraakt");
                        duck.hit();
                        deadDucks.add(duck);
                        panel.addScore(KILL_POINTS);
                    }
                }
                duck.update();
            }
        }
        ducks.removeAll(deadDucks);
        shooting = false;
    }

    public ArrayList<Duck> getDucks(){
        return ducks;
    }
    
    public void render() {  
        panel.repaint();
    }

    private void addDucks() {
//        System.out.println("addDucks");
        if (time > 200) {
            System.out.println("addDuck");
            Duck d = new Duck(panel.getWidth() / 2, (int) (panel.getHeight() * 0.7));
            ducks.add(d);
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
}
