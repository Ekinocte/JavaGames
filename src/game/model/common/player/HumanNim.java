/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.model.common.player;

/**
 *
 * @author Guillaume and Florian
 */
public class HumanNim extends Human
{
    public HumanNim(Human human)
    {
        super(human.getLastName(), human.getAge());
    }
}
