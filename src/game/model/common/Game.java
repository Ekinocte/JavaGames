package game.model.common;

import java.util.ArrayList;
import java.util.Scanner;

public class Game 
{
    private final int maxPlayers;
    private ArrayList<Player> playersInGame;
	
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
    
/*------------------- Who playing? ----------------------*/
    
    public void addPlayersInGame(int maxplayer, ArrayList<Player> globalPlayers)
    {
    	Scanner sc = new Scanner(System.in);
    	int idPlayer = 0;
    	
    	System.out.println("-1 for leave player selection");
    	System.out.print(this.playersToString(globalPlayers));
    	System.out.println("number of player who want playing (enter on by one): ");
    	
    	while(idPlayer != -1 && this.playersInGame.size() < maxplayer)
    	{
    		idPlayer = sc.nextInt();
    		if(idPlayer != -1) 
    		{
                    Player p = globalPlayers.get(idPlayer);
                    this.playersInGame.add(p);
    		} 		
    	}
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
}
