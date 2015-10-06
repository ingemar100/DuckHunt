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
public abstract class MoveBehavior {
    protected Unit u;
    protected int xSpeed = 0;
    protected int ySpeed = 0;
    protected double time = 0;
    
    public MoveBehavior(Unit u){
        this.u = u;
        xSpeed = getDefaultRightSpeed();
        ySpeed = getDefaultUpSpeed();
    }
    
    public void update(double dt){
        time += dt;
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
