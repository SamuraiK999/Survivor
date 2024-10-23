package gameplay.entities;

import core.states.Game;
import java.util.Random;
import shapes.Rect;
import utility.Engine;
import utility.IM;

/**
 * Enemy.
 */
public class Enemy extends Entity {

    private int type;
    private boolean dormant = true;

    private Player player;

    /**
     * Contructor.
     */
    public Enemy(Rect hitbox) {
        super(hitbox);

        speed = 5;

        offsetY = 18;

        type = new Random().nextInt(3);
        idle = IM.enemyIdle.get(type);
        running = IM.enemyRunning.get(type);
        attacking = IM.enemyAttacking.get(type);
        dying = IM.enemyDying.get(type);

        player = Game.getPlayer();
    }

    @Override
    public void update() {
        super.update();
        movementAI();
        shooting();
        if (isFinished && health <= 0) {
            Game.enemiesToRemove.add(this);
        }
    }

    @Override
    public void die() {
        super.die();
    }

    private void movementAI() {
        if (Engine.distance(player.getHitbox(), hitbox) < 500) {
            dormant = false;
        }
            
        if (player.getState() == State.ATTACKING
            && Engine.distance(player.getHitbox(), hitbox) < 1000) {
            dormant = false;
        }

        if (!dormant) {
            move(player.getHitbox().x - hitbox.x,
                -(player.getHitbox().y - hitbox.y));
        }
    }

    /**
     * Attack when in range.
     */
    private void shooting() {
        if (Engine.distance(player.getHitbox(), hitbox) < 100) {
            this.useWeapon(player.getHitbox().x);
        }
    }
}
