package Zombie;

import Game.GamePanel;
import Plant.FreezePeashooter;
import Plant.Peashooter;
import Plant.Sunflower;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class BalloonZombie extends Zombie {

    public BalloonZombie(GamePanel parent, int lane) {
        super(parent, lane);
    }

}
