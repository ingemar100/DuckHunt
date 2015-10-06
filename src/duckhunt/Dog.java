package duckhunt;

import javax.swing.ImageIcon;

public class Dog extends Unit {
    
    public Dog(int initialX, int initialY) {
        super(initialX, initialY);
    }

    public Dog() {
        super();
    }

    @Override
    protected void move() {
        ySpeed = 0;
        xSpeed = RIGHT_SPEED;
    }

    @Override
    protected String getImageLocation() {
        return "Images/dog_with_duck_running.gif";
    }

    @Override
    public int getKillPoints() {
        return 250;
    }

}
