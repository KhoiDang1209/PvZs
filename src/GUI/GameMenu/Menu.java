package GUI.GameMenu;
import GUI.GamePanel;
import GUI.GameSFX.Button;
import javax.swing.border.AbstractBorder;
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
    public Font LabelMenuFont = loadCustomFont("Fonts/House_Of_Terror.ttf",60);
    public static Font customMenuButtonFont = loadCustomFont("Fonts/House_Of_Terror.ttf",30);
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
            ImageIcon image = new ImageIcon("Image/background/MainMenu.png");
            JLabel label1= new JLabel();
            label1.setText("Welcome to PvZs");
            label1.setFont(LabelMenuFont);
            label1.setForeground(new Color(0x006600));
            setLayout(null);
            label1.setBounds(550,100,600,100);
            add(label1);
            JLabel label = new JLabel();
            label.setIcon(image);
            setLayout(null);
            label.setBounds(0, 0, 1536, 900);
            add(label);
            JButton Abutton = createStyledButton("Adventure", customMenuButtonFont,new Color(0xEFEF5B),new Color(0x006600));
            Abutton.setIcon(new ImageIcon("Image/Icon/NewGameIcon.jpg")); // Set the path to your icon image
            JButton Pbutton = createStyledButton("Plants", customMenuButtonFont,new Color(0xEFEF5B),new Color(0x006600));
            JButton Zbutton = createStyledButton("Zombies", customMenuButtonFont,new Color(0xEFEF5B),new Color(0x006600));
            JButton Ebutton = createStyledButton("Exit", customMenuButtonFont,new Color(0xEFEF5B),new Color(0x006600));
            setLayout(null);
            Abutton.setBounds(300, 675, 150, 75);
            Abutton.addActionListener(new ActionListener() {
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
            Pbutton.setBounds(550, 675, 150, 75);
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
            Zbutton.setBounds(800, 675, 150, 75);
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
            Ebutton.setBounds(1050, 675, 150, 75);
            Ebutton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        buttonHandlerMenu.ButtonClickSound();
                        MusicStop();
                        dispose();
                        System.exit(0);
                    }catch(Exception x){x.printStackTrace();}
                }
            });
            add(Abutton);
            add(Pbutton);
            add(Zbutton);
            add(Ebutton);
            setVisible(true);
        }
    }

