/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import game.ihm.text.Text;
import game.model.nim.Nim;

import java.util.Scanner;

/**
 *
 * @author Guillaume and Florian
 */
public class Main 
{

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int gameMode = 0;
        while (gameMode !=1 && gameMode !=2)
        {
            System.out.println("Game interface :");
            System.out.println("1 : Text");
            System.out.println("2 : Graphic ");
            System.out.print("Choise : ");
            gameMode = sc.nextInt();
        }
        
        if (gameMode == 1)
        {
            Text textGame = new Text();
            textGame.textMain();
            
            // Affichage score + sortie.
        }
        else
        {
           
        }
    }
    
}
