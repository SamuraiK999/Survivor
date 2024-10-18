package entities;

import java.awt.event.KeyEvent;
import shapes.Circle;
import utility.EH;

/**
 * Player.
 */
public class Player extends Entity {

    /**
     * Constructor.
     */
    public Player(Circle body) {
        super(body);
    }

    @Override
    public void update() {
        super.update();
        handleInput();
    }

    @Override
    public void die() {
        System.out.println("rip bozo");
    }

    private void handleInput() {
        movementInput();
        shootingInput();
    }

    /**
     * Handle input for movement.
     */
    private void movementInput() { // can we remove that from the checkstyle pretty please xd
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
            useWeapon(EH.getMouseCoordinatesRelative().x + body.x,
                    EH.getMouseCoordinatesRelative().y + body.y);
        }
    }
}
