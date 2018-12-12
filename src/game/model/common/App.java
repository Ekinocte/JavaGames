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
    
    public Game getGameSelected() {
		return gameSelected;
	}

	public void setGameSelected(Game gameSelected) {
		this.gameSelected = gameSelected;
	}

	public void setPlayer(Player player)
    {
        this.players.add(player);
    }
       
    /* ----------- Basic functions ----------------------------*/
    
    
    /* ------------------ Who's begin? ----------------------*/
    
    
    /* ------------------ Random Method ---------------------*/
    
    /*public int randomSelect()
    {
        Random rand = new Random();
    }*/
}
