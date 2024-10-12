package shapes;

import java.awt.Graphics;
import main.Game;

/**
 * Rectangle.
 */
public class Rect {
    public float x;
    public float y;
    public float width;
    public float height;

    /**
     * Constuctor.
     */
    public Rect(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    /**
     * Draw the rectangle.
     */
    public void draw(Graphics g) {
        g.fillRect(
            (int) (x - Game.getCamera().x), 
            (int) (y - Game.getCamera().y), 
            (int) width, 
            (int) height);
    }
}
