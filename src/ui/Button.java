package ui;

import java.awt.*;
import java.awt.image.BufferedImage;
import shapes.Rect;
import utility.EH;
import utility.Engine;
import utility.IM;

/**
 * Class for the buttons in the game.
 */

public class Button {

    private BufferedImage image;
    private Rect body;
    private boolean drawMask = false;
    private float alpha = 0.5f;

    /**
     * Constuctor.
     */
    public Button(BufferedImage image, Rect body) {
        this.image = image;
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
        Graphics gCpy = g.create();

        IM.drawRotatedImage(g, image,
            new Point((int) (body.x + body.width / 2), (int) (body.y + body.height / 2)), 1, 1, 0);

        if (drawMask) {
            drawMask(gCpy);
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
            alpha = 0.25f;
            if (EH.isMousePressed()) {
                alpha = 0.5f;
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

    /**
     * Draw the button's mask.
     */
    public void drawMask(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));

        g2d.setColor(Color.WHITE);
        body.draw(g2d);
    }
}
