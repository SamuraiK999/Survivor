package ui;

import java.awt.Graphics;
import java.util.ArrayList;

import ui.buttons.Button;

/**
 * The main class from which all menus derive.
 */
public abstract class Menu {
    public ArrayList<Button> buttons = new ArrayList<>();

    public Menu(){
        init();
    }

    protected void init() {

    }

    /**
     * Update.
     */
    public void update() {
        for (Button b : buttons) {
            b.update();
        }
    }

    /**
     * Draw.
     */
    public void draw(Graphics g) {
        for (Button b : buttons) {
            b.draw(g);
        }
    }
}
