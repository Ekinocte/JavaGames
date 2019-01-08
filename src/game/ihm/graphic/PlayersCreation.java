package game.ihm.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import game.model.common.App;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PlayersCreation extends JFrame implements ActionListener
{
    private App app;

    private JLabel title; 

    private JPanel body;
    private JLabel titleBody;
    private ButtonGroup playerType;
    private JRadioButton human;
    private JRadioButton computer;
    private JTextField name;

    private JPanel footer;
    private JButton bvalid;
    private JButton bfinish;

    public PlayersCreation() 
    {
        this.setPreferredSize(new Dimension(600, 600));// a enlever

        this.app = new App();

        this.title = new JLabel("Player creation");
        this.title.setHorizontalAlignment(JLabel.CENTER);

        this.footer = new JPanel();
        this.bvalid = new JButton("Create & next player");
        this.bfinish = new JButton("Terminate");

        this.footer.add(this.bvalid);
        this.footer.add(this.bfinish);

        this.playerCreation();

        this.getContentPane().add(title, BorderLayout.NORTH);
        this.getContentPane().add(body, BorderLayout.CENTER);
        this.getContentPane().add(footer, BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void playerCreation() //rempli le JPanel du body
    {
        this.body = new JPanel();
        this.body.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.titleBody = new JLabel("Player "+this.getPlayerNumber());
        this.body.setLayout(new BoxLayout(this.body, BoxLayout.Y_AXIS));
        this.body.add("Center",this.titleBody);

        this.playerType = new ButtonGroup();
        this.human = new JRadioButton("Human");
        this.human.setAlignmentX(JButton.CENTER_ALIGNMENT);
        this.human.setSelected(true);
        this.computer = new JRadioButton("Computer");
        this.computer.setAlignmentX(JButton.CENTER_ALIGNMENT);

        this.playerType.add(this.human);
        this.playerType.add(this.computer);

        this.body.add(this.human);
        this.body.add(this.computer);

        this.name = new JTextField("Name");
        this.body.add(this.name);
        this.name.setMaximumSize(new Dimension(150,30));
        
        this.human.addActionListener(this);
    }

    public int getPlayerNumber() 
    {
        int n = 1;
        if(!this.app.getTabPlayers().isEmpty()) 
        {
            n = this.app.getTabPlayers().size()+1;
        }
        return n;
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() instanceof JRadioButton)
        {
           if(((JRadioButton)ae.getSource()).isSelected())
           {
                System.out.println("test");
           }
        }
    }
    
}