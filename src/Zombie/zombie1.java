package Zombie;

import Game.GamePanel;
import GameElement.Collider;
import Plant.FreezePeashooter;
import Plant.Peashooter;
import Plant.Sunflower;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class zombie1 implements ZombieFather{
    protected String imagePath;
    // the attribute of zombie
    public int health ;
    private BufferedImage zombieImage1;
    private  int slowInt;
    private int speed;
    private Color zombieColor;
    private final GamePanel gp;
    public int posX ;
    public int myLane;
    public boolean isMoving = true;
    private  int damage ;
    private boolean isUnderAttack;
    private Timer regenerationTimer;
    private boolean isSlowed;
    private int armor;
    private boolean shielded;
    private Timer slowTimer;
    private static final int MAX_ZOMBIE_TYPES = 3; // Số lượng loại Zombie có thể tạo
    private boolean isGameRunning;
    public zombie1(GamePanel parent, int lane) {
        this.gp = parent;
        myLane = lane;
        this.zombieColor = Color.green;
        this.isUnderAttack = false;
        startAttackTimer();
        this.isSlowed = false;
        this.imagePath= imagePath;
        initAttributes();
        this.shielded = false;
    }
    public void initAttributes() {
        this.health = 5000;
        this.slowInt = 3000;
        this.speed = 5;
        this.damage = 100;
        this.posX = 1500;
        Timer slowTimer = new Timer();
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
        } else if (this.health <= 0.3 * this.health) {

            createShield();

        }else {
            die();
        }
        this.isUnderAttack = true;

        // Reset đếm thời gian khi bị tấn công
        resetAttackTimer();
    }
    public void createShield() {
        // Kiểm tra nếu máu của zombie từ 30% trở xuống và chưa có giáp ảo
        if (this.health <= 0.3* this.health && !this.shielded) {
            // Tăng giáp của zombie lên 10 đơn vị
            this.armor += 100;
            // Đặt trạng thái giáp ảo là true
            this.shielded = true;
            // Tạo một luồng mới để đếm ngược 3 giây
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    armor -= 100;
                    shielded = false;
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, 3000);
        }
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

                if (slowInt > 0) {
                    if (slowInt % 2 == 0) {
                        posX--;
                    }
                    slowInt--;
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
                JOptionPane.showMessageDialog(gp, "ZOMBIES ATE YOUR BRAIN !" + '\n' + "Starting the level again");
                //GameWindow.gw.dispose();
                //GameWindow.gw = new GameWindow();
            }
        }
    }
    public void slowDown() {
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
    public void restoreSpeed(int ignoredSpeed) {
        isSlowed = false;
        ignoredSpeed= this.speed/3; // Khôi phục lại tốc độ di chuyển
        System.out.println("Zombie's speed is restored!");
        startAttackTimer();
    }
    public static zombie1 getZombie(String type,GamePanel parent, int lane, String imagePath) {
        zombie1 z;
        switch(type) {
            case "NormalZombie" : z = new NormalZombie1(parent,lane);
                break;
            case "ConeHeadZombie" : z = new ConeheadZombie1(parent,lane);
               break;
            case "ZombieFire" : z= new ZombieFire(imagePath, parent, lane);
            default:
                z = new zombie1(parent, lane);
                break;

        }
        return z;
    }
    public void setColor(Color color) {
        this.zombieColor = color;

    }
    public void changeColor(Color color) {
        setColor(color);
    }


    public void applyDamageEffects() {
        slowDown();
        changeColor(Color.red);
    }
    public boolean isAlive() {
        return health>0;
    }

    public void die() {
        isMoving = false;
        System.out.println("Zombie has been defeated!");
        playDeathAnimation();
        dropItems();
        regenerationTimer.cancel();
    }
    public void playDeathAnimation() {
        System.out.println("Zombie death animation!");
    }
    public void dropItems() {
        List<String> itemsToDrop = new ArrayList<>();
        itemsToDrop.add("Health Potion");
        itemsToDrop.add("Gold Coin");
        itemsToDrop.add("Weapon Shard");
        Random random = new Random();
        int randomIndex = random.nextInt(itemsToDrop.size());
        String droppedItem = itemsToDrop.get(randomIndex);
        System.out.println("Zombie dropped: " + droppedItem);
    }
    public void playdDeathSound() {
        try {
            // Load the sound file (replace "death_sound.wav" with your actual sound file)
            var audioInputStream = AudioSystem.getAudioInputStream(Objects.requireNonNull(getClass().getResource("PlantVsZombies_src_assets_sounds_crazydaveextralong1 (1).wav")));
            Clip clip = AudioSystem.getClip();

            // Open and start playing the audio clip
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        int dx = switch (key) {
            case KeyEvent.VK_LEFT ->
                // Xử lý khi nhấn phím trái
                    dx=-1; // Ví dụ: Di chuyển về bên trái
            case KeyEvent.VK_RIGHT ->
                // Xử lý khi nhấn phím phải
                    dx=1;
            default -> 0; // Ví dụ: Di chuyển về bên phải
        };

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
    public int getMaxHealth() {
        return 500;
    }

    public void draw(Graphics g) {
        if (zombieImage1 != null) {
            g.drawImage(zombieImage1, posX, myLane, null);
        }
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
    public void setZombieImage(BufferedImage zombieImage) {
        this.zombieImage1 = zombieImage;
    }
    public void newspeed(){
        int newspeed;
        if(this.health<= 0.3* this.health){
            newspeed= (int) (this.speed*1.5);
        }else{
            newspeed=this.speed;
        }

    }
    public zombie1(GamePanel gp) {
        this.gp = gp;
        isGameRunning = true;
    }

    public void startGame() {
        // Bắt đầu game
        while (isGameRunning) {
            generateRandomZombie();
            try {
                // Giả định thời gian giữa việc tạo các Zombie là 3000 milliseconds (1 giây)
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopGame() {
        // Dừng game
        isGameRunning = false;
    }

    public void generateRandomZombie() {
        Random random = new Random();
        int zombieType = random.nextInt(MAX_ZOMBIE_TYPES) + 1;

        switch (zombieType) {
            case 1:
                System.out.println("Spawned Normal Zombie");
                // Logic để tạo và xử lý Normal Zombie
                break;
            case 2:
                System.out.println("Spawned Fast Zombie");
                // Logic để tạo và xử lý Fast Zombie
                break;
            case 3:
                System.out.println("Spawned Strong Zombie");

                break;
            // Thêm các loại Zombie khác nếu cần
            default:
                System.out.println("Spawned Unknown Zombie");
                // Xử lý khi có loại Zombie không được dự kiến
        }
    }















}
