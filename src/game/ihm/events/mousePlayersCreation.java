package game.ihm.events;

import game.ihm.graphic.GameChoice;
import game.ihm.graphic.PlayersCreation;
import game.model.common.player.Computer;
import game.model.common.player.Human;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author Guillaume and Florian
 */
public class mousePlayersCreation implements MouseListener
{
    private PlayersCreation pc;
    public mousePlayersCreation(PlayersCreation pc)
    {
        this.pc = pc;
    }
    @Override
    public void mouseClicked(MouseEvent ae) 
    {
        if(ae.getSource() == this.pc.getBvalid())
        {
            String lastName = this.pc.getPlayerName().getText();
            if (this.pc.getComputer().isSelected())
            {
                this.pc.getApp().getTabPlayers().add(new Computer(lastName));
            }
            else
            {
                this.pc.setJour((int)this.pc.getDay().getSelectedItem());
                this.pc.setMois((int)this.pc.getMonth().getSelectedItem());
                this.pc.setAnnee((int)this.pc.getYear().getSelectedItem());
                int age = this.pc.AgeCalc();
                this.pc.getApp().getTabPlayers().add(new Human(lastName, age));
            }
            System.out.println(lastName);
            this.pc.playerCreationBody();
            this.pc.getBody().repaint();
            this.pc.getBody().revalidate();
            
        }
        
        if(ae.getSource() == this.pc.getBfinish())
        {
            GameChoice gc = new GameChoice(this.pc.getApp());
            gc.pack();
            gc.setLocationRelativeTo(null);
            gc.setVisible(true);
            gc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.pc.dispose();
        }
    }

    @Override
    public void mousePressed(MouseEvent me) 
    {
    }

    @Override
    public void mouseReleased(MouseEvent me) 
    {
    }

    @Override
    public void mouseEntered(MouseEvent me) 
    {
    }

    @Override
    public void mouseExited(MouseEvent me) 
    {
    }
    
}
