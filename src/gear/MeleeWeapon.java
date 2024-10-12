package gear;

import entities.Entity;
import main.Game;
import shapes.Circle;

/**
 * Melee weapon class.
 */
public class MeleeWeapon extends Weapon {

    public MeleeWeapon(Entity owner, float attackSpeed) {
        super(owner, attackSpeed);
    }

    @Override
    public void use(float x, float y) {
        
        if (cooldown > 0) {
            return;
        }

        Game.bullets.add(
                new Bullet(new Circle(owner.getBody().x, owner.getBody().y, 5), this, x, y));

        reload();
    }
}
