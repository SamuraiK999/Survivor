package gear;

import main.Game;
import shapes.Circle;

/**
 * Melee weapon class.
 */
public class MeleeWeapon extends Weapon {

    public MeleeWeapon(float attackSpeed) {
        super(attackSpeed);
    }

    @Override
    public void use(float x, float y) {
        if (cooldown > 0) {
            return;
        }
        
        Game.slashes.add(
                new Slash(new Circle(owner.getBody().x, owner.getBody().y, 50), this, x, y));

        reload();
    }
}
