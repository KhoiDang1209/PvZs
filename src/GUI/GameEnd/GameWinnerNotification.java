package GUI.GameEnd;
import GUI.GameMenu.Menu;
import GUI.GameSFX.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.GameSFX.Button.*;

public class GameWinnerNotification extends JFrame {
    public Font Label1Font = loadCustomFont("Fonts/House_Of_Terror.ttf",60);
    public Font Label2Font = loadCustomFont("Fonts/House_Of_Terror.ttf",30);
    public Font GWNButtonFont = loadCustomFont("Fonts/House_Of_Terror.ttf",35);
    private Button buttonHandlerGON=new Button();

    public GameWinnerNotification(){
        innitializeGWN();
    }
    public void innitializeGWN(){
        setTitle("Plants VS Zombies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 541);
        setResizable(false);
        JLabel label1=new JLabel();
        label1.setText("YOU WON !");
        label1.setFont(Label1Font);
        label1.setForeground(Color.GREEN);
        label1.setBounds(200,120,250,100);
        add(label1);
        JLabel label2=new JLabel();
        label2.setText("THE ZOMBIE ATTACK HAS BEEN REPELLED!");
        label2.setFont(Label2Font);
        label2.setForeground(Color.GREEN);
        label2.setBounds(80,220,450,100);
        add(label2);
        ImageIcon image = new ImageIcon("Image/background/GWN.png");
        JLabel label = new JLabel();
        label.setIcon(image);
        setLayout(null);
        label.setBounds(0, 0, 600, 541);
        add(label);
        JButton BTMButton=createStyledButton("Back to menu",GWNButtonFont,new Color(0x46EF4B),new Color(0xEFEF5B));
        BTMButton.setBounds(50,350,225,70);
        label.add(BTMButton);
        BTMButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonHandlerGON.ButtonClickSound();
                    dispose();
                    Menu menu=new Menu();
                    menu.setVisible(true);
                }catch (Exception x){x.printStackTrace();}
            }
        });
        JButton ExitGameButton=createStyledButton("Exit game",GWNButtonFont,new Color(0x46EF4B),new Color(0xEFEF5B));
        ExitGameButton.setBounds(300,350,225,70);
        label.add(ExitGameButton);
        ExitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonHandlerGON.ButtonClickSound();
                    dispose();
                    System.exit(0);
                }catch (Exception x){x.printStackTrace();}
            }
        });
        setVisible(true);
    }
}
