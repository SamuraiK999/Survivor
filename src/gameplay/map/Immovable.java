package gameplay.map;

import java.awt.Graphics;
import shapes.Rect;

/**
 * Immovable objects such as walls, barels, crates, ect.
 */
public class Immovable {

    private Rect hitbox;
    
    public Immovable(Rect dimensions) {
        hitbox = dimensions;
    }

    public void update() {

    }

    /**
     * Draw the hitbox; won't be used in release.
     */
    public void draw(Graphics g) {
        hitbox.drawRelative(g);
    }

    public Rect getHitbox() {
        return hitbox;
    }
}
