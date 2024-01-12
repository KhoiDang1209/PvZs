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


import Plant.FreezePeashooter;
import Plant.Peashooter;
import Plant.Sunflower;

public class Zombie extends Component  {
	// the attribute of zombie
    public int health = 1000;
    public int speed ;

    private final GamePanel gp;

    public int posX = 1000;
    public int myLane;
    public boolean isMoving = true;
    protected String imagePath;
    // the attribute of zombie
    private final BufferedImage zombieImage1;
    private  int slowInt;
    private  int damage ;
    private boolean isUnderAttack;
    private java.util.Timer regenerationTimer;
    private boolean isSlowed;
    // Constructor
    public Zombie(GamePanel parent){
        this.gp = parent;

        this.isUnderAttack = false;
        startAttackTimer();
        this.isSlowed = false;
        this.imagePath= imagePath;
        initAttributes();
        zombieImage1 = (BufferedImage) new ImageIcon(Objects.requireNonNull(this.getClass().getResource("zombie1 (1).gif"))).getImage();

        }
        public void initAttributes() {
            this.health = 5000;
            this.slowInt = 3000;
            this.speed = 5;
            this.damage = 100;


       }
    //Move straight

    public void restoreSpeed(int ignoredSpeed) {
        isSlowed = false;
        ignoredSpeed= this.speed/3; // Khôi phục lại tốc độ di chuyển
        System.out.println("Zombie's speed is restored!");
        startAttackTimer();
    }
    public void applyDamageEffects() {
        slowDown(speed);

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

        health -= damage;

        if (isAlive()) {
            applyDamageEffects();
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
    public void slowDown(int speed) {
        if(!isSlowed) {
            this.speed /= 3;
            isSlowed = true;
            System.out.println("Zombie slowed down for 3 seconds.");
            Timer slowTimer = new Timer();
            slowTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isSlowed = false;
                    System.out.println("Zombie back to normal speed.");
                    slowTimer.cancel();
                    restoreSpeed(speed);
                }
            }, 3000);
        }
    }
    public int getMaxHealth() {
        return 500;
    }
    public void attackOtherObject(Peashooter peashooter) {
        // Ensure the target object is not null
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
    public void attackObject2(Sunflower sunflower){
        if(sunflower!=null){
            if(this.health < 0.3* this.health){
                int healthPercentage2= (int) (this.health * 3/ this.getMaxHealth());
                int dame= (int)(this.damage*0.5*healthPercentage2);
                sunflower.receivedamage(dame);
            }
        } else {
            double healthPercentage2 = (double) this.health/ getMaxHealth();
            int dame = (int) (this.damage  * healthPercentage2);
            sunflower.receivedamage(dame);
        }
    }
    public void spawn() {
        int maxX = 1600;
        int maxY = 900;

        Random random = new Random();

        int xCoordinate = random.nextInt(maxX);
        int yCoordinate = random.nextInt(maxY);

        System.out.println("Zombie spawned at: (" + xCoordinate + ", " + yCoordinate + ")");
    }
    public void paintComponent(Graphics g) {
        if (zombieImage1 == null) {
            System.out.println("No image");
        }
        gp.paintComponents(g);
        g.drawImage(zombieImage1, 0, 0, null);
    }
    public void drawZombie(Graphics g) {
        String gifPath = "zombie1 (1).gif"; // Thay đổi đường dẫn tới file GIF của bạn

        // Sử dụng URL để đọc file từ đường dẫn
        URL imageUrl = getClass().getResource(gifPath);

        // Kiểm tra xem có thể đọc được file không
        if (imageUrl != null) {
            // Sử dụng ImageIcon để hiển thị hình ảnh từ URL
            ImageIcon zombieIcon = new ImageIcon(imageUrl);

            // Vẽ hình ảnh zombie tại vị trí (0, 0)
            zombieIcon.paintIcon(this, g, 0, 0);
        } else {
            // Xử lý trường hợp không thể đọc file
            g.drawString("Không thể đọc file zombie.gif", 10, 20);
        }
    }
}

