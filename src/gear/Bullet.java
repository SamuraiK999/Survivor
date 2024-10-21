package gear;

import core.Game;
import entities.Enemy;
import entities.Player;
import java.awt.Graphics;
import shapes.Rect;
import utility.Engine;

/**
 * Bullet.
 */
public class Bullet {

    private Rect hitbox;
    private Weapon weapon;
    
    private float dirX;
    private float dirY;
    private float speed = 10;

    /**
     * Constructor.
     */
    public Bullet(Rect hitbox, Weapon weapon, float dirX, float dirY) {
        this.hitbox = hitbox;
        this.weapon = weapon;
        this.dirX = dirX;
        this.dirY = dirY;
    }

    /**
     * Bullet logic.
     */
    public void update() {
        move();
        checkForCollision();
    }

    public void draw(Graphics g) {
        hitbox.drawRelative(g);
    }

    private void move() {
        // calculating magnitude
        float magnitude = (float) Math.sqrt(dirX * dirX + dirY * dirY);

        // normalizing
        if (magnitude != 0) {
            dirX /= magnitude;
            dirY /= magnitude;
        }

        // moving the body
        hitbox.x += dirX * speed;
        hitbox.y -= dirY * speed;
    }

    private void checkForCollision() {
        if (weapon.owner instanceof Player) {
            for (Enemy enemy : Game.enemies) {
                if (Engine.collisionRect(hitbox, enemy.getHitbox())) {
                    enemy.takeDamage(weapon.damage);
                    Game.bulletsToRemove.add(this);
                }
            }
        } else {
            if (Engine.collisionRect(hitbox, Game.player.getHitbox())) {
                Game.player.takeDamage(weapon.damage);
                Game.bulletsToRemove.add(this);
            }
        }
    }
}
