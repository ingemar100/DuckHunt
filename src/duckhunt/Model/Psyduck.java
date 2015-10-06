/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Model;

import static duckhunt.Model.Unit.DEF_RIGHT_SPEED;
import java.util.Random;
import javax.swing.ImageIcon;

/**
 *
 * @author Ingemar
 */
public class Psyduck extends Unit{
    public Psyduck(int initialX, int initialY) {
        super(initialX, initialY);
    }
    
    public Psyduck(){
        super();
    }
    
    @Override
    public String getImageLocation(){
        return "../Images/psyduck.gif";
    }
    
    @Override
    public int getKillPoints(){
        return 500;
    }

    @Override
    protected void setBehavior() {
        behavior = new TestBehavior();
    }
}
