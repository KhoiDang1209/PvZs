package Zombie;

import Game.GamePanel;
import GameElement.Collider;
import Plant.FreezePeashooter;
import Plant.Peashooter;
import Plant.Sunflower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Timer;
import java.util.TimerTask;

public class ConeheadZombie1 extends  zombie1 {
    private int DEFAULT_HEALTH ;
    private int DEFAULT_DAMAGE ;
    public int posX ;
    private final GamePanel gp2;
    public boolean isMoving = true;
    private int speed2;
    private int armor1;
    private Timer slowTimer2;
    private boolean isUnderAttack2;
    public int myLane;
    private Timer regenerationTimer2;
    private boolean isSlowed2;
    private boolean isProtected;

    private int slowInt2;
    private int specialPower;

    private String name;
    private boolean shielded1;


    public ConeheadZombie1(GamePanel parent, int lane)  {
        super( parent, lane);
        this.DEFAULT_HEALTH= 2800;
        this.DEFAULT_DAMAGE= 200;
        this.gp2= parent;
        isProtected = true;
        this.specialPower= 50;
        this.isUnderAttack2 = false;
        startAttackTimer();
        this.isSlowed2 = false;
        this.speed2=10;
        this.myLane=lane;
        startAttackTimer();
        this.posX=1500;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public boolean isProtected() {
        return isProtected;
    }
    public void setProtected(boolean isProtected){
        this.isProtected=isProtected;
    }
    public void startAttackTimer() {
        this.regenerationTimer2 = new Timer();
        regenerationTimer2.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!isUnderAttack2) {

                    regenerateHealth2();
                } else {

                    isUnderAttack2 = false;
                }
            }
        }, 3000, 3000);
    }
    public void createShield() {
        // Kiểm tra nếu máu của zombie từ 30% trở xuống và chưa có giáp ảo
        if (this.DEFAULT_HEALTH <= 0.3* this.DEFAULT_HEALTH && !this.shielded1) {
            // Tăng giáp của zombie lên 10 đơn vị
            this.armor1 += 200;
            // Đặt trạng thái giáp ảo là true
            this.shielded1 = true;
            // Tạo một luồng mới để đếm ngược 3 giây
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    armor1 -= 200;
                    shielded1= false;
                }
            };
            java.util.Timer timer = new Timer();
            timer.schedule(task, 3000);
        }
    }
    public void takeDamage(int totaldamage){
        int totalDamage = DEFAULT_DAMAGE - specialPower;
        if (isAlive()) {
            applyDamageEffects();
        } else if (this.DEFAULT_HEALTH <= 0.3 * this.DEFAULT_HEALTH) {

            createShield();

        }else {
            die();
        }
        this.isUnderAttack2= true;

        // Reset đếm thời gian khi bị tấn công
        resetAttackTimer();
    }


    public void resetAttackTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isUnderAttack2 = false;
            }
        }, 3000);

    }
    public void regenerateHealth2() {
        DEFAULT_HEALTH = Math.min(2800, health + 100);
    }
    public void slowDown() {
        if(!isSlowed2) {
            this.speed2 /= 3;
            isSlowed2 = true;
            System.out.println("Zombie slowed down for 3 seconds.");
            Timer slowTimer2 = new Timer();
            slowTimer2.schedule(new TimerTask() {
                @Override
                public void run() {
                    isSlowed2 = false;
                    System.out.println("Zombie back to normal speed.");
                    slowTimer2.cancel();
                    restoreSpeed(speed2);
                }
            }, 2500);
        }
    }
    public void restoreSpeed(int startSpeed) {
        isSlowed2 = false;
        startSpeed= this.speed2/3; // Khôi phục lại tốc độ di chuyển
        System.out.println("Zombie's speed is restored!");
        startAttackTimer();
    }
    public boolean isAlive() {
        return DEFAULT_HEALTH>0;
    }
    public void advance() {
        if (isMoving) {
            boolean isCollides = false;
            Collider collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (gp2.colliders[i].assignedPlant != null && gp2.colliders[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = gp2.colliders[i];
                }
            }
            if (!isCollides) {

                if (slowInt2 > 0) {
                    if (slowInt2 % 2 == 0) {
                        posX--;
                    }
                    slowInt2--;
                } else {
                    posX -= 1;
                }
            } else {
                collided.assignedPlant.health -= 10;
                if (collided.assignedPlant.health < 0) {
                    collided.removePlant();
                }
            }
            if (posX < 0) {
                isMoving = false;
                JOptionPane.showMessageDialog(gp2, "ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
                //GameWindow.gw.dispose();
                //GameWindow.gw = new GameWindow();
            }
        }
    }
    public void playDeathAnimation() {
        super.playDeathAnimation();
    }
    public void dropItems() {
        super.dropItems();
    }

    @Override
    public void playdDeathSound() {
        super.playdDeathSound();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
    }
    public void attackOtherObject(Peashooter peashooter) {
        // Ensure the target object is not null
        if (peashooter!= null) {
            if (DEFAULT_HEALTH <= 0.3 * DEFAULT_HEALTH) {
                double healthPercentage = (double) DEFAULT_HEALTH*2.5 / getMaxHealth();
                int calculatedDamage = (int) (DEFAULT_DAMAGE * healthPercentage);
                peashooter.receivedamage(calculatedDamage);
            } else {
                double healthPercentage = (double) DEFAULT_HEALTH*1.5 / getMaxHealth();
                int calculatedDamage = (int) (DEFAULT_DAMAGE * healthPercentage);
                peashooter.receivedamage(calculatedDamage);
            }
        }
    }
    public void attackOtherObject1(FreezePeashooter freezePeashooter){
        if(freezePeashooter!= null) {
            if (DEFAULT_HEALTH <= 0.3 * DEFAULT_HEALTH) {
                double healthPercentage1 = (double)DEFAULT_HEALTH*2/ getMaxHealth();
                int calculatedDamage1 = (int) (DEFAULT_DAMAGE*3* healthPercentage1);
                freezePeashooter.receivedamage(calculatedDamage1);
            } else {
                double healthPercentage1 = (double) DEFAULT_HEALTH*1.5 / getMaxHealth();
                int calculatedDamage1 = (int) (DEFAULT_DAMAGE* healthPercentage1);
                freezePeashooter.receivedamage(calculatedDamage1);
            }
        }
    }
    public void attackObject2(Sunflower sunflower){
        if(sunflower!=null){
            if(this.DEFAULT_HEALTH < 0.3* this.DEFAULT_HEALTH){
                int healthPercentage2= (int) (this.DEFAULT_HEALTH * 2/ this.getMaxHealth());
                int dame= 2 * (int)(this.DEFAULT_DAMAGE*healthPercentage2);
                sunflower.receivedamage(dame);
            }
        } else {
            double healthPercentage2 = (double) this.DEFAULT_HEALTH  / getMaxHealth();
            int dame = (int) (this.DEFAULT_DAMAGE  * healthPercentage2);
            sunflower.receivedamage(dame);
        }
    }
    public int getMaxHealth() {
        return 800;
    }
    public void changeColor(Color color2) {

        setColor(color2);
    }
    public void applyDamageEffects() {
        slowDown();
        changeColor(Color.yellow);
    }
    public  void die() {
        isProtected = false;
        System.out.println("Zombie has been killed!");
        playDeathAnimation();
        dropItems();
        regenerationTimer2.cancel();
    }
    public void newspeed(){
        int newspeed2;
        if(this.DEFAULT_HEALTH <=0.3* this.DEFAULT_HEALTH){
            newspeed2= (int)(this.speed2*1.5);
        }else {
            newspeed2 = this.speed2;
        }
    }



}
