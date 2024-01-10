package Game;

import GUI.GameMenu.Menu;

public class GameWindow {
    enum PlantType {
        None,
        Sunflower,
        Peashooter,
        FreezePeashooter
    }

    public GameWindow() {
        initializeComponents();
    }

    private void initializeComponents() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}
