package Zombie;

import Game.GamePanel;
import GameElement.Collider;
import Plant.FreezePeashooter;
import Plant.Peashooter;
import Plant.Sunflower;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ZombieFire extends zombie1 {
    private int health1;
    private BufferedImage zombieImage1;
    private  int slowInt1;
    private int speed1;

    private final GamePanel gp1;
    private int posX ;
    public int myLane;
    public boolean isMoving = true;
    private  int damage1 ;
    private boolean isUnderAttack1;
    private Timer regenerationTimer1;
    private boolean isSlowed1;
    private Timer slowTimer1;
    private int xCoordinate1;
    private int yCoordinate1;
    public ZombieFire(String imagePath, GamePanel parent, int lane) {
        super( parent, lane);
        this.gp1 = parent;
        myLane = lane;
        this.isUnderAttack1 = false;
        startAttackTimer();
        this.isSlowed1 = false;
        this.imagePath= imagePath;
        initAttributes();
        try {
            zombieImage1 = ImageIO.read(new File(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void initAttributes() {
        this.health1 = 4000;
        this.slowInt1 = 3000;
        this.speed1 = 8;
        this.damage1 = 300;
        this.posX = 1500;
        this.slowTimer1 = new Timer();
    }
    public void startAttackTimer() {
        this.regenerationTimer1 = new Timer();
        regenerationTimer1.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!isUnderAttack1) {

                    regenerateHealth1();
                } else {

                    isUnderAttack1 = false;
                }
            }
        }, 3000, 2000);
    }

    public void regenerateHealth1() {
        health1 = Math.min(4000, health + 100);
    }

    public void takeDamage(int damage1) {

        health1 -= damage1;

        if (isAlive()) {
            applyDamageEffects();
        } else if (this.health1 <= 0.3 * this.health1) {
            createShield();
        }else {
            die();
        }
        this.isUnderAttack1= true;
        resetAttackTimer();
    }


    public void resetAttackTimer() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                isUnderAttack1 = false;
            }
        }, 4000);

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

    public void kill() {
        if (health1 > 0) {
            health1 = 0;
        }

        if (gp1 != null) {
            //gp1.laneZombies.get(posX).remove(this);
        }
    }
    public void slowDown() {
        if(!isSlowed1) {
            this.speed1 /= 2;
            isSlowed1 = true;
            System.out.println("Zombie slowed down for 3 seconds.");
            Timer slowTimer = new Timer();
            slowTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    isSlowed1 = false;
                    System.out.println("Zombie back to normal speed.");
                    slowTimer.cancel();
                    restoreSpeed(speed1);
                }
            }, 3000);
        }
    }
    public void restoreSpeed(int speed1) {
        isSlowed1 = false;
        this.speed1 *= 2; // Khôi phục lại tốc độ di chuyển
        System.out.println("Zombie's speed is restored!");
        startAttackTimer();
    }


    public void changeColor(Color color) {
        setColor(color);
    }


    public void applyDamageEffects() {
        slowDown();
        changeColor(Color.red);
    }
    public boolean isAlive() {
        return health1>0;
    }

    public void die() {
        isMoving = false;
        System.out.println("Zombie has been defeated!");
        playDeathAnimation();
        dropItems();
        regenerationTimer1.cancel();
    }

    public void playDeathAnimation() {
        super.playDeathAnimation();
    }
    public void dropItems(){
        super.dropItems();
    }




    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
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
    public void changeSkinColor(Color newColor) {
        int width = zombieImage1.getWidth();
        int height = zombieImage1.getHeight();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Lấy màu tại từng pixel
                Color pixelColor = new Color(zombieImage1.getRGB(x, y));

                // Kiểm tra nếu là màu xanh
                if (isGreen(pixelColor)) {
                    // Thay đổi thành màu đỏ
                    zombieImage1.setRGB(x, y, Color.RED.getRGB());
                }
            }
        }

        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = newImage.createGraphics();

        // Apply color transformation
        g2d.setColor(newColor);
        g2d.fillRect(0, 0, width, height);
        g2d.setComposite(AlphaComposite.DstIn);

        // Draw original image on top
        g2d.drawImage(zombieImage1, 0, 0, null);
        g2d.dispose();

        // Update zombie image
        zombieImage1 = newImage;
    }

    private boolean isGreen(Color pixelColor) {
        return pixelColor.getGreen() > 150 && pixelColor.getRed() < 100 && pixelColor.getBlue() < 100;
    }
    public BufferedImage getZombieImage() {
        return zombieImage1;
    }
    public void saveModifiedImage() {
        try {
            String outputFilePath = "zombie4.png";
            File outputFile = new File(outputFilePath);
            ImageIO.write(zombieImage1, "png", outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getMaxHealth() {
        return 600;
    }
    public void draw(Graphics g) {
        if (zombieImage1 != null) {
            g.drawImage(zombieImage1, posX, myLane, null);
        }
    }
    @Override
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
