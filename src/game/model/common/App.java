/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model.common;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Guillaume ands Florian
 */
public class App 
{
    private ArrayList<Player> players;
    private int beginPlayer; //Correspond à la méthode qui définie le joueur qui commence
    private Game gameSelected;
    
    public App()
    {
        this.players = new ArrayList<Player>();   
    }
    
    /* ------------- Getters and setters -----------------*/

    public Player getPlayer(int idPlayer)
    {
        return this.players.get(idPlayer);
    }
    
    public Game getGameSelected() 
    {
        return gameSelected;
    }

    public void setGameSelected(Game gameSelected) 
    {
        this.gameSelected = gameSelected;
    }

    public void setPlayer(Player player)
    {
        this.players.add(player);
    }
       
    public ArrayList<Player> getTabPlayers() 
    {
        return this.players;
    }
     
       
    /* ----------- Basic functions ----------------------------*/
    
    
    /* ------------------ Who's begin? ----------------------*/
    
    
    /* ------------------ Random Method ---------------------*/
    
    public void randomSelectBegginer()
    {
        Random rand = new Random();
        this.beginPlayer = rand.nextInt(
            this.getGameSelected().getPlayersInGame().size());
    }
    
    /* ------------------ Younger Method ---------------------*/

    public void youngerSelectBegginer()
    {
        int ageMin = this.getGameSelected().getPlayerInGame(0).getAge();
        this.beginPlayer = 0;
        for(int i=1; i<this.getGameSelected().getPlayersInGame().size(); i++)
        {
            if(ageMin < this.getGameSelected().getPlayerInGame(i).getAge());
            {
                ageMin = this.getGameSelected().getPlayerInGame(i).getAge();
                this.beginPlayer = i;
            }
        }
    }
    
    /* ------------------ First alphabetic lastname Method ---------------------*/

    public void lastNameSelectBegginer()
    {
        String lastName = this.getGameSelected().getPlayerInGame(0).getLastName();
        this.beginPlayer = 0;
        for(int i=1; i<this.getGameSelected().getPlayersInGame().size(); i++)
        {
            if(lastName.toLowerCase().compareTo(
                    this.getGameSelected().getPlayerInGame(i).
                            getLastName().toLowerCase())<0)
            {
                lastName = this.getGameSelected().getPlayerInGame(i).getLastName();
                this.beginPlayer = i;
            }
        }
    }
}
