package GameMode;

import Game.GamePanel;
import Game.Game;
public class NormalMode {
    private GamePanel gamePanel;
    private Game game;
    private int ZombDieToWin=75;
    public NormalMode(Game game){
        this.game=game;
        displayNormalMode();
    }
    public void displayNormalMode(){
        GamePanel gamePanel= new GamePanel(game,ZombDieToWin);
        gamePanel.setVisible(true);
    }
}
