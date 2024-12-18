package core.states;

import core.Main;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import ui.Menu;
import ui.buttons.MainMenuButton;
import ui.buttons.ResumeButton;
import ui.buttons.enums.ButtonSite;
import utility.EH;
import utility.IM;
import utility.shapes.Rect;

/**
 * The pause menu.
 */
public class PauseMenu extends Menu {

    /**
     * Constructor.
     */
    public PauseMenu() {
        super(
            ButtonSite.PAUSE_MENU, 
            IM.pauseMenuBackground, 
            new Rect(
                Main.FRAME_WIDTH / 2, 
                Main.FRAME_HEIGTH / 2, 
                IM.pauseMenuBackground.getWidth() * 2, 
                IM.pauseMenuBackground.getHeight() * 2));
    }

    @Override
    public void init() {
        EH.setPauseMenu(this);
        buttons.add(
                new ResumeButton(
                        location,
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2 - 70,
                                IM.resumeButton.getWidth(),
                                IM.resumeButton.getHeight())));

        buttons.add(
                new MainMenuButton(
                        location,
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2,
                                IM.mainMenuButtonPause.getWidth(),
                                IM.mainMenuButtonPause.getHeight())));
    }

    @Override
    public void draw(Graphics g) {
        tintBackground(g);
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
        }
    }
}
