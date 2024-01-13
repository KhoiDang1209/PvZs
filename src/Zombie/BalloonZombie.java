package Zombie;

import Game.GamePanel;

import javax.swing.*;
import java.awt.*;

public class BalloonZombie extends Zombie {
    ImageIcon balloonZombieImage;

    public BalloonZombie(GamePanel parent, int lane) {
        super(parent, lane);
        health = 800;
        speed = 3;
        balloonZombieImage = new ImageIcon("Image/Zombie/balloonzombie.gif");
        setIcon(balloonZombieImage);
        setSize(balloonZombieImage.getIconWidth(),balloonZombieImage.getIconHeight());
//        setBounds(posX,lane,balloonZombieImage.getIconWidth(),balloonZombieImage.getIconHeight());
    }
}