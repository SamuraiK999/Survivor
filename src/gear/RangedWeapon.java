package gear;

import core.Game;
import entities.State;
import shapes.Rect;

/**
 * Ranged weapon class.
 */
public class RangedWeapon extends Weapon {

    public RangedWeapon(float damage, float attackSpeed) {
        super(damage, attackSpeed);
    }

    @Override
    public void use(float x, float y) {
        if (cooldown > 0) {
            return;
        }

        if (owner.currentState != State.ATTACKING) {
            owner.switchState(State.ATTACKING);
        }

        Game.bullets.add(
                new Bullet(
                    new Rect(owner.getHitbox().x + owner.getHitbox().width / 2, 
                    owner.getHitbox().y + owner.getHitbox().height / 2, 
                    10, 10), 
                    this, x, y));

        reload();
    }
}
