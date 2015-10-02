package duckhunt;

import javax.swing.ImageIcon;

public class Goku extends Unit {

    public Goku(int initialX, int initialY) {
        super(initialX, initialY);

        image = new ImageIcon(getClass().getResource("Images/goku.gif")).getImage();

    }

    @Override
    protected void move() {
        ySpeed = 0;
        xSpeed = RIGHT_SPEED;
    }
}
