/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ihm.graphic;

import game.model.common.App;
import game.model.nim.Nim;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author Florian et Guillaume
 */

public final class NimGame extends JFrame implements ActionListener
{

	private JPanel panelGame;
	private JPanel panelInfo;
	private JPanel panelGameZone;
	private JPanel panelGamePlay;
	private JPanel panelGameBoard;
	private JPanel panelEnd;
	private JPanel panelHisto;
	private JPanel panelInfoPlayers;
	private JPanel panelInfoRules;

    private App app;

    private JMenuBar bar;
    private JMenu menu;
    private JMenuItem menuItemReset;
    private JMenuItem menuItemGameSelect;
    private JMenuItem menuItemChangePlayers;
    private JMenuItem menuItemLeave;


    public NimGame(App app)
    {
        this.app = app;
        Nim GameP = ((Nim)this.app.getGameSelected());
        GameP.setMatches(GameP.getMatchesBase());
        this.setPreferredSize(new Dimension(600, 600));// to delete

        this.menu();
        this.game();
    }

    public void menu()
    {
        this.bar = new JMenuBar();

        this.menu = new JMenu("Game");
        this.menuItemReset = new JMenuItem("Reset nim");
        this.menuItemReset.addActionListener(this);
        this.menu.add(this.menuItemReset);
        this.menu.addSeparator();
        this.menuItemGameSelect = new JMenuItem("Game select");
        this.menu.add(this.menuItemGameSelect);
        this.menu.addSeparator();
        this.menuItemChangePlayers = new JMenuItem("Change players");
        this.menu.add(this.menuItemChangePlayers);
        this.menu.addSeparator();
        this.menuItemLeave = new JMenuItem("Leave game");
        this.menu.add(this.menuItemLeave);
        this.bar.add(this.menu);

        this.menu = new JMenu("Graphic");
        this.bar.add(this.menu);

        this.setJMenuBar(this.bar);

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
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == this.menuItemReset)
        {   
            JOptionPane jop = new JOptionPane();
            int option = jop.showConfirmDialog(null, "Do you want to restart the game?",
                    "Restart game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if(option == JOptionPane.OK_OPTION)
            {
                NimGame ng2 = new NimGame(this.app);
                ng2.setTitle("Nim");
                ng2.pack();                
                ng2.setLocationRelativeTo(null);
                ng2.setVisible(true);
                ng2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                this.dispose();
            }
        }    
    }

    /*------------------- Getters and Setters ---------------*/

    public App getApp() 
    {
        return this.app;
    }
    
}
