package GUI;

import static GUI.GameSFX.Music.*;

import java.awt.Color;
import java.awt.Font;
import java.io.File;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import Game.Game;
import InputForGame.MyMouseListener;

public class GamePanel extends JFrame implements Runnable {
    private Clip clip;
    private Game game;
    private JLabel timerLabel;
    private double setFPS = 60;

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
        setLayout(null);
        label.setBounds(0, 0, 1600, 900);
        add(label);
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
}
