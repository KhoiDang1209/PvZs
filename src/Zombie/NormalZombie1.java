package Zombie;

import Game.GamePanel;

import javax.swing.*;
import java.awt.*;
import java.net.URL;
import java.util.Random;

public class NormalZombie1 extends zombie1 {
    private int xCoordinate3;
    private int yCoordinate3;

    public NormalZombie1(GamePanel parent, int lane) {
        super(parent, lane);
    }
    public void spawn(){
        int maxX = 1600;
        int maxY = 900;

        Random random = new Random();

        this.xCoordinate3 = random.nextInt(maxX);
        this.yCoordinate3 = random.nextInt(maxY);

        System.out.println("Zombie spawned at: (" + xCoordinate3 + ", " + yCoordinate3+ ")");

    }
    public void drawZombie(Graphics g) {
        String gifPath4 = "normalzombie.gif"; // Thay đổi đường dẫn tới file GIF của bạn

        // Sử dụng URL để đọc file từ đường dẫn
        URL imageUrl4 = getClass().getResource(gifPath4);

        // Kiểm tra xem có thể đọc được file không
        if (imageUrl4 != null) {
            // Sử dụng ImageIcon để hiển thị hình ảnh từ URL
            ImageIcon zombieIcon = new ImageIcon(imageUrl4);

            // Vẽ hình ảnh zombie tại vị trí (0, 0)
            zombieIcon.paintIcon(this, g, 0, 0);
        } else {
            // Xử lý trường hợp không thể đọc file
            g.drawString("Không thể đọc file zombie.gif", 10, 20);
        }
    }
}
