package gameplay.gear.particles;

import core.states.Game;
import gameplay.entities.Enemy;
import gameplay.entities.Entity;
import gameplay.entities.Player;
import gameplay.gear.weapons.Weapon;

import java.awt.Graphics;
import java.util.ArrayList;

import utility.EH;
import utility.Engine;
import utility.shapes.Rect;

/**
 * Slash.
 */
public class Slash {

    private Rect hitbox;
    private Weapon weapon;

    private ArrayList<Entity> hasHit = new ArrayList<>();

    private int timer = EH.getTick();

    private Player player;

    /**
     * Constructor.
     */
    public Slash(Rect hitbox, Weapon weapon) {
        this.hitbox = hitbox;
        this.weapon = weapon;

        player = Game.getPlayer();
    }

    /**
     * Bullet logic.
     */
    public void update() {
        checkForCollision();
        if (EH.getTick() - timer > 60) {
            Game.slashesToRemove.add(this);
        }
    }

    /**
     * Draw.
     */
    public void draw(Graphics g) {
        //hitbox.drawRelative(g);
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
            if (Engine.collisionRect(hitbox, player.getHitbox()) 
                && !hasHit.contains(Game.getPlayer())) {

                player.takeDamage(weapon.damage);
                hasHit.add(player);
            }
        }
    }
}
