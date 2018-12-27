/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model.common;

import game.model.common.player.Player;
import java.util.ArrayList;

/**
 *
 * @author Guillaume and Florian
 */
public class App 
{
    private ArrayList<Player> players;
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
    
}
