package game.ihm.text;

import game.model.common.App;
import game.model.common.Player;
import game.model.nim.Nim;

import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guillaume and Florian
 */
public class Text 
{
    private App app;
    private Scanner sc = new Scanner(System.in);
    
    public Text()
    {
        this.app = new App();
    }
    
    /* ------------- Getters and setters -----------------*/

    public App getApp()
    {
        return this.app;
    }
    
    public void textMain() 
    {
    	int endGame = 1;
    	Scanner sc = new Scanner(System.in);
        
        this.addPlayers();
        
    	// Choix du jeu
        while (endGame!=0)
        {
            int selectedGame = this.gameSelect();
            switch (selectedGame)
            {
                case 0 :
                    endGame = 0;
                    break;
                case 1 :
                	int cptMatches = 3;
                	int cptTakeMatches = 2;
                	boolean lastObjectTakenWin = false;
                	int scannerTest = 2;
                	boolean historic = false;
                	
                	while(cptMatches<3) 
                	{
                		System.out.println("Number of matches: ");
                		cptMatches = sc.nextInt();
                	}
                	while(cptTakeMatches<2)
                	{
                		System.out.println("Number of selected matches: ");
                		cptMatches = sc.nextInt();
                	}
                	while(scannerTest >1 || scannerTest<0)
                	{
                		System.out.println("0: You lose if you take the last matche");
                		System.out.println("1: You win if you take the last matche");
                		
                		scannerTest = sc.nextInt();
                	}
                	if(scannerTest == 1) 
                	{
                		lastObjectTakenWin = true;
                	}else {
                		lastObjectTakenWin = false;
                	}
                	scannerTest = 2;
                	while(scannerTest >1 || scannerTest<0)
                	{
                		System.out.println("0: Without historic?");
                		System.out.println("1: With historic?");
                		scannerTest = sc.nextInt();
                	}
                	
                	if(scannerTest == 1) 
                	{
                		lastObjectTakenWin = true;
                	}
                	else 
                	{
                		lastObjectTakenWin = false;
                	}
                	scannerTest = 2;
                	
                	this.app.setGameSelected(new Nim(cptMatches, cptTakeMatches, lastObjectTakenWin, historic));
                	this.app.getGameSelected().addPlayersInGame(
                            this.app.getGameSelected().getMaxPlayers(),
                            this.app.getTabPlayers());
                        
                        scannerTest = 3;
                        while(scannerTest!=0 && scannerTest!=1 && scannerTest!=2)
                        {
                            System.out.println("0 : Random begginer ");
                            System.out.println("1 : Younger begginer ");
                            System.out.println("2 : First alphabetic lastname begginer ");
                            scannerTest = sc.nextInt();
                        }
                	
                        switch(scannerTest)
                        {
                            case 0 :
                                this.app.randomSelectBegginer();
                                break;
                            case 1 :
                                this.app.youngerSelectBegginer();
                                break;
                            case 2 :
                                this.app.lastNameSelectBegginer();
                                break;
                            default :
                                break;
                        }
                        
                        Nim gameP = ((Nim)(this.app.getGameSelected()));	
                    	int cptRound = 0;
                    	Player pLast = gameP.getPlayerInGame(this.app.getBeginPlayer());
                        while(gameP.getMatches()>0)
                        {
                        	for(Player p : gameP.getPlayersInGame())
                        	{
                        		//0 because for the moment we have only 2 players
                        		if(((cptRound == 0 && this.app.getBeginPlayer() == 0) || (cptRound !=0))
                        				&& gameP.getMatches() != 0)
                        		{
                        			if(p.isComputer()) 
                        			{
                        				gameP.aiEasy();
                        			}
                        			else
                        			{
                        				
                        			}
                        			
                        			pLast = p;
                        		}
                        		cptRound++;
                        	}
                        }
                        Player winner;
                        if(gameP.getPlayerInGame(0) == pLast)
                		{                     	
                			winner = gameP.whoWin(pLast, gameP.getPlayerInGame(1));
                		}
                		else
                		{
                			winner = gameP.whoWin(pLast, gameP.getPlayerInGame(0)); 
                		}
                        System.out.println(winner.toString()+" win!");
                        
                default :
                    break;
            }
        }
    }
    
    
    /* ------------- We select all the players -------------*/
    
    public void addPlayers()
    {
        String lastName = "";
        int age;
        int bool = 2;
        boolean computer;
        int end = 2;
        while(end!=0)
        {
            end = 2;
            bool = 2;
            System.out.println("Player Name?");
            lastName = sc.next();
            System.out.println("Age?");
            age = sc.nextInt(); 
            while (bool !=0 && bool !=1)
            {
                System.out.println("It is a computer?");
                System.out.println("1 : yes");
                System.out.println("0 : no");
                bool = sc.nextInt();
            }            
            computer = bool == 1; // 1 it's true.
            this.app.setPlayer(new Player(lastName, age, computer));
            while (end !=0 && end !=1)
            {
                System.out.println("1 : next player");
                System.out.println("0 : end (we need minimum 2 players)");
                end = sc.nextInt();
            }
        }
    } 
    
    
    /* ------------------- Game Selection -----------------------*/
    
    public int gameSelect()
    {
        System.out.println("Game selection :");
        System.out.println("0 : Leave");
        System.out.println("1 : Nim");
        return sc.nextInt();
    }
}
