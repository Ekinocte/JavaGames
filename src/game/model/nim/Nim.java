/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model.nim;

import java.util.Random;
import java.util.Stack;

import game.model.common.Game;
import game.model.common.player.Player;

/**
 *
 * @author Guillaume and Florian
*/

public class Nim extends Game
{
    private int cptMatches; // cptMaches = N
    private int cptTakeMatches; // cptTakeMatches = K
    private boolean lastObjectTakenWin; // lastObjectTaken = V
    private boolean historic;
    private Stack<Integer> stackHistoric= new Stack<Integer>();
    private final int maxPlayers = 2;
    public final static int cptMatchesMin = 3;
    public final static int cptTakeMatchesMin = 2;
    
    /* --- On sélectionne les joueurs qui participeront à ce jeu ---*/
    
    public Nim (int cptMatches, int cptTakeMatches, boolean lastObjectTakenWin,
            boolean historic)
    {
    	super(2); // à voir avec la prof. this.maxPlayers
        this.cptMatches = cptMatches;
        this.cptTakeMatches = cptTakeMatches;
        this.lastObjectTakenWin = lastObjectTakenWin;
        this.historic = historic;
    }
    
    
    /* ------------- Getters and setters -----------------*/

    
    public void setMatches(int cptMatches)
    {
        this.cptMatches = cptMatches;
    }

    public int getMatches()
    {
        return this.cptMatches;
    }
    
    public void setCptTakeMatches(int cptTakeMatches)
    {
        this.cptTakeMatches = cptTakeMatches;
    }
    
    public int getCptTakeMatches()
    {
        return this.cptTakeMatches;
    }
    
    public void setLastObjectTakenWin(boolean lastObjectTakenWin)
    {
        this.lastObjectTakenWin = lastObjectTakenWin;
    }
    
    public boolean getLastObjectTakenWin()
    {
        return this.lastObjectTakenWin;
    }
    
    public boolean isHistoric() 
    {
	return historic;
    }

    public void setHistoric(boolean historic) 
    {
        this.historic = historic;
    }
    
    
    /* ------------- Nim Functions -----------------*/


    public boolean validRound(int nbTakenMatches)
    {    
        return(nbTakenMatches <= this.cptTakeMatches && 
            nbTakenMatches<=this.cptMatches && nbTakenMatches >0);
    }
    
    public void takeMatches(int nbTakenMatches)
    {
        this.cptMatches -= nbTakenMatches;
    }
    
    public Player whoWin(Player player, Player player2)
    {
    	Player p;
        if (this.lastObjectTakenWin)
        {
            player.setCptGameWin(player.getCptGameWin()+1);
            p = player;
            
        }
        else
        {
            player.setCptGameWin(player2.getCptGameWin()+1);
            p = player2;
        }
        return p;
    }
    
    public void addRoundHistoric(int nbMatchesTaken)
    {
    	this.stackHistoric.push(nbMatchesTaken);
    }
    
    public int comeBack(int nbRoundComeBack)
    {
        int test = 0;
    	//Check that there has been at last one round playing
    	if(nbRoundComeBack < this.stackHistoric.size())
    	{
            for(int i = 0; i<nbRoundComeBack; i++)
            {
            	System.out.println(this.cptMatches+" matches");
            	System.out.println(this.stackHistoric.peek());
                this.cptMatches += this.stackHistoric.peek();
                this.stackHistoric.pop();
            }
            test = 1;
    	}
    	return test; // Fatal Error
    }
    
}
