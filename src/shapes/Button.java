package shapes;

import java.awt.*;
import main.EH;
import main.Engine;

/**
 * Class for the buttons in the game
 */

public class Button {
    private String label;
    private Rect body;

    /**
     * Constuctor
     */
    public Button(String label, Rect body) {
        this.label = label;
        this.body = body;
        EH.getInstance().addButton(this);
    }

    public void update() {
        handleHovering();
    }

    public void draw (Graphics g) {
        body.draw(g);
        g.setColor(Color.white);
        g.drawString(
            label, (int) body.getCenterredCordinates().x,
                   (int) body.getCenterredCordinates().y);
    }

    private void use() {
        System.out.println("MAZNA");
    }

    public void onMouseReleased() {
        if (isHovering()) {
            use();
        }
    }

    public void handleHovering() {
        if (isHovering()) {
            // draw mask
            if (EH.isMousePressed()) {
                //draw another mask (same one)
            }
        }
    }

    private boolean isHovering() {
        if (Engine.collisionRect(body, new Rect(
            EH.getMouseCoordinates().x, 
            EH.getMouseCoordinates().y, 1, 1))) {

            return true;
        }
        return false;
    }
}