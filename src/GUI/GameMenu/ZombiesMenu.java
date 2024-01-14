package GUI.GameMenu;

import GUI.GameMenu.Menu;
import GUI.GameSFX.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static GUI.GameSFX.Button.*;
import static GUI.GameSFX.Music.*;
public class ZombiesMenu extends JFrame {
    private GUI.GameSFX.Button buttonHandlerZombies=new Button();
    ZombiesMenu() {
        innitializeZombiesMenu();
        ZombiesMenuMusic();
    }
    public void ZombiesMenuMusic(){
        File zombiesMenuSound= new File("Sound/ZombiesMenuSound.wav");
        MusicStart(zombiesMenuSound);
    }
    public void innitializeZombiesMenu()
    {
        setTitle("Plants VS Zombies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setResizable(false);
        ImageIcon image = new ImageIcon("Image/background/ZombiesMenu.jpg");
        JLabel label = new JLabel();
        label.setIcon(image);
        setLayout(null);
        label.setBounds(0, 0, 1600, 900);
        add(label);
        ImageIcon zombieDescriptionImage = new ImageIcon("Image/Galary/Zombies/NormalZombieExplain.png");
        JLabel zombieDescriptionLabel = new JLabel();
        zombieDescriptionLabel.setIcon(zombieDescriptionImage);
        zombieDescriptionLabel.setBounds(100, 250, zombieDescriptionImage.getIconWidth(), zombieDescriptionImage.getIconHeight());
        label.add(zombieDescriptionLabel);
        ImageIcon zombieDescriptionImage2 = new ImageIcon("Image/Galary/Zombies/ConeHeadZombieExplain.png");
        JLabel zombieDescriptionLabel2 = new JLabel();
        zombieDescriptionLabel2.setIcon(zombieDescriptionImage2);
        zombieDescriptionLabel2.setBounds(450, 250, zombieDescriptionImage2.getIconWidth(), zombieDescriptionImage2.getIconHeight());
        label.add(zombieDescriptionLabel2);
        ImageIcon zombieDescriptionImage3 = new ImageIcon("Image/Galary/Zombies/BucketHeadZombieExplain.png");
        JLabel zombieDescriptionLabel3 = new JLabel();
        zombieDescriptionLabel3.setIcon(zombieDescriptionImage3);
        zombieDescriptionLabel3.setBounds(800, 250, zombieDescriptionImage3.getIconWidth(), zombieDescriptionImage3.getIconHeight());
        label.add(zombieDescriptionLabel3);
        ImageIcon zombieDescriptionImage4 = new ImageIcon("Image/Galary/Zombies/BalloonZombieExplain.png");
        JLabel zombieDescriptionLabel4 = new JLabel();
        zombieDescriptionLabel4.setIcon(zombieDescriptionImage4);
        zombieDescriptionLabel4.setBounds(1150, 250, zombieDescriptionImage4.getIconWidth(), zombieDescriptionImage4.getIconHeight());
        label.add(zombieDescriptionLabel4);
        JButton Backbutton=createStyledButton("Back",customButtonFont, new Color(0xEFEF5B),new Color(0x006600));
        Backbutton.setBounds(50,50,150,65);
        Backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    MusicStop();
                    dispose();
                    buttonHandlerZombies.ButtonClickSound();
                    GUI.GameMenu.Menu menu = new Menu();
                    menu.setVisible(true);
                }catch (Exception x)
                {
                    x.printStackTrace();
                }
            }
        });
        label.add(Backbutton);
    }
}
