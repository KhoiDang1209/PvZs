package Plant;

import Game.GamePanel;

public abstract class Plant {

    public int x;
    public int y;
    protected int health = 300;
    protected int tankhealth = 20000;
    public GamePanel gp;

    public Plant(GamePanel gp, int x, int y) {
        this.x = x;
        this.y = y;
        this.gp = gp;
    }

    public void stop() {
    }

    public int getNormalHealth() {
        return health;
    }

    public int getTankerHealth() {
        return tankhealth;
    }

    public void setNormalHealth(int newHealth) {
        this.health = newHealth;
    }

    public void setTankerHealth(int newHealth) {
        this.tankhealth = newHealth;
    }
}
