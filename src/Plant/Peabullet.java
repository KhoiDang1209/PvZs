//package Plant;
//
//import java.awt.Rectangle;
//
//import Game.GamePanel;
//import Zombie.Zombie;
//
//public class Peabullet {
//    public int posX;
//    protected GamePanel gp;
//    public int myLane;
//
//    public Peabullet(GamePanel parent, int lane, int startX) {
//        this.gp = parent;
//        this.myLane = lane;
//        posX = startX;
//    }
//
//    public void advance() {
//        Rectangle pRect = new Rectangle(posX, 130 + myLane * 120, 28, 28);
//        for (int i = 0; i < gp.Zombie_units.get(myLane).size(); i++) {
//            Zombie z = gp.Zombie_units.get(myLane).get(i);
//            Rectangle zRect = new Rectangle(z.posX, 109 + myLane * 120, 400, 120);
//            if (pRect.intersects(zRect)) {
//                z.health -= 300;
//                boolean exit = false;
//                if (z.health < 0) {
//                    System.out.println("ZOMBIE DIE");
//
//                    gp.Zombie_units.get(myLane).remove(i);
//                    GamePanel.setProgress(10);/*
//                                               * THis is the condition to pass a level like if kill 15 zombie = 150
//                                               * points = win
//                                               */
//                   exit = true;
//                }
//                gp.lanePeas.get(myLane).remove(this);
//                if (exit)
//                    break;
//            }
//        }
//        /*
//         * if(posX > 2000){
//         * gp.lanePeas.get(myLane).remove(this);
//         * }
//         */
//        posX += 15;
//    }
//}
