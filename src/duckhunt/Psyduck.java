/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package duckhunt;

import javax.swing.ImageIcon;

/**
 *
 * @author Ingemar
 */
public class Psyduck extends Duck{
    @Override
    public String getImageLocation(){
        return "Images/psyduck.gif";
    }
    
    @Override
    public int getKillPoints(){
        return 500;
    }
}
