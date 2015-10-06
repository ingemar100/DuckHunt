/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Model;

import java.util.Random;

/**
 *
 * @author Ingemar
 */
public class WalkingBehavior extends Behavior {

    public WalkingBehavior(Unit u) {
        super(u);
        ySpeed = 0;
    }

    @Override
    public void move() {
        
        u.setXPos(u.getXPos() + xSpeed);
        u.setYPos(u.getYPos() + ySpeed);
    }

    @Override
    public int getDefaultRightSpeed() {
        return 10;
    }

    @Override
    public int getDefaultDownSpeed() {
        return 0;
    }
}
