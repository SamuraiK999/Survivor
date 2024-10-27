package gameplay.entities;

import core.states.Game;
import gameplay.entities.enums.State;
import gameplay.map.Immovable;
import gameplay.map.Map;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import utility.EH;
import utility.Engine;
import utility.IM;
import utility.shapes.Line;
import utility.shapes.Rect;

/**
 * Enemy.
 */
public class Enemy extends Entity {

    private int type;
    private boolean dormant = true;

    private Player player;

    private Point2D.Float nextDestination;

    /**
     * Contructor.
     */
    public Enemy(Rect hitbox) {
        super(hitbox);

        speed = 5;

        offsetY = 18;

        type = new Random().nextInt(3);
        idle = IM.enemyIdle.get(type);
        running = IM.enemyRunning.get(type);
        attacking = IM.enemyAttacking.get(type);
        dying = IM.enemyDying.get(type);

        player = Game.getPlayer();
    }

    @Override
    public void update() {
        super.update();
        movementAI();
        shooting();
        dying();
    }

    @Override
    public void die() {
        super.die();
    }

    /**
     * Actions done after death.
     */
    public void dying() {
        if (isFinished && getState() == State.DYING) {
            Game.enemiesToRemove.add(this);
            Game.score = Game.score + 500;
        }
    }

    private void movementAI() {
        if (Engine.distance(player.getHitbox(), hitbox) < 500) {
            dormant = false;
        }

        if (player.getState() == State.ATTACKING
                && Engine.distance(player.getHitbox(), hitbox) < 1000) {
            dormant = false;
        }

        if (!dormant) {
            moveTowardsPlayer();
            /*
             * move(player.getHitbox().x - hitbox.x,
             * -(player.getHitbox().y - hitbox.y));
             */
        }
    }

    /**
     * Moves the enemy towards the player.
     */
    private void moveTowardsPlayer() {

        boolean isPathStraightforward = true;
        // Line from this to the player.
        Line lineToPlayer = new Line(
                new Point(
                        (int) hitboxMovement.getCentered().x,
                        (int) hitboxMovement.getCentered().y),
                new Point(
                        (int) Game.getPlayer().hitboxMovement.getCentered().x,
                        (int) Game.getPlayer().hitboxMovement.getCentered().y));

        // Determine whether the path to the player is straightforward.
        for (Immovable obj : Map.getEnvironment()) {
            if (Engine.collisionLineRect(lineToPlayer, obj.getHitbox())) {
                isPathStraightforward = false;
            }
        }

        if (isPathStraightforward) {
            move(player.getHitbox().x - hitbox.x,
                    -(player.getHitbox().y - hitbox.y));
        } else { // Move towards the player through the path found using Dijkstra's algorithm

            // The closest vertex to this.
            int closestV = Map.getGraph().getClosestVertexTo(hitbox);
            // The closest vertex to the player.
            int closestVPlayer = Map.getGraph().getClosestVertexTo(Game.getPlayer().hitbox);
            // The shortest path to the player.
            ArrayList<Integer> path = Map.getGraph().shortestPath(closestV, closestVPlayer);

            

            if (EH.getTick() % 2 == 0) {
                if (path.size() > 1) {
                    path.remove(path.size() - 1);
                }
                nextDestination = Map.getGraph().getVertex(path.get(path.size() - 1));
            }
                    
            move(nextDestination);
        }
    }

    /**
     * Attack when in range.
     */
    private void shooting() {
        if (Engine.distance(player.getHitbox(), hitbox) < 100) {
            this.useWeapon(player.getHitbox().x);
        }
    }
}
