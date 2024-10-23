package core.states;

import core.Main;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import shapes.Rect;
import ui.Menu;
import ui.PlayButton;
import ui.ReturnMenuButton;
import utility.IM;

/**
 * The menu that the user sees when he dies.
 */
public class DeathMenu extends Menu {

    public DeathMenu() {
        super();
    }

    @Override
    protected void init() {
        buttons.add(
                new PlayButton(
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2 - 70,
                                IM.playButton.getWidth(),
                                IM.playButton.getHeight())));
        buttons.add(
                new ReturnMenuButton(
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
