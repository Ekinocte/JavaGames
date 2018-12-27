package game.model.common;

import game.model.common.player.Human;
import game.model.common.player.Player;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class Game 
{
    private final int maxPlayers;
    private ArrayList<Player> playersInGame;
    private int beginPlayer; //Correspond à la méthode qui définie le joueur qui commence

        
    protected Game(int maxPlayers)
    {
        this.playersInGame = new ArrayList<Player>();
        this.maxPlayers = maxPlayers;
    }
	
    /* ------------- Getters and setters -----------------*/
    public int getMaxPlayers()
    {
        return this.maxPlayers;
    }

    public ArrayList<Player> getPlayersInGame() {
        return playersInGame;
    }
    
    public Player getPlayerInGame(int idPlayer)
    {
        return this.playersInGame.get(idPlayer);
    }
    
    public void setPlayerInGame(Player player)
    {
        this.playersInGame.add(player);
    }
    
    public int getBeginPlayer() 
    {
	return beginPlayer;
    }
    
    /* ----------- Basic functions ----------------------------*/
    public String playersToString(ArrayList<Player> players)
    {
        String str ="";
        int id = 0;
        for (Player P : players)
        {
            str = str + id + " - " + P.toString() + "\n";
            id++;
        }
        return str;
    }
    
    /* ------------------ Who's begin? ----------------------*/
    
    
    /* ------------------ Random Method ---------------------*/

    public void randomSelectBegginer()
    {
        Random rand = new Random();
        this.beginPlayer = rand.nextInt(
            this.getPlayersInGame().size());
    }
    
    /* ------------------ Younger Method ---------------------*/

    public void youngerSelectBegginer()
    {
        this.beginPlayer = 0;
        int ageMin = 999999999;
        for(int i=1; i<this.getPlayersInGame().size(); i++)
        {
            if (this.getPlayersInGame().get(i) instanceof Human)
            {
                Human humain = ((Human)this.getPlayerInGame(i));
                /*if (ageMin == null)
                {
                    this.beginPlayer = i;
                    ageMin = humain.getAge();
                }*/
                if (humain.getAge() < ageMin)
                {
                    this.beginPlayer = i;
                    ageMin = humain.getAge();
                }
            }
        }
    }
    
    /* ------------------ First alphabetic lastname Method ---------------------*/

    public void lastNameSelectBegginer()
    {
        String lastName = this.getPlayerInGame(0).getLastName();
        this.beginPlayer = 0;
        for(int i=1; i<this.getPlayersInGame().size(); i++)
        {
            if(lastName.toLowerCase().compareTo(
                    this.getPlayerInGame(i).
                            getLastName().toLowerCase())>0)
            {
                lastName = this.getPlayerInGame(i).getLastName();
                this.beginPlayer = i;
            }
        }
    }
}
