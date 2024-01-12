package Zombie;

import Game.GamePanel;

import javax.swing.*;

public class BalloonZombie extends Zombie {
    private int speed;
    private int health;
    ImageIcon balloonZombieImage = new ImageIcon("Image/Zombie/balloonzombie.gif");
    public BalloonZombie(GamePanel parent, int lane, int health, int speed) {
        super(parent,lane,health,speed);
        this.health = 1500;
        this.speed=10;
    }


}