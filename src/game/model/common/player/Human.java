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
public class Human extends Player
{
    private int age;

    public Human(String lastName, int age) 
    {
        super(lastName);
        this.age = age;
    }
    
    
    /* ------------- Getters and setters -----------------*/

    public int getAge()
    {
        return this.age;
    }
    
    public void setAge(int age)
    {
        this.age = age;
    }
}
