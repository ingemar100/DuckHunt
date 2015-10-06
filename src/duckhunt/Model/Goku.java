package duckhunt.Model;

import javax.swing.ImageIcon;

public class Goku extends Unit {

    public Goku(int initialX, int initialY) {
        super(initialX, initialY);
    }

    @Override
    protected String getImageLocation() {
        return "../Images/goku.gif";
    }

    @Override
    public int getKillPoints() {
        return 9001;
    }

    @Override
    protected void setBehavior() {
        behavior = new TestBehavior();
    }
}
