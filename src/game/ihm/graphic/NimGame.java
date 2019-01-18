/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ihm.graphic;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

/**
 *
 * @author Florian et Guillaume
 */

public class NimGame extends JFrame implements ActionListener
{
    
    private JMenuBar menuBar;
    private JMenu menu;
    
    
    public NimGame()
    {
        this.setPreferredSize(new Dimension(600, 600));// to delete
        
        this.menu();

    }
    
    public void menu()
    {
        this.menuBar = new JMenuBar();
        
        this.menu = new JMenu("Game"); 
        this.menuBar.add(this.menu);
        
        this.menu = new JMenu("Graphic");
        this.menuBar.add(this.menu);
        
        
    }
    
    /*------------------ Events Listeners ---------------------------*/
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*------------------- Getters and Setters ---------------*/

}
