package duckhunt.Boundary;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class View {
    private JFrame frame;
    
    public View(String gameName){
        frame = new JFrame(gameName);
    }
    
    public void createAndShowUI() {

        frame.setExtendedState(frame.MAXIMIZED_BOTH);
        frame.setMinimumSize(Toolkit.getDefaultToolkit().getScreenSize());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    
    public void setPanel(JPanel panel){
        frame.getContentPane().removeAll();
        frame.getContentPane().add(panel);
    }
}
