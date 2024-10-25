package gameplay.gear.particles;

import core.states.Game;
import gameplay.entities.Enemy;
import gameplay.entities.Player;
import gameplay.gear.weapons.Weapon;
import gameplay.map.Immovable;
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
    private void checkForCollision() { // checkstyle.
        if (weapon.owner instanceof Player) {
            for (Enemy enemy : Game.enemies) {
                if (Engine.collisionRect(hitbox, enemy.getHitbox()) && enemy.getHealth() > 0) {
                    enemy.takeDamage(weapon.damage);
                    Game.bulletsToRemove.add(this);
                }
            }
        } else {
            if (Engine.collisionRect(hitbox, player.getHitbox())) {
                player.takeDamage(weapon.damage);
                Game.bulletsToRemove.add(this);
            }
        }
        for (Immovable e : Game.map.getEnvironment()) {
            if (Engine.collisionRect(
                new Rect(hitbox.x, hitbox.y + 50, hitbox.width, hitbox.height), 
                e.getHitbox())) {
                Game.bulletsToRemove.add(this);
            }
        }
    }
}
