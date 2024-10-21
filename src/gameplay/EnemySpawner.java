package gameplay;

import core.Game;
import entities.Enemy;
import entities.Entity;
import entities.Player;
import gear.MeleeWeapon;
import java.util.ArrayList;
import java.util.Random;
import shapes.Rect;
import utility.EH;

/**
 * Handles spawning the waves of enemies.
 */
public class EnemySpawner {

    private Player player;
    private ArrayList<Enemy> enemies;

    //private int waveInd;
    private int waveSize;
    private int spawnRadius;
    private int spawnInterval;
    private Random random;
    private int timer;

    /**
     * Constructor.
     */
    public EnemySpawner() {
        player = Game.player;
        enemies = Game.enemies;

        //waveInd = 1;
        waveSize = 1;

        spawnRadius = 200;
        spawnInterval = 1; // 1 minute
        spawnInterval *= 60 * 1000; // transform into ms
        random = new Random();
        timer = EH.getTick();
        spawnEnemy();
    }
    
    /**
     * Update.
     */
    public void update() {
        if (EH.getTick() - timer > spawnInterval) { // Spawn every n minutes
            for (int i = 0; i < waveSize; i++) {
                spawnEnemy();
            }
            //waveInd++;
            waveSize = (int) Math.floor(waveSize * 1.5);
            timer = EH.getTick();
        }
    }

    /**
     * Calculate where to spawn enemies and then do so.
     */
    private void spawnEnemy() {
        double angle = random.nextDouble() * 2 * Math.PI;

        // caluculate enemy spawn coordinates
        int x = (int) (player.getHitbox().x + spawnRadius * Math.cos(angle));
        int y = (int) (player.getHitbox().y + spawnRadius * Math.sin(angle));

        enemies.add(new Enemy(new Rect(x, y, Entity.getDefaultWidth(), Entity.getDefaultHeight())));
        enemies.get(enemies.size() - 1).setWeapon(new MeleeWeapon(10, 200));;
    }
}
