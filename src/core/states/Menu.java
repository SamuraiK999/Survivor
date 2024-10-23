package core.states;

import java.awt.Graphics;
import java.util.ArrayList;
import ui.Button;

public abstract class Menu {
    public ArrayList<Button> buttons = new ArrayList<>();

    public Menu(){
        initializeMenu();
    }

    protected void initializeMenu() {

    }

    /*
     * Updates 
     */
    public void update() {
        for (Button b : buttons) {
            b.update();
        }
    }

    /*
     * DRAW
     */
    public void draw(Graphics g) {
        for (Button b : buttons) {
            b.draw(g);
        }
    }
}
