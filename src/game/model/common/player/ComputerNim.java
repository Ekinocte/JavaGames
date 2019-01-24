/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model.common.player;

import java.util.Random;

/**
 *
 * @author Guillaume et Florian
 */
public class ComputerNim extends Computer
{
    public ComputerNim(String lastName)
    {
        super(lastName);
    }
    
    /* --------------- AI Functions --------------------- */
    
    public int aiEasy(int cptMatches, int cptTakeMatches) 
    {
        int nbMatches;
    	Random r = new Random();
    	if(cptMatches < cptTakeMatches)
    	{
            nbMatches = r.nextInt(cptMatches)+1; 
            //si le nombre d'alumettes total est inferieur a celui que l'on peut prendre on
            // lance le random sur le nombre d'alumette totale
    	}
    	else 
    	{
            nbMatches = r.nextInt(cptTakeMatches)+1;
    	}
        return nbMatches;
    }
}
