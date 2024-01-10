package Zombie;

import java.awt.*;
import java.awt.event.KeyEvent;

public interface ZombieFather {
    void advance();


    void takeDamage(int damage);


    void setColor(Color color);


    void changeColor(Color color);


    void slowDown();


    void applyDamageEffects();


    boolean isAlive();


    void die();

    void playDeathAnimation();


    void dropItems();

    void keyPressed(KeyEvent e);
}


