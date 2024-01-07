package Game;

import static GUI.GameSFX.Music.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import GameElement.Position;
import javax.sound.sampled.Clip;
import javax.swing.*;
import Game.Game;
import GameElement.Collider;
import InputForGame.Mouse;
import InputForGame.MyMouseListener;
import Zombie.Zombie;
import sun.security.provider.Sun;

public class GamePanel extends JFrame implements Runnable, Mouse {
    public Collider[] colliders;
    private Clip clip;
    private Game game;
    private JLabel timerLabel;
    private double setFPS = 60;
    public ArrayList<ArrayList<Zombie>> Zombie_units;

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
    JButton SunflowerButtton=new JButton();
    ImageIcon SunflowerCard=new ImageIcon("Image/Plants/Cards/SunflowerCard.png");
    JButton PeashooterButton= new JButton();
    ImageIcon PeashooterCard=new ImageIcon("Image/Plants/Cards/Peashootercard.png");
    private volatile boolean isRunning = true;
    public GamePanel(Game game) {
        innitializeGamePanel();
        GamePanelMusic();
        this.start();
    }

    public void innitializeGamePanel() {
        setTitle("Plants VS Zombies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1600, 900);
        setResizable(false);
        ImageIcon image = new ImageIcon("Image/background/Frontyard.png");
        timerLabel = new JLabel("FPS = 0| UPS = 0| Time On Game = 0");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        timerLabel.setForeground(new Color(0x006600));
        timerLabel.setBounds(1200, 20, 300, 30);
        add(timerLabel);
        JLabel label = new JLabel();
        label.setIcon(image);
        label.setBounds(0, 0, 1600, 900);
        add(label);
        JPanel ButtonPanel=new JPanel(new FlowLayout());
        SunflowerButtton.setIcon(SunflowerCard);
        SunflowerButtton.setBounds(50,50,140,194);
        SunflowerButtton.setBorder(BorderFactory.createLineBorder(new Color(0x818FB4), 3));
        SunflowerButtton.setFocusable(true);
        SunflowerButtton.setHorizontalAlignment(JButton.CENTER);
        SunflowerButtton.setVerticalAlignment(JButton.CENTER);
        SunflowerButtton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int x = evt.getX();
                int y = evt.getY();
                Position position = new Position(x,y); // Assuming y is for Lane and x is for Box
                int lane = position.Lane(y);
                int box = position.Box(x);
                System.out.println("Sunflower Released at Lane: " + lane + ", Box: " + box);
            }
        });
        PeashooterButton.setIcon(PeashooterCard);
        PeashooterButton.setBounds(50,250,140,195);
        PeashooterButton.setBorder(BorderFactory.createLineBorder(new Color(0x818FB4), 3));
        PeashooterButton.setFocusable(true);
        PeashooterButton.setHorizontalAlignment(JButton.CENTER);
        PeashooterButton.setVerticalAlignment(JButton.CENTER);
        PeashooterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int x = evt.getX();
                int y = evt.getY();
                Position position = new Position(x,y); // Assuming y is for Lane and x is for Box
                int lane = position.Lane(y);
                int box = position.Box(x);
                System.out.println("Sunflower Released at Lane: " + lane + ", Box: " + box);
            }
        });
        label.add(SunflowerButtton);
        label.add(PeashooterButton);
        getContentPane().add(label);
        pack();
        setLocationRelativeTo(null);
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
    @Override
    public void mouseMoved(int x, int y) {
    }
}