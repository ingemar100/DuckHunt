/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Boundary;

import duckhunt.Boundary.Input;
import java.awt.Point;

/**
 *
 * @author Ingemar
 */
public class ShootInput extends Input {
    private Point point;

    
    public ShootInput(Point point) {
        this.point = point;
    }
    
    public Point getPoint() {
        return point;
    }
}
