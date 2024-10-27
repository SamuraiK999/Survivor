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

    /**
     * Make the camera follow a target of type rect.
     */
    public static void follow(Rect target) {
        float offsetX = 12;
        float offsetY = -1;
        if (Main.scaleX > 1) {
            offsetX = 3;
            offsetY = 1;
        }
        float newX = Engine.clamp(
                Engine.lerp(x,
                        -(target.x + target.width / 2) + Main.FRAME_WIDTH / 2,
                        0.1f),
                Entity.getDefaultWidth() * offsetX / (float) Main.scaleX,
                IM.backgroundMap.getWidth() / 2 * IM.UI_SCALE);

        System.out.println(Main.scaleX);

        float newY = Engine.clamp(
                Engine.lerp(y,
                        -(target.y + target.height / 2) + Main.FRAME_HEIGTH / 2,
                        0.1f),
                -(IM.backgroundMap.getHeight() / 2
                        + Entity.getDefaultHeight() * (float) Main.scaleY * offsetY),
                IM.backgroundMap.getHeight() / 2 * IM.UI_SCALE);

        x = newX;
        y = newY;
    }

    public static Point2D.Float getCoordinates() {
        return new Point2D.Float(x, y);
    }
}
