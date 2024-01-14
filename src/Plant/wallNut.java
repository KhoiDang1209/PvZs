package Plant;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import Game.GamePanel;

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
