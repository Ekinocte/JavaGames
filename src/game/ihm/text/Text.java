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

    public App getGame()
    {
        return this.app;
    }
    
    public void textMain() 
    {
    	int endGame = 1;
    	Scanner sc = new Scanner(System.in);
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
                	int bool = 2;
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
                	while(bool >1 || bool<0)
                	{
                		System.out.println("0: You lose if you take the last matche");
                		System.out.println("1: You win if you take the last matche");
                		
                		bool = sc.nextInt();
                	}
                	if(bool == 1) 
                	{
                		lastObjectTakenWin = true;
                	}else {
                		lastObjectTakenWin = false;
                	}
                	bool = 2;
                	while(bool >1 || bool<0)
                	{
                		System.out.println("0: Without historic?");
                		System.out.println("1: With historic?");
                		bool = sc.nextInt();
                	}
                	
                	if(bool == 1) 
                	{
                		lastObjectTakenWin = true;
                	}
                	else 
                	{
                		lastObjectTakenWin = false;
                	}
                	bool = 2;
                	
                	this.app.setGameSelected(new Nim(cptMatches, cptTakeMatches, lastObjectTakenWin, historic));
                	this.app.addplayers(nim.getMaxPlayers());
                	
                	
                	
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
        while(end!=0 || this.app.getGameSelected<2)
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
