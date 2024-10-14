package gear;

import entities.Enemy;
import entities.Player;
import java.awt.Graphics;
import main.Engine;
import main.Game;
import shapes.Circle;

/**
 * Bullet.
 */
public class Bullet {

    private Circle body;
    private Weapon weapon;
    
    private float dirX;
    private float dirY;
    private float speed = 10;

    /**
     * Constructor.
     */
    public Bullet(Circle body, Weapon weapon, float dirX, float dirY) {
        this.body = body;
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
        body.drawRelative(g);
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
        body.x += dirX * speed;
        body.y -= dirY * speed;
    }

    private void checkForCollision() {
        if (weapon.owner instanceof Player) {
            for (Enemy enemy : Game.enemies) {
                if (Engine.collisionCirc(body, enemy.getBody())) {
                    enemy.takeDamage(weapon.damage);
                    Game.bulletsToRemove.add(this);
                }
            }
        } else {
            if (Engine.collisionCirc(body, Game.player.getBody())) {
                Game.player.takeDamage(weapon.damage);
                Game.bulletsToRemove.add(this);
            }
        }
    }
}
