package Zombie;

import Game.GamePanel;
import GameElement.Collider;
import Plant.FreezePeashooter;
import Plant.Sunflower;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;
import java.util.Timer;

public class BucketHeadZombie extends Zombie {
    private int xCoordinate1;
    private int yCoordinate1;
    private  int slowInt1;
    private int speed1;
    private int health1;
    private  int damage1 ;
    private final GamePanel gp1;
    private boolean isSlowed1;


    public BucketHeadZombie(GamePanel parent, int lane) {
        super(parent, lane);
        this.gp1=parent;
        ;
        initAttributes();
    }
    public void initAttributes() {
        this.health1 = 4000;
        this.slowInt1 = 3000;
        this.speed1 = 8;
        this.damage1 = 300;

    }
    public void advance() {
        if (isMoving) {
            boolean isCollides = false;
            Collider collided = null;
            for (int i = myLane * 9; i < (myLane + 1) * 9; i++) {
                if (gp1.colliders[i].assignedPlant != null && gp1.colliders[i].isInsideCollider(posX)) {
                    isCollides = true;
                    collided = gp1.colliders[i];
                }
            }
            if (!isCollides) {

                if (slowInt1 > 0) {
                    if (slowInt1 % 2 == 0) {
                        posX--;
                    }
                    slowInt1--;
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
                JOptionPane.showMessageDialog(gp1, "ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
                //GameWindow.gw.dispose();
                //GameWindow.gw = new GameWindow();
            }
        }
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
    public void attackObject2(Sunflower sunflower){
        if(sunflower!=null){
            if(this.health1 < 0.3* this.health1){
                int healthPercentage2= (int) (this.health1 * 2/ this.getMaxHealth());
                int dame= 2 * (int)(this.damage1*healthPercentage2);
                sunflower.receivedamage(dame);
            }
        } else {
            double healthPercentage2 = (double) this.health1  / getMaxHealth();
            int dame = (int) (this.damage1  * healthPercentage2);
            sunflower.receivedamage(dame);
        }
    }
    public void takeDamage(int damage1){
        super.takeDamage(damage1);
    }
    public void slowDown(int speed1){
        super.slowDown(speed1);
    }
    public void restoreSpeed(int ignoredSpeed1) {
        isSlowed1 = false;
        ignoredSpeed1= this.speed1/3; // Khôi phục lại tốc độ di chuyển
        System.out.println("Zombie's speed is restored!");
        startAttackTimer();
    }
    public void applyDamageEffects() {
        super.applyDamageEffects();
    }
    public void die(){
        super.die();
    }
    public void newspeed(){
        int newspeed1;
        if(this.health1<= 0.3* this.health1){
            newspeed1= (int) (this.speed1*1.5);
        }else{
            newspeed1=this.speed1;
        }
    }
    public void spawn(){
        int maxX = 1600;
        int maxY = 900;

        Random random = new Random();

        this.xCoordinate1 = random.nextInt(maxX);
        this.yCoordinate1 = random.nextInt(maxY);

        System.out.println("Zombie spawned at: (" + xCoordinate1 + ", " + yCoordinate1+ ")");

    }

    public void drawZombie(Graphics g) {
        String gifPath2 = "zombie2.gif"; // Thay đổi đường dẫn tới file GIF của bạn

        // Sử dụng URL để đọc file từ đường dẫn
        URL imageUrl2 = getClass().getResource(gifPath2);

        // Kiểm tra xem có thể đọc được file không
        if (imageUrl2 != null) {
            // Sử dụng ImageIcon để hiển thị hình ảnh từ URL
            ImageIcon zombieIcon = new ImageIcon(imageUrl2);

            // Vẽ hình ảnh zombie tại vị trí (0, 0)
            zombieIcon.paintIcon(this, g, 0, 0);
        } else {
            // Xử lý trường hợp không thể đọc file
            g.drawString("Không thể đọc file zombie.gif", 10, 20);
        }
    }
    

}
