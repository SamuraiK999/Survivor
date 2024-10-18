package entities;

import core.Game;
import gear.Weapon;
import java.awt.Graphics;
import shapes.Circle;
import utility.Engine;

/**
 * The class from which all other entity classes derive of.
 */
public abstract class Entity {

    protected Circle body;
    protected Weapon weapon;

    protected float maxHealth = 100;
    protected float health = maxHealth;
    protected float armor = 10; // a percentage
    protected float speed = 6; // movement speed

    private static int defaultEntityRadius = 25;

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
        handleClipping();
    }

    /**
     * Draw the entity.
     */
    public void draw(Graphics g) {
        // g.setColor(color); - if it will be just a color and not an image
        body.drawRelative(g);
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
        body.x += x * speed / 2;
        body.y -= y * speed / 2;
    }

    /**
     * Experimental. Same as move(), but smooth.
     * Currently used only to handle clipping
     */
    public void moveLerp(float x, float y) {
        // calculating magnitude
        float magnitude = (float) Math.sqrt(x * x + y * y);

        // normalizing
        if (magnitude != 0) {
            x /= magnitude;
            y /= magnitude;
        }

        // moving the body
        body.x = Engine.lerp(body.x, body.x + x * 2, speed / 7);
        body.y = Engine.lerp(body.y, body.y - y * 2, speed / 7);
    }

    /**
     * Make sure that entities don't overlap/clip through each other.
     */
    public void handleClipping() {
        for (Entity e : Game.entities) {
            if (e != this) {
                if (Engine.collisionCirc(body, e.getBody())) {
                    moveLerp(-(e.getBody().x - body.x), (e.getBody().y - body.y));
                }
            }
        }
    }

    /**
     * Get a weapon in the beginning.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.weapon.owner = this;
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
        float dmg = Engine.clamp(damage * (100 - armor) / 100, 0, maxHealth);
        health -= dmg;
        if (health <= 0) {
            health = 0;
            die();
        }
    }

    /**
     * Death.
     */
    public abstract void die();

    public static int getDefaultRadius() {
        return defaultEntityRadius;
    }
}
