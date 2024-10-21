package entities;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import shapes.Rect;
import utility.EH;
import utility.IM;

/**
 * Player.
 */
public class Player extends Entity {

    protected ArrayList<BufferedImage> shooting = new ArrayList<>();

    /**
     * Constructor.
     */
    public Player(Rect hitbox) {
        super(hitbox);
        idle = IM.playerIdle;
        running = IM.playerRunning;
        attacking = IM.playerShooting;
        dying = IM.playerDying;
    }

    @Override
    public void update() {
        super.update();
        handleInput();
    }

    @Override
    public void die() {
        super.die();
        System.out.println("rip bozo - Player.java die()");
    }

    private void handleInput() {
        movementInput();
        shootingInput();
    }

    /**
     * Handle input for movement.
     */
    private void movementInput() { // can we remove that from the checkstyle pretty please xd

        if (health <= 0) {
            return;
        }

        float x = 0; // horizontal dir
        float y = 0; // vertical dir

        if (EH.isKeyPressed(KeyEvent.VK_W) && EH.isKeyPressed(KeyEvent.VK_S)) {
            y = 0;
        } else {
            if (EH.isKeyPressed(KeyEvent.VK_W)) {
                y = 1;
            }
            if (EH.isKeyPressed(KeyEvent.VK_S)) {
                y = -1;
            }
        }

        if (EH.isKeyPressed(KeyEvent.VK_A) && EH.isKeyPressed(KeyEvent.VK_D)) {
            x = 0;
        } else {
            if (EH.isKeyPressed(KeyEvent.VK_A)) {
                x = -1;
            }
            if (EH.isKeyPressed(KeyEvent.VK_D)) {
                x = 1;
            }
        }

        move(x, y);
    }

    /**
     * Handle input for shooting.
     */
    private void shootingInput() {
        if (EH.isMousePressed()) {
            useWeapon(EH.getMouseCoordinatesRelative().x + hitbox.x,
                    EH.getMouseCoordinatesRelative().y + hitbox.y);
        }
    }
}
