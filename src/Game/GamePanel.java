package Game;

import static GUI.GameSFX.Music.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.sound.sampled.Clip;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import GameElement.Collider;
import GameElement.Position;
import InputForGame.Mouse;
import InputForGame.MyMouseListener;
import Plant.Pea;
import Sun.Sun;
import Zombie.Zombie;
//import sun.security.provider.Sun;

public class GamePanel extends JFrame implements Runnable, Mouse {

    public Collider[] colliders;
    private Clip clip;
    private Game game;
    private double setFPS = 60;

    // Set of ArrayList
    // Use the zombie_units array
    // ArrayList<ArrayList<Zombie>> laneZombies;
    public ArrayList<ArrayList<Zombie>> Zombie_units;
    public ArrayList<Sun> activeSuns;
    public ArrayList<ArrayList<Pea>> PlantInField;

    // Set of Jlabel
    private JLabel timerLabel;
    JLabel NumOfSunBoard = new JLabel();
    JLabel sunScoreboard;
    private JLabel label = new JLabel();

    // Set of image
    Image bgImage;
    Image peashooterImage;
    Image freezePeashooterImage;
    Image sunflowerImage;
    Image peaImage;
    Image freezePeaImage;

    Image normalZombieImage;
    Image ZombieFireImage;
    Image coneHeadZombieImage;

    // Set of imageicon
    JButton SunflowerButtton = new JButton();
    ImageIcon SunflowerCard = new ImageIcon("Image/Plants/Cards/SunflowerCard.png");
    JButton PeashooterButton = new JButton();
    ImageIcon PeashooterCard = new ImageIcon("Image/Plants/Cards/Peashootercard.png");

    // Set of Timer
    Timer redrawTimer;
    Timer advancerTimer;
    Timer sunProducer;
    Timer zombieProducer;

    public double getSetFPS() {
        return setFPS;
    }

    public void setSetFPS(double setFPS) {
        this.setFPS = setFPS;
    }

    private double setUPS = 60;

    public double getSetUPS() {
        return setUPS;
    }

    public void setSetUPS(double setUPS) {
        this.setUPS = setUPS;
    }

    private Thread gameThread;

    public Thread getGameThread() {
        return gameThread;
    }

    public void setGameThread(Thread gameThread) {
        this.gameThread = gameThread;
    }

    private MyMouseListener myMouseListener;

    public MyMouseListener getMyMouseListener() {
        return myMouseListener;
    }

    public void setMyMouseListener(MyMouseListener myMouseListener) {
        this.myMouseListener = myMouseListener;
    }

    // global value of Sun count
    private int Sun;

    public int getNumOfSun() {
        return Sun;
    }

    public JLabel setNumOfSun(int sunNum) {
        this.Sun = sunNum;
        this.NumOfSunBoard.setText(String.valueOf(sunNum));
        return NumOfSunBoard;
    }

    private volatile boolean isRunning = true;

    public GamePanel(Game game) {
        this.game=game;
        innitializeGamePanel();
        GamePanelMusic();
        this.start();
    }

    public void innitializeGamePanel() {
        setTitle("Plants VS Zombies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setResizable(false);

        ImageIcon background_image = new ImageIcon("Image/background/Frontyard.png");
        label.setIcon(background_image);
        label.setBounds(0, 0, 1600, 900);

        timerLabel = new JLabel("FPS = 0| UPS = 0| Time On Game = 0");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(new Color(0x006600));
        timerLabel.setBounds(1200, 20, 300, 30);
        add(timerLabel);
        add(label);
        JPanel ButtonPanel = new JPanel(new FlowLayout());
        SunflowerButtton.setIcon(SunflowerCard);
        SunflowerButtton.setBounds(50, 50, 140, 194);
        SunflowerButtton.setBorder(BorderFactory.createLineBorder(new Color(0x818FB4), 3));
        SunflowerButtton.setFocusable(true);
        SunflowerButtton.setHorizontalAlignment(JButton.CENTER);
        SunflowerButtton.setVerticalAlignment(JButton.CENTER);
        SunflowerButtton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int x = evt.getX();
                int y = evt.getY();
                Position position = new Position(x, y); // Assuming y is for Lane and x is for Box
                int lane = position.Lane(y);
                int box = position.Box(x);
                System.out.println("Sunflower Released at Lane: " + lane + ", Box: " + box);
            }
        });
        PeashooterButton.setIcon(PeashooterCard);
        PeashooterButton.setBounds(50, 250, 140, 195);
        PeashooterButton.setBorder(BorderFactory.createLineBorder(new Color(0x818FB4), 3));
        PeashooterButton.setFocusable(true);
        PeashooterButton.setHorizontalAlignment(JButton.CENTER);
        PeashooterButton.setVerticalAlignment(JButton.CENTER);
        PeashooterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int x = evt.getX();
                int y = evt.getY();
                Position position = new Position(x, y); // Assuming y is for Lane and x is for Box
                int lane = position.Lane(y);
                int box = position.Box(x);
                System.out.println("Peashooter Released at Lane: " + lane + ", Box: " + box);
            }
        });
        label.add(SunflowerButtton);
        label.add(PeashooterButton);
        getContentPane().add(label);
        pack();
        setLocationRelativeTo(null);
        // Sun making
        // Sun count

        ImageIcon sunshow = new ImageIcon("Image/Picture holder.PNG");
        JLabel sunshowLabel = new JLabel(sunshow);
        sunshowLabel.setBounds(100, 0, 470, 100);
        label.add(sunshowLabel);

        int InitialnumOfSun = 150;
        setNumOfSun(InitialnumOfSun);
        NumOfSunBoard.setFont(new Font("Arial", Font.BOLD, 20));
        NumOfSunBoard.setForeground(new Color(0, 0, 0));
        NumOfSunBoard.setBounds(218, 0, 1400, 166);
        sunshowLabel.add(NumOfSunBoard);

        redrawTimer = new Timer(25, (ActionEvent e) -> {
            repaint();
        });

        redrawTimer.start();
        activeSuns = new ArrayList<>();
        // 6 seconds 1 sun
        Timer sunProducer = new Timer(6000, (ActionEvent e) -> {
            System.out.println("Add sun");
            Random rnd = new Random();
            Sun newSun = new Sun(this, rnd.nextInt(800) + 100, 0, rnd.nextInt(300) + 200);
            activeSuns.add(newSun);
            label.add(newSun);

            Timer sunTimer = new Timer(60, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent ae) {
                    // Update the position of each sun in activeSuns
                    Iterator<Sun> iterator = activeSuns.iterator();
                    while (iterator.hasNext()) {
                        Sun sun = iterator.next();
                        sun.FallSun();
                    }
                }
            });

            // Start the timer only if it's not already running
            if (!sunTimer.isRunning()) {
                sunTimer.start();
            }
        });
        sunProducer.start();

        // Manage the zombie and plant in 5 line

        Zombie_units = new ArrayList<>();
        Zombie_units.add(new ArrayList<>()); // line 1
        Zombie_units.add(new ArrayList<>()); // line 2
        Zombie_units.add(new ArrayList<>()); // line 3
        Zombie_units.add(new ArrayList<>()); // line 4
        Zombie_units.add(new ArrayList<>()); // line 5

        PlantInField = new ArrayList<>();
        PlantInField.add(new ArrayList<>()); // line 1
        PlantInField.add(new ArrayList<>()); // line 2
        PlantInField.add(new ArrayList<>()); // line 3
        PlantInField.add(new ArrayList<>()); // line 4
        PlantInField.add(new ArrayList<>()); // line 5
    }

    private void advance() {
        /*
         * for (int i = 0; i < 5; i++) {
         * for (Zombie z : laneZombies.get(i)) {
         * z.advance();
         * }
         * 
         * for (int j = 0; j < lanePeas.get(i).size(); j++) {
         * Pea p = lanePeas.get(i).get(j);
         * p.advance();
         * }
         * 
         * }
         */
        for (int i = 0; i < activeSuns.size(); i++) {
            activeSuns.get(i).FallSun();
        }

    }

    // Make the jpanel to remove the sun after being destroy
    public void removeSun(Sun sun) {
        activeSuns.remove(sun);
        label.remove(sun);
        label.revalidate();
        label.repaint();
    }

    public void GamePanelMusic() {
        File soundIngameFile = new File("Sound/IngameSound.wav");
        MusicStart(soundIngameFile);
    }

    public void start() {
        gameThread = new Thread(this) {
        };
        gameThread.start();
    }

    public void pauseGame() {
        isRunning = false;
    }

    public void resumeGame() {
        isRunning = true;
    }

    @Override
    public void run() {
        double lastTimeCheck = System.currentTimeMillis();
        double timePerFrame = Math.pow(10, 9) / setFPS;
        double timePerUpdate = Math.pow(10, 9) / setUPS;
        double lastTimeFPS = System.nanoTime();
        double lastTimeUPS = System.nanoTime();
        double startTime = System.currentTimeMillis();
        double countTime = 0;
        int updateGame = 0;
        int frame = 0;
        double now;
        while (true) {
            now = System.nanoTime();
            countTime = System.currentTimeMillis();
            // Render
            if (now - lastTimeFPS >= timePerFrame) {
                frame++;
                lastTimeFPS = System.nanoTime();
                SwingUtilities.invokeLater(() -> repaint());
            }
            // Update
            if (now - lastTimeUPS >= timePerUpdate) {
                updateGame++;
                lastTimeUPS = System.nanoTime();
            }
            // FPS Counter & UPS Counter
            if (System.currentTimeMillis() - lastTimeCheck >= 1000) {
                String rs = "FPS: " + frame + "| UPS: " + updateGame + "| Time On Game: "
                        + (int) (countTime - startTime) / 1000 + " s";
                SwingUtilities.invokeLater(() -> timerLabel.setText(rs));
                updateGame = 0;
                frame = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    void initializeInput() {
        myMouseListener = new MyMouseListener(this);
        this.addMouseListener(myMouseListener);
        this.addMouseMotionListener(myMouseListener);

        requestFocus();
    }

    @Override
    public void mouseClicked(int x, int y) {
    }

    @Override
    public void mousePressed(int x, int y) {
    }

    @Override
    public void mouseOver(int x, int y) {
    }

    @Override
    public void mouseReleased(int x, int y) {
        Position position = new Position(y, x); // Assuming y is for Lane and x is for Box
        int lane = position.Lane(y);
        int box = position.Box(x);
        System.out.println("Released at Lane: " + lane + ", Box: " + box);
    }

    @Override
    public void mouseEntered(int x, int y) {
    }

    @Override
    public void mouseExited(int x, int y) {
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    int mouseX, mouseY;

    @Override
    public void mouseMoved(int x, int y) {
        mouseX = x;
        mouseY = y;
    }

}