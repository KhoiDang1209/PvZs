package Plant;

import java.awt.Rectangle;

import Game.Game;
import Game.GamePanel;
import Zombie.Zombie;

public class Pea {
    public int posX;
    protected GamePanel gp;
    public int myLane;
    private Game gm;

    public Pea(GamePanel parent, int lane, int startX) {
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
    }

    public void advance() {
        Rectangle pRect = new Rectangle(posX, 130 + myLane * 120, 28, 28);
        for (int i = 0; i < gp.gm.Zombie_units.get(myLane).size(); i++) {
            Zombie z = gp.gm.Zombie_units.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX, 109 + myLane * 120, 300, 120);
            if (pRect.intersects(zRect)) {
                z.health -= 150;
                boolean exit = false;
                if (z.health < 0) {
                    System.out.println("ZOMBIE DIE");
                    // This to do
                    gp.gm.Zombie_units.get(myLane).remove(i);
                    gp.removeDieZombie(z);
                    exit = true;
                }
                // Make a remove pea method in gamepanel
                gp.gm.PeaInField.get(myLane).remove(this);
                if (exit)
                    break;
            }
        }
        if (posX > 2000) {
            gp.gm.PeaInField.get(myLane).remove(this);
        }
        posX += 8;
    }

}
