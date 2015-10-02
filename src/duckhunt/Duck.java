package duckhunt;

import java.util.Random;
import javax.swing.ImageIcon;

public class Duck extends Unit {

    public Duck(int initialX, int initialY) {
        super(initialX, initialY);
                
        image = new ImageIcon(getClass().getResource("Images/duck_hunt_bird_big2.gif")).getImage();
    }
    
    @Override
     protected void move() {
        Random rn = new Random();
        int n = 10;
        int i = rn.nextInt(n) + 1;
        System.out.println(i);
        if (i < 5) {
            //verander x richting
            if (xSpeed == RIGHT_SPEED) {
                xSpeed = LEFT_SPEED;

            } else {
                xSpeed = RIGHT_SPEED;
            }
        } else if (i > 4) {
            //verander richting y
            if (ySpeed == DOWN_SPEED) {
                ySpeed = UP_SPEED;
            } else {
                ySpeed = DOWN_SPEED;
            }
        }
        if (yPos >= maxY) {
            ySpeed = UP_SPEED;
        }
    }
}
