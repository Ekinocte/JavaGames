package game.ihm.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import game.model.common.App;

public class GameChoice extends JFrame implements ActionListener{

	private JLabel title;
	private JComboBox games;
	private JButton bvalid;
	private App app;
	private JPanel body;
	private JButton validGame;
	private final String[] listGames = {"Nim"};
	private JPanel rules;//affiche les formulaire en fonction du jeu selctionner
	
	//----------Nimm-----------------------------------------------------------
	private ButtonGroup historic;
    private JRadioButton histoTrue;
    private JRadioButton histoFalse;
    
    private ButtonGroup type;
    private JRadioButton lastMatcheWin;
    private JRadioButton lastMatcheLose;
    
    //-------------------------------------------------------------------------
	
	public GameChoice(App app) {
		this.app = app;
		this.setPreferredSize(new Dimension(300, 300));
		
		this.title = new JLabel("What game do you want to play?");
		
		this.games = new JComboBox(this.listGames);
		this.games.setPreferredSize(new Dimension(140, 20));
		this.games.getSelectedItem();
		
		this.validGame = new JButton("Confirm game");
		
		this.body = new JPanel();
		this.body.add(this.games);
		this.body.add(this.validGame);
		this.Nim();
		
		this.bvalid = new JButton("Ok");
		this.bvalid.addActionListener(this);
		
		this.getContentPane().add(this.title, BorderLayout.NORTH);
		this.getContentPane().add(this.body, BorderLayout.CENTER);
		this.getContentPane().add(this.bvalid, BorderLayout.SOUTH);
		
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void Nim() {
		this.rules = new JPanel();
		
		this.histoTrue = new JRadioButton("With historic");
		this.histoFalse = new JRadioButton("Without historic");
		this.historic = new ButtonGroup();
		this.historic.add(this.histoFalse);
		this.historic.add(this.histoTrue);
		
		this.lastMatcheLose = new JRadioButton("Last matche lose");
		this.lastMatcheWin = new JRadioButton("Last matche win");
		this.type = new ButtonGroup();
		this.type.add(this.lastMatcheLose);
		this.type.add(this.lastMatcheWin);
		
		this.rules.add(this.lastMatcheLose);
		this.rules.add(this.lastMatcheWin);
		this.rules.add(this.histoTrue);
		this.rules.add(this.histoFalse);
		
		this.body.add(this.rules);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource() == this.bvalid) {
			new PlayersChoice(this.app);
			this.dispose();
		}else if(evt.getSource() == this.validGame) {
			if(this.games.getSelectedItem() == "Nim") {
				this.Nim();
				this.revalidate();
				this.repaint();
			}
		}
	}

}
