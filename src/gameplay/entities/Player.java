package gameplay.entities;

import java.awt.event.KeyEvent;

import core.GameState;
import core.states.DeathMenu;
import core.states.Game;
import shapes.Rect;
import utility.EH;
import utility.IM;

/**
 * Player.
 */
public class Player extends Entity {

    private int timer;
    
    // to replace attacking, attacking becomes a melee attack
    //protected ArrayList<BufferedImage> shooting = new ArrayList<>(); 

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
        if(EH.getTick() - timer > 20 && getState() == State.DYING){
            Game.createDeathMenu();
        }
    }

    @Override
    public void die() {
        super.die();
        timer = EH.getTick();
    }

    private void handleInput() {
        movementInput();
        shootingInput();
    }

    /**
     * Handle input for movement.
     */
    private void movementInput() { // can we remove that from the checkstyle pretty please xd

        if (health <= 0 || currentState == State.ATTACKING) {
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
            useWeapon(EH.getMouseCoordinatesRelative().x + hitbox.x);
        }
    }
}
