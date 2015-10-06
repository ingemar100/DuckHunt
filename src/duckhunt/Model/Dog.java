package duckhunt.Model;

import javax.swing.ImageIcon;

public class Dog extends Unit {
    
    public Dog(int initialX, int initialY) {
        super(initialX, initialY);
    }

    public Dog() {
        super();
    }

    @Override
    protected String getImageLocation() {
        return "../Images/dog_with_duck_running.gif";
    }

    @Override
    public int getKillPoints() {
        return 250;
    }

    @Override
    protected void setBehavior() {
        behavior = new WalkingBehavior();
    }

}
