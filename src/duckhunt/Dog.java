package duckhunt;

import javax.swing.ImageIcon;

public class Dog extends Unit {

    public Dog(int initialX, int initialY) {
        super(initialX, initialY);
        
        image = new ImageIcon(getClass().getResource("Images/dog_with_duck_running.gif")).getImage();
    }

    @Override
    protected void move() {
        ySpeed = 0;
        xSpeed = RIGHT_SPEED;
    }
    
}
