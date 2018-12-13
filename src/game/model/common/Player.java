/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model.common;

/**
 *
 * @author Guillaume and Florian
 */
public class Player 
{
    private String lastName;
    private int age;
    private int cptGameWin = 0;
    private int cptGamePlayed = 0;
    private boolean computer;
    
    public Player(String lastName, int age, boolean computer)
    {
        this.lastName = lastName;
        this.age = age;
        this.computer = computer;
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
    
    public int getAge()
    {
        return this.age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
    
    public int getCptGameWin()
    {
        return this.cptGameWin;
    }
    
    public void setCptGameWin(int cptGameWin)
    {
        this.cptGameWin = cptGameWin;
    }
    
    public int getCptGamePlayer()
    {
        return this.cptGamePlayed;
    }
    
    public void setCptGamePlayer(int cptGamePlayed)
    {
        this.cptGamePlayed = cptGamePlayed;
    }
    
    public boolean isComputer()
    {
        return this.computer;
    }
    
    public void setComputer(boolean computer)
    {
        this.computer = computer;
    }
    
    /* -------------- Basic methods ---------------------*/
    
    @Override
    public String toString()
    {
        return this.lastName + " - " + this.age + " - " + "computer: " 
                + this.computer + " - " + "in game : "; 
    }
    
}