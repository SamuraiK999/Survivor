package gear;

import entities.Entity;

/**
 * Weapon.
 */
public abstract class Weapon {

    public Entity owner;
    public float damage;

    protected float cooldown;
    protected float attackSpeed;

    /**
     * Constructor.
     */
    public Weapon(Entity owner, float attackSpeed) {
        this.owner = owner;
        this.attackSpeed = attackSpeed;
    }

    /**
     * Update method.
     */
    public void update() {
        cooldown--;
    }

    /**
     * Reload weapon.
     */
    protected void reload() {
        cooldown = attackSpeed;
    }

    public abstract void use(float x, float y);

}
