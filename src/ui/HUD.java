package ui;

import core.Main;
import core.states.Game;
import gameplay.map.Map;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import utility.Engine;
import utility.IM;
import utility.shapes.Rect;

/**
 * HUD.
 */
public class HUD {
    private static int healthLength = IM.health.getWidth() * 5;
    private static int healthTailLength = healthLength;

    private static Point playerMinimap = new Point(0, 0);
    private static Rect minimapDimensions = new Rect(
            Main.FRAME_WIDTH - IM.minimapbg.getWidth() * 2 + 8,
            14,
            133 * 2,
            86 * 2);

    public static ArrayList<Point> enemyMinimap = new ArrayList<>();

    /**
     * Keeps the HUD updated.
     */
    public static void update() {
        updateHealthbar();
        updateMinimap();
    }

    /**
     * Draw the HUD.
     */
    public static void draw(Graphics g) {
        drawStatbar(g);
        drawScore(g);
        drawMinimap(g);
    }

    private static void updateHealthbar() {
        healthLength = (int) (IM.health.getWidth() * 5 * Game.getPlayer().getHealth()
                / Game.getPlayer().getMaxHealth());

        healthTailLength = (int) Engine.lerp(healthTailLength, healthLength, 0.01f);
    }

    private static void updateMinimap() {
        enemyMinimap.clear();
        playerMinimap = new Point(
                (int) (minimapDimensions.x + minimapDimensions.width / 2
                        + Game.getPlayer().getHitbox().x
                                / Map.getBoundaties().x
                                * minimapDimensions.width / 2.9f),
                (int) (minimapDimensions.y + minimapDimensions.height / 2
                        + Game.getPlayer().getHitbox().y
                                / (Map.getBoundaties().y)
                                * minimapDimensions.height / 2.6f + 7));

        if (!Game.enemies.isEmpty()) {
            for (int i = 0; i < Game.enemies.size(); i++) {
                enemyMinimap.add(new Point(
                        (int) (minimapDimensions.x + minimapDimensions.width / 2
                                + Game.enemies.get(i).getHitbox().x
                                        / Map.getBoundaties().x
                                        * minimapDimensions.width / 2.9f),
                        (int) (minimapDimensions.y + minimapDimensions.height / 2
                                + Game.enemies.get(i).getHitbox().y
                                        / (Map.getBoundaties().y)
                                        * minimapDimensions.height / 2.6f + 7)));
            }
        }
    }

    private static void drawStatbar(Graphics g) {
        g.drawImage(IM.statbar, 0, 0, IM.statbar.getWidth() * 5, IM.statbar.getHeight() * 5, null);
        g.drawImage(IM.healthTail, 130, 10, healthTailLength, IM.healthTail.getHeight() * 5, null);
        g.drawImage(IM.health, 130, 10, healthLength, IM.health.getHeight() * 5, null);
    }

    private static void drawScore(Graphics g) {
        g.drawImage(
                IM.score,
                Main.FRAME_WIDTH / 2 - IM.score.getWidth() / 2,
                0,
                IM.score.getWidth(),
                IM.score.getHeight(),
                null);
    }

    private static void drawMinimap(Graphics g) {
        g.drawImage(
                IM.minimapbg,
                Main.FRAME_WIDTH - IM.minimapbg.getWidth() * 2,
                0, IM.minimapbg.getWidth() * 2,
                IM.minimapbg.getHeight() * 2,
                null);
        g.drawImage(IM.soldierMinimap, playerMinimap.x - 2, playerMinimap.y - 2, 4, 4, null);
        if (!Game.enemies.isEmpty()) {
            for (int i = 0; i < Game.enemies.size(); i++) {
                if (!enemyMinimap.isEmpty()) {
                    g.drawImage(
                            IM.enemyMinimap,
                            enemyMinimap.get(i).x - 2,
                            enemyMinimap.get(i).y - 2,
                            4, 4,
                            null);
                }
            }
        }
    }
}
