/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ihm.events;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JRadioButton;

/**
 *
 * @author Guillaume & Florian
 */
public class PlayersCreationBtnHuman implements ActionListener
{

    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() instanceof JRadioButton)
        {
           if(((JRadioButton)ae.getSource()).isSelected())
           {
                System.out.println("test");
           }
        }
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
