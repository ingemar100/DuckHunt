package duckhunt.Boundary;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class InputContainer implements MouseListener {

    private LinkedList<Input> inputs;
    private static InputContainer instance;

    private InputContainer() {
        inputs = new LinkedList<>();
    }

    public LinkedList<Input> getInputs() {
        LinkedList<Input> clone = (LinkedList<Input>) inputs.clone();
        inputs.clear();
        return clone;
    }

    @Override
    public void mouseClicked(MouseEvent me) {
//        System.out.println("a");   
    }

    @Override
    public void mousePressed(MouseEvent me) {
        Input i = new ShootInput(me.getPoint());
        inputs.add(i);
    }

    @Override
    public void mouseReleased(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static InputContainer getInstance(){
        if (instance == null){
            instance = new InputContainer();
        }
        return instance;
    }
}
