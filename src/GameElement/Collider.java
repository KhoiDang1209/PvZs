package GameElement;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import Plant.Plant;

//Use to create plant on the field from the button
public class Collider extends JPanel implements MouseListener {

    ActionListener al;
    ImageIcon Peashootergif = new ImageIcon("Image/Plants/Fields/Peashooter.gif");
    public Collider() {
        // setBorder(new LineBorder(Color.RED));
        setOpaque(false);
        addMouseListener(this);
        // setBackground(Color.green);
        // Set the range of collider in this area only collider will receive mouseclic,
        // sun no use
        setSize(80, 100);
    }

    public Plant assignedPlant;

    public void setPlant(Plant p) {
        assignedPlant = p;
    }

    public void removePlant() {
        assignedPlant.stop();
        assignedPlant = null;
    }

    public boolean isInsideCollider(int tx) {
        return (tx > getLocation().x) && (tx < getLocation().x + 100);
    }

    public void setAction(ActionListener al) {
        this.al = al;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (al != null) {
            al.actionPerformed(new ActionEvent(this, ActionEvent.RESERVED_ID_MAX + 1, ""));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
