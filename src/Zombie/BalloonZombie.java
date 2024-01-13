package Zombie;

import Game.GamePanel;

public class BalloonZombie extends Zombie {

    public BalloonZombie(GamePanel parent, int lane) {
        super(parent, lane);
        health = 800; 
        speed = 3;   
    }
}