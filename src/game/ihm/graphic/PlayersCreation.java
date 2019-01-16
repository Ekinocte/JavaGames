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

import game.ihm.events.mousePlayersCreation;
import game.model.common.App;
import java.util.Calendar;
import java.util.GregorianCalendar;

public final class PlayersCreation extends JFrame implements ActionListener
{
    private final App app = new App();

    private JLabel title; 
    private JPanel body;
    private JLabel titleBody;
    
    private ButtonGroup playerType;
    private JRadioButton human;
    private JRadioButton computer;
    private JTextField name;
    private String lastName;
    private JLabel birthdateTitle;
    
    private JPanel birthdateYear;
    private JComboBox day;
    private JComboBox month;
    private JComboBox year;
    private int jour;
    private int mois;
    private int annee;
    
    private JPanel footer;
    private JButton bvalid;
    private JButton bfinish;
    
    
    
    public PlayersCreation() 
    {
        this.setPreferredSize(new Dimension(600, 600));// to delete

        this.playerCreationTitle();
        this.playerCreationBody();
        this.playerCreationFooter();
    }
    
    public void playerCreationTitle()
    {
        this.title = new JLabel("Player creation");
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(title, BorderLayout.NORTH);
    }

    public void playerCreationBody()
    {
        if (this.body != null)
        {
            this.remove(this.body);
        }
        this.body = new JPanel();
        this.body.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        this.titleBody = new JLabel("Player "+(this.app.getTabPlayers().size()+1));
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
        this.lastName = this.name.getText();
        this.body.add(this.name);
        this.name.setMaximumSize(new Dimension(150,30));
        
        this.birthdate();
        this.computer.addActionListener(this);
        this.human.addActionListener(this);
        
        // Avatar in comming
        this.getContentPane().add(body, BorderLayout.CENTER);
        
        if (this.app.getTabPlayers().size() ==2)
        {
            this.footer.add(this.bfinish);
        }
    }
    
    public void playerCreationFooter()
    {
        this.footer = new JPanel();
        this.bvalid = new JButton("Create & next player");
        this.bfinish = new JButton("Terminate");

        this.footer.add(this.bvalid);
        this.getContentPane().add(footer, BorderLayout.SOUTH);
        this.bvalid.addMouseListener(new mousePlayersCreation(this));
        this.bfinish.addMouseListener(new mousePlayersCreation(this));
    }
    
    /*--------------  age calcul ---------------------*/
    
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
        for (int i=2019; i>=1900; i--)
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
    
    /*------------------- Getters and Setters ---------------*/
    
    public String getLastName()
    {
        return this.lastName;
    }

    public App getApp() {
        return app;
    }

    public JPanel getBody() {
        return body;
    }

    public JComboBox getDay() {
        return day;
    }

    public JRadioButton getComputer()
    {
    return this.computer;
    }


    public JComboBox getMonth() {
        return month;
    }

    public JComboBox getYear() {
        return year;
    }

    public JButton getBvalid() {
        return bvalid;
    }

    public JButton getBfinish() {
        return bfinish;
    }

    public void setJour(int jour) {
        this.jour = jour;
    }

    public void setMois(int mois) {
        this.mois = mois;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    } 
    
    
}