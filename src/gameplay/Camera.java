package gameplay;

import core.Main;
import gameplay.entities.Entity;
import java.awt.geom.Point2D;
import utility.Engine;
import utility.IM;
import utility.shapes.Rect;

/**
 * The camera.
 */
public class Camera {
    
    private static float x = 0;
    private static float y = 0;

    private Camera() {

    }


    /**
     * Make the camera follow a target of type rect.
     */
    public static void follow(Rect target) {
        float newX = Engine.clamp(
                Engine.lerp(x,
                    -(target.x + target.width / 2) + Main.FRAME_WIDTH / (float) Main.scaleX / 2,
                    0.1f),
                IM.backgroundMap.getWidth() / 2,
                IM.backgroundMap.getWidth() / 2 * 3);

        float newY = Engine.clamp(
                Engine.lerp(y,
                    -(target.y + target.height / 2) + Main.FRAME_HEIGTH / (float) Main.scaleY / 2,
                    0.1f),
                -IM.backgroundMap.getHeight() / 2 + Entity.getDefaultHeight(),
                IM.backgroundMap.getHeight() / 2 * 3);

        x = newX;
        y = newY;
    }

    public static Point2D.Float getCoordinates() {
        return new Point2D.Float(x, y);
    }
}
