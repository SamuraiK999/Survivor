package shapes;

import core.states.Game;
import java.awt.Graphics;

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
            (int) (x - r), 
            (int) (y - r),
            (int) r * 2, 
            (int) r * 2, 
            0, 360);
    }

    /**
     * Draw the circle relative to the camera.
     */
    public void drawRelative(Graphics g) {
        g.fillArc(
            (int) (getRelative().x - r), 
            (int) (getRelative().y - r),
            (int) r * 2, 
            (int) r * 2, 
            0, 360);
    }

    public Circle getRelative() {
        return new Circle(x + Game.getCamera().x, y + Game.getCamera().y, r);
    }
}
