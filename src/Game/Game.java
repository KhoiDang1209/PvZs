    package Game;
    import GUI.GameMenu.Menu;
    import GUI.GameMenu.MenuMode;
    import GUI.GameMenu.PlantsMenu;
    import GUI.GameMenu.ZombiesMenu;

    import javax.swing.*;

    import InputForGame.Mouse;

    public class Game implements Mouse {
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
        public Game()
        {
            initializeComponents();
        }
        @Override
        public void mouseClicked(int x, int y) {
        }
        @Override
        public void mousePressed(int x, int y) {
        }
        @Override
        public void mouseOver(int x, int y) {
        }
        @Override
        public void mouseReleased(int x, int y) {
        }
        @Override
        public void mouseEntered(int x, int y) {
        }
        @Override
        public void mouseExited(int x, int y) {
        }
        @Override
        public void mouseDragged(int x, int y) {
        }
        @Override
        public void mouseMoved(int x, int y) {
        }
        public void updateGame() {
            /*if (!pauseGame) {
                zombieManager.update();
                plantManager.update();
                projectileManager.update();
            }*/
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