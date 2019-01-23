/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ihm.graphic;

import game.model.common.App;
import game.model.common.player.Player;
import game.model.nim.Nim;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
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
//--------------------------------------------
	private JPanel panelGameZone;
	private JPanel panelInfo;
	
	private JPanel panelGamePlay;
	private JPanel panelGameBoard;
	
	private JPanel panelInfoPlayers;
	private JPanel panelInfoRules;
	
	private JPanel panelEnd;
	private JPanel panelHisto;
//---------------------------------------------
	
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
        this.gameZone();
        this.info();
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
        this.menuItemGameSelect.addActionListener(this);
        this.menu.add(this.menuItemGameSelect);
        this.menu.addSeparator();
        this.menuItemChangePlayers = new JMenuItem("Change players");
        this.menuItemChangePlayers.addActionListener(this);
        this.menu.add(this.menuItemChangePlayers);
        this.menu.addSeparator();
        this.menuItemLeave = new JMenuItem("Leave game");
        this.menuItemLeave.addActionListener(this);
        this.menu.add(this.menuItemLeave);
        this.bar.add(this.menu);

        this.menu = new JMenu("Graphic");
        this.bar.add(this.menu);

        this.setJMenuBar(this.bar);

    }

    public void gameZone() 
    {
    	this.panelGameZone = new JPanel();
    	this.panelGameZone.setLayout(new BorderLayout());
    	this.add(this.panelGameZone, BorderLayout.CENTER);
    	this.gameBoard();
    	this.gamePlay();
    }
    
    public void info() 
    {
    	this.panelInfo = new JPanel();
    	this.panelInfo.setLayout(new BorderLayout());
    	this.add(this.panelInfo, BorderLayout.EAST);
    }

    public void gameBoard() 
    {
    	this.panelGameBoard = new JPanel();
    	this.panelGameZone.add(this.panelGameBoard, BorderLayout.CENTER);
    }

    public void gamePlay() 
    {
    	this.panelGamePlay = new JPanel();
    	this.panelGameZone.add(this.panelGamePlay, BorderLayout.SOUTH);
    }
    
    public void infoPlayers() 
    {
    	this.panelInfoPlayers = new JPanel();
    	this.listPlayers();
    	this.panelInfo.add(this.panelInfoPlayers, BorderLayout.NORTH);
    }
    
    public void infoRules() 
    {
    	this.panelInfoRules = new JPanel();
    	this.panelInfo.add(this.panelInfoRules, BorderLayout.SOUTH);
    }
    
    public void end() 
    {
    	this.panelEnd = new JPanel();
    	this.panelGamePlay.add(this.panelEnd, BorderLayout.EAST);
    }
    
    public void histo()
    {
    	this.panelHisto = new JPanel();
    	this.panelGamePlay.add(this.panelHisto, BorderLayout.WEST);
    }
    
    public void listPlayers() {
    	for(Player p : this.app.getGameSelected().getPlayersInGame())
    	{
    		this.panelInfoPlayers.add(new JLabel(p.toString() + " Win: " + p.getCptGameWin()));
    	}
    }

    /*------------------ Events Listeners ---------------------------*/
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == this.menuItemReset)
        {   
            if(((Nim)this.app.getGameSelected()).getMatches() != 0)
            {
                JOptionPane jop = new JOptionPane();
                int option = jop.showConfirmDialog(null, "Do you want to restart the game?",
                        "Restart game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(option == JOptionPane.OK_OPTION)
                {
                    this.nimGame();
                }
            }
            else
            {
                this.nimGame();
            }           
        }
        
        if(ae.getSource() == this.menuItemGameSelect)
        {
            if(((Nim)this.app.getGameSelected()).getMatches() != 0)
            {
                JOptionPane jop = new JOptionPane();
                int option = jop.showConfirmDialog(null, "Do you want to change game?",
                        "Change game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(option == JOptionPane.OK_OPTION)
                {
                    this.gameChoice();
                }
            }
            else
            {
                this.gameChoice();
            }
        }
        
        if (ae.getSource() == this.menuItemChangePlayers)
        {
            if(((Nim)this.app.getGameSelected()).getMatches() != 0)
            {
                JOptionPane jop = new JOptionPane();
                int option = jop.showConfirmDialog(null, "Do you want change players?",
                        "Change players", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(option == JOptionPane.OK_OPTION)
                {
                    this.creationPlayers();
                }
            }
            else
            {
                this.creationPlayers();
            }
        }
        
        if(ae.getSource() == this.menuItemLeave)
        {
            Credits c = new Credits();
            c.setTitle("Credits");
            c.pack();
            c.setLocationRelativeTo(null);
            c.setVisible(true);
            c.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.dispose();
        }
    }
    
    /*------------------- Functons Listener -----------------*/
    
    public void nimGame()
    {
        NimGame ng = new NimGame(this.app);
        ng.setTitle("Nim");
        ng.pack();                
        ng.setLocationRelativeTo(null);
        ng.setVisible(true);
        ng.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }
    
    public void gameChoice()
    {
        GameChoice gc = new GameChoice(this.app);
        gc.setTitle("Game Choice");
        gc.pack();                
        gc.setLocationRelativeTo(null);
        gc.setVisible(true);
        gc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }
    
    public void creationPlayers()
    {
        PlayersCreation pc = new PlayersCreation();
        pc.setTitle("Player Creation");
        pc.pack();
        pc.setLocationRelativeTo(null);
        pc.setVisible(true);
        pc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.dispose();
    }

    /*------------------- Getters and Setters ---------------*/

    public App getApp() 
    {
        return this.app;
    }
    
}
