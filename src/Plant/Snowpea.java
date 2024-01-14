package Plant;

import java.awt.Rectangle;

import javax.swing.ImageIcon;

import Game.GamePanel;
import Zombie.Zombie;

public class Snowpea extends Pea{
    public Snowpea(GamePanel parent,int lane,int startX){
    super(parent,lane,startX);
}

@Override
public void advance(){
    Rectangle pRect = new Rectangle(posX,130+myLane*120,28,28);
    for (int i = 0; i < gp.gm.Zombie_units.get(myLane).size(); i++) {
        Zombie z = gp.gm.Zombie_units.get(myLane).get(i);
        Rectangle zRect = new Rectangle(z.posX,109 + myLane*120,400,120);
        if(pRect.intersects(zRect)){
            z.health -= 300;
            z.slow();
            boolean exit = false;
            if(z.health < 0){
                System.out.println("ZOMBIE DIE");
                gp.gm.Zombie_units.get(myLane).remove(i);
                    gp.removeDieZombie(z);
                    exit = true;
            }
            gp.gm.PeaInField.get(myLane).remove(this);
            if(exit) break;
        }
    }
    if(posX > 1300){
        gp.gm.PeaInField.get(myLane).remove(this);
    }
    posX += 15;
}

}

    
