package game.ihm.graphic;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;

import game.model.common.App;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
    private JLabel birthdateTitle;
    
    private JPanel birthdateYear;
    private JComboBox day;
    private JComboBox month;
    private JComboBox year;

    private JPanel footer;
    private JButton bvalid;
    private JButton bfinish;
    
    private int jour =2;
    private int mois =1;
    private int annee =1984;

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
        
        this.birthdate();
        this.computer.addActionListener(this);
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
    
    public void birthdate()
    {
        this.birthdateTitle = new JLabel("Birthdate");
        this.birthdateYear = new JPanel();
        this.day = new JComboBox();
        this.month = new JComboBox();
        this.year = new JComboBox();
        for (int i=1; i<=31; i++)
        {
            this.day.addItem(i);
        }
        for (int i=1; i<=12; i++)
        {
            this.month.addItem(i);
        }
        for (int i=1900; i<=2019; i++)
        {
            this.year.addItem(i);
        }
              
        this.body.add(this.birthdateTitle);
        this.birthdateYear.add(this.day);
        this.birthdateYear.add(this.month);
        this.birthdateYear.add(this.year);
        this.body.add(this.birthdateYear);
        this.body.revalidate();
        this.body.repaint();          
    }
    
    public int AgeCalc()
    {
        int res;
        Calendar cal = new GregorianCalendar(this.annee, this.mois, this.jour);
        Calendar now = new GregorianCalendar();
        res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
        if (now.get(Calendar.MONTH)+1 > this.mois)
        {
            res ++;
        }
        else if ((now.get(Calendar.MONTH)+1 == this.mois) 
                && (now.get(Calendar.DAY_OF_MONTH) > this.jour))
        {
            res ++;
        }
        System.out.println(res);
        return res;
    }
    /*------------ Events Listeners -----------------*/
    
    @Override
    public void actionPerformed(ActionEvent ae) 
    {
        if(ae.getSource() == this.human)
        {
           if(((JRadioButton)ae.getSource()).isSelected())
           {
                this.birthdate();
                int res = this.AgeCalc();
           }
        }
        else if(ae.getSource() == this.computer)
        {
            if(((JRadioButton)ae.getSource()).isSelected())
            {
                this.body.remove(this.birthdateTitle);
                this.body.remove(this.birthdateYear);
                this.body.revalidate();
                this.body.repaint();
            }
        }
    }
    
    
    
}