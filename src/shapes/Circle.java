package shapes;

import java.awt.Graphics;
import main.Game;

/**
 * Circle.
 */
public class Circle {
    public float x;
    public float y;
    public float r;

    /**
     * Constuctor.
     */
    public Circle(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    /**
     * Draw the circle.
     */
    public void draw(Graphics g) {
        g.fillArc(
            (int) (x - r + Game.getCamera().x), 
            (int) (y - r + Game.getCamera().y),
            (int) r * 2, 
            (int) r * 2, 
            0, 360);
    }
}
