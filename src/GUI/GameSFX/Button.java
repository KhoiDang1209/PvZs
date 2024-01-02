package GUI.GameSFX;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class Button {
    private Clip clip;
    public static Font customButtonFont = loadCustomFont("Fonts/House_Of_Terror.ttf",30);
    public static Font loadCustomFont (String fontPath,int size){
        try {
            return Font.createFont(Font.TRUETYPE_FONT, new File(fontPath)).deriveFont(Font.PLAIN, size);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public void  ButtonClickSound() {
        try {
            File ClickSound = new File("Sound/click.wav");
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(ClickSound);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static JButton createStyledButton (String text, Font font,Color color1,Color color2){
        JButton button = new JButton(text);
        button.setFont(font);
        button.setForeground(color2);
        button.setBackground(color1);
        button.setFocusable(false);
        button.setHorizontalTextPosition(JButton.CENTER);
        button.setVerticalTextPosition(JButton.CENTER);
        button.setHorizontalAlignment(JButton.CENTER);
        button.setVerticalAlignment(JButton.CENTER);
        button.setBorder(BorderFactory.createLineBorder(new Color(0x006600), 2));
        return button;
    }
    public Button(){
    }
}
