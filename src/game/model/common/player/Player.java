/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model.common.player;

/**
 *
 * @author Guillaume and Florian
 */
public class Player 
{
    protected String lastName;
    protected int cptGameWin = 0;
    protected int cptGamePlayed = 0;
    
    protected Player(String lastName)
    {
        this.lastName = lastName;
    }
    
    /* ------------- Getters and setters -----------------*/

    
    public String getLastName()
    {
        return this.lastName;
    }
    
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    
    public int getCptGameWin()
    {
        return this.cptGameWin;
    }
    
    public void setCptGameWin(int cptGameWin)
    {
        this.cptGameWin = cptGameWin;
    }
    
    public int getCptGamePlayed()
    {
        return this.cptGamePlayed;
    }
    
    public void setCptGamePlayed(int cptGamePlayed)
    {
        this.cptGamePlayed = cptGamePlayed;
    }
    
    /* -------------- Basic methods ---------------------*/
    
    
}