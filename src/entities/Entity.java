package entities;

import gear.Weapon;
import java.awt.Graphics;
import shapes.Circle;

/**
 * The class from which all other entity classes derive of.
 */
public abstract class Entity {

    public String type;

    protected Circle body;
    protected Weapon weapon;

    protected float maxHealth = 100;
    protected float health = maxHealth;
    protected float armor = 10; // a percentage
    protected float speed = 3; // movement speed

    // texture/color

    /**
     * Constructor.
     */
    public Entity(Circle body) {
        this.body = body;
    }

    /**
     * Getter for the body.
     */
    public Circle getBody() {
        return body;
    }

    /**
     * Entity game logic.
     */
    public void update() {
        weapon.update();
    }

    /**
     * Draw the entity.
     */
    public void draw(Graphics g) {
        // g.setColor(color); - if it will be just a color and not an image
        body.draw(g);
    }

    /**
     * Move the entity in one of 8 directions; x & y should be between -1 & 1.
     */
    public void move(float x, float y) {
        // calculating magnitude
        float magnitude = (float) Math.sqrt(x * x + y * y);

        // normalizing
        if (magnitude != 0) {
            x /= magnitude;
            y /= magnitude;
        }

        // moving the body
        body.x += x * speed;
        body.y -= y * speed;
    }

    /**
     * Get a weapon in the beginning.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    /**
     * Use the weapon in a direction.
     */
    public void useWeapon(float x, float y) {
        weapon.use(x - body.x, body.y - y);
    }

    /**
     * Deals damage to the entity.
     */
    public void takeDamage(float damage) {
        health -= damage - armor / 100;
        if (health <= 0) {
            health = 0;
            die();
        }
    }

    /**
     * Death.
     */
    public abstract void die();
}
