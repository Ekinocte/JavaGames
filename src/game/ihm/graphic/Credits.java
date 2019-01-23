/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.ihm.graphic;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Guillaume & Florian
 */
public class Credits extends JFrame
{
    private final JLabel title;
    private final JLabel body;
    private final JLabel foot;
    public Credits() 
    {
        this.setPreferredSize(new Dimension(600, 600));// to delete
        
        this.title = new JLabel("MULTIGAMES (only nim)");
        this.title.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(title, BorderLayout.NORTH);
        this.body = new JLabel("2019");
        this.body.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(body, BorderLayout.CENTER);
        this.foot = new JLabel("Guillaume Garcia & Florian Delgado");
        this.foot.setHorizontalAlignment(JLabel.CENTER);
        this.getContentPane().add(foot, BorderLayout.SOUTH);
    }
}
