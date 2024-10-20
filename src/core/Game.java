package core;

import entities.Enemy;
import entities.Entity;
import entities.Player;
import gameplay.EnemySpawner;
import gear.Bullet;
import gear.RangedWeapon;
import gear.Slash;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import shapes.Circle;
import shapes.Rect;
import utility.Engine;

/**
 * Game.
 */
public class Game {

    public static ArrayList<Entity> entities = new ArrayList<>();

    public static Player player = new Player(
        new Rect(400, 300, Entity.getDefaultWidth(), Entity.getDefaultHeight())
        );

    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static List<Enemy> enemiesToRemove = new ArrayList<>();

    public static ArrayList<Bullet> bullets = new ArrayList<>();
    public static List<Bullet> bulletsToRemove = new ArrayList<>();
    
    public static ArrayList<Slash> slashes = new ArrayList<>();
    public static List<Slash> slashesToRemove = new ArrayList<>();
    

    private static Point2D.Float camera = new Point2D.Float(0, 0);

    private EnemySpawner enemySpawner = new EnemySpawner();

    /**
     * Constructor.
     */
    public Game() {
        player.setWeapon(new RangedWeapon(10, 50));
    }

    /**
     * Handles game logic.
     */
    public void update() {
        enemySpawner.update();

        entities.clear();
        entities.add(player);
        
        for (Enemy e : enemies) {
            entities.add(e);
            e.update();
        }
        enemies.removeAll(enemiesToRemove);
        enemiesToRemove.clear();

        player.update();

        cameraFollow(player.getHitbox());
        
        for (Bullet b : bullets) {
            b.update();
        }
        bullets.removeAll(bulletsToRemove);
        bulletsToRemove.clear();

        for (Slash s : slashes) {
            s.update();
        }
        slashes.removeAll(slashesToRemove);
        slashesToRemove.clear();
    }

    /**
     * Render everything.
     */
    public void draw(Graphics g) {

        for (Slash s : slashes) {
            s.draw(g);
        }

        player.draw(g);

        for (Enemy enemy : enemies) {
            enemy.draw(g);
        }

        for (Bullet bullet : bullets) {
            bullet.draw(g);
        }
    }

    /**
     * Make the camera follow a target of type circle.
     */
    public static void cameraFollow(Circle target) {
        float newX = Engine.lerp(camera.x, -target.x + Main.frame.getWidth() / 2, 0.05f);
        float newY = Engine.lerp(camera.y, -target.y + Main.frame.getHeight() / 2, 0.05f);

        camera.setLocation(newX, newY);
    }

    /**
     * Make the camera follow a target of type circle.
     */
    public static void cameraFollow(Rect target) {
        float newX = Engine.lerp(camera.x,
                -(target.x + target.width / 2) + Main.frame.getWidth() / 2,
                0.1f);
        float newY = Engine.lerp(camera.y,
                -(target.y + target.height / 2) + Main.frame.getHeight() / 2,
                0.1f);

        camera.setLocation(newX, newY);
    }

    public static Point2D.Float getCamera() {
        return camera;
    }

}
