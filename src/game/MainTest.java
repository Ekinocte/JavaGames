package game;

import javax.swing.JFrame;

import game.ihm.graphic.GameChoice;
import game.ihm.graphic.PlayersChoice;
import game.model.common.App;
import game.model.common.player.Computer;
import game.model.common.player.ComputerNim;
import game.model.common.player.Human;
import game.model.common.player.HumanNim;
import game.model.common.player.Player;
import game.model.nim.Nim;

public class MainTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HumanNim p1 = new HumanNim(new Human("Guigui", 50));
		ComputerNim p2 = new ComputerNim("Guigui");
		
		App a = new App();
		
		a.setGameSelected(new Nim(21,3,true,false));
		a.setPlayer(p1);
		a.setPlayer(p2);
		
		a.getGameSelected().setPlayerInGame(p1);
		a.getGameSelected().setPlayerInGame(p2);
		
		//new PlayersChoice(a);
		
		GameChoice gc = new GameChoice(a);
		gc.pack();
		gc.setLocationRelativeTo(null);
		gc.setVisible(true);
		gc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
