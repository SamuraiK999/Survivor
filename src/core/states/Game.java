package core.states;

import gameplay.Camera;
import gameplay.EnemySpawner;
import gameplay.entities.Enemy;
import gameplay.entities.Entity;
import gameplay.entities.Player;
import gameplay.gear.particles.Bullet;
import gameplay.gear.particles.Slash;
import gameplay.gear.weapons.RangedWeapon;
import gameplay.map.Map;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Game.
 */
public class Game {

    public static Map map = new Map();
    public static PauseMenu pausedMenu = new PauseMenu();
    public static DeathMenu deathMenu;

    public static ArrayList<Entity> entities = new ArrayList<>();

    private static Player player = new Player();

    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static List<Enemy> enemiesToRemove = new ArrayList<>();

    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static List<Bullet> bulletsToRemove = new ArrayList<>();

    public static ArrayList<Slash> slashes = new ArrayList<>();
    public static List<Slash> slashesToRemove = new ArrayList<>();

    private static EnemySpawner enemySpawner = new EnemySpawner();

    private static boolean isGamePaused = false;
    private static boolean isPlayerAlive = true;

    /**
     * Constructor.
     */
    public Game() {
        init();
    }

    /**
     * Init.
     */
    public void init() {
        entities = new ArrayList<>();

        player = new Player();
        player.setWeapon(new RangedWeapon(34, 10, new Point(0, -26)));
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        slashes = new ArrayList<>();
        enemySpawner = new EnemySpawner();

        pausedMenu.init();
    }

    /**
     * Handles game logic.
     */
    public void update() {
        
        if (isGamePaused) {
            pausedMenu.update();
            return;
        }

        map.update();

        enemySpawner.update();

        entities.clear();
        entities.add(player);

        for (Bullet b : bullets) {
            b.update();
        }
        bullets.removeAll(bulletsToRemove);
        bulletsToRemove.clear();

        player.update();

        for (Enemy e : enemies) {
            entities.add(e);
            e.update();
        }
        enemies.removeAll(enemiesToRemove);
        enemiesToRemove.clear();

        Camera.follow(player.getHitbox());

        for (Slash s : slashes) {
            s.update();
        }
        slashes.removeAll(slashesToRemove);
        slashesToRemove.clear();

        if (!isPlayerAlive) {
            deathMenu.update();
        }
    }

    /**
     * Render everything.
     */
    public void draw(Graphics g) {
        map.draw(g);

        for (Slash s : slashes) {
            s.draw(g);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }

        player.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        map.drawOverlay(g);

        if (isGamePaused) {
            pausedMenu.draw(g);
        }

        if (!isPlayerAlive) {
            deathMenu.draw(g);
        }
    }

    public static Player getPlayer() {
        return player;
    }

    /**
     * Inits a post-death menu.
     */
    public static void initDeathMenu() {
        if (deathMenu == null) {
            deathMenu = new DeathMenu();
        }
    }

    /**
     * Getter for the player state (alive or dead).
     */
    public static boolean getPlayerState() {
        return isPlayerAlive;
    }

    /**
     * Setter for the player state (alive or dead).
     */
    public static void setPlayerState(boolean state) {
        isPlayerAlive = state;
    }

    /**
     * Getter for the pause state.
     */
    public static boolean getPauseState() {
        return isGamePaused;
    }

    /**
     * Setter for the pause state.
     */
    public static void setPauseState(boolean state) {
        isGamePaused = state;
    }
}
