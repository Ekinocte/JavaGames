package game.ihm.events;

import game.ihm.graphic.NimGame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author Guillaume and Florian
 */
public class mouseNimMenu implements MouseListener
{
    private final NimGame ng;
    public mouseNimMenu(NimGame ng)
    {
        this.ng = ng;
    }
    
    @Override
    public void mouseClicked(MouseEvent ae) 
    {
       if(ae.getSource() == this.ng.getMenuItemReset())
        {   
            System.out.println("test");
            NimGame ng2 = new NimGame(this.ng.getApp());
            ng2.setTitle("Nim");
            ng2.pack();
            ng2.setLocationRelativeTo(null);
            ng2.setVisible(true);
            ng2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.ng.dispose();
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
