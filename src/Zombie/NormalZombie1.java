package Zombie;

import Game.GamePanel;

import java.util.Random;

public class NormalZombie1 extends zombie1 {
    private int xCoordinate3;
    private int yCoordinate3;

    public NormalZombie1(GamePanel parent, int lane) {
        super(parent, lane);
    }
    public void spawn(){
        int maxX = 1600;
        int maxY = 900;

        Random random = new Random();

        this.xCoordinate3 = random.nextInt(maxX);
        this.yCoordinate3 = random.nextInt(maxY);

        System.out.println("Zombie spawned at: (" + xCoordinate3 + ", " + yCoordinate3+ ")");

    }

}
