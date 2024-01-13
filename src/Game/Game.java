package Game;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import GUI.GameMenu.Menu;
import GUI.GameMenu.MenuMode;
import GUI.GameMenu.PlantsMenu;
import GUI.GameMenu.ZombiesMenu;
import Plant.Pea;
import Zombie.Zombie;

public class Game extends JFrame {
    public ArrayList<ArrayList<Zombie>> Zombie_units;
    public ArrayList<ArrayList<Pea>> PeaInField;
    private Thread gameThread;
    private GamePanel gamePanel;
    private Menu menu;
    private MenuMode menuMode;
    private PlantsMenu plantsMenu;
    private ZombiesMenu zombiesMenu;
    private Timer gameTimer;
    private int gameTimeInSeconds = 0;
    private JLabel timerLabel;
    private static int checkTime = 0;

    public Game() {
        this.gamePanel = new GamePanel(this);

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

    public void setCheckTime(int num) {
        this.checkTime += num;
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
