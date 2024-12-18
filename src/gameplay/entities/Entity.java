package gameplay.entities;

import core.states.Game;
import gameplay.entities.enums.Direction;
import gameplay.entities.enums.State;
import gameplay.gear.weapons.Weapon;
import gameplay.map.Immovable;
import gameplay.map.Map;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utility.EH;
import utility.Engine;
import utility.IM;
import utility.shapes.Rect;

/**
 * The class from which all other entity classes derive from.
 */
public abstract class Entity {

    protected Rect hitbox;
    protected Rect hitboxMovement;
    protected Weapon weapon;

    protected float maxHealth = 100;
    protected float health = maxHealth;
    protected float armor = 0; // a percentage
    protected float speed = 1; // movement speed

    public State currentState = State.IDLE;
    protected boolean isAnimationLocked = false;
    private Point direction = new Point(1, 1);
    protected int offsetY = 4;

    protected ArrayList<BufferedImage> idle = new ArrayList<>();
    protected ArrayList<BufferedImage> running = new ArrayList<>();
    protected ArrayList<BufferedImage> attacking = new ArrayList<>();
    protected ArrayList<BufferedImage> dying = new ArrayList<>();

    protected boolean isFinished = false;

    protected BufferedImage currentFrame;

    private int animIndex = 0;

    private static int defaultEntityWidth = 40;
    private static int defaultEntityHeigth = 100;

    /**
     * Constructor.
     */
    public Entity(Rect hitbox) {
        this.hitbox = hitbox;
        hitboxMovement = new Rect(hitbox.x,
                hitbox.y + hitbox.height - hitbox.width,
                defaultEntityWidth,
                defaultEntityWidth);
    }

    /**
     * Entity game logic.
     */
    public void update() {
        weapon.update();
        handleClipping();

        if (EH.getTick() % 5 == 0) {
            currentFrame = nextFrame();
        }

    }

    /**
     * Draw the entity.
     */
    public void draw(Graphics g) {
        // hitbox.drawRelative(g);

        Color transparentBlack = new Color(0, 0, 0, 128);
        g.setColor(transparentBlack);

        int xOffset = 0;
        int yOffset = 0;
        if (currentState == State.RUNNING) {
            xOffset = -direction.x * 10;
            yOffset = -5 * direction.y;
        }

        g.fillOval(
                (int) (hitboxMovement.getRelative().x - hitboxMovement.width / 3 + xOffset),
                (int) hitboxMovement.getRelative().y + 30 + yOffset,
                (int) (hitboxMovement.width * 1.5f),
                (int) hitboxMovement.height / 2);
        g = g.create();

        if (currentFrame != null) {
            IM.drawRotatedImage(g, currentFrame,
                    new Point((int) (hitbox.getRelative().x + hitbox.width / 2),
                            (int) (hitbox.getRelative().y + offsetY)),
                    1.5 * direction.x, 1.5, 0);
        }
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
    public void die() {
        setState(State.DYING);
    }

    /**
     * Move the entity in one of 8 directions; x & y should be between -1 & 1.
     */
    protected void move(float x, float y) {

        if (health <= 0) {
            return;
        }

        if (x == 0 && y == 0) {
            setState(State.IDLE);
            return;
        }

        if (currentState != State.RUNNING) {
            setState(State.RUNNING);
        }

        // calculating magnitude
        float magnitude = (float) Math.sqrt(x * x + y * y);

        // normalizing
        if (magnitude != 0) {
            x /= magnitude;
            y /= magnitude;
        }

        // setting direction.x for the animation
        if (x > 0) {
            setDirectionX(Direction.RIGHT);
        } else if (x < 0) {
            setDirectionX(Direction.LEFT);
        }

        // setting direction.y for the animation
        if (y > 0) {
            setDirectionY(Direction.DOWN);
        } else if (y < 0) {
            setDirectionY(Direction.UP);
        } else if (y == 0) {
            setDirectionY(Direction.NONE);
        }

        // moving the body
        hitbox.x += x * speed;
        hitbox.y -= y * speed;
    }

    /**
     * Move the entity in one of 8 directions; x & y should be between -1 & 1.
     */
    protected void move(Point2D.Float dir) {

        if (dir == null) {
            return;
        }

        float x = dir.x - hitboxMovement.getCentered().x;
        float y = -(dir.y - hitboxMovement.getCentered().y);

        move(x, y);
    }

    /**
     * Use the weapon in a direction.x.
     */
    protected void useWeapon(float x) {
        if (health <= 0) {
            return;
        }

        // setting direction.x for the animation
        if (x - hitbox.x > 0) {
            setDirectionX(Direction.RIGHT);
        }
        if (x - hitbox.x < 0) {
            setDirectionX(Direction.LEFT);
        }

        weapon.use(x - hitbox.x);
    }

    /**
     * Determine the next frame of the animation.
     */
    private BufferedImage nextFrame() {

        animIndex++;
        ArrayList<BufferedImage> animArray;

        switch (currentState) {
            case IDLE:
                animArray = idle;
                break;

            case RUNNING:
                animArray = running;
                break;

            case ATTACKING:
                animArray = attacking;
                break;

            case DYING:
                animArray = dying;
                break;

            default:
                animArray = idle;
                break;
        }

        checkAnimFinish(animArray);

        return animArray.get(animIndex);
    }

    /**
     * Experimental. Same as move(), but smooth.
     * Used only to handle clipping
     */
    private void moveLerp(float x, float y) {

        if (x == 0 && y == 0) {
            setState(State.IDLE);
            return;
        }

        if (currentState != State.RUNNING) {
            setState(State.RUNNING);
        }

        // calculating magnitude
        float magnitude = (float) Math.sqrt(x * x + y * y);

        // normalizing
        if (magnitude != 0) {
            x /= magnitude;
            y /= magnitude;
        }

        // moving the body
        hitbox.x = Engine.lerp(hitbox.x, hitbox.x + x, speed);
        hitbox.y = Engine.lerp(hitbox.y, hitbox.y - y, speed);
    }

    /**
     * Makes sure that entities don't overlap/clip through each other.
     */
    private void handleClipping() {
        hitboxMovement = new Rect(hitbox.x,
                hitbox.y + hitbox.height - hitbox.width,
                defaultEntityWidth,
                defaultEntityWidth);

        for (Entity e : Game.entities) {
            if (e != this) {
                if (Engine.collisionRect(hitbox, e.getHitbox())) {
                    moveLerp(-(e.getHitbox().x - hitbox.x), (e.getHitbox().y - hitbox.y));
                }
            }
        }

        for (Immovable object : Map.getEnvironment()) {
            repulseSelf(object.getHitbox());
        }
    }

    /**
     * Repulse self away from a rect.
     */
    private void repulseSelf(Rect rect) {
        if (Engine.collisionRect(hitboxMovement, rect)) {
            float overlapX1 = Math.max(hitboxMovement.x, rect.x);
            float overlapY1 = Math.max(hitboxMovement.y, rect.y);

            float overlapX2 = Math.min(
                    hitboxMovement.x + hitboxMovement.width,
                    rect.x + rect.width);
            float overlapY2 = Math.min(
                    hitboxMovement.y + hitboxMovement.height,
                    rect.y + rect.height);

            // Calculate the center of the overlapping area
            int collisionX = (int) (overlapX1 + overlapX2) / 2;
            int collisionY = (int) (overlapY1 + overlapY2) / 2;

            // Move the opposite direction.x from the point of collision
            move(-(collisionX - hitboxMovement.getCentered().x),
                    (collisionY - hitboxMovement.getCentered().y));
        }
    }
    
    private void checkAnimFinish(ArrayList<BufferedImage> animArray) {
        isFinished = false;
        if (animIndex > animArray.size() - 1) {
            animIndex = animArray.size() - 1;
            if (currentState != State.DYING) {
                animIndex = 0;
                isAnimationLocked = false;
            }
            isFinished = true;
            setState(State.IDLE);
        }
    }

    /**
     * Get a weapon in the beginning.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.weapon.owner = this;
    }

    public State getState() {
        return currentState;
    }

    /**
     * Switch the animation state.
     */
    public void setState(State newState) {
        if (isAnimationLocked && newState != State.DYING) {
            return;
        }
        if (newState == State.ATTACKING || newState == State.DYING) {
            isAnimationLocked = true;
        }
        animIndex = 0;
        currentState = newState;
    }

    /**
     * Getter for the body.
     */
    public Rect getHitbox() {
        return hitbox;
    }

    public float getHealth() {
        return health;
    }

    public static int getDefaultWidth() {
        return defaultEntityWidth;
    }

    public static int getDefaultHeight() {
        return defaultEntityHeigth;
    }

    /**
     * Returns the direction.x that the entity is direction.x.
     */
    public int getDirection() {
        return direction.x;
    }

    /**
     * Setting the direction.x at which the entity is looking.
     */
    protected void setDirectionX(Direction newDirection) {
        if (newDirection == Direction.RIGHT) {
            direction.x = 1;
        } else {
            direction.x = -1;
        }
    }

    /**
     * Setting the direction.x at which the entity is looking.
     */
    protected void setDirectionY(Direction newDirection) {
        if (newDirection == Direction.UP) {
            direction.y = -1;
        } else if (newDirection == Direction.DOWN) {
            direction.y = 1;
        } else {
            direction.y = 0;
        }
    }
}
