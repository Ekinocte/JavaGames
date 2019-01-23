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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
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
    
    private JLabel boardTitle;
    private JLabel boardMatches;
    
    private JButton bEnd;
    private JComboBox cbMatchesTaken;


    public NimGame(App app)
    {
        this.app = app;
        Nim gameP = ((Nim)this.app.getGameSelected());
        gameP.setMatches(gameP.getMatchesBase());
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
    	this.getContentPane().add(this.panelGameZone, BorderLayout.CENTER);
    	this.gameBoard();
    	this.gamePlay();
    }
    
    public void info() 
    {
    	this.panelInfo = new JPanel();
    	this.panelInfo.setLayout(new BorderLayout());
    	this.getContentPane().add(this.panelInfo, BorderLayout.EAST);
    	this.infoPlayers();
    	this.infoRules();
    }

    public void gameBoard() 
    {
        Nim gameP = ((Nim)this.app.getGameSelected());
        gameP.setMatches(gameP.getMatchesBase());
        
    	this.panelGameBoard = new JPanel();
        
        this.boardTitle = new JLabel("Nb matches left :" + gameP.getMatches());
        this.boardTitle.setHorizontalAlignment(JLabel.CENTER);
        this.panelGameBoard.add(boardTitle, BorderLayout.NORTH);
        this.boardDisplayMatches();
        
    	this.panelGameZone.add(this.panelGameBoard, BorderLayout.CENTER);
    	this.panelGameBoard.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }

    public void gamePlay() 
    {
    	this.panelGamePlay = new JPanel();
    	this.panelGameZone.add(this.panelGamePlay, BorderLayout.SOUTH);
    	this.end();
    	this.histo();
    }
    
    public void infoPlayers() 
    {
    	this.panelInfoPlayers = new JPanel();
    	this.panelInfoPlayers.setLayout(new BoxLayout(this.panelInfoPlayers, BoxLayout.Y_AXIS));
    	this.listPlayers();
    	this.panelInfo.add(this.panelInfoPlayers, BorderLayout.NORTH);
    	this.panelInfoPlayers.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public void infoRules() 
    {
    	this.panelInfoRules = new JPanel();
    	this.panelInfoRules.setLayout(new BoxLayout(this.panelInfoRules, BoxLayout.Y_AXIS));
    	this.panelInfo.add(this.panelInfoRules, BorderLayout.CENTER);
    	this.panelInfoRules.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    	this.listrules();
    }
    
    public void end() 
    {
    	this.panelEnd = new JPanel();
    	this.panelEnd.setLayout(new BorderLayout());
    	
    	this.bEnd = new JButton("End round");
    	this.bEnd.addActionListener(this);
    	this.cbMatchesTaken = new JComboBox();
    	
    	Nim n = (Nim)this.app.getGameSelected();
    	for(int i = 1; i<=n.getCptTakeMatches(); i++)
    	{
    		this.cbMatchesTaken.addItem(i);
    	}
    	
    	this.panelEnd.add(this.cbMatchesTaken, BorderLayout.WEST);
    	this.panelEnd.add(this.bEnd, BorderLayout.EAST);
    	
    	this.panelGamePlay.add(this.panelEnd, BorderLayout.EAST);
    	this.panelEnd.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public void histo()
    {
    	this.panelHisto = new JPanel();
    	this.panelGamePlay.add(this.panelHisto, BorderLayout.WEST);
    	this.panelHisto.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
    
    public void listPlayers() 
    {
    	for(Player p : this.app.getGameSelected().getPlayersInGame())
    	{
    		this.panelInfoPlayers.add(new JLabel("\n"+p.toString()));
    		this.panelInfoPlayers.add(new JLabel(" Win: " + p.getCptGameWin()));
    		this.panelInfoPlayers.add(new JLabel(" Games played: " + p.getCptGamePlayed()));
    		this.panelInfoPlayers.add(new JLabel(" \n \n "));
    	}
    }
    
    public void listrules() 
    {
    	Nim n = (Nim)this.app.getGameSelected();
    	this.panelInfoRules.add(new JLabel("\n Historic: "+n.isHistoric()));
    	if(n.getLastObjectTakenWin()) 
    	{
    		this.panelInfoRules.add(new JLabel("Game rule: last matches taken win"));
    	}
    	else
    	{
    		this.panelInfoRules.add(new JLabel("Game rule: last matches taken lose"));
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
        
        if(ae.getSource() == this.bEnd) 
        {
        	int i = (int) this.cbMatchesTaken.getSelectedItem();
        	((Nim)this.app.getGameSelected()).takeMatches(i);
        	
        	this.gameZone();
        	this.panelGameZone.repaint();
        	this.panelGameZone.revalidate();
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

    /*------------------- Functions game board --------------*/
    
    public void boardDisplayMatches()
    {
        Nim gameP = ((Nim)this.app.getGameSelected());
        gameP.setMatches(gameP.getMatchesBase());
        
        String str = new String();
        for (int i=0; i<gameP.getMatches(); i++)
        {
            str = str + " |";
        }
        
        this.boardMatches = new JLabel(str);
        this.boardMatches.setHorizontalAlignment(JLabel.CENTER);
        this.panelGameBoard.add(this.boardMatches, BorderLayout.CENTER);
    }
    
    /*------------------- Getters and Setters ---------------*/

    public App getApp() 
    {
        return this.app;
    }
    
}
