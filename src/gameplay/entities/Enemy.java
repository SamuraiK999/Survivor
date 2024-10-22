package gameplay.entities;

import core.states.Game;
import gameplay.gear.MeleeWeapon;
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

    /**
     * Contructor.
     */
    public Enemy(Rect hitbox) {
        super(hitbox);

        speed = 10;

        offsetY = 18;

        type = new Random().nextInt(3);
        idle = IM.enemyIdle.get(type);
        running = IM.enemyRunning.get(type);
        attacking = IM.enemyAttacking.get(type);
        dying = IM.enemyDying.get(type);
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
        if (weapon instanceof MeleeWeapon) {
            if (Engine.distance(Game.getPlayer().getHitbox(), hitbox) < 500) {
                dormant = false;
            }
            
            if (Game.getPlayer().getState() == State.ATTACKING
                && Engine.distance(Game.getPlayer().getHitbox(), hitbox) < 1000) {
                dormant = false;
            }
        }

        if (!dormant) {
            move(Game.getPlayer().getHitbox().x - hitbox.x,
                -(Game.getPlayer().getHitbox().y - hitbox.y));
        }
    }

    /**
     * Attack when in range.
     */
    private void shooting() {
        if (Engine.distance(Game.getPlayer().getHitbox(), hitbox) < 100) {
            this.useWeapon(Game.getPlayer().getHitbox().x);
        }
    }
}
