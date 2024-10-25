package core.states;

import core.Main;
import gameplay.EnemySpawner;
import gameplay.entities.Enemy;
import gameplay.entities.Entity;
import gameplay.entities.Player;
import gameplay.gear.particles.Bullet;
import gameplay.gear.particles.Slash;
import gameplay.gear.weapons.RangedWeapon;
import gameplay.map.Map;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

import utility.Engine;
import utility.IM;
import utility.shapes.Circle;
import utility.shapes.Rect;

/**
 * Game.
 */
public class Game {

    public static DeathMenu deathMenu;
    public static Map map = new Map();

    public static ArrayList<Entity> entities = new ArrayList<>();

    private static Player player = new Player(
            new Rect(0, 0, Entity.getDefaultWidth(), Entity.getDefaultHeight()));

    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static List<Enemy> enemiesToRemove = new ArrayList<>();

    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static List<Bullet> bulletsToRemove = new ArrayList<>();

    public static ArrayList<Slash> slashes = new ArrayList<>();
    public static List<Slash> slashesToRemove = new ArrayList<>();

    private static Point2D.Float camera = new Point2D.Float(0, 0);

    private static EnemySpawner enemySpawner = new EnemySpawner();

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

        player = new Player(new Rect(0, 0, Entity.getDefaultWidth(), Entity.getDefaultHeight()));
        player.setWeapon(new RangedWeapon(34, 10, new Point(0, -26)));
        enemies = new ArrayList<>();
        bullets = new ArrayList<>();
        slashes = new ArrayList<>();
        enemySpawner = new EnemySpawner();
    }

    /**
     * Handles game logic.
     */
    public void update() {
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

        cameraFollow(player.getHitbox());

        for (Slash s : slashes) {
            s.update();
        }
        slashes.removeAll(slashesToRemove);
        slashesToRemove.clear();

        if (deathMenu != null) {
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

        if (deathMenu != null) {
            deathMenu.draw(g);
        }
    }

    /**
     * Make the camera follow a target of type rect.
     */
    public static void cameraFollow(Rect target) {
        float newX = Engine.clamp(
                Engine.lerp(camera.x,
                    -(target.x + target.width / 2) + Main.FRAME_WIDTH / (float) Main.scaleX / 2,
                    0.1f),
                IM.backgroundMap.getWidth() / 2,
                IM.backgroundMap.getWidth() / 2 * 3);

        float newY = Engine.clamp(
                Engine.lerp(camera.y,
                    -(target.y + target.height / 2) + Main.FRAME_HEIGTH / (float) Main.scaleY / 2,
                    0.1f),
                -IM.backgroundMap.getHeight() / 2 + Entity.getDefaultHeight(),
                IM.backgroundMap.getHeight() / 2 * 3);

        camera.setLocation(newX, newY);
    }

    public static Point2D.Float getCamera() {
        return camera;
    }

    public static Player getPlayer() {
        return player;
    }

    /**
     * Inits a post-death menu.
     */
    public static void createDeathMenu() {
        if (deathMenu == null) {
            deathMenu = new DeathMenu();
        }
    }

}
