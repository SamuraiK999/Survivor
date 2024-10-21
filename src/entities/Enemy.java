package entities;

import core.Game;
import gear.MeleeWeapon;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import shapes.Rect;
import utility.IM;

/**
 * Enemy.
 */
public class Enemy extends Entity {

    protected ArrayList<BufferedImage> walking = new ArrayList<>();

    /**
     * Contructor.
     */
    public Enemy(Rect hitbox) {
        super(hitbox);
        speed = 1;
        idle = IM.playerIdle;
        running = IM.playerRunning;
        attacking = IM.playerShooting;
        dying = IM.playerDying;
    }

    @Override
    public void update() {
        super.update();
        movementAI();
        shooting();
    }

    @Override
    public void die() {
        super.die();
        if (isFinished) {
            Game.enemiesToRemove.add(this);
        }
    }

    private void movementAI() {
        if (weapon instanceof MeleeWeapon) {
            move(Game.player.getHitbox().x - hitbox.x, -(Game.player.getHitbox().y - hitbox.y));
        }
    }

    /**
     * Always shoot.
     */
    private void shooting() {
        this.useWeapon(Game.player.getHitbox().x, Game.player.getHitbox().y);
    }
}
