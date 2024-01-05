package GUI.GameMenu;
import GUI.GameMenu.Menu;
import GUI.GameSFX.Button;

import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import static GUI.GameSFX.Music.*;
import static GUI.GameSFX.Button.*;
public class PlantsMenu extends JFrame {
    private GUI.GameSFX.Button buttonHandlerPlants=new Button();
    private Clip clip;
    PlantsMenu() {
        innitializePlantsMenu();
        PlantsMenuMusic();
    }
    public void PlantsMenuMusic() {
        File soundPlantsMenuFile = new File("Sound/PlantsMenuSound(1).wav");
        MusicStart(soundPlantsMenuFile);
    }
    public void innitializePlantsMenu()
    {
        setTitle("Plants VS Zombies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setResizable(false);
        ImageIcon image = new ImageIcon("Image/background/PlantsMenu.jpg");
        JLabel label = new JLabel();
        label.setIcon(image);
        setLayout(null);
        label.setBounds(0, 0, 1600, 900);
        add(label);
        JButton Backbutton=createStyledButton("Back",customButtonFont,new Color(0xEFEF5B),new Color(0x006600));
        Backbutton.setBounds(50,50,150,65);
        Backbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    dispose();
                    buttonHandlerPlants.ButtonClickSound();
                    MusicStop();
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
