package gameplay.gear.weapons;

import core.states.Game;
import gameplay.entities.enums.State;
import gameplay.gear.Bullet;

import java.awt.Point;

import utility.Engine;
import utility.shapes.Rect;

/**
 * Ranged weapon class.
 */
public class RangedWeapon extends Weapon {

    Point muzzleOffset = new Point(0, 0);

    /**
     * Constructor.
     */
    public RangedWeapon(float damage, float attackSpeed, Point muzzleOffset) {
        super(damage, attackSpeed);
        this.muzzleOffset = muzzleOffset;
    }

    @Override
    public void use(float x) {
        if (cooldown > 0) {
            return;
        }

        x = Engine.clamp(x, -1, 1);

        if (owner.currentState != State.ATTACKING) {
            owner.setState(State.ATTACKING);
        }

        Game.bullets.add(
                new Bullet(
                    new Rect(owner.getHitbox().x + owner.getHitbox().width / 2 + muzzleOffset.x, 
                    owner.getHitbox().y + owner.getHitbox().height / 2 + muzzleOffset.y, 
                    10, 3), 
                    this, 
                    x));

        reload();
    }
}
