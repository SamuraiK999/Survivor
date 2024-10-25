package gameplay;

import core.states.Game;
import gameplay.entities.Enemy;
import gameplay.entities.Entity;
import gameplay.gear.weapons.MeleeWeapon;
import java.util.ArrayList;
import java.util.Random;
import utility.EH;
import utility.shapes.Rect;

/**
 * Handles spawning the waves of enemies.
 */
public class EnemySpawner {

    private ArrayList<Enemy> enemies;

    private int waveInd;
    private int waveSize;
    private int spawnInterval;
    private int timer;

    /**
     * Constructor.
     */
    public EnemySpawner() {
        enemies = Game.enemies;

        waveInd = 1;
        waveSize = 1;

        spawnInterval = 1; // 1 minute
        spawnInterval *= 60 * 10; // transform into ms
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
            waveInd++;
            waveSize++;
            timer = EH.getTick();
        }
    }

    /**
     * Calculate where to spawn enemies and then do so.
     */
    private void spawnEnemy() {
        Random random = new Random();

        // caluculate enemy spawn coordinates
        int x = random.nextInt(Game.map.getBoundaties().x) - Game.map.getBoundaties().x / 2;
        int y = random.nextInt(Game.map.getBoundaties().y) - Game.map.getBoundaties().y / 2;

        enemies.add(
            new Enemy(new Rect(x, y, Entity.getDefaultWidth(), Entity.getDefaultHeight() - 10))
        );

        enemies.get(enemies.size() - 1).setWeapon(new MeleeWeapon(10, 100));;
    }

    public int getWaveInd() {
        return waveInd;
    }
}
