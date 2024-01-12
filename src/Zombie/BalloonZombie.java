package Zombie;

import Game.GamePanel;
import Plant.FreezePeashooter;
import Plant.Peashooter;
import Plant.Sunflower;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Objects;
import java.util.Random;

public class BalloonZombie extends Zombie {
    private int DEFAULT_HEALTH ;
    private final int DEFAULT_DAMAGE ;
    private final GamePanel gp2;

    private final int speed2;

    public int myLane;
    private final BufferedImage zombieImage2;
    public BalloonZombie(GamePanel parent, int lane) {
        super(parent, lane);
        myLane=lane;
        this.DEFAULT_HEALTH= 2800;
        this.DEFAULT_DAMAGE= 200;
        this.gp2= parent;
        startAttackTimer();
        this.speed2=10;
        zombieImage2 = (BufferedImage) new ImageIcon(Objects.requireNonNull(this.getClass().getResource("balloonzombie.gif"))).getImage();
    }

    @Override
    public void takeDamage(int DEFAULT_DAMAGE) {
        super.takeDamage(DEFAULT_DAMAGE);
    }



    @Override
    public void die() {
        super.die();
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


    public void spawn(){
        int maxX = 1600;

        Random random = new Random();
        int yCoordinate2 = random.nextInt(maxX);

        System.out.println("Zombie spawned at: ("  + ", " + yCoordinate2 + ")");

    }

    public void drawZombie(Graphics g2) {
        String gifPath2 = "balloonzombie.gif"; // Thay đổi đường dẫn tới file GIF của bạn

        // Sử dụng URL để đọc file từ đường dẫn
        URL imageUrl2 = getClass().getResource(gifPath2);

        // Kiểm tra xem có thể đọc được file không
        if (imageUrl2 != null) {
            // Sử dụng ImageIcon để hiển thị hình ảnh từ URL
            ImageIcon zombieIcon = new ImageIcon(imageUrl2);

            // Vẽ hình ảnh zombie tại vị trí (0, 0)
            zombieIcon.paintIcon(this, g2, 0, 0);
        } else {
            // Xử lý trường hợp không thể đọc file
            g2.drawString("Không thể đọc file zombie.gif", 10, 20);

        }
    }

    @Override
    public void paintComponent(Graphics g2) {
        if (zombieImage2 == null) {
            System.out.println("No image");
        }
        gp2.paintComponents(g2);
        g2.drawImage(zombieImage2, 0, 0, null);
    }
    public void regenerateHealth() {
        DEFAULT_HEALTH = Math.min(5000, DEFAULT_HEALTH + 100);
    }

    @Override
    public void startAttackTimer() {
        super.startAttackTimer();
    }

    @Override
    public void resetAttackTimer() {
        super.resetAttackTimer();
    }
}
