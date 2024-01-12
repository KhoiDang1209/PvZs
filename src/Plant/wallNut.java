package Plant;

import Game.GamePanel;

import javax.swing.*;

public class WallNut extends Plant{
    private int WallNuthealth= 2000;
    public WallNut(GamePanel gp, int x, int y) {
        super(gp, x, y);
    }
    public void receivedamage(int dame){
        if (dame > 0) {
            int newHealth = this.WallNuthealth - dame;

            if (newHealth <= 0) {
                newHealth = 0; 
                System.out.println("WallNut has been defeated!");
            }

            System.out.println("WallNut received damage: " + dame);
            System.out.println("WallNut remaining health: " + newHealth);

            this.WallNuthealth = newHealth; 
        } else {
            System.out.println("Invalid damage value. Damage must be greater than 0.");
        }
    }
}
