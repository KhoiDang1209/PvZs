package Game;

import GUI.GameMenu.Menu;

public class GameWindow {

    public GameWindow() {
        initializeComponents();
    }

    private void initializeComponents() {
        Menu menu = new Menu();
        menu.setVisible(true);
    }
}
