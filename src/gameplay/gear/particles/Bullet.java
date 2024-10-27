package gameplay.gear.particles;

import core.states.Game;
import gameplay.entities.Enemy;
import gameplay.entities.Entity;
import gameplay.entities.Player;
import gameplay.gear.weapons.Weapon;
import gameplay.map.Immovable;
import gameplay.map.Map;
import java.awt.*;
import utility.Engine;
import utility.shapes.Rect;

/**
 * Bullet.
 */
public class Bullet {

    private Rect hitbox;
    private Weapon weapon;

    private float dirX;
    private float speed = 20;

    private Player player;

    /**
     * Constructor.
     */
    public Bullet(Rect hitbox, Weapon weapon, float dirX) {
        this.hitbox = hitbox;
        this.weapon = weapon;
        this.dirX = dirX;

        player = Game.getPlayer();
    }

    /**
     * Bullet logic.
     */
    public void update() {
        move();
        checkForCollision();
    }

    /**
     * Drawing the bullet.
     */
    public void draw(Graphics g) {
        Graphics g1 = g.create();
        g1.setColor(Color.YELLOW);
        hitbox.drawRelative(g1);
    }

    /**
     * Moving the bullet.
     */
    private void move() {
        hitbox.x += dirX * speed;
    }

    /**
     * Checking for collision with an apropriate entity.
     */
    private void checkForCollision() {
        // For collision with entities
        if (weapon.owner instanceof Player) {
            for (Enemy enemy : Game.enemies) {
                entityCollisionCheck(enemy);
            }
        } else { // If the owner is an enemy
            entityCollisionCheck(player);
        }

        // For collision with walls
        // Gives me an error when I use Map.getEnvironment()?
        for (Immovable object : Map.getEnvironment()) {
            immovablesCollisionCheck(object);
        }
    }

    private void entityCollisionCheck(Entity object) {
        if (Engine.collisionRect(hitbox, object.getHitbox()) && object.getHealth() > 0) {
            object.takeDamage(weapon.damage);
            Game.bulletsToRemove.add(this);
        }
    }

    private void immovablesCollisionCheck(Immovable object) {
        // Offsetting the bullet's hitbox on the Y axis to simulate depth
        if (Engine.collisionRect(
                new Rect(hitbox.x, hitbox.y + 50, hitbox.width, hitbox.height),
                object.getHitbox())) {
            Game.bulletsToRemove.add(this);
        }
    }
}
