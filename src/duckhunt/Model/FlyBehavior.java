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
public class FlyBehavior extends Behavior {

    protected int lastChange;

    public FlyBehavior(Unit u) {
        super(u);
    }

    public void changeDirection() {
        Random rn = new Random();
        int n = 10;
        int i = rn.nextInt(n) + 1;
        if (i < 5) {
            //verander x richting
            if (xSpeed == getDefaultRightSpeed()) {
                xSpeed = getDefaultLeftSpeed();

            } else {
                xSpeed = getDefaultRightSpeed();
            }
        } else if (i > 4) {
            //verander richting y
            if (ySpeed == getDefaultDownSpeed()) {
                ySpeed = getDefaultUpSpeed();
            } else {
                ySpeed = getDefaultDownSpeed();
            }
        }
    }

    @Override
    public void move() {
        lastChange += xSpeed;
        if (lastChange > 50) {
            Random r = new Random();
            int i = r.nextInt(100) + 1;
            //chance of changing direction
            if (i > 50) {
                changeDirection();
            }
            lastChange = 0;
        }
        u.setXPos(u.getXPos() + xSpeed);
        u.setYPos(u.getYPos() + ySpeed);

    }

    @Override
    public int getDefaultRightSpeed() {
        return 10;
    }

    @Override
    public int getDefaultDownSpeed() {
        return 4;
    }
}
