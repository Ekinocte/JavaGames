package game.ihm.text;

import game.model.common.App;
import game.model.common.player.Computer;
import game.model.common.player.ComputerNim;
import game.model.common.player.Human;
import game.model.common.player.HumanNim;
import game.model.common.player.Player;
import game.model.nim.Nim;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Guillaume and Florian
 */
public class Text 
{
    private App app;
    private Scanner sc = new Scanner(System.in);
    
    public Text()
    {
        this.app = new App();
    }
    
    /* ------------- Getters and setters -----------------*/

    public App getApp()
    {
        return this.app;
    }
    
    /* -------------------- Begin ------------------------ */
    
    public void textMain() 
    {
    	int endGame = 1;
    	Scanner sc = new Scanner(System.in);
        
        this.addPlayers();
        
    	// Game Choice
        while (endGame!=0)
        {
            
            int selectedGame = this.gameSelect();
            
            switch (selectedGame)
            {
                case 0 :
                    // Affichage score.
                    endGame = 0;
                    break;
                case 1 :
                    int cptMatches = Nim.cptMatchesMin; //Minimum
                    int cptTakeMatches = Nim.cptTakeMatchesMin; //Minimum
                    boolean lastObjectTakenWin = false;
                    int scannerTest = 2;
                    boolean historic = false;

                    while(cptMatches<=Nim.cptMatchesMin) 
                    {
                        System.out.println("Number of matches: ");
                        cptMatches = sc.nextInt();
                    }
                    while(cptTakeMatches<=Nim.cptTakeMatchesMin)
                    {
                        System.out.println("Number of selected matches by round: ");
                        cptTakeMatches = sc.nextInt();
                    }
                    while(scannerTest >1 || scannerTest<0)
                    {
                        System.out.println("0: You lose if you take the last matche");
                        System.out.println("1: You win if you take the last matche");
                        scannerTest = sc.nextInt();
                    }
                    lastObjectTakenWin = scannerTest == 1;
                    scannerTest = 2;
                    
                    while(scannerTest >1 || scannerTest<0)
                    {
                        System.out.println("0: Without historic");
                        System.out.println("1: With historic");
                        scannerTest = sc.nextInt();
                    }
                    historic = scannerTest == 1;
                    scannerTest = 2;

                    // Nim Initialisation
                    this.app.setGameSelected(new Nim(cptMatches, cptTakeMatches, lastObjectTakenWin, historic));
                    this.addPlayersInGame();

                    scannerTest = 3;
                    while(scannerTest!=0 && scannerTest!=1 && scannerTest!=2)
                    {
                        System.out.println("0 : Random begginer ");
                        System.out.println("1 : Younger begginer ");
                        System.out.println("2 : First alphabetic lastname begginer ");
                        scannerTest = sc.nextInt();
                    }

                    switch(scannerTest)
                    {
                        case 0 :
                            this.app.getGameSelected().randomSelectBegginer();
                            break;
                        case 1 :
                            this.app.getGameSelected().youngerSelectBegginer();
                            break;
                        case 2 :
                            this.app.getGameSelected().lastNameSelectBegginer();
                            break;
                        default :
                            break;
                    }

                    Nim gameP = ((Nim)(this.app.getGameSelected()));	
                    int cptRound = 0;
                    Player pLast = gameP.getPlayerInGame(this.app.getGameSelected().getBeginPlayer());
                    while(gameP.getMatches()>0)
                    {
                        for(Player p : gameP.getPlayersInGame())
                        {
                            //0 because for the moment we have only 2 players
                            if(((cptRound == 0 && this.getApp().getGameSelected().getBeginPlayer() == 0) 
                                    || (cptRound !=0)) && gameP.getMatches() != 0)
                            {
                                System.out.println(gameP.getMatches() + " matches left");
                                
                                if(p instanceof ComputerNim) 
                                {
                                    System.out.println("Ai round :" + p.toString());
                                    int nbMatches = ((ComputerNim)p).aiEasy(
                                        gameP.getMatches(),gameP.getCptTakeMatches());
                                    gameP.takeMatches(nbMatches);
                                    if (gameP.isHistoric())
                                    {
                                        gameP.addRoundHistoric(nbMatches);
                                    }
                                    System.out.println("Ai have taken " + nbMatches + " matches\n");
                                }
                                else
                                {
                                    System.out.println("Human round :" + p.toString());
                                    int nbMatches = 0;
                                                                        
                                    if (gameP.isHistoric())
                                    {
                                        int nbRoundComeBack =0;
                                        scannerTest = 2;
                                        if(cptRound ==0)
                                        {
                                            scannerTest = 1;
                                            System.out.println(scannerTest);
                                        }
                                        while(scannerTest!=0 && scannerTest!=1)
                                        {
                                            System.out.println("0 : Comeback");
                                            System.out.println("1 : Play");
                                            scannerTest = sc.nextInt();
                                        }
                                        if (scannerTest == 1)
                                        {
                                            playerRound(gameP, nbMatches); 
                                            gameP.addRoundHistoric(nbMatches);
                                            nbMatches = 0;

                                        }
                                        else
                                        {
                                            scannerTest = 0;
                                            while (scannerTest == 0)
                                            {
                                                System.out.println("number of round to comeback ?");
                                                nbRoundComeBack = sc.nextInt();
                                                scannerTest = gameP.commeBack(nbRoundComeBack);
                                            }
                                            cptRound -= nbRoundComeBack;
                                            if (nbRoundComeBack%2 ==0)
                                            {
                                                playerRound(gameP, nbMatches);
                                                gameP.addRoundHistoric(nbMatches);
                                                nbMatches = 0;
                                            }

                                        }
                                    }
                                    else
                                    {
                                        playerRound(gameP, nbMatches);
                                        nbMatches = 0;
                                    }  
                                    System.out.println(p.getLastName() + " have taken " + nbMatches + " matches\n");
                                }

                                pLast = p;
                            }
                            cptRound++;
                        }
                    }
                    System.out.println("Game over :");
                    Player winner;
                    if(gameP.getPlayerInGame(0) == pLast)
                    {                     	
                        winner = gameP.whoWin(pLast, gameP.getPlayerInGame(1));
                    }
                    else
                    {
                        winner = gameP.whoWin(pLast, gameP.getPlayerInGame(0)); 
                    }
                    System.out.println(winner.toString()+" win!");
                default :
                    break;
            }
        }
    }
    
    
    /* ------------- We select all the players -------------*/
    
    public void addPlayers()
    {
        String lastName = "";
        int age;
        int bool = 2;
        boolean computer;
        int end = 2;
        while(end!=0)
        {
            end = 2;
            bool = 2;
            System.out.println("Player Name?");
            lastName = sc.next();
            while (bool !=0 && bool !=1)
            {
                System.out.println("It is a computer?");
                System.out.println("1 : yes");
                System.out.println("0 : no");
                bool = sc.nextInt();
            }            
            computer = bool == 1; // 1 it's true.
            if (computer == true)
            {
                this.app.setPlayer(new Computer(lastName));
            }
            else
            {
                System.out.println("Age?");
                age = sc.nextInt(); 
                this.app.setPlayer(new Human(lastName, age));
            }
            
            while ((end !=0 || this.app.getTabPlayers().size()<2) && end !=1)
            {
                System.out.println("1 : next player");
                System.out.println("0 : end (we need minimum 2 players)");
                end = sc.nextInt();
            }
        }
    } 
    
    
    /* ------------------- Game Selection -----------------------*/
    
    public int gameSelect()
    {
        int selectedGame = 2;
        while (selectedGame !=0 && selectedGame !=1)
        {
            System.out.println("Game selection :");
            System.out.println("0 : Leave");
            System.out.println("1 : Nim");
            selectedGame = sc.nextInt();
        }   
        return selectedGame;
    }
    
    /*------------------- Who playing? ----------------------*/
    
    public void addPlayersInGame()
    {
        int maxplayer = this.app.getGameSelected().getMaxPlayers();
        ArrayList<Player> globalPlayers = this.app.getTabPlayers();
    	Scanner sc = new Scanner(System.in);
    	int idPlayer = 0;
    	
    	System.out.println("-1 for leave player selection");
    	System.out.print(this.getApp().getGameSelected().playersToString(globalPlayers));
    	System.out.println("number of player who want playing (enter on by one): ");
    	
    	while(idPlayer != -1 && this.getApp().getGameSelected().getPlayersInGame().size() < maxplayer)
    	{
            idPlayer = sc.nextInt();
            if(idPlayer != -1) 
            {
                Player p = globalPlayers.get(idPlayer);
                if (p instanceof Human)
                {
                    this.getApp().getGameSelected().getPlayersInGame().add(new HumanNim((Human)p));
                }
                else
                {
                    this.getApp().getGameSelected().getPlayersInGame().add(
                            new ComputerNim(p.getLastName()));
                }
            } 		
    	}
    }
    
    /*---------------------- Player Round ------------------------*/
    
    public void playerRound(Nim gameP, int nbMatches)
    {
        while (!gameP.validRound(nbMatches))
        {
            System.out.println("How many matches do you want to take?");
            nbMatches = sc.nextInt();
        }
        gameP.takeMatches(nbMatches);
    }
}
