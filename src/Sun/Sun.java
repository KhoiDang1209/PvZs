package Sun;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Game.GamePanel;

public class Sun extends JPanel implements MouseListener {

    private GamePanel gp;
    Image sunImage;

    private int myX;
    private int myY;
    private int endY;
    private static final int FALL_SPEED = 4;
    private static final int SUN_VALUE = 25;

    private int destruct = 200;

    public Sun(GamePanel parent, int startX, int startY, int endY) {
        this.gp = parent;
        this.endY = endY;
        setSize(200, 200);
        setOpaque(false);
        myX = startX;
        myY = startY;
        setLocation(myX, myY);
        sunImage = new ImageIcon(this.getClass().getResource("/Sun/images/Sun.png")).getImage();
        addMouseListener(this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (sunImage == null) {
            System.out.println("No image");
        }
        super.paintComponent(g);
        g.drawImage(sunImage, 0, 0, null);
    }

    public void FallSun() {
        if (myY < endY) {
            myY += 4;
        } else {
            destruct--;
            if (destruct < 0) {
                gp.remove(this);
                gp.activeSuns.remove(this);
            }
        }
        setLocation(myX, myY);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        gp.setNumOfSun(gp.getNumOfSun() + SUN_VALUE);
        gp.remove(this);
        gp.activeSuns.remove(this);
    }

    @Override
    @SuppressWarnings("unused")
    public void mouseClicked(MouseEvent e) {
        // Additional logic for mouseClicked if needed
    }

    @Override
    @SuppressWarnings("unused")
    public void mousePressed(MouseEvent e) {
        // Additional logic for mousePressed if needed
    }

    @Override
    @SuppressWarnings("unused")
    public void mouseEntered(MouseEvent e) {
        // Additional logic for mouseEntered if needed
    }

    @Override
    @SuppressWarnings("unused")
    public void mouseExited(MouseEvent e) {
        // Additional logic for mouseExited if needed
    }
}
