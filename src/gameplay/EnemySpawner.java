package gameplay;

import core.Main;
import core.states.Game;
import gameplay.entities.Enemy;
import gameplay.entities.Entity;
import gameplay.gear.weapons.MeleeWeapon;
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
        int x = (random.nextInt(Map.getBoundaties().x) - Map.getBoundaties().x / 2) * 3;
        int y = (random.nextInt(Map.getBoundaties().y) - Map.getBoundaties().y / 2) * 3;

        while (Engine.collisionRect(
                new Rect(
                        Game.getPlayer().getHitbox().x
                                - Main.FRAME_WIDTH / 2 + Entity.getDefaultWidth() / 2,
                        Game.getPlayer().getHitbox().y
                                - Main.FRAME_HEIGTH / 2 + Entity.getDefaultHeight() / 2,
                        Main.FRAME_WIDTH, Main.FRAME_HEIGTH),
                new Rect(x, y, Entity.getDefaultWidth(), Entity.getDefaultHeight()))) {

            x = (random.nextInt(Map.getBoundaties().x) - Map.getBoundaties().x / 2) * 3;
            y = (random.nextInt(Map.getBoundaties().y) - Map.getBoundaties().y / 2) * 3;
        }

        enemies.add(
                new Enemy(new Rect(x, y, Entity.getDefaultWidth(), Entity.getDefaultHeight() - 10)));

        enemies.get(enemies.size() - 1).setWeapon(new MeleeWeapon(10, 100));
        ;
    }

    public int getWaveInd() {
        return waveInd;
    }
}
