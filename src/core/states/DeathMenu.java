package core.states;

import core.Main;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import ui.Menu;
import ui.buttons.MainMenuButton;
import ui.buttons.PlayButton;
import ui.buttons.enums.ButtonSite;
import utility.IM;
import utility.shapes.Rect;

/**
 * The menu that the user sees when he dies.
 */
public class DeathMenu extends Menu {

    /**
     * Constructor.
     */
    public DeathMenu() {
        super(
                ButtonSite.DEATH_MENU,
                IM.deathMenuBackground,
                new Rect(
                        Main.FRAME_WIDTH / 2,
                        Main.FRAME_HEIGTH / 2,
                        IM.deathMenuBackground.getWidth() * 2,
                        IM.deathMenuBackground.getHeight() * 2));
    }

    @Override
    protected void init() {
        buttons.add(
                new PlayButton(
                        location,
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2,
                                IM.playButton.getWidth(),
                                IM.playButton.getHeight())));
        buttons.add(
                new MainMenuButton(
                        location,
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2 + 70,
                                IM.mainMenuButtonDeath.getWidth(),
                                IM.mainMenuButtonDeath.getHeight())));
    }

    @Override
    public void draw(Graphics g) {
        tintBackground(g);
        super.draw(g);
        drawScore(g);
    }

    private void drawScore(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        Font impactFont = new Font("Impact", Font.PLAIN, 24);
        FontMetrics metrics = g2d.getFontMetrics(impactFont);

        int scoreTextWidth = metrics.stringWidth(String.valueOf("SCORE"));
        int scoreWidth = metrics.stringWidth(String.valueOf(Game.score));
        Point scoreCoords = new Point(
                Main.FRAME_WIDTH / 2 
                - (IM.deathMenuBackground.getWidth() / 2) - scoreTextWidth / 2,
                Main.FRAME_HEIGTH / 2 - (IM.deathMenuBackground.getHeight() / 8 * IM.UI_SCALE));

        int highscoreTextWidth = metrics.stringWidth(String.valueOf("HIGHSCORE"));
        int highscoreWidth = metrics.stringWidth(String.valueOf(Game.highscore));
        Point highscoreCoords = new Point(
                Main.FRAME_WIDTH / 2 
                + (IM.deathMenuBackground.getWidth() / 2) - highscoreTextWidth / 2,
                Main.FRAME_HEIGTH / 2 - (IM.deathMenuBackground.getHeight() / 8 * IM.UI_SCALE));



        g2d.drawImage(IM.scoreBg,
                Main.FRAME_WIDTH / 2 - (IM.deathMenuBackground.getWidth() / 2) 
                - IM.scoreBg.getWidth() / 2,
                Main.FRAME_HEIGTH / 2 - (IM.deathMenuBackground.getHeight() / 8 * IM.UI_SCALE) 
                - IM.scoreBg.getHeight() / 2 + 5,
                null);
        g2d.drawString("SCORE",
                scoreCoords.x,
                scoreCoords.y);
        g2d.drawString(String.valueOf(Game.score),
                scoreCoords.x + scoreTextWidth / 2 - scoreWidth / 2,
                scoreCoords.y + 30);
            

        g2d.drawImage(IM.scoreBg,
                Main.FRAME_WIDTH / 2 + (IM.deathMenuBackground.getWidth() / 2) 
                - IM.scoreBg.getWidth() / 2,
                Main.FRAME_HEIGTH / 2 - (IM.deathMenuBackground.getHeight() / 8 * IM.UI_SCALE) 
                - IM.scoreBg.getHeight() / 2 + 5,
                null);
        g2d.drawString("HIGHSCORE",
                highscoreCoords.x,
                highscoreCoords.y);
        g2d.drawString(String.valueOf(Game.highscore),
                highscoreCoords.x + highscoreTextWidth / 2 - highscoreWidth / 2,
                highscoreCoords.y + 30);
    }
}
