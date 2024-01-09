package Game;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Timer;

import GUI.GameMenu.Menu;
import GUI.GameMenu.MenuMode;
import GUI.GameMenu.PlantsMenu;
import GUI.GameMenu.ZombiesMenu;

public class Game extends JFrame {
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

    private void initializeComponents() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }

    public Game() {
        initializeComponents();
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