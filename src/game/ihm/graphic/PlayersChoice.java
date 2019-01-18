package game.ihm.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextPane;

import game.model.common.App;
import game.model.common.player.Human;
import game.model.common.player.Player;
import game.model.nim.Nim;

public class PlayersChoice extends JFrame implements ActionListener{
	
	private App app;
	
	private JLabel titleBegin;
	private ButtonGroup bBeginChoice;
	private JRadioButton bYear;
	private JRadioButton bRandom;
	private JRadioButton bName;
	
	private JLabel title;
	private JPanel body;
	private JLabel titleBody;
	private JComboBox playersList;
	private JLabel labelList;
	
	private JPanel footer;
	private JButton bvalid;
	private JButton	badd;
	
	public PlayersChoice (App app) {
		this.app = app;
		this.setPreferredSize(new Dimension(600, 600));
		
		this.titleBegin = new JLabel("Beginer method choice");
		this.titleBegin.setHorizontalAlignment(JLabel.CENTER);
		this.bBeginChoice = new ButtonGroup();
		this.bName = new JRadioButton("By name");
		this.bYear = new JRadioButton("By year");
		this.bRandom = new JRadioButton("Random");
		this.bBeginChoice.add(this.bName);
		this.bBeginChoice.add(this.bYear);
		this.bBeginChoice.add(this.bRandom);
		this.bRandom.setSelected(true);
		
		this.title = new JLabel("Who is playing?");
		this.title.setHorizontalAlignment(JLabel.CENTER);
		
		this.PlayerChoice();
		
		this.footer = new JPanel();
		this.bvalid = new JButton("Next");
		this.bvalid.addActionListener(this);
		
		this.footer.add(this.bvalid);
		
		this.getContentPane().add(title, BorderLayout.NORTH);
		this.getContentPane().add(body, BorderLayout.CENTER);
		this.getContentPane().add(footer, BorderLayout.SOUTH);
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
		this.playersList.setMaximumSize(new Dimension(400, 50));
		
		this.badd = new JButton("Add");
		this.badd.addActionListener(this);
		this.badd.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		this.labelList = new JLabel("Players in game:");
		this.labelList.setAlignmentX(JButton.CENTER_ALIGNMENT);
		
		ArrayList<Player> inG = this.app.getGameSelected().getPlayersInGame();
		
		this.body.add(this.titleBegin);
		this.body.add(this.bRandom);
		this.body.add(this.bName);
		this.body.add(this.bYear);
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

	@Override
	public void actionPerformed(ActionEvent evt) 
	{
		if(evt.getSource() == this.badd) 
		{
			if(!this.app.getTabPlayers().isEmpty()) 
			{
				if(this.app.getGameSelected().getPlayersInGame().size()<2 && this.app.getGameSelected() instanceof Nim) {
					Player p = (Player)this.playersList.getSelectedItem();
					this.app.getGameSelected().setPlayerInGame(p);
					this.body.add(new JLabel("- "+p.toString()));
					
					this.body.revalidate();
					this.body.repaint();
				}
			}
		}
		else if(evt.getSource() == this.bvalid)
		{
			this.beginChoice();
			NimGame ng = new NimGame(this.app);
			ng.setTitle("Nim");
			ng.pack();
			ng.setLocationRelativeTo(null);
			ng.setVisible(true);
			ng.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			this.dispose();
		}
	}
	
	public boolean onlyComputer() {
		Boolean res = true;
			
		for(Player p: this.app.getGameSelected().getPlayersInGame())
		{
			if(p instanceof Human)
			{
				res = false;
			}
		}
		return res;
	}
	
	public void beginChoice() {
		if(this.bName.isSelected()) 
		{
			System.out.println("name");
			this.app.getGameSelected().lastNameSelectBegginer();
		}
		else if(this.bYear.isSelected() && !this.onlyComputer())
		{
			System.out.println("year");
			this.app.getGameSelected().youngerSelectBegginer();
		}
		else 
		{
			System.out.println("ramdom");
			this.app.getGameSelected().randomSelectBegginer();
		}
	}
}
