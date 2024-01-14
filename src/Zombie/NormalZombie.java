package Zombie;
import Game.GamePanel;

import javax.swing.*;
import java.awt.*;

public class NormalZombie extends Zombie{
    ImageIcon normalZombieImage;

    public NormalZombie(GamePanel parent,int lane){
        super(parent,lane);
       normalZombieImage = new ImageIcon("Image/Zombie/normalzombie.gif");
        setIcon(normalZombieImage);
        setSize(normalZombieImage.getIconWidth(),normalZombieImage.getIconHeight());
//        setBounds(posX,lane,normalZombieImage.getIconWidth(),normalZombieImage.getIconHeight());
    }

}
