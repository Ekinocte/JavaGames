package game.ihm.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import game.model.common.App;
import game.model.nim.Nim;

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
	private JLabel libHisto;
	private ButtonGroup historic;
    private JRadioButton histoTrue;
    private JRadioButton histoFalse;
    
    private JLabel libType;
    private ButtonGroup type;
    private JRadioButton lastMatcheWin;
    private JRadioButton lastMatcheLose;
    
    private JLabel libNbMatches;
    private JSpinner nbMatches;
    private JLabel libNbMatchesTaken;
    private JSpinner nbMatchesTaken;
    
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
	}
	
	public void Nim() {
		this.rules = new JPanel();
		this.rules.setLayout(new BoxLayout(this.rules, BoxLayout.Y_AXIS));
		this.histoTrue = new JRadioButton("With historic");
		this.histoFalse = new JRadioButton("Without historic");
		this.historic = new ButtonGroup();
		this.histoTrue.setSelected(true);
		this.historic.add(this.histoFalse);
		this.historic.add(this.histoTrue);
		
		this.libHisto = new JLabel("Historic:");
		this.libType = new JLabel("Game type:");
		this.libNbMatches = new JLabel("Total of matches:");
		this.libNbMatchesTaken = new JLabel("Matches taken by round:");
		
		this.lastMatcheLose = new JRadioButton("Last matche lose");
		this.lastMatcheWin = new JRadioButton("Last matche win");
		this.type = new ButtonGroup();
		this.lastMatcheLose.setSelected(true);
		this.type.add(this.lastMatcheLose);
		this.type.add(this.lastMatcheWin);
		
		this.nbMatches = new JSpinner(new SpinnerNumberModel(21,3,null,1));
		this.nbMatchesTaken = new JSpinner(new SpinnerNumberModel(3,2,null,1));
		
		this.rules.add(this.libNbMatches);
		this.rules.add(this.nbMatches);
		this.rules.add(this.libNbMatchesTaken);
		this.rules.add(this.nbMatchesTaken);
		this.rules.add(this.libType);
		this.rules.add(this.lastMatcheLose);
		this.rules.add(this.lastMatcheWin);
		this.rules.add(this.libHisto);
		this.rules.add(this.histoTrue);
		this.rules.add(this.histoFalse);
		
		this.body.add(this.rules);
	}
	
	@Override
	public void actionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		if(evt.getSource() == this.bvalid) {
			if((int)this.nbMatchesTaken.getValue()<(int)this.nbMatches.getValue()) {
					
				Boolean histo = true;
				boolean typeg = true;
				if(this.lastMatcheLose.isSelected()) {
					histo = false;
				}
				
				if(this.histoFalse.isSelected()) {
					histo = false;
				}
				
				this.app.setGameSelected(new Nim((int)this.nbMatches.getValue(), (int)this.nbMatchesTaken.getValue(), typeg, histo));
				PlayersChoice pc = new PlayersChoice(this.app);
				pc.pack();
				pc.setLocationRelativeTo(null);
				pc.setVisible(true);
				pc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				this.dispose();
			}
		}else if(evt.getSource() == this.validGame) {
			if(this.games.getSelectedItem() == "Nim") {
				this.Nim();
				this.revalidate();
				this.repaint();
			}
		}
	}

}
