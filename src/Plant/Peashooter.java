//
//package Plant;
//
///*Still Working */
//import java.awt.event.ActionEvent;
//
//import javax.swing.Timer;
//
//import Game.GamePanel;
//
//public class Peashooter extends Plant {
//
//    public Timer shootTimer;
//
//    public Peashooter(GamePanel parent, int x_box, int y_lineland) {
//        super(parent, x_box, y_lineland);
//        shootTimer = new Timer(1500, (ActionEvent Shot) -> {
//            /*
//             * Get the position of zombie as zombie store in an array has 5 small arrays so
//             * it get y
//             * mean it get which land that has zombie or the size > 0
//             */
//            if (gp.Zombie_units.get(y_lineland).size() > 0) {
//                gp.lanePeas.get(y_lineland).add(new Pea(gp, y_lineland, 103 + this.x * 100));/* Cong thuc */
//                /* Add an bullet */
//         }
//        });
//        shootTimer.start();
//    }
//
//    @Override
//    public void stop() {
//        shootTimer.stop();
//    }
//
//}
