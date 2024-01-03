package Plant;

import Game.GamePanel;

public abstract class Plant {
    public int health = 200;

    public int x;
    public int y;

    public GamePanel gp;

    public Plant(GamePanel gp, int x, int y) {
        this.x = x;
        this.y = y;
        this.gp = gp;
    }

    public void stop() {
    }
}
