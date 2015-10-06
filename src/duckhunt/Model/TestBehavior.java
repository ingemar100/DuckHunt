/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Model;

import static duckhunt.Model.Unit.DEF_RIGHT_SPEED;
import java.util.Random;

/**
 *
 * @author Ingemar
 */
public class TestBehavior extends Behavior{

    public void move(Unit u) {
        Random rn = new Random();
        int n = 10;
        int i = rn.nextInt(n) + 1;
        if (i < 5) {
            //verander x richting
            if (u.getXSpeed() == DEF_RIGHT_SPEED) {
                u.setXSpeed(u.DEF_LEFT_SPEED);

            } else {
                u.setXSpeed(DEF_RIGHT_SPEED);
            }
        } else if (i > 4) {
            //verander richting y
            if (u.getYSpeed() == u.DEF_DOWN_SPEED) {
                u.setYSpeed(u.DEF_UP_SPEED);
            } else {
                u.setYSpeed(u.DEF_DOWN_SPEED);
            }
        }
    }
}
