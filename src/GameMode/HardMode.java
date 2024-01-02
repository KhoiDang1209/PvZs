package GameMode;
import Game.Game;
import GUI.GamePanel;

public class HardMode {
    private Game game;
    private GamePanel gamePanel;

    public HardMode(Game game){
        this.game=game;
        displayHardMode();
    }
    public void displayHardMode(){
        GamePanel gamePanel= new GamePanel(game);
        gamePanel.setVisible(true);
    }
}
