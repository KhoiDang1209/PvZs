package Plant;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Game.GamePanel;
import Zombie.Zombie;

public class Pea {
    public int posX;
    protected GamePanel gp;
    public int myLane;
    ImageIcon Peabullet = new ImageIcon("Image/Plants/Fields/ProjectilePea.png");

    public Pea(GamePanel parent, int lane, int startX) {
        this.gp = parent;
        this.myLane = lane;
        posX = startX;
    }

    public void advance() {
        Rectangle pRect = new Rectangle(posX, 80 + myLane * 160, Peabullet.getIconWidth(), Peabullet.getIconHeight());
        for (int i = 0; i < gp.gm.Zombie_units.get(myLane).size(); i++) {
            Zombie z = gp.gm.Zombie_units.get(myLane).get(i);
            Rectangle zRect = new Rectangle(z.posX, 80 + myLane * 160, z.getWidth(), z.getHeight());
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
                gp.gm.PeaInField.get(myLane).remove(this);
                if (exit)
                    break;
            }
        }
        if (posX > 1300) {
            gp.gm.PeaInField.get(myLane).remove(this);
        }
        posX += 15;
    }

}
