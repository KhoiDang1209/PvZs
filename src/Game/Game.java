package Game;

import java.util.ArrayList;

import javax.swing.JFrame;

import Plant.Pea;
import Plant.Snowpea;
import Zombie.Zombie;

public class Game {
    public ArrayList<ArrayList<Zombie>> Zombie_units;
    public ArrayList<ArrayList<Pea>> PeaInField;
    public ArrayList<ArrayList<Snowpea>> SnowPeaInField;
    private Thread gameThread;
    private GamePanel gamePanel;

    public Game(GamePanel gp) {
        this.gamePanel = gp;

        // Initialize Zombie_units
        // Zombie add in GamePanel when draw
        Zombie_units = new ArrayList<>();
        Zombie_units.add(new ArrayList<>()); // line 1
        Zombie_units.add(new ArrayList<>()); // line 2
        Zombie_units.add(new ArrayList<>()); // line 3
        Zombie_units.add(new ArrayList<>()); // line 4
        Zombie_units.add(new ArrayList<>()); // line 5

        // Initialize PlantInField
        // PlantInField add in PeaShooter
        PeaInField = new ArrayList<>();
        PeaInField.add(new ArrayList<>()); // line 1
        PeaInField.add(new ArrayList<>()); // line 2
        PeaInField.add(new ArrayList<>()); // line 3
        PeaInField.add(new ArrayList<>()); // line 4
        PeaInField.add(new ArrayList<>()); // line 5

        SnowPeaInField = new ArrayList<>();
        SnowPeaInField.add(new ArrayList<>()); // line 1
        SnowPeaInField.add(new ArrayList<>()); // line 2
        SnowPeaInField.add(new ArrayList<>()); // line 3
        SnowPeaInField.add(new ArrayList<>()); // line 4
        SnowPeaInField.add(new ArrayList<>()); // line 5
    }

    public void updateGame() {
        /*
         * if (!pauseGame) {
         * zombieManager.update();
         * plantManager.update();
         * projectileManager.update();
         * }
         */
    }

    public void startGameThread() {
        gameThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(16);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        gameThread.start();
    }
}
