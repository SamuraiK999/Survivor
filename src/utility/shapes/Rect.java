package utility.shapes;

import gameplay.Camera;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;

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
     * Constuctor.
     */
    public Rect(Point point, float width, float height) {
        this.x = point.x;
        this.y = point.y;
        this.width = width;
        this.height = height;
    }

    /**
     * Constuctor.
     */
    public Rect(Point2D.Float point, float width, float height) {
        this.x = point.x;
        this.y = point.y;
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
     * Returns coordinates udes to draw the rect by giving the coordinates of it's center.
     */
    public Point toDrawCentered() {
        return new Point((int) (x - width / 2), (int) (y - height / 2));
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

    /**
     * Returns the point at the center of the rect.
     */
    public Point getCentered() {
        return new Point((int) (x + width / 2), (int) (y + height / 2));
    }

    /**
     * Returns a copy of the rect with coordinates relative to the camera.
     */
    public Rect getRelative() {
        return new Rect(x + Camera.getCoordinates().x,
                y + Camera.getCoordinates().y, width, height);
    }
}
