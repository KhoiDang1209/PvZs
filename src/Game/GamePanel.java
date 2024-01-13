package Game;

import static GUI.GameSFX.Music.*;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;
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
import Plant.Peashooter;
import Plant.Plant;
import Sun.Sun;
import Zombie.Zombie;

public class GamePanel extends JFrame implements Runnable, Mouse {
    enum PlantType {
        None,
        Sunflower,
        Peashooter,
    }

    public Game gm = new Game(this);
    private MouseListener peashooterButtonMouseListener;
    private JButton pauseButton;

    private volatile boolean isPaused = false;
    public int PlantBox;
    public int PlantLane;
    public int PosOfBox;
    public int PosOflane;
    public Position position;
    public Collider[] colliders;
    private Clip clip;
    private double setFPS = 60;
    private long lastFrameTime = System.nanoTime();
    PlantType activePlantingBrush = PlantType.None;

    // Set of ArrayList
    // Use the zombie_units array
    // ArrayList<ArrayList<Zombie>> laneZombies;

    public ArrayList<Sun> activeSuns;

    // Set of Jlabel
    private JLabel timerLabel;
    JLabel NumOfSunBoard = new JLabel();
    JLabel sunScoreboard;

    // Set of image
    Image freezePeashooterImage;
    Image freezePeaImage;

    // Load zombie images

    // Set of imageicon
    JButton SunflowerButtton = new JButton();
    ImageIcon SunflowerCard = new ImageIcon("Image/Plants/Cards/SunflowerCard.png");
    JButton PeashooterButton = new JButton();
    ImageIcon PeashooterCard = new ImageIcon("Image/Plants/Cards/Peashootercard.png");
    JButton SnowPeashooterButton = new JButton();
    ImageIcon SnowPeashooterCard = new ImageIcon("Image/Plants/Cards/SnowPeaSeedCard.png");
    JButton WallnutButton = new JButton();
    ImageIcon WallnutCard = new ImageIcon("Image/Plants/Cards/Wall-nutCard.png");
    ImageIcon Peashootergif = new ImageIcon("Image/Plants/Fields/Peashooter.gif");
    ImageIcon Sunflowergif = new ImageIcon("Image/Plants/Fields/SunFlower.gif");
    ImageIcon originalImageIcon = new ImageIcon("Image/background/Frontyard.png");
    ImageIcon PeaImage = new ImageIcon("Image/Plants/Fields/ProjectilePea.png");
    Image originalImage = originalImageIcon.getImage();
    private JLabel PeashooterGIF;
    private JLabel PlantGIF;
    // Scale factor <1 = zoom out, >1 = zoom in
    double zoomOutFactor = 0.87; // Adjust this factor as needed
    int scaledWidth = (int) (originalImage.getWidth(null) * zoomOutFactor);
    int scaledHeight = (int) (originalImage.getHeight(null) * zoomOutFactor);

    Image scaledImage = originalImage.getScaledInstance(scaledWidth, scaledHeight, Image.SCALE_SMOOTH);
    ImageIcon scaledImageIcon = new ImageIcon(scaledImage);
    public JLabel label = new JLabel();

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
        innitializeGamePanel();
        GamePanelMusic();
        this.start();
    }

    private int PlacedPeashoter;
    public int[] LaneTopLeft;
    public int[] BoxTopLeft;

    public void innitializeGamePanel() {
        setTitle("Plants VS Zombies");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(originalImage.getWidth(null), originalImage.getHeight(null));
        setResizable(true);

        // Load zombie images
        // Other code...

        label.setIcon(scaledImageIcon);
        label.setBounds(0, 0, originalImage.getWidth(null), originalImage.getHeight(null));

        timerLabel = new JLabel("FPS = 0| UPS = 0| Time On Game = 0");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 12));
        timerLabel.setForeground(new Color(0x006600));
        timerLabel.setBounds(800, 20, 300, 30);
        add(timerLabel);
        add(label);
        //// ImageIcon pauseicon = new ImageIcon("Image/background/pause.png");
        //// pauseButton = new JButton();
        //// pauseButton.setIcon(pauseicon);
        //// pauseButton.setBounds(1400, 20, pauseicon.getIconWidth(),
        //// pauseicon.getIconHeight());
        //// pauseButton.addActionListener(new ActionListener() {
        //// @Override
        //// public void actionPerformed(ActionEvent e) {
        ////
        //// }
        // });
        add(pauseButton);
        JPanel ButtonPanel = new JPanel(new FlowLayout());
        SunflowerButtton.setIcon(SunflowerCard);
        SunflowerButtton.setBounds(50, 30, SunflowerCard.getIconWidth(), SunflowerCard.getIconHeight());
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
        PeashooterButton.setBounds(50, 215, PeashooterCard.getIconWidth(), PeashooterCard.getIconHeight());
        PeashooterButton.setBorder(BorderFactory.createLineBorder(new Color(0x818FB4), 3));
        PeashooterButton.setFocusable(true);
        PeashooterButton.setHorizontalAlignment(JButton.CENTER);
        PeashooterButton.setVerticalAlignment(JButton.CENTER);
        PeashooterGIF = new JLabel();
        // PeashooterButton.setAction(new AbstractAction() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // activePlantingBrush = PlantType.Peashooter;
        // }
        // });
        /*
         * peashooterButtonMouseListener = new MouseAdapter() {
         * 
         * @Override
         * public void mouseClicked(MouseEvent e) {
         * // Code to get an image when the button is clicked
         * // PeashooterGIF != null
         * activePlantingBrush = PlantType.Peashooter;
         * PlacedPeashoter = 0;
         * PeashooterGIF.setIcon(Peashootergif);
         * PeashooterGIF.setBounds(e.getXOnScreen() - Peashootergif.getIconWidth() / 2,
         * e.getYOnScreen() - Peashootergif.getIconHeight() / 2,
         * Peashootergif.getIconWidth(),
         * Peashootergif.getIconHeight());
         * // Change the MouseListener dynamically
         * PeashooterButton.removeMouseListener(peashooterButtonMouseListener); //
         * Remove the current MouseListener
         * label.addMouseListener(new LabelMouseListener()); // Add a new MouseListener
         * label.addMouseMotionListener(new LabelMouseMotionListener()); // Add a new
         * MouseMotionListener
         * }
         * 
         * @Override
         * public void mousePressed(MouseEvent e) {
         * // Handle mouse press event
         * }
         * 
         * @Override
         * public void mouseReleased(MouseEvent e) {
         * // Handle mouse release event here
         * activePlantingBrush = PlantType.Peashooter;
         * }
         * 
         * @Override
         * public void mouseEntered(MouseEvent e) {
         * // Handle mouse enter event
         * }
         * 
         * @Override
         * public void mouseExited(MouseEvent e) {
         * // Code to move the GIF when the mouse leaves the button
         * 
         * }
         * };
         */
        // PeashooterButton.addMouseListener(peashooterButtonMouseListener);
        /*
         * PeashooterButton.addMouseMotionListener(new MouseMotionListener() {
         * 
         * @Override
         * public void mouseDragged(MouseEvent e) {
         * // Handle mouse drag event
         * }
         * 
         * @Override
         * public void mouseMoved(MouseEvent e) {
         * 
         * }
         * });
         */
        PeashooterButton.addActionListener((ActionEvent e) -> {
            activePlantingBrush = PlantType.Peashooter;
        });
        label.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle mouse click event
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Handle mouse press event
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Handle mouse release event
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Handle mouse enter event
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Handle mouse exit event
            }
        });
        label.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Handle mouse drag event
            }

            @Override
            public void mouseMoved(MouseEvent e) {
            }
        }); // Add a new
        SnowPeashooterButton.setIcon(SnowPeashooterCard);
        SnowPeashooterButton.setBounds(50, 400, SnowPeashooterCard.getIconWidth(), SnowPeashooterCard.getIconHeight());
        SnowPeashooterButton.setBorder(BorderFactory.createLineBorder(new Color(0x818FB4), 3));
        SnowPeashooterButton.setFocusable(true);
        SnowPeashooterButton.setHorizontalAlignment(JButton.CENTER);
        SnowPeashooterButton.setVerticalAlignment(JButton.CENTER);
        SnowPeashooterButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int x = evt.getX();
                int y = evt.getY();
                Position position = new Position(x, y); // Assuming y is for Lane and x is for Box
                int lane = position.Lane(y);
                int box = position.Box(x);
                System.out.println("Peashooter Released at Lane: " + lane + ", Box: " + box);
            }
        });
        WallnutButton.setIcon(WallnutCard);
        WallnutButton.setBounds(50, 585, WallnutCard.getIconWidth(), WallnutCard.getIconHeight());
        WallnutButton.setBorder(BorderFactory.createLineBorder(new Color(0x818FB4), 3));
        WallnutButton.setFocusable(true);
        WallnutButton.setHorizontalAlignment(JButton.CENTER);
        WallnutButton.setVerticalAlignment(JButton.CENTER);
        WallnutButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                int x = evt.getX();
                int y = evt.getY();
                Position position = new Position(x, y); // Assuming y is for Lane and x is for Box
                int lane = position.Lane(y);
                int box = position.Box(x);
                System.out.println("Peashooter Released at Lane: " + lane + ", Box: " + box);
            }
        });
        label.add(SnowPeashooterButton);
        label.add(WallnutButton);
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

        int InitialnumOfSun = 1500;
        setNumOfSun(InitialnumOfSun);
        NumOfSunBoard.setFont(new Font("Arial", Font.BOLD, 20));
        NumOfSunBoard.setForeground(new Color(0, 0, 0));
        NumOfSunBoard.setBounds(218, 0, 1400, 166);
        sunshowLabel.add(NumOfSunBoard);

        activeSuns = new ArrayList<>();
        // 6 seconds 1 sun
        Timer sunProducer = new Timer(7500, (ActionEvent e) -> {
            System.out.println("Add sun");
            Random rnd = new Random();
            // Game Field from 313 = minX to maxX = 1270 or 1273, yMin = 85 to 650= maxY
            // This is the range x and y of Field
            Sun newSun = new Sun(this, rnd.nextInt(887) + 313, 0, rnd.nextInt(300) + 350);
            activeSuns.add(newSun);
            label.add(newSun);
        });
        sunProducer.start();
        advancerTimer = new Timer(60, (ActionEvent e) -> advance());
        advancerTimer.start();

        // Zombie producer

        zombieProducer = new Timer(10000, (ActionEvent e) -> {
            Random rnd = new Random();
            int l = rnd.nextInt(5);
            int t = rnd.nextInt(100);
            int y = laneSpawn(l);
            int x = 1600;
            Zombie z = null;
            String[] allZombieTypes = { "NormalZombie", "ConeHeadZombie",
                    "BucketHeadZombie", "BalloonZombie" };
            int randomZombieIndex = rnd.nextInt(allZombieTypes.length);
            String selectedZombieType = allZombieTypes[randomZombieIndex];
            z = Zombie.getZombie(selectedZombieType, GamePanel.this, l);
            System.out.printf("Add z at land %d", l);
            gm.Zombie_units.get(l).add(z);
            label.add(z);
            z.setBounds(1600, y, z.getWidth(), z.getHeight());
        });
        zombieProducer.start();

        // Set the box where plant will be add and action in each box for all field

        colliders = new Collider[45];
        LaneTopLeft = new int[] { 120, 250, 380, 510, 645 };
        BoxTopLeft = new int[] { 315, 442, 543, 654, 755, 871, 964, 1070, 1175 };
        for (int i = 0; i < 45; i++) {
            int Box = i % 9;
            int Land = i / 9;
            Position position = new Position(BoxTopLeft[Box], LaneTopLeft[Land]);
            PosOfBox = position.BoxDraw(BoxTopLeft[Box]);
            PosOflane = position.LaneDraw(LaneTopLeft[Land]);
            Collider a = new Collider();
            a.setLocation(PosOfBox, PosOflane);
            // i % 9 = Box, i / 9 = Lane
            a.setAction(new PlantActionListener(Box, Land));
            colliders[i] = a;
            label.add(a);
        }

        redrawTimer = new Timer(1500, (ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> repaint());
        });
        redrawTimer.start();

    }

    private void togglePause() {
        if (isPaused) {
            resumeGame();
            pauseButton.setText("Pause");
        } else {
            pauseGame();
            pauseButton.setText("Resume");
        }
        isPaused = !isPaused;
    }

    // Trigger advanced method in each class
    private void advance() {

        for (int i = 0; i < gm.Zombie_units.size(); i++) {

            for (Zombie z : gm.Zombie_units.get(i)) {
                int newX = z.posX - z.speed;
                int y = laneSpawn(z.myLane);
                z.setPosX(newX);
                z.setBounds(newX, y, z.getWidth(), z.getHeight());
                System.out.println("Zombie is at " + newX + " and speed is " + z.getSpeed());
                z.advance();
            }

            for (int j = 0; j < gm.PeaInField.get(i).size(); j++) {
                Pea p = gm.PeaInField.get(i).get(j);
                p.advance();
            }
        }

        for (int i = 0; i < activeSuns.size(); i++) {
            activeSuns.get(i).FallSun();
        }

    }

    // Make the jpanel to remove the sun after being destroy
    public void removeSun(Sun sun) {
        activeSuns.remove(sun);
        label.remove(sun);
        label.revalidate();
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

    private int frame = 0;
    private double lastTimeFPS = System.nanoTime();
    private double now;
    private int updateGame = 0;
    private double lastTimeUPS = System.nanoTime();
    private double lastTimeCheck = System.currentTimeMillis();
    private double countTime = 0;

    @Override
    public void run() {

        double timePerFrame = Math.pow(10, 9) / setFPS;
        double timePerUpdate = Math.pow(10, 9) / setUPS;

        double startTime = System.currentTimeMillis();

        while (true) {
            now = System.nanoTime();
            countTime = System.currentTimeMillis();
            // Render
            if (now - lastTimeFPS >= timePerFrame) {
                frame++;
                lastTimeFPS = System.nanoTime();
                // SwingUtilities.invokeLater(() -> repaint());
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
            /*
             * if (now - lastTimeFPS >= timePerFrame) {
             * frame++;
             * lastTimeFPS = System.nanoTime();
             * SwingUtilities.invokeLater(() -> repaint());
             * }
             */

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

    // Make Plant

    class PlantActionListener implements ActionListener {

        int x, y;

        public PlantActionListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (activePlantingBrush == PlantType.Peashooter) {
                if (getNumOfSun() >= 100) {
                    // Set place that bullet fire
                    colliders[x + y * 9].setPlant(new Peashooter(GamePanel.this, x, y));
                    // new Peashooter(GamePanel.this, x, y) position where the pea bullet fire
                    setNumOfSun(getNumOfSun() - 100);
                }
            }

            activePlantingBrush = PlantType.None;
        }
    }

    // Finding correct formular in process
    private int DrawBox;
    private int DrawLane;

    @Override
    public void paint(Graphics graphic) {
        super.paint(graphic);
        // Plant Generation

        for (int i = 0; i < 45; i++) {
            int Box = i % 9;
            int Land = i / 9;
            Position position = new Position(BoxTopLeft[Box], LaneTopLeft[Land]);
            DrawBox = position.BoxDraw(BoxTopLeft[Box]);
            DrawLane = position.LaneDraw(LaneTopLeft[Land]);
            Collider c = colliders[i];
            if (c.assignedPlant != null) {
                Plant WhichPlant = c.assignedPlant;
                if (WhichPlant instanceof Peashooter) {
                    // Draw ImageIcon as Image
                    Peashootergif.paintIcon(this, graphic, DrawBox, DrawLane);
                }
            }
        }

        // Bullet generation for normal Pea
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < gm.PeaInField.get(i).size(); j++) {
                Pea p = gm.PeaInField.get(i).get(j);
                if (p instanceof Pea) {
                    // Draw ImageIcon as Image
                    PeaImage.paintIcon(this, graphic, p.posX, 130 + (i * 120));
                }
            }
        }
    }

    private int x;
    private int y;

    private class LabelMouseListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent e) {
            /*
             * System.out.println("Click!");
             * x = e.getXOnScreen();
             * y = e.getYOnScreen();
             * Position position = new Position(x, y);
             * System.out.printf("x = %d, y = %d", x, y);
             * System.out.println();
             * int box = position.Box(x);// get the box number
             * int lane = position.Lane(y);// get the Lane number
             * System.out.printf("Box is: %d. Lane is: %d", box, lane);
             * System.out.println();
             * 
             * if (PeashooterGIF != null && PlacedPeashoter == 0) {
             * // Need a check if there is a plant or not
             * PlantBox = position.BoxDraw(x);
             * PlantLane = position.LaneDraw(y);
             * PlantGIF = new JLabel();
             * PlantGIF.setIcon(Peashootergif);
             * PlacedPeashoter = 1;
             * label.remove(PeashooterGIF);
             * 
             * }
             * if (PlacedPeashoter == 1) {
             * PeashooterButton.addMouseListener(peashooterButtonMouseListener);
             * label.removeMouseListener(this);
             * }
             */
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }
    }

    private class LabelMouseMotionListener implements MouseMotionListener {
        @Override
        public void mouseMoved(MouseEvent e) {
            // updateGIFPosition(e);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            // Code for the new MouseMotionListener on the label
        }
    }

    /*
     * private void updateGIFPosition(MouseEvent e) {
     * // Code for updating the position of the GIF
     * if (PeashooterGIF != null && PlacedPeashoter == 0) {
     * PeashooterGIF.setIcon(Peashootergif);
     * PeashooterGIF.setBounds(
     * e.getXOnScreen() - Peashootergif.getIconWidth() / 2,
     * e.getYOnScreen() - Peashootergif.getIconHeight() / 2,
     * Peashootergif.getIconWidth(),
     * Peashootergif.getIconHeight());
     * label.add(PeashooterGIF);
     * 
     * } else if (PlacedPeashoter == 1) {
     * label.remove(PeashooterGIF);
     * 
     * }
     * }
     */
    public int laneSpawn(int x) {
        if (x == 1)
            return 80;
        else if (x == 2) {
            return 225;
        } else if (x == 3) {
            return 360;
        } else if (x == 4) {
            return 480;
        } else
            return 600;
    }
}