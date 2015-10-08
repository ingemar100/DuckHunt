package duckhunt.Boundary;

import duckhunt.Boundary.Input;
import java.awt.Point;

public class ShootInput extends Input {
    private Point point;

    
    public ShootInput(Point point) {
        this.point = point;
    }
    
    public Point getPoint() {
        return point;
    }
}
