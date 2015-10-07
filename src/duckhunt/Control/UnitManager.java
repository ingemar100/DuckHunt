/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Control;

import duckhunt.Model.Level1;
import duckhunt.Boundary.ShootInput;
import duckhunt.Model.BaseLevelState;
import duckhunt.Model.Unit;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Ingemar
 */
public class UnitManager {
    
    private BaseLevelState level;
    private ArrayList<Unit> units = new ArrayList();
    
    public UnitManager(BaseLevelState level){
        this.level = level;
    }
    
    public void move(){
        
        Iterator<Unit> it = units.iterator();

        while (it.hasNext()) {
            Unit unit = it.next();
            unit.move();
        }
    }
    
    public void collide(List<ShootInput> shots){
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

                    level.addScore(unit.getKillPoints());
                }
            }
        }
    }
    
    public void update(double dt){
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
    }
    
    public ArrayList<Unit> getUnits() {
        return (ArrayList<Unit>) units.clone();
    }

    private boolean isOffscreen(int x, int y) {
        return x < 0 || x > level.getPanel().getWidth() || y < 0 || y > level.getPanel().getHeight();
    }
    
    public void addUnit(Unit unit) {
        Random r = new Random();

        int minX = (int) (level.getPanel().getWidth() * 0.4);
        int xMargin = (int) (level.getPanel().getWidth() * 0.2);
        int x = (r.nextInt(xMargin) + minX);

        addUnit(unit, x, getSpawnHeight());
    }
    
    public void addUnit(Unit unit, int x, int y){        
        unit.setXPos(x);
        unit.setYPos(y);
        units.add(unit);
    }
    
    public void addUnit(Unit unit, int x){
        addUnit(unit, x, getSpawnHeight());
    }
    
    private int getSpawnHeight(){
        return (int) (level.getPanel().getHeight() * 0.7);
    }
}
