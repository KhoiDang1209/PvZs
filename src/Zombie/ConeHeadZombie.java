package Zombie;
import Game.GamePanel;

import javax.swing.*;

public class ConeHeadZombie extends Zombie {
    ImageIcon coneHeadZombieImage = new ImageIcon("Image/Zombie/coneheadzombie.gif");
    private int speed;
    private int health;
    public ConeHeadZombie(GamePanel parent, int lane, int health, int speed){
        super(parent, lane,health,speed);
        health = 1800;
    }
}