package duckhunt.Model;

public class Psyduck extends Unit{
    
    public Psyduck(){
        super();
    }
    
    @Override
    public String getImageLocation(){
        return "../Images/psyduck.gif";
    }
    
    @Override
    public int getKillPoints(){
        return 500;
    }

    @Override
    protected void setBehavior() {
        behavior = new FlyBehavior(this);
    }
}
