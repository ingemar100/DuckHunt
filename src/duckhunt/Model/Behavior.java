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
    
    public Behavior(Unit u){
        this.u = u;
        u.setXSpeed(getDefaultRightSpeed());
        u.setYSpeed(getDefaultUpSpeed());
    }
    public abstract void changeDirection();
    
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
