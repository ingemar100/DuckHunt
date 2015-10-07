/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt.Boundary;

import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author Ingemar
 */
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
