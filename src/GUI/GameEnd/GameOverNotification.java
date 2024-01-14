package GUI.GameEnd;
import GUI.GameMenu.Menu;
import GUI.GameSFX.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static GUI.GameSFX.Button.*;

public class GameOverNotification extends JFrame {
    public Font Label1Font = loadCustomFont("Fonts/House_Of_Terror.ttf",50);
    public Font Label2Font = loadCustomFont("Fonts/House_Of_Terror.ttf",30);
    public Font GONButtonFont = loadCustomFont("Fonts/House_Of_Terror.ttf",40);
    private Button buttonHandlerGON=new Button();

    public GameOverNotification(){
        innitializeGON();
    }
    public void innitializeGON(){
        setTitle("Plants VS Zombies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350);
        setResizable(false);
        JLabel label1=new JLabel();
        label1.setText("GAME OVER !");
        label1.setFont(Label1Font);
        label1.setForeground(Color.yellow);
        label1.setBounds(175,50,250,100);
        add(label1);
        JLabel label2=new JLabel();
        label2.setText("WANNA TRY AGAIN ? WE NEED YOU!");
        label2.setFont(Label2Font);
        label2.setForeground(Color.yellow);
        label2.setBounds(80,100,400,100);
        add(label2);
        ImageIcon image = new ImageIcon("Image/background/GON.png");
        JLabel label = new JLabel();
        label.setIcon(image);
        setLayout(null);
        label.setBounds(0, 0, 600, 350);
        add(label);
        JButton ContinueButton=createStyledButton("Continue",GONButtonFont,new Color(0x5B5B7D),Color.GREEN);
        ContinueButton.setBounds(75,230,150,50);
        label.add(ContinueButton);
        ContinueButton.addActionListener(new ActionListener() {
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
        JButton ExitGameButton=createStyledButton("Exit game",GONButtonFont,new Color(0x5B5B7D),Color.GREEN);
        ExitGameButton.setBounds(300,230,150,50);
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
