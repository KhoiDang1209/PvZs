package Zombie;

import Game.GamePanel;

import javax.swing.*;
import java.awt.*;

public class BucketHeadZombie extends Zombie {
     ImageIcon bucketHeadZombieImage;
    public BucketHeadZombie(GamePanel parent, int lane) {
        super(parent, lane);
        health = 2500;
        bucketHeadZombieImage = new ImageIcon("Image/Zombie/bucketheadzombie.gif");
        setIcon(bucketHeadZombieImage);
        setSize(bucketHeadZombieImage.getIconWidth(),bucketHeadZombieImage.getIconHeight());
//        setBounds(posX,lane,bucketHeadZombieImage.getIconWidth(),bucketHeadZombieImage.getIconHeight());
    }

}
