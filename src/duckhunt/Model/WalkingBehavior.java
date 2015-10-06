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
public class WalkingBehavior extends Behavior {

    public void move(Unit u) {
        u.setXSpeed(DEF_RIGHT_SPEED);
        u.setYSpeed(0);
    }
}
