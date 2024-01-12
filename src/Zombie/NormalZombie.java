package Zombie;
import Game.GamePanel;

import javax.swing.*;

public class NormalZombie extends Zombie{
    private int speed;
    private int health;
    ImageIcon normalZombieimage = new ImageIcon("Image/Zombie/normalzombie.gif");
    public NormalZombie(GamePanel parent, int lane, int health, int speed){
        super(parent, lane,health,speed);
    }

}