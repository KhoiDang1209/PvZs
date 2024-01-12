package Plant;

import Game.GamePanel;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Repeater  extends Plant{
    private int blood= 300;

    public Timer shootTimer;

  public Repeater(GamePanel parent, int x_box, int y_lineland) {
      super(parent, x_box, y_lineland);
      shootTimer = new Timer(1500, (ActionEvent Shot) -> {
         if (gp.Zombie_units.get(y_lineland).size() > 0) {
             gp.lanePeas.get(y_lineland).add(new Pea(gp, y_lineland, 103 + this.x * 100));
             gp.lanePeas.get(y_lineland).add(new Pea(gp, y_lineland, 103 + this.x * 100));/* Cong thuc */
         }
       });
      shootTimer.start();
    }

  @Override
  public void stop() {
       shootTimer.stop();
   }
    public void receivedamage(int calculatedDamage){
        if (calculatedDamage > 0) {
            int newHealth = this.blood - calculatedDamage;

            if (newHealth <= 0) {
                newHealth = 0; 
                System.out.println("Peashooter has been defeated!");
            }

            System.out.println("Repeater received damage: " + calculatedDamage);
            System.out.println("Repeater remaining health: " + newHealth);

            this.blood = newHealth; 
        } else {
            System.out.println("Invalid damage value. Damage must be greater than 0.");
        }
    }

}