package Plant;

import Game.GamePanel;

import javax.swing.*;

public class Sunflower extends Plant{
    private int Sunflowerheealth= 500;

    Timer sunProduceTimer;


    public Sunflower(GamePanel gp, int x, int y) {
        super(gp, x, y);
    }
    public void receivedamage(int dame){
        if (dame > 0) {
            int newHealth = this.Sunflowerheealth - dame;

            if (newHealth <= 0) {
                newHealth = 0; // Ensure health doesn't go below zero
                System.out.println("Peashooter has been defeated!");
            }

            System.out.println("Peashooter received damage: " + dame);
            System.out.println("Peashooter remaining health: " + newHealth);

            this.Sunflowerheealth = newHealth; // Update the Peashooter's health
        } else {
            System.out.println("Invalid damage value. Damage must be greater than 0.");
        }
    }
}
