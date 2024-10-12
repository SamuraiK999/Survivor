package main;

import entities.Enemy;
import entities.Player;
import gear.Bullet;
import gear.MeleeWeapon;
import gear.RangedWeapon;
import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import shapes.Circle;
import shapes.Rect;

/**
 * Game.
 */
public class Game {

    public static Player player = new Player(new Circle(400, 300, 25));
    public static ArrayList<Enemy> enemies = new ArrayList<>();
    public static ArrayList<Bullet> bullets = new ArrayList<>();

    private static Point2D.Float camera = new Point2D.Float(0, 0);

    /**
     * Constructor.
     */
    public Game() {
        player.setWeapon(new RangedWeapon(player, 50));

        for (Enemy enemy : enemies) {
            enemy.setWeapon(new MeleeWeapon(enemy, 75));
        }
    }

    /**
     * Handles game logic.
     */
    public void update() {
        player.update();
        cameraFollow(player.getBody());

        for (Enemy enemy : enemies) {
            enemy.update();
        }

        for (Bullet bullet : bullets) {
            bullet.update();
        }
    }

    /**
     * Render everything.
     */
    public void draw(Graphics g) {
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
                target.x + target.width / 2 + Main.frame.getWidth() / 2,
                0.1f);
        float newY = Engine.lerp(camera.y,
                target.y + target.width / 2 + Main.frame.getHeight() / 2,
                0.1f);

        camera.setLocation(newX, newY);
    }

    public static Point2D.Float getCamera() {
        return camera;
    }

}
