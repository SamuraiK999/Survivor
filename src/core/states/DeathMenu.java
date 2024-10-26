package core.states;

import core.Main;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;

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
        int scoreWidth;
        tintBackground(g);
        super.draw(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.white);
        Font impactFont = new Font("Impact", Font.PLAIN, 24);
        FontMetrics metrics = g2d.getFontMetrics(impactFont);
        scoreWidth = metrics.stringWidth(String.valueOf("YOUR SCORE"));
        g2d.drawString("YOUR SCORE", 
        (Main.FRAME_WIDTH / 2 - 3 * (IM.deathMenuBackground.getWidth() / 4)) , 
        Main.FRAME_HEIGTH / 2 - 3 * (IM.deathMenuBackground.getHeight() / 8));    
        g2d.drawString(String.valueOf(Game.score), 
            Main.FRAME_WIDTH / 2 - (3 * (IM.deathMenuBackground.getWidth() / 4) - scoreWidth / 2) , 
            Main.FRAME_HEIGTH/2 - 3 * (IM.deathMenuBackground.getHeight() / 8) + 35);
        
        

    }
}
