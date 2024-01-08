package Plant;

import Game.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class FreezePeashooter  extends Plant{
    public Timer shootTimer;
    private int blood1= 1000;

    public FreezePeashooter(GamePanel gp, int x, int y) {
        super(gp, x, y);
    }


    @Override
    public void stop(){
        shootTimer.stop();
    }

    public void receivedamage(int calculatedDamage1) {
        if (calculatedDamage1 > 0) {
            int newHealth = this.blood1 - calculatedDamage1;

            if (newHealth <= 0) {
                newHealth = 0; // Ensure health doesn't go below zero
                System.out.println("Peashooter has been defeated!");
            }

            System.out.println("Peashooter received damage: " + calculatedDamage1);
            System.out.println("Peashooter remaining health: " + newHealth);

            this.blood1 = newHealth; // Update the Peashooter's health
        } else {
            System.out.println("Invalid damage value. Damage must be greater than 0.");
        }

    }

}
