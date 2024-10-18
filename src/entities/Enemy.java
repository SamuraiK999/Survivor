package entities;

import core.Game;
import gear.MeleeWeapon;
import shapes.Circle;

/**
 * Enemy.
 */
public class Enemy extends Entity {

    /**
     * Contructor.
     */
    public Enemy(Circle body) {
        super(body);
        speed = 1;
    }

    @Override
    public void update() {
        super.update();
        movementAI();
        shooting();
    }

    @Override
    public void die() {
        System.out.println("-1");
        Game.enemiesToRemove.add(this);
    }

    private void movementAI() {
        if (weapon instanceof MeleeWeapon) {
            move(Game.player.getBody().x - body.x, -(Game.player.getBody().y - body.y));
        }
    }

    /**
     * Always shoot.
     */
    private void shooting() {
        this.useWeapon(Game.player.getBody().x, Game.player.getBody().y);
    }
}
