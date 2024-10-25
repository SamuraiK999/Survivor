package utility;

import utility.shapes.Circle;
import utility.shapes.Rect;

/**
 * Created for the purpose of this game; contains useful custom functions.
 */
public class Engine {

    private Engine() { }

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
     * Distance between 2 rectangles' centers.
     */
    public static float distance(Rect obj1, Rect obj2) {
        float deltaX = (obj1.x + obj1.width / 2) - (obj2.x + obj2.width / 2);
        float deltaY = (obj1.y + obj1.height / 2) - (obj2.y + obj2.height / 2);

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
}
