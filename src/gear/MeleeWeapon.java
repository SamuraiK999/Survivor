package gear;

import core.Game;
import entities.Entity;
import entities.State;
import shapes.Rect;

/**
 * Melee weapon class.
 */
public class MeleeWeapon extends Weapon {

    public MeleeWeapon(float damage, float attackSpeed) {
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
        
        Game.slashes.add(
            new Slash(
                new Rect(owner.getHitbox().x + owner.getDirection() * owner.getHitbox().width, 
                        owner.getHitbox().y, 
                        Entity.getDefaultWidth(), Entity.getDefaultHeight()), 
                this, x, y));

        reload();
    }
}
