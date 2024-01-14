package Zombie;

import java.awt.Image;

import javax.swing.JLabel;

import Game.GamePanel;
import GameElement.Collider;
import Plant.Peashooter;
import Plant.SnowPeashooter;
import Plant.Sunflower;
import Plant.wallNut;

public class Zombie extends JLabel {
    // the attribute of zombie
    public int health = 1000;
    public int speed = 1;
    Image zombieimage;
    private GamePanel gp;

    public int posX = 1350;

    public int getPosX() {
        return posX;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int myLane;
    public boolean isMoving = true;

    // Constructor
    public Zombie(GamePanel parent, int lane) {
        this.gp = parent;
        myLane = lane;
    }

    // Move straight
    public void advance() {
        if (isMoving) {
            boolean isCollides = false;
            Collider collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (gp.colliders[i].assignedPlant != null && gp.colliders[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = gp.colliders[i];
                }
            }
            if (!isCollides) {
                // The way to slow the zombie
                speed = 1;
                if (slowInt > 0) {
                    if (slowInt % 2 == 0) {
                        speed = 0;
                    }
                    slowInt--;
                } else {
                    posX -= speed;
                }
            } else {
                if (collided.assignedPlant instanceof Peashooter || collided.assignedPlant instanceof SnowPeashooter
                        || collided.assignedPlant instanceof Sunflower) {
                    int plantHealth = collided.assignedPlant.getNormalHealth();
                    plantHealth -= 10;
                    // Update the health property of the assigned plant
                    collided.assignedPlant.setNormalHealth(plantHealth);
                    if (plantHealth <= 0) {
                        collided.removePlant();
                    }
                }

                // the way for the zombir to stop as it stop then back then get speed =1
                if (collided.assignedPlant instanceof wallNut) {
                    int plantHealth = collided.assignedPlant.getTankerHealth();
                    plantHealth -= 100;
                    // Update the health property of the assigned plant
                    collided.assignedPlant.setTankerHealth(plantHealth);
                    speed = 0;
                    speed = -1;
                    if (plantHealth <= 0) {
                        collided.removePlant();
                    }
                }
            }
            if (posX < 315) {
                isMoving = false;
                gp.GameOver(gp);
            }
        }
    }

    public void ZombieMove() {
        int currentX = getPosX();
        posX = currentX - speed;
        setLocation(posX, myLane);
    }

    int slowInt = 0;

    // How to slow a Zombie
    public void slow() {
        slowInt = 25;
    }

    public static Zombie getZombie(String type, GamePanel parent, int lane) {
        Zombie z = new Zombie(parent, lane);
        switch (type) {
            case "NormalZombie":
                z = new NormalZombie(parent, lane);
                break;
            case "ConeHeadZombie":
                z = new ConeHeadZombie(parent, lane);
                break;
            case "BucketHeadZombie":
                z = new BucketHeadZombie(parent, lane);
                break;
            case "BalloonZombie":
                z = new BalloonZombie(parent, lane);
                break;
        }
        return z;
    }

}
