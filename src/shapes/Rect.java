package shapes;

import core.Game;
import java.awt.Graphics;
import java.awt.Point;

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
            (int) x, 
            (int) y, 
            (int) width, 
            (int) height);
    }

    /**
     * Draw the rectangle relatice to the camera.
     */
    public void drawRelative(Graphics g) {
        g.fillRect(
            (int) (getRelative().x), 
            (int) (getRelative().y), 
            (int) width, 
            (int) height);
    }

    public Point getCentered() {
        return new Point((int) (x + width / 2), (int) (y + height / 2));
    }

    public Rect getRelative() {
        return new Rect(x + Game.getCamera().x, y + Game.getCamera().y, width, height);
    }
}
