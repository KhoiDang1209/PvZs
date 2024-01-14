package Plant;

import java.awt.event.ActionEvent;

import javax.swing.Timer;

import Game.GamePanel;
import Sun.Sun;

public class Sunflower extends Plant {
    private int Sunflowerheealth = 500;

    Timer sunProduceTimer;

    public Sunflower(GamePanel gp, int x, int y) {
        super(gp, x, y);
        sunProduceTimer = new Timer(15000, (ActionEvent e) -> {
            Sun sta = new Sun(gp, 360 + x * 120, 110 + y * 120, (130 + y * 120) + 20);
            gp.activeSuns.add(sta);
            gp.label.add(sta);
        });
        sunProduceTimer.start();
    }

    public void receivedamage(int dame) {
        if (dame > 0) {
            int newHealth = this.Sunflowerheealth - dame;

            if (newHealth <= 0) {
                newHealth = 0; // Ensure health doesn't go below zero
                System.out.println("Sunflower has been defeated!");
            }

            System.out.println("Sunflower received damage: " + dame);
            System.out.println("Sunflower remaining health: " + newHealth);

            this.Sunflowerheealth = newHealth; // Update the Peashooter's health
        } else {
            System.out.println("Invalid damage value. Damage must be greater than 0.");
        }
    }

}
