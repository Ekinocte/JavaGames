package game.ihm.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import game.model.common.App;
import game.model.common.player.Player;

public class PlayersChoice extends JFrame{
	
	private App app;
	
	private JLabel title;
	private JPanel body;
	private JLabel titleBody;
	private JComboBox playersList;
	private JLabel labelList;
	
	private JPanel footer;
	private JButton bvalid;
	private JButton bprevious;
	private JButton	badd;
	
	public PlayersChoice (App app) {
		this.app = app;
		this.setPreferredSize(new Dimension(600, 600));
		
		this.title = new JLabel("Who is playing?");
		this.title.setHorizontalAlignment(JLabel.CENTER);
		
		this.PlayerChoice();
		
		this.footer = new JPanel();
		this.bprevious = new JButton("Previous");
		this.bvalid = new JButton("Next");
		
		this.footer.add(this.bprevious);
		this.footer.add(this.bvalid);
		
		this.getContentPane().add(title, BorderLayout.NORTH);
		this.getContentPane().add(body, BorderLayout.CENTER);
		this.getContentPane().add(footer, BorderLayout.SOUTH);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void PlayerChoice() {
		this.body = new JPanel();
		this.body.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.body.setLayout(new BoxLayout(this.body, BoxLayout.Y_AXIS));
		
		this.titleBody = new JLabel("List of players");
		this.titleBody.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		this.playersList = new JComboBox();
		this.playersList.setAlignmentX(JButton.CENTER_ALIGNMENT);
		ArrayList<Player> players = this.app.getTabPlayers();
		for(Player p : players) {
			this.playersList.addItem(p);
		}
		this.playersList.setMaximumSize(new Dimension(200, 50));
		
		this.badd = new JButton("Add");
		this.badd.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		this.labelList = new JLabel("Players in game:");
		this.labelList.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		ArrayList<Player> inG = this.app.getGameSelected().getPlayersInGame();
		
		this.body.add(this.titleBody);
		this.body.add(this.playersList);
		this.body.add(this.badd);
		this.body.add(this.labelList);
		if(!inG.isEmpty()) {
			for(Player p : inG) {
				this.body.add(new JLabel("- "+p.toString()));
			}
		}
	}
}
