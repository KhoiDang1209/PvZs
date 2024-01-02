package GameMode;

import GUI.GamePanel;
import Game.Game;

public class EasyMode {
    private Game game;
    public EasyMode(Game game){
        this.game=game;
        displayEasyMode();
    }
    public void displayEasyMode(){
        GamePanel gamePanel=new GamePanel(game);
        gamePanel.setVisible(true);
    }
}
