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
    protected void loadImage() {
        image = new ImageIcon(getClass().getResource("Images/dog_with_duck_running.gif")).getImage();
    }

}
