/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ihm.graphic;

import game.model.common.App;
import game.model.common.player.ComputerNim;
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
    
    private JLabel boardPlayer;
    private JLabel boardTitle;
    private JLabel boardMatches;
    
    private JLabel winner;
    
    private JButton bEnd;
    private JComboBox cbMatchesTaken;
    
    private int round = 0;


    public NimGame(App app)
    {
    	int turn;
        this.app = app;
        Nim gameP = ((Nim)this.app.getGameSelected());
        gameP.setMatches(gameP.getMatchesBase());
        this.setPreferredSize(new Dimension(600, 600));// to delete

        this.menu();
        if((this.app.getGameSelected().getBeginPlayer()==0 && this.round%2==0)
                || (this.app.getGameSelected().getBeginPlayer()==1 && this.round%2!=0))
        {
            turn = 0;        
        }
        else
        {
            turn=1;
        }
    	
    	if(((Nim)this.app.getGameSelected()).getPlayerInGame(turn) instanceof ComputerNim)
    	{
    		this.computerPlay((ComputerNim)((Nim)this.app.getGameSelected()).getPlayerInGame(turn));
    	}
    	else
    	{
    		this.gameZone();
    	}
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
        int turn;
        Nim gameP = ((Nim)this.app.getGameSelected());
    	this.panelGameBoard = new JPanel();
        
        if((this.app.getGameSelected().getBeginPlayer()==0 && this.round%2==0)
                || (this.app.getGameSelected().getBeginPlayer()==1 && this.round%2!=0))
        {
            turn = 1;        
        }
        else
        {
            turn=2;
        }
        this.boardPlayer = new JLabel("Player :" + turn);
        this.boardPlayer.setHorizontalAlignment(JLabel.CENTER);
        this.panelGameBoard.add(boardPlayer, BorderLayout.NORTH);
        
        this.boardDisplayMatches();
        
        this.boardTitle = new JLabel("Nb matches left :" + gameP.getMatches());
        this.boardTitle.setHorizontalAlignment(JLabel.CENTER);
        this.panelGameBoard.add(boardTitle, BorderLayout.SOUTH);
        
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
        for (int i=0; i<this.app.getGameSelected().getPlayersInGame().size(); i++)
        {
            this.panelInfoPlayers.add(
                    new JLabel("\n Player :" +(i+1) + " "
                            +this.app.getGameSelected().getPlayerInGame(i).getLastName()
                            +" Win: " + this.app.getGameSelected().getPlayerInGame(i).getCptGameWin()
                            + " Games played: " 
                            +this.app.getGameSelected().getPlayerInGame(i).getCptGamePlayed()));
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
            Nim gameP = ((Nim)this.app.getGameSelected());
            if(((Nim)this.app.getGameSelected()).getMatches() != 0)
            {
                JOptionPane jop = new JOptionPane();
                int option = jop.showConfirmDialog(null, "Do you want to restart the game?",
                        "Restart game", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if(option == JOptionPane.OK_OPTION)
                {
                    gameP.setMatches(gameP.getMatchesBase());
                    this.nimGame();
                }
            }
            else
            {
                gameP.setMatches(gameP.getMatchesBase());
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
        	int turn;
        	int i = (int) this.cbMatchesTaken.getSelectedItem();
        	((Nim)this.app.getGameSelected()).takeMatches(i);
        	
        	this.round++;
        	
        	this.panelGameZone.removeAll();
                if (((Nim)this.app.getGameSelected()).getMatches()>0)
                {
                	if((this.app.getGameSelected().getBeginPlayer()==0 && this.round%2==0)
                            || (this.app.getGameSelected().getBeginPlayer()==1 && this.round%2!=0))
                    {
                        turn = 1;        
                    }
                    else
                    {
                        turn=2;
                    }
                	
                	if(((Nim)this.app.getGameSelected()).getPlayerInGame(turn-1) instanceof ComputerNim)
                	{
                		this.computerPlay((ComputerNim)((Nim)this.app.getGameSelected()).getPlayerInGame(turn-1));
                	}
                	else
                	{
                		this.gameZone();
                	}
                }
                else
                {
                    this.winner();
                }
        	this.panelGameZone.revalidate();
        	this.panelGameZone.repaint();
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
        gameP.setMatches(gameP.getMatches());
        
        String str = new String();
        for (int i=0; i<gameP.getMatches(); i++)
        {
            str = str + " |";
        }
        
        this.boardMatches = new JLabel(str);
        this.boardMatches.setHorizontalAlignment(JLabel.CENTER);
        this.panelGameBoard.add(this.boardMatches, BorderLayout.CENTER);
    }
    
    public void computerPlay(ComputerNim cn) {
    	int turn;
    	Nim n = ((Nim)this.app.getGameSelected());
    	int mTaken = cn.aiEasy(n.getMatches(), n.getCptTakeMatches());
    	n.takeMatches(mTaken);
    	if (this.panelGameZone != null)
    	{
        	this.panelGameZone.removeAll();
    	}
    	this.round++;
    	if (((Nim)this.app.getGameSelected()).getMatches()>0)
        {
        	if((this.app.getGameSelected().getBeginPlayer()==0 && this.round%2==0)
                    || (this.app.getGameSelected().getBeginPlayer()==1 && this.round%2!=0))
            {
                turn = 1;        
            }
            else
            {
                turn=0;
            }
        	
        	if(((Nim)this.app.getGameSelected()).getPlayerInGame(turn) instanceof ComputerNim)
        	{
        		this.computerPlay((ComputerNim)((Nim)this.app.getGameSelected()).getPlayerInGame(turn));
        	}
        	else
        	{
        		this.gameZone();
        	}
        }
        else
        {
            this.winner();
        }
        this.panelGameZone.revalidate();
        this.panelGameZone.repaint();
    }
    
    /*-------------------- Win ------------------------------*/
    
    public void winner()
    {
        int player;
        this.panelGameZone = new JPanel();
        this.panelGameZone.setLayout(new BorderLayout());
    	this.getContentPane().add(this.panelGameZone, BorderLayout.CENTER);
        
        if((this.app.getGameSelected().getBeginPlayer()==0 && this.round%2==0)
                || (this.app.getGameSelected().getBeginPlayer()==1 && this.round%2!=0))
        {
            player = 0;        
        }
        else
        {
            player=1;
        }
        
        this.app.getGameSelected().getPlayerInGame(0).setCptGamePlayed(
                    this.app.getGameSelected().getPlayerInGame(0).getCptGamePlayed()+1);
        this.app.getGameSelected().getPlayerInGame(1).setCptGamePlayed(
                    this.app.getGameSelected().getPlayerInGame(1).getCptGamePlayed()+1);
        
        if(((Nim)this.app.getGameSelected()).getLastObjectTakenWin() ==true)
        {
            if (player==0)
            {
                this.winner = new JLabel("Player" + 2 + "win");
                this.panelGameZone.add(this.winner, BorderLayout.CENTER);
                this.app.getGameSelected().getPlayerInGame(1).setCptGameWin(
                        this.app.getGameSelected().getPlayerInGame(1).getCptGameWin()+1);
                    
            }
            else
            {
                this.winner = new JLabel("Player " + 1 + " win");
                this.panelGameZone.add(this.winner, BorderLayout.CENTER); 
                this.app.getGameSelected().getPlayerInGame(0).setCptGameWin(
                        this.app.getGameSelected().getPlayerInGame(0).getCptGameWin()+1);
            }
        }
        else
        {
            if(player==0)
            {
                this.winner = new JLabel("Player " + 1 + " win");
                this.panelGameZone.add(this.winner, BorderLayout.CENTER);
                this.app.getGameSelected().getPlayerInGame(0).setCptGameWin(
                        this.app.getGameSelected().getPlayerInGame(0).getCptGameWin()+1);
            }
            else
            {
                this.winner = new JLabel("Player " + 2 + " win");
                this.panelGameZone.add(this.winner, BorderLayout.CENTER);
                this.app.getGameSelected().getPlayerInGame(1).setCptGameWin(
                        this.app.getGameSelected().getPlayerInGame(1).getCptGameWin()+1);
            }
        }
                    
    }
    
    /*------------------- Getters and Setters ---------------*/

    public App getApp() 
    {
        return this.app;
    }
    
}
