package gear;

import core.Game;
import entities.Enemy;
import entities.Entity;
import entities.Player;
import java.awt.Graphics;
import java.util.ArrayList;
import shapes.Rect;
import utility.EH;
import utility.Engine;

/**
 * Slash.
 */
public class Slash {

    private Rect hitbox;
    private Weapon weapon;

    private ArrayList<Entity> hasHit = new ArrayList<>();

    private int timer = EH.getTick();

    /**
     * Constructor.
     */
    public Slash(Rect hitbox, Weapon weapon, float dirX, float dirY) {
        this.hitbox = hitbox;
        this.weapon = weapon;

    }

    /**
     * Bullet logic.
     */
    public void update() {
        checkForCollision();
        if (EH.getTick() - timer > 100) {
            Game.slashesToRemove.add(this);
        }
    }

    /**
     * Draw.
     */
    public void draw(Graphics g) {
        hitbox.drawRelative(g);
    }

    /**
     * Check for collision with entities.
     */
    private void checkForCollision() { // checkstyle >:(
        if (weapon.owner instanceof Player) {
            for (Enemy enemy : Game.enemies) {
                if (Engine.collisionRect(hitbox, enemy.getHitbox())
                    && !hasHit.contains(enemy)) {
                    enemy.takeDamage(weapon.damage);
                    hasHit.add(enemy);
                }
            }
        } else {
            if (Engine.collisionRect(hitbox, Game.player.getHitbox()) 
                && !hasHit.contains(Game.player)) {

                Game.player.takeDamage(weapon.damage);
                hasHit.add(Game.player);
            }
        }
    }
}
