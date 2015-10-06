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
        u.setYSpeed(0);
    }

    @Override
    public void changeDirection() {
        u.setXSpeed(getDefaultRightSpeed());
        u.setYSpeed(0);
    }

    @Override
    public void move() {
        
        u.setXPos(u.getXPos() + u.getXSpeed());
        u.setYPos(u.getYPos() + u.getYSpeed());
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
