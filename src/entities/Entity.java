package entities;

import core.Game;
import gear.Weapon;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import shapes.Rect;
import utility.Direction;
import utility.EH;
import utility.Engine;
import utility.IM;

/**
 * The class from which all other entity classes derive from.
 */
public abstract class Entity {

    protected Rect hitbox;
    protected Weapon weapon;

    protected float maxHealth = 100;
    protected float health = maxHealth;
    protected float armor = 10; // a percentage
    protected float speed = 6; // movement speed
    
    public State currentState = State.IDLE;
    protected boolean isAnimationLocked = false;
    private int facing = 1;

    protected ArrayList<BufferedImage> idle = new ArrayList<>();
    protected ArrayList<BufferedImage> running = new ArrayList<>();
    protected ArrayList<BufferedImage> attacking = new ArrayList<>();
    protected ArrayList<BufferedImage> dying = new ArrayList<>();

    protected boolean isFinished = false;

    private BufferedImage currentFrame;

    private int animIndex = 0;

    private static int defaultEntityWidth = 40;
    private static int defaultEntityHeigth = 100;


    /**
     * Constructor.
     */
    public Entity(Rect hitbox) {
        this.hitbox = hitbox;
    }

    /**
     * Getter for the body.
     */
    public Rect getHitbox() {
        return hitbox;
    }

    /**
     * Entity game logic.
     */
    public void update() {
        weapon.update();
        handleClipping();
        if (EH.getTick() % 5 == 0) {
            switchFrame();
        }
    }

    /**
     * Draw the entity.
     */
    public void draw(Graphics g) {
        hitbox.drawRelative(g);

        if (currentFrame != null) {
            IM.drawRotatedImage(g, currentFrame, 
                new Point((int) (hitbox.getRelative().x + hitbox.width / 2), 
                          (int) (hitbox.getRelative().y + 4)),
                1.5 * facing, 1.5, 0);
        }
    }

    /**
     * Handle animations.
     */
    private void switchFrame() {
        currentFrame = nextFrame();
    }

    /**
     * Determine the next frame of the animation.
     */
    public BufferedImage nextFrame() {

        animIndex++;
        ArrayList<BufferedImage> animArray;

        switch (currentState) {
            case IDLE:
                animArray = idle;
                break;

            case WALKING:
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

        if (animIndex > animArray.size() - 1) {
            if (currentState != State.DYING) {
                animIndex = 0;
                isAnimationLocked = false;
            } else {
                animIndex = animArray.size() - 1;
            }
            isFinished = true;
        } else {
            isFinished = false;
        }

        return animArray.get(animIndex);
    }

    /**
     * Switch the animation state.
     */
    public void switchState(State newState) {
        if (isAnimationLocked) {
            return;
        }
        if (newState == State.ATTACKING || newState == State.DYING) {
            isAnimationLocked = true;
        }
        animIndex = 0;
        currentState = newState;
    }

    /**
     * Move the entity in one of 8 directions; x & y should be between -1 & 1.
     */
    public void move(float x, float y) {
        
        if (health <= 0) {
            return;
        }

        if (x == 0 && y == 0) {
            if (currentState != State.IDLE) {
                switchState(State.IDLE);
                return;
            }
        } else {
            if (currentState != State.WALKING) {
                switchState(State.WALKING);
            }
        }

        // calculating magnitude
        float magnitude = (float) Math.sqrt(x * x + y * y);

        // normalizing
        if (magnitude != 0) {
            x /= magnitude;
            y /= magnitude;
        }

        // setting direction for the animation
        if (x > 0) {
            setDirection(Direction.RIGHT);
        }
        if (x < 0) {
            setDirection(Direction.LEFT);
        }

        // moving the body
        hitbox.x += x * speed / 2;
        hitbox.y -= y * speed / 2;
    }

    /**
     * Experimental. Same as move(), but smooth.
     * Used only to handle clipping
     */
    public void moveLerp(float x, float y) {

        if (x == 0 && y == 0) {
            if (currentState != State.IDLE) {
                switchState(State.IDLE);
            }
            return;
        } else {
            if (currentState != State.WALKING) {
                switchState(State.WALKING);
            }
        }

        // calculating magnitude
        float magnitude = (float) Math.sqrt(x * x + y * y);

        // normalizing
        if (magnitude != 0) {
            x /= magnitude;
            y /= magnitude;
        }

        // moving the body
        hitbox.x = Engine.lerp(hitbox.x, hitbox.x + x * 2, speed / 7);
        hitbox.y = Engine.lerp(hitbox.y, hitbox.y - y * 2, speed / 7);
    }

    /**
     * Make sure that entities don't overlap/clip through each other.
     */
    public void handleClipping() {
        for (Entity e : Game.entities) {
            if (e != this) {
                if (Engine.collisionRect(hitbox, e.getHitbox())) {
                    moveLerp(-(e.getHitbox().x - hitbox.x), (e.getHitbox().y - hitbox.y));
                }
            }
        }
    }

    /**
     * Get a weapon in the beginning.
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
        this.weapon.owner = this;
    }

    /**
     * Use the weapon in a direction.
     */
    public void useWeapon(float x, float y) {
        if (health <= 0) {
            return;
        }

        // setting direction for the animation
        if (x - hitbox.x > 0) {
            setDirection(Direction.RIGHT);
        }
        if (x - hitbox.x < 0) {
            setDirection(Direction.LEFT);
        }

        weapon.use(x - hitbox.x, hitbox.y - y);
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
        switchState(State.DYING);
    }

    public static int getDefaultWidth() {
        return defaultEntityWidth;
    }

    public static int getDefaultHeight() {
        return defaultEntityHeigth;
    }

    /**
     * Setting the direction at which the entity is looking.
     */
    protected void setDirection(Direction newDirection) {
        if (newDirection == Direction.RIGHT) {
            facing = 1;
        } else {
            facing = -1;
        }
    }

    public int getDirection() {
        return facing;
    }
}
