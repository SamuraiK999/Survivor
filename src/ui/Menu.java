package ui;

import java.awt.Graphics;
import java.util.ArrayList;

import ui.buttons.enums.ButtonSite;

/**
 * The main class from which all menus derive.
 */
public abstract class Menu {
    public ArrayList<Button> buttons = new ArrayList<>();

    protected ButtonSite location;

    /**
     * Constructor.
     */
    public Menu(ButtonSite location) {
        this.location = location;
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
