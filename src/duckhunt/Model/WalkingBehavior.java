package duckhunt.Model;

public class WalkingBehavior extends MoveBehavior {

    public WalkingBehavior(Unit u) {
        super(u);
        ySpeed = 0;
    }

    @Override
    public void move() {
        
        u.setXPos(u.getXPos() + xSpeed);
        u.setYPos(u.getYPos() + ySpeed);
    }

    @Override
    public int getDefaultRightSpeed() {
        return 10;
    }

    @Override
    public int getDefaultDownSpeed() {
        return 0;
    }
}
