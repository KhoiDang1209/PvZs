package GUI.GameMenu;
import Game.GamePanel;
import GUI.GameSFX.Button;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.sound.sampled.Clip;
import javax.swing.*;
import static GUI.GameSFX.Music.*;
import static GUI.GameSFX.Button.*;
import Game.Game;
public class Menu extends JFrame  {
    public Font LabelMenuFont = loadCustomFont("Fonts/House_Of_Terror.ttf",120);
    public static Font customMenuButtonFont = loadCustomFont("Fonts/House_Of_Terror.ttf",45);
    private GUI.GameSFX.Button buttonHandlerMenu=new Button();
    private String name;
    private Clip clip;
    private Game game;
    private GamePanel gamePanel;
    public void MenuMusicStart() {
        File soundMenuFile = new File("Sound/MenuSound.wav");
        MusicStart(soundMenuFile);
    }
    public Menu() {
        innitializeMenu();
        MenuMusicStart();
    }
    public void innitializeMenu () {
            setTitle("Plants VS Zombies");
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setSize(1600, 900);
            setResizable(false);
            ImageIcon image1 = new ImageIcon("Image/background/MainMenu02.png");;
            JLabel label2= new JLabel();
            ImageIcon image2 = new ImageIcon("Image/background/Title.png");
            label2.setIcon(image2);
            label2.setBounds(350,50,899,278);
            add(label2);
            JLabel label = new JLabel();
            label.setIcon(image1);
            setLayout(null);
            label.setBounds(0, 0, 1600, 900);
            add(label);
            JButton Nbutton = createStyledButton("New", customMenuButtonFont,new Color(0x627184),Color.black);
            Nbutton.setBorderPainted(false);
            Nbutton.setVerticalTextPosition(JButton.BOTTOM);
            JButton Gbutton = createStyledButton("Game", customMenuButtonFont,new Color(0x627184),Color.black);
            Gbutton.setBorderPainted(false);
            JButton Pbutton = createStyledButton("Plants", customMenuButtonFont,new Color(0x777E84),Color.black);
            Pbutton.setBorderPainted(false);
            JButton Zbutton = createStyledButton("Zombies", customMenuButtonFont,new Color(0x8C9696),Color.black);
            Zbutton.setBorderPainted(false);
            setLayout(null);
            Nbutton.setBounds(685, 380, 150, 75);
            Nbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        buttonHandlerMenu.ButtonClickSound();
                        MusicStop();
                        dispose();
                        MenuMode menuMode = new MenuMode(game, gamePanel);
                        menuMode.setVisible(true);
                    }catch (Exception x){x.printStackTrace();}
                }
            });
            Gbutton.setBounds(685, 450, 150, 75);
            Gbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    buttonHandlerMenu.ButtonClickSound();
                    MusicStop();
                    dispose();
                    MenuMode menuMode = new MenuMode(game, gamePanel);
                    menuMode.setVisible(true);
                }catch (Exception x){x.printStackTrace();}
            }
        });
            Pbutton.setBounds(320, 460, 150, 75);
            Pbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        buttonHandlerMenu.ButtonClickSound();
                        MusicStop();
                        dispose();
                        PlantsMenu plantsMenu = new PlantsMenu();
                        plantsMenu.setVisible(true);
                    }catch(Exception x){x.printStackTrace();}
                }
            });
            Zbutton.setBounds(1050, 420, 150, 75);
            Zbutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        buttonHandlerMenu.ButtonClickSound();
                        MusicStop();
                        dispose();
                        ZombiesMenu zombiesMenu = new ZombiesMenu();
                        zombiesMenu.setVisible(true);
                    }catch(Exception x){x.printStackTrace();}
                }
            });
            add(Nbutton);
            add(Gbutton);
            add(Pbutton);
            add(Zbutton);
            setVisible(true);
        }
    }

