package GameMode;
import Game.Game;
import Game.GamePanel;

public class HardMode {
    private Game game;
    private GamePanel gamePanel;
    private int ZombDieToWin=100;
    public HardMode(Game game){
        this.game=game;
        displayHardMode();
    }
    public void displayHardMode(){
        GamePanel gamePanel= new GamePanel(game,ZombDieToWin);
        gamePanel.setVisible(true);
    }
}
