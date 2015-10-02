package duckhunt;

import javax.swing.ImageIcon;

public class Goku extends Unit {

    public Goku(int initialX, int initialY) {
        super(initialX, initialY);
    }

    @Override
    protected void move() {
        ySpeed = 0;
        xSpeed = RIGHT_SPEED;
    }

    @Override
    protected void loadImage() {
        image = new ImageIcon(getClass().getResource("Images/goku.gif")).getImage();
    }
}
