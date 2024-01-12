package Zombie;

import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import Game.GamePanel;


import GameElement.Collider;
import Plant.FreezePeashooter;
import Plant.Peashooter;
import Plant.Sunflower;

public class Zombie   {
    // the attribute of zombie
    public int health = 1000;
    public int speed = 5;
    private  int damage;
    private boolean isUnderAttack;
    private java.util.Timer regenerationTimer;

    GamePanel gp;

    public int posX = 1000;
    public int myLane;
    public boolean isMoving = true;


    public Zombie(GamePanel parent,int lane,int health, int speed){
        this.gp = parent;
        myLane = lane;
        this.health=health;
        this.speed=speed;
    }

    public void advance(){
        if(isMoving) { //kiểm tra zombie có di chuyển không nếu là true thì tiếp tục
            boolean isCollides = false; // va chạm false
            Collider collided = null;   // tạo một đối tượng va chạm
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) { //đoạn mã code này kiểm tra có trong vùng va chạm không
                if (gp.colliders[i].assignedPlant != null && gp.colliders[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = gp.colliders[i]; // đối tượng collider được gán cho biến collided
                }
            }
            if (!isCollides) { // không va chạm
                if(slowInt>0){ // bị bắn đạn frezze
                    if(slowInt % 2 == 0) { // nếu biến slowint là số chẵn thì zombie được di chuyển
                        posX--; // nếu là số lẽ thì sẽ không di chuyển
                    }
                    slowInt--; // sau khi thực hiện xong hết slowint sẽ tự trừ đi 1
                }else {
                    posX -= 1; // nếu không bị làm chậm và không va chạm thì posx zombie -1
                }
            } else {
                collided.assignedPlant.health -= 10; // máu của plant -10
                if (collided.assignedPlant.health < 0){ // nếu máu của plant bé hơn 0 thì remove plant
                    collided.removePlant();
                }
            }
            if (posX < 0) { // zombie đi hết đường đi
                isMoving = false;
                JOptionPane.showMessageDialog(gp,"ZOMBIES KILLED THE GUARDIANS !" + '\n' + "Starting the level again");// trình một cửa sổ thông báo kết thúc game

//                GameWindow.gw.dispose();
//                GameWindow.gw = new GameWindow();
            }
        }
    }

    public void kill(){
        if (health > 0 ){
            health = 0;
        }

        if (gp != null){
            //gp.laneZombies.get(posX).remove(this);
        }
    }

    public boolean isAlive() {
        return health>0;
    }
    public void die() {
        isMoving = false;
        System.out.println("Zombie has been defeated!");
        regenerationTimer.cancel();
    }
    public void startAttackTimer() {
        this.regenerationTimer = new Timer();
        regenerationTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!isUnderAttack) {
                    regenerateHealth();
                } else {
                    isUnderAttack = false;
                }
            }
        }, 5000, 5000);
    }
    public void regenerateHealth() {
        health = Math.min(5000, health + 100);
    }
    public void takeDamage(int damage) {
        if (isAlive()) {
            health -= damage;
        }else {
            die();
        }
        this.isUnderAttack = true;

        // Reset đếm thời gian khi bị tấn công
        resetAttackTimer();
    }

    public void resetAttackTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isUnderAttack = false;
            }
        }, 5000);

    }

    public int getMaxHealth() {
        return 500;
    }
    public void attackOtherObject(Peashooter peashooter) {
        if (peashooter!= null) {
            if (health <= 0.3 * health) {
                double healthPercentage = (double) health / getMaxHealth();
                int calculatedDamage = (int) (damage*2 * healthPercentage);
                peashooter.receivedamage(calculatedDamage);

            } else {
                double healthPercentage = (double) health / getMaxHealth();
                int calculatedDamage = (int) (damage * healthPercentage);
                peashooter.receivedamage(calculatedDamage);
            }
        }
    }
    public void attackOtherObject1(FreezePeashooter freezePeashooter){
        if(freezePeashooter!= null) {
            if (health <= 0.3 * health) {
                double healthPercentage1 = (double) health * 4 / getMaxHealth();
                int calculatedDamage1 = 2 * (int) (damage*2* healthPercentage1);
                freezePeashooter.receivedamage(calculatedDamage1);
            } else {
                double healthPercentage1 = (double) health / getMaxHealth();
                int calculatedDamage1 = (int) (damage * healthPercentage1);
                freezePeashooter.receivedamage(calculatedDamage1);
            }
        }
    }
    int slowInt = 0; // biến slowint lúc đầu
    public void slow(){ //phương thức slow khi frezze pea được bắn dính zombie
        slowInt = 1000; // mỗi lần bắn dính zombie biến slowint =1000
    }
    public static Zombie getZombie(String type,GamePanel parent, int lane, int health , int speed) {
        Zombie z = new Zombie(parent,lane,health,speed);
        switch(type) {
            case "NormalZombie" : z = new NormalZombie(parent,lane,health,speed);
                break;
            case "ConeHeadZombie" : z = new ConeHeadZombie(parent,lane,health,speed);
                break;
            case "BalloonZombie" : z = new BalloonZombie(parent,lane,health,speed);
                break;
            case "BucketHeadZombie" : z= new BucketHeadZombie(parent, lane,health,speed);
                break;
        }
        return z;
    }


}