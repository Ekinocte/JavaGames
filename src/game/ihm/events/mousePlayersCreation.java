package game.ihm.events;

import game.ihm.graphic.GameChoice;
import game.ihm.graphic.PlayersCreation;
import game.model.common.player.Computer;
import game.model.common.player.Human;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

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
            String lastName = this.pc.getName();
            if (this.pc.getComputer().isSelected())
            {
                this.pc.getApp().getTabPlayers().add(new Computer(lastName));
                this.pc.setCpt(this.pc.getCpt()+1);
            }
            else
            {
                this.pc.setJour((int)this.pc.getDay().getSelectedItem());
                this.pc.setMois((int)this.pc.getMonth().getSelectedItem());
                this.pc.setAnnee((int)this.pc.getYear().getSelectedItem());
                int age = this.pc.AgeCalc();
                this.pc.getApp().getTabPlayers().add(new Human(lastName, age));
                this.pc.setCpt(this.pc.getCpt()+1);
            }
            this.pc.playerCreation();
            this.pc.getBody().revalidate();
            this.pc.getBody().repaint();
        }
        
        if(ae.getSource() == this.pc.getBfinish())
        {
            new GameChoice(this.pc.getApp());
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
