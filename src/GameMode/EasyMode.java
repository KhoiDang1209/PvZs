package GameMode;

import Game.Game;
import Game.GamePanel;

public class EasyMode {
    private Game game;

    public EasyMode(Game game) {
        this.game = game;
        displayEasyMode();
    }

    public void displayEasyMode() {
        /*
         * enum PlantType {
         * None,
         * Sunflower,
         * Peashooter,
         * FreezePeashooter
         * }
         */
        GamePanel gamePanel = new GamePanel(game);
        gamePanel.setVisible(true);
    }
}