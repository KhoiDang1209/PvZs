import GUI.GamePanel;

public abstract class Plant {
    public int health = 200;

    public int x;
    public int y;

    public GamePanel gp;

    public Plant(GamePanel MainGame, int x, int y) {
        this.x = x;
        this.y = y;
        gp = MainGame;
    }

    public void stop() {
    }
}
