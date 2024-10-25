package gameplay.gear.weapons;

import core.states.Game;
import gameplay.entities.Entity;
import gameplay.entities.enums.State;
import gameplay.gear.Slash;
import utility.shapes.Rect;

/**
 * Melee weapon class.
 */
public class MeleeWeapon extends Weapon {

    public MeleeWeapon(float damage, float attackSpeed) {
        super(damage, attackSpeed);
    }

    @Override
    public void use(float x) {
        if (cooldown > 0) {
            return;
        }

        if (owner.currentState != State.ATTACKING) {
            owner.setState(State.ATTACKING);
        }
        
        Game.slashes.add(
            new Slash(
                new Rect(owner.getHitbox().x + owner.getDirection() * owner.getHitbox().width, 
                        owner.getHitbox().y, 
                        Entity.getDefaultWidth(), Entity.getDefaultHeight()), 
                this));

        reload();
    }
}
