package core.states;

import core.GameStateManager;
import core.Main;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import ui.Menu;
import ui.buttons.ResumeButton;
import ui.buttons.ReturnMenuButton;
import ui.buttons.enums.ButtonSite;
import utility.EH;
import utility.IM;
import utility.shapes.Rect;

/**
 * The pause menu.
 */
public class PauseMenu extends Menu {

    public PauseMenu() {
        super(ButtonSite.PAUSE_MENU);
    }

    @Override
    public void init() {
        EH.setPauseMenu(this);
        buttons.add(
                new ResumeButton(
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
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.20f));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGTH);
        super.draw(g);
    }

    @Override
    public void update() {
        super.update();
    }

    /**
     * Called a key when a key release.
     * 
     * @param keyCode
     * 
     */
    public void onKeyReleased(int keyCode) {
        if (keyCode == KeyEvent.VK_P || keyCode == KeyEvent.VK_ESCAPE) {
            Game.setPauseState(!Game.getPauseState());
            System.out.println("kur\n\n\n\n");
        }
    }
}
