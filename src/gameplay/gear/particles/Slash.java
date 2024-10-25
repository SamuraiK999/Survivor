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

    // Used to ensure that 1 slash can hit an entity only unce
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
    private void checkForCollision() {
        if (weapon.owner instanceof Player) {
            for (Enemy enemy : Game.enemies) {
                entityCollisionCheck(enemy);
            }
        } else { // if the owner is an enemy
            entityCollisionCheck(player);
        }
    }

    private void entityCollisionCheck(Entity object) {
        if (Engine.collisionRect(hitbox, object.getHitbox())
            && object.getHealth() > 0
            && !hasHit.contains(Game.getPlayer())) {

            object.takeDamage(weapon.damage);
            hasHit.add(object);
        }
    }
}
