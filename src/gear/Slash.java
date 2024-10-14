package gear;

import entities.Enemy;
import entities.Player;
import java.awt.Graphics;
import main.Engine;
import main.Game;
import shapes.Circle;

/**
 * Slash.
 */
public class Slash {

    private Circle body;
    private Weapon weapon;

    private int startAngle;
    private int arcAngle;

    private int timeExisting = 0;
    private int currentAngle = 0;
    private int currentArcAngle;

    private float decaySpeed = 10;

    /**
     * Constructor.
     */
    public Slash(Circle body, Weapon weapon, float dirX, float dirY) {
        this.body = body;
        this.weapon = weapon;

        // Calculate the angle between the owner and the target in degrees
        int angleThisToTarget = (int) Math.toDegrees(Math.atan2(dirY, dirX));
        
        if (angleThisToTarget < 0) {
            angleThisToTarget += 360;
        }
    
        arcAngle = 134;
        startAngle = angleThisToTarget - arcAngle / 2;
        currentArcAngle = 0;

    }

    /**
     * Bullet logic.
     */
    public void update() {
        checkForCollision();
        doAnimation();
    }

    /**
     * Draw.
     */
    public void draw(Graphics g) {
        g.fillArc(
            (int) (body.x - body.r + Game.getCamera().x), 
            (int) (body.y - body.r + Game.getCamera().y),
            (int) body.r * 2, 
            (int) body.r * 2, 
            startAngle + currentAngle, currentArcAngle - currentAngle);
    }

    private void checkForCollision() {
        if (weapon.owner instanceof Player) {
            for (Enemy enemy : Game.enemies) {
                if (Engine.collisionCirc(body, enemy.getBody())) {
                    enemy.takeDamage(weapon.damage);
                }
            }
        } else {
            if (Engine.collisionCirc(body, Game.player.getBody())) {
                Game.player.takeDamage(weapon.damage);
            }
        }
    }

    private void doAnimation() {
        timeExisting++;

        if (timeExisting > 20) {
            currentAngle += decaySpeed;
        }

        if (currentAngle >= arcAngle) {
            Game.slashesToRemove.add(this);
        }

        if (currentArcAngle < arcAngle) {
            currentArcAngle += decaySpeed * 1.5;
        }
    }
}
