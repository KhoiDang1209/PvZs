package Zombie;
import Game.GamePanel;

import javax.swing.*;

public class BucketHeadZombie extends Zombie {
    private int health;
    private int speed;
    ImageIcon bucketHeadZombieImage = new ImageIcon("Image/Zombie/bucketheadzombie.gif");
    public BucketHeadZombie(GamePanel parent, int lane, int health, int speed) {
        super(parent, lane,health,speed);
        health = 1800;


    }


}