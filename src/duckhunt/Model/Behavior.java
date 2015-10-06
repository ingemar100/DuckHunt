/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Model;

/**
 *
 * @author Ingemar
 */
public abstract class Behavior {
    protected Unit u;
    protected int xSpeed = 0;
    protected int ySpeed = 0;
    
    public Behavior(Unit u){
        this.u = u;
        xSpeed = getDefaultRightSpeed();
        ySpeed = getDefaultUpSpeed();
    }
    
    public abstract void move();
    
    public abstract int getDefaultRightSpeed();
    
    public int getDefaultLeftSpeed(){
        return getDefaultRightSpeed() * -1;
    }
    
    public abstract int getDefaultDownSpeed();
    
    public int getDefaultUpSpeed(){
        return getDefaultDownSpeed() * -1;
    }
}
