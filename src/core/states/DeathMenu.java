package core.states;

import core.Main;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import ui.Menu;
import ui.buttons.PlayButton;
import ui.buttons.ReturnMenuButton;
import ui.buttons.enums.ButtonSite;
import utility.IM;
import utility.shapes.Rect;

/**
 * The menu that the user sees when he dies.
 */
public class DeathMenu extends Menu {

    public DeathMenu() {
        super(ButtonSite.DEATH_MENU);
    }

    @Override
    protected void init() {
        buttons.add(
                new PlayButton(
                        location,
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2 - 70,
                                IM.playButton.getWidth(),
                                IM.playButton.getHeight())));
        buttons.add(
                new ReturnMenuButton(
                        location,
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2,
                                IM.playButton.getWidth(),
                                IM.playButton.getHeight())));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGTH);
        super.draw(g);
    }
}
