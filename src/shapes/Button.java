package shapes;

import java.awt.*;
import utility.EH;
import utility.Engine;

/**
 * Class for the buttons in the game.
 */

public class Button {
    private String label;
    private Rect body;
    private boolean drawMask = false;
    /**
     * Constuctor.
     */
    public Button(String label, Rect body) {
        this.label = label;
        this.body = body;
        EH.getInstance().addButton(this);
    }

    public void update() {
        handleHovering();
    }

    /**
     * Draw (non-relative to the camera).
     */
    public void draw(Graphics g) {
        Graphics g1 = g.create();
        g1.setColor(Color.black);
        body.draw(g1);
        g.setColor(Color.white);
        g.drawString(
            label, (int) body.getCentered().x,
                   (int) body.getCentered().y);
        g.setColor(Color.black);
        if (drawMask) {
            drawMask(g1);
        }
    }

    public void use() {
        System.out.println("MAZNA");
    }

    /**
     * Callback for when the mouse is released.
     */
    public void onMouseReleased() {
        if (isHovering()) {
            use();
        }
    }

    /**
     * Handles hovering the mouse above the button.
     */
    public void handleHovering() {
        if (isHovering()) {
            drawMask = true;
            if (EH.isMousePressed()) {
                //draw another mask (same one)
            }
        } else {
            drawMask = false;
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

    public void drawMask(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        float alpha = 0.5f; 
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
        g2d.setColor(Color.WHITE);
        body.draw(g2d);
        g2d.setColor(Color.black);
    }
}
