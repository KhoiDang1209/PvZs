package GameElement;

import Game.GamePanel;

import javax.swing.*;

public class LawnMower extends JLabel {
    ImageIcon lawnmower;
    private int x;
@Override
    public int getX() {
        return x;
    }
    private int y;
@Override
    public int getY() {
        return y;
    }
    public GamePanel gamePanel;
    public LawnMower(GamePanel gamePanel, int x, int y)
    {
        this.gamePanel=gamePanel;
        this.x=x;
        this.y=y;
        lawnmower=new ImageIcon("Image/background/Lawn_Mower.png");
        setIcon(lawnmower);
        setSize(lawnmower.getIconWidth(),lawnmower.getIconHeight());
    }

}
