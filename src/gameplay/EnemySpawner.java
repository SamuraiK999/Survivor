package gameplay;

import core.Game;
import entities.Enemy;
import entities.Entity;
import entities.Player;
import gear.MeleeWeapon;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import shapes.Circle;

/**
 * Handles spawning the waves of enemies.
 */
public class EnemySpawner {

    private static EnemySpawner instance;

    private Player player;
    private ArrayList<Enemy> enemies;

    //private int waveInd;
    private int waveSize;
    private int spawnRadius;
    private int spawnInterval;
    private Random random;
    private Timer timer;

    /**
     * Constructor.
     */
    private EnemySpawner() {
        player = Game.player;
        enemies = Game.enemies;

        //waveInd = 1;
        waveSize = 1;

        spawnRadius = 200;
        spawnInterval = 1; // 1 minute
        spawnInterval *= 60 * 1000; // transform into ms
        random = new Random();
        timer = new Timer();
    }

    /**
     * Get the single instance of the class.
     */
    public static EnemySpawner getInstance() {
        if (instance == null) {
            instance = new EnemySpawner();
        }
        return instance;
    }

    /**
     * Begin the spawning cycle.
     */
    public void startSpawning() {
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                for (int i = 0; i < waveSize; i++) {
                    spawnEnemy();
                }
                //waveInd++;
                waveSize = (int) Math.floor(waveSize * 1.5);
            }
        }, 0, spawnInterval); // Spawn every 5 minutes
    }

    /**
     * Calculate where to spawn enemies and then do so.
     */
    private void spawnEnemy() {
        double angle = random.nextDouble() * 2 * Math.PI;

        // caluculate enemy spawn coordinates
        int x = (int) (player.getBody().x + spawnRadius * Math.cos(angle));
        int y = (int) (player.getBody().y + spawnRadius * Math.sin(angle));

        enemies.add(new Enemy(new Circle(x, y, Entity.getDefaultRadius())));
        enemies.get(enemies.size() - 1).setWeapon(new MeleeWeapon(10, 200));;
    }
}
