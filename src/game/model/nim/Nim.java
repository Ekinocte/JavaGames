/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model.nim;

import java.util.Stack;

import game.model.common.Game;

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
    private Stack stack= new Stack();
    
    /* --- On sélectionne les joueurs qui participeront à ce jeu ---*/
    
    public Nim (int cptMatches, int cptTakeMatches, boolean lastObjectTakenWin,
            boolean historic)
    {
    	super();
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
    
    public int getMaxPlayers() {
		return maxPlayers;
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
    
    
    
}
