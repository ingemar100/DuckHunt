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
public class SpecialFlyBehavior extends FlyBehavior{

    public SpecialFlyBehavior(Unit u) {
        super(u);
        
    }
    
    @Override
    public void move(){
        timeSinceChange += xSpeed;
        if (timeSinceChange > 100) {
            Random r = new Random();
            int i = r.nextInt(100) + 1;
            //chance of changing direction
            if (i > 50) {
                changeDirection();
            }
            timeSinceChange = 0;
        }
        u.setXPos(u.getXPos() + xSpeed);
        u.setYPos(u.getYPos() + ySpeed);

    }

    @Override
    public int getDefaultRightSpeed() {
        return 20;
    }

    @Override
    public int getDefaultDownSpeed() {
        return 8;
    }
    
}
