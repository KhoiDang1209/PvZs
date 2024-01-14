package Zombie;
import Game.GamePanel;

import javax.swing.*;
import java.awt.*;

public class ConeHeadZombie extends Zombie {
    ImageIcon coneHeadZombieImage;
    public ConeHeadZombie(GamePanel parent,int lane){
        super(parent,lane);
        health = 1300;
        coneHeadZombieImage = new ImageIcon("Image/Zombie/coneheadzombie.gif");
        setIcon(coneHeadZombieImage);
        setSize(coneHeadZombieImage.getIconWidth(),coneHeadZombieImage.getIconHeight());
//      setBounds(posX,lane,coneHeadZombieImage.getIconWidth(),coneHeadZombieImage.getIconHeight());
    }


}
