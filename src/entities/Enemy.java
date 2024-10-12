package entities;

import main.Game;
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

    }

    @Override
    public void update() {
        super.update();
        movementAI();
        shooting();
    }

    @Override
    public void die() {
        Game.enemies.remove(this);
    }

    private void movementAI() {

    }

    /**
     * Always shoot.
     */
    private void shooting() {
        this.useWeapon(Game.player.body.x, Game.player.body.y);
    }
}
