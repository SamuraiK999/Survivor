package gameplay;

import core.Main;
import core.states.Game;
import gameplay.entities.Enemy;
import gameplay.entities.Entity;
import gameplay.gear.weapons.MeleeWeapon;
import gameplay.map.Immovable;
import gameplay.map.Map;
import java.util.ArrayList;
import java.util.Random;
import utility.EH;
import utility.Engine;
import utility.shapes.Rect;

/**
 * Handles spawning the waves of enemies.
 */
public class EnemySpawner {

    private ArrayList<Enemy> enemies;

    private int waveInd;
    private int waveSize;
    private float spawnInterval;
    private int timer;

    /**
     * Constructor.
     */
    public EnemySpawner() {
        enemies = Game.enemies;

        waveInd = 1;
        waveSize = 3;

        spawnInterval = 0.008f; // minutes
        spawnInterval *= 60 * 1000; // transform into ms
        timer = EH.getTick();
        spawnEnemy();
        spawnEnemy();
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
            waveSize += 2;
            spawnInterval += waveSize * 12;
            timer = EH.getTick();
        }
    }

    /**
     * Calculate where to spawn enemies and then do so.
     */
    private void spawnEnemy() {
        Random random = new Random();

        // Starting spawnpoint
        int x = 100;
        int y = 100;

        // While the random spawnpoint is within the user's FOV,
        while (Engine.collisionRect(
                new Rect(
                        Game.getPlayer().getHitbox().x
                                - Main.FRAME_WIDTH / 2 + Entity.getDefaultWidth() / 2,
                        Game.getPlayer().getHitbox().y
                                - Main.FRAME_HEIGTH / 2 + Entity.getDefaultHeight() / 2,
                        Main.FRAME_WIDTH, Main.FRAME_HEIGTH),
                new Rect(x, y, Entity.getDefaultWidth(), Entity.getDefaultHeight()))) {

            // Change spawnpoint.
            x = 32 * 4
                    + (random.nextInt(Map.getBoundaties().x - 34 * 3)
                            - Map.getBoundaties().x / 2) * 3;
            y = 32 * 5 + 16
                    + (random.nextInt(Map.getBoundaties().y - 34 * 5)
                            - Map.getBoundaties().y / 2) * 3;
        }
        for (Immovable obj : Map.getEnvironment()) {
            // If enemy is spawning in a wall,
            if (Engine.collisionRect(
                    new Rect(x, y, Entity.getDefaultWidth(), Entity.getDefaultHeight()),
                    obj.getHitbox())) {
                // Change spawnpoint.
                x = 32 * 4
                        + (random.nextInt(Map.getBoundaties().x - 34 * 3)
                                - Map.getBoundaties().x / 2) * 3;
                y = 32 * 5 + 16
                        + (random.nextInt(Map.getBoundaties().y - 34 * 5)
                                - Map.getBoundaties().y / 2) * 3;
            }
        }

        // Spawn the enemy at the determined spawnpoint.
        enemies.add(
                new Enemy(
                        new Rect(x, y, Entity.getDefaultWidth(), Entity.getDefaultHeight() - 10)));

        enemies.get(enemies.size() - 1).setWeapon(new MeleeWeapon(10, 100));
    }

    public int getWaveInd() {
        return waveInd;
    }
}
