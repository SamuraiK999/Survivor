package gameplay.entities;

import core.states.Game;
import gameplay.entities.enums.State;
import java.awt.event.KeyEvent;
import utility.EH;
import utility.IM;
import utility.shapes.Rect;

/**
 * Player.
 */
public class Player extends Entity {

    private int timer;

    /**
     * Constructor.
     */
    public Player(Rect hitbox) {
        super(hitbox);

        speed = 8;

        idle = IM.playerIdle;
        running = IM.playerRunning;
        attacking = IM.playerShooting;
        dying = IM.playerDying;
    }

    @Override
    public void update() {
        super.update();
        handleInput();

        if (EH.getTick() - timer > 20 && getState() == State.DYING) {
            handlePostDeath();
        }
    }

    @Override
    public void die() {
        super.die();
        timer = EH.getTick();
    }

    private void handlePostDeath() {
        Game.setPlayerState(false);
        Game.initDeathMenu();
    }

    private void handleInput() {
        movementInput();
        shootingInput();
    }

    /**
     * Handle input for movement.
     */
    private void movementInput() {

        if (health <= 0 || currentState == State.ATTACKING) {
            return;
        }

        // horizontal input
        float x = (EH.isKeyPressed(KeyEvent.VK_A) && EH.isKeyPressed(KeyEvent.VK_D)) ? 0
                : (EH.isKeyPressed(KeyEvent.VK_A) ? -1 : (EH.isKeyPressed(KeyEvent.VK_D) ? 1 : 0));

        // vertical input
        float y = (EH.isKeyPressed(KeyEvent.VK_W) && EH.isKeyPressed(KeyEvent.VK_S)) ? 0
                : (EH.isKeyPressed(KeyEvent.VK_W) ? 1 : (EH.isKeyPressed(KeyEvent.VK_S) ? -1 : 0));

        move(x, y);
    }

    /**
     * Handle input for shooting.
     */
    private void shootingInput() {
        int leftInput = EH.isKeyPressed(37) ? 1 : 0;
        int rightInput = EH.isKeyPressed(39) ? 1 : 0;
        if (-leftInput + rightInput != 0) {
            useWeapon(hitbox.x + (-leftInput + rightInput));
        }
    }
}
