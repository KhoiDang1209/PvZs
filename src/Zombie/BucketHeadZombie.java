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

public class BucketHeadZombie extends Zombie {
    private int health1;
    private int speed1;
    private  int damage1 ;
    private final GamePanel gp1;

    private final BufferedImage zombieImage;

    public BucketHeadZombie(GamePanel parent, int lane) {
        super(parent, lane);
        myLane=lane;
        this.gp1=parent;
        initAttributes();
        zombieImage = (BufferedImage) new ImageIcon(Objects.requireNonNull(this.getClass().getResource("bucketheadzombie.gif"))).getImage();

    }
    public void initAttributes() {
        this.health1 = 4000;
        int speed1 = 8;
        this.damage1 = 300;
        startAttackTimer();


    }


    public void attackOtherObject1(FreezePeashooter freezePeashooter){
        if(freezePeashooter!= null) {
            if (health1 <= 0.3 * health1) {
                double healthPercentage1 = (double) health1 * 4/ getMaxHealth();
                int calculatedDamage1 = (int) (damage1*3* healthPercentage1);
                freezePeashooter.receivedamage(calculatedDamage1);
            } else {
                double healthPercentage1 = (double) health1 / getMaxHealth();
                int calculatedDamage1 = (int) (damage1* healthPercentage1);
                freezePeashooter.receivedamage(calculatedDamage1);
            }
        }
    }

    public void attackOtherObject(Peashooter peashooter) {
        // Ensure the target object is not null
        if (peashooter!= null) {
            if (health1 <= 0.3 * health1) {
                double healthPercentage = (double) health1*2 / getMaxHealth();
                int calculatedDamage = (int) (damage1*2 * healthPercentage);
                peashooter.receivedamage(calculatedDamage);
            } else {
                double healthPercentage = (double) health1 / getMaxHealth();
                int calculatedDamage = (int) (damage1 * healthPercentage);
                peashooter.receivedamage(calculatedDamage);
            }
        }
    }
    public void takeDamage(int damage1){
        super.takeDamage(damage1);
    }



    public void die(){
        super.die();
    }

    public void spawn(){
        int maxX = 1600;
        int maxY = 900;

        Random random = new Random();

        int xCoordinate = random.nextInt(maxX);
        int yCoordinate = random.nextInt(maxY);

        System.out.println("Zombie spawned at: (" + xCoordinate + ", " + yCoordinate + ")");

    }

    public void drawZombie(Graphics g3) {
        String gifPath2 = "zombie2.gif"; // Thay đổi đường dẫn tới file GIF của bạn

        // Sử dụng URL để đọc file từ đường dẫn
        URL imageUrl2 = getClass().getResource(gifPath2);

        // Kiểm tra xem có thể đọc được file không
        if (imageUrl2 != null) {
            // Sử dụng ImageIcon để hiển thị hình ảnh từ URL
            ImageIcon zombieIcon = new ImageIcon(imageUrl2);

            // Vẽ hình ảnh zombie tại vị trí (0, 0)
            zombieIcon.paintIcon(this, g3, 0, 0);
        } else {
            // Xử lý trường hợp không thể đọc file
            g3.drawString("Không thể đọc file zombie.gif", 10, 20);
        }
    }
    @Override
    public void paintComponent(Graphics g1) {
        if (zombieImage == null) {
            System.out.println("No image");
        }
        gp1.paintComponents(g1);
        g1.drawImage(zombieImage, 0, 0, null);
    }
    public void regenerateHealth() {
        health1 = Math.min(5000, health1 + 100);
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
