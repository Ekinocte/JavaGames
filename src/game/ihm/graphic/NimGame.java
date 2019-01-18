/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ihm.graphic;

import game.model.common.App;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author Florian et Guillaume
 */

public final class NimGame extends JFrame implements ActionListener
{
<<<<<<< HEAD
	private JPanel panelGame;
	private JPanel panelInfo;
	private JPanel panelGameZone;
	private JPanel panelGamePlay;
	private JPanel panelGameBoard;
	private JPanel panelEnd;
	private JPanel panelHisto;
	private JPanel panelInfoPlayers;
	private JPanel panelInfoRules;
    
=======
    private App app;
>>>>>>> 80ea6098e92478a9de05be1aa708b9a4b7aa615f
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem menuItem;
    
    public NimGame(App app)
    {
        this.app = app;
        this.setPreferredSize(new Dimension(600, 600));// to delete
        
        this.menu();
        this.game();
    }
    
    public void menu()
    {
        this.menuBar = new JMenuBar();
        
        this.menu = new JMenu("Game"); 
        this.menuItem = new JMenuItem("Reset nim");
        this.menu.add(this.menuItem);
        this.menu.addSeparator();
        this.menuItem = new JMenuItem("Game select");
        this.menu.add(this.menuItem);
        this.menu.addSeparator();
        this.menuItem = new JMenuItem("Change players");
        this.menu.add(this.menuItem);
        this.menu.addSeparator();
        this.menuItem = new JMenuItem("Leave game");
        this.menu.add(this.menuItem);
        
        this.menuBar.add(this.menu);
        
        this.menu = new JMenu("Graphic");
        this.menuBar.add(this.menu);
        
        this.setJMenuBar(this.menuBar);
    }
    
    public void global() {
    	this.game();
    	this.info();
    }
    
    public void game() {
    	this.panelGame = new JPanel();
    	this.add(this.panelGame, BorderLayout.CENTER);
    	this.gameZone();
    }
    
    public void gameZone() {
    	this.panelGameZone = new JPanel();
    	this.panelGameZone.setLayout(new BorderLayout());
    	this.gameBoard();
    	this.gamePlay();
    }
    
    public void gameBoard() {
    	this.panelGameBoard = new JPanel();
    	this.panelGameZone.add(this.panelGameBoard, BorderLayout.CENTER);
    }
    
    public void gamePlay() {
    	this.panelGamePlay = new JPanel();
    	this.panelGamePlay.add(this.panelGameBoard, BorderLayout.SOUTH);
    }
    
    public void info() {
    	
    }
    
    /*------------------ Events Listeners ---------------------------*/
    @Override
    public void actionPerformed(ActionEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*------------------- Getters and Setters ---------------*/

}
