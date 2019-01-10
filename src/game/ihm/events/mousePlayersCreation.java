package game.ihm.events;

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
        if(ae.getSource() == this.pc.bvalid)
        {
            String lastName = this.name.getText();
            if (this.computer.isSelected())
            {
                this.app.getTabPlayers().add(new Computer(lastName));
            }
            else
            {
                this.jour = (int)this.day.getSelectedItem();
                this.mois = (int)this.month.getSelectedItem();
                this.pc.annee = (int)this.pc.year.getSelectedItem();
                int age = this.pc.AgeCalc();
                this.app.getTabPlayers().add(new Human(lastName, age));
            }
            this.cpt++;
            this.playerCreation();
            this.body.revalidate();
            this.body.repaint();
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
