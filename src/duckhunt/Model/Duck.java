package duckhunt.Model;

import java.util.Random;
import javax.swing.ImageIcon;

public class Duck extends Unit {
    
    public Duck(){
        super();
    }

    @Override
    protected String getImageLocation() {
        return "../Images/duck_hunt_bird_big2.gif";
    }

    @Override
    public int getKillPoints() {
        return 100;
    }

    @Override
    protected void setBehavior() {
        behavior = new FlyBehavior(this);
    }
}
