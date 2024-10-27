package utility;

import java.awt.Point;
import java.awt.geom.Point2D;
import utility.shapes.Circle;
import utility.shapes.Line;
import utility.shapes.Rect;

/**
 * Created for the purpose of this game; contains useful custom functions.
 */
public class Engine {

    private Engine() {
    }

    /**
     * Linear interpolation.
     */
    public static float lerp(float startingValue, float targetValue, float t) {
        return startingValue + (targetValue - startingValue) * t;
    }

    /**
     * "Clamps" a value between a minimum and a maximum.
     */
    public static float clamp(float value, float minimalValue, float maximalValue) {
        if (value < minimalValue) {
            return minimalValue;
        }
        if (value > maximalValue) {
            return maximalValue;
        }
        return value;
    }

    /**
     * Checks if 2 line segments intersect.
     */
    public static boolean collisionLine(Line line1, Line line2) {
        float x1 = line1.p1.x;
        float x2 = line1.p2.x;
        float x3 = line2.p1.x;
        float x4 = line2.p2.x;

        float y1 = line1.p1.y;
        float y2 = line1.p2.y;
        float y3 = line2.p1.y;
        float y4 = line2.p2.y;

        // Check whether the denominator is 0. If so, the lines are paralel.
        if (((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1)) == 0) {
            return false;
        }

        // Calculate the distance to intersection point.
        float uA = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3))
                / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));

        float uB = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3))
                / ((y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1));

        // If uA and uB are between 0-1, lines are colliding.
        if (uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1) {
            return true;
        }
        return false;
    }

    /**
     * Checks wheree a line segment intersects with a rectangle.
     */
    public static boolean collisionLineRect(Line line, Rect rect) {

        // Check if the line segments intersects any of the rectangle's sides
        boolean left = collisionLine(
                line,
                new Line(
                        new Point((int) rect.x, (int) rect.y),
                        new Point((int) rect.x, (int) (rect.y + rect.height))));
        boolean right = collisionLine(
                line,
                new Line(
                        new Point((int) (rect.x + rect.width), (int) rect.y),
                        new Point((int) (rect.x + rect.width), (int) (rect.y + rect.height))));
        boolean top = collisionLine(
                line,
                new Line(
                        new Point((int) rect.x, (int) rect.y),
                        new Point((int) (rect.x + rect.width), (int) rect.y)));
        boolean bottom = collisionLine(
                line,
                new Line(
                        new Point((int) rect.x, (int) (rect.y + rect.height)),
                        new Point((int) (rect.x + rect.width), (int) (rect.y + rect.height))));

        // if ANY of the above are true, the line
        // has hit the rectangle
        if (left || right || top || bottom) {
            return true;
        }
        return false;
    }

    /**
     * Checks if 2 rectangles collide.
     */
    public static boolean collisionRect(Rect obj1, Rect obj2) {
        if (obj1.x < obj2.x + obj2.width
                && obj1.x + obj1.width > obj2.x
                && obj1.y < obj2.y + obj2.height
                && obj1.y + obj1.height > obj2.y) {
            return true;
        }
        return false;
    }

    /**
     * Checks if 2 circles collide.
     */
    public static boolean collisionCirc(Circle obj1, Circle obj2) {
        if (distance(obj1, obj2) <= obj1.r + obj2.r) {
            return true;
        }
        return false;
    }

    /**
     * Distance between 2 points.
     */
    public static float distance(Point obj1, Point obj2) {
        float deltaX = obj1.x - obj2.x;
        float deltaY = obj1.y - obj2.y;

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Distance between 2 points (float).
     */
    public static float distance(Point2D.Float obj1, Point2D.Float obj2) {
        float deltaX = obj1.x - obj2.x;
        float deltaY = obj1.y - obj2.y;

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Distance between 2 circles' centers.
     */
    public static float distance(Circle obj1, Circle obj2) {
        float deltaX = obj1.x - obj2.x;
        float deltaY = obj1.y - obj2.y;

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }

    /**
     * Distance between 2 rectangles' centers.
     */
    public static float distance(Rect obj1, Rect obj2) {
        float deltaX = (obj1.x + obj1.width / 2) - (obj2.x + obj2.width / 2);
        float deltaY = (obj1.y + obj1.height / 2) - (obj2.y + obj2.height / 2);

        return (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
    }
}
