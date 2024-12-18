package core.states;

import core.GameStateManager;
import core.Main;
import core.enums.GameState;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import ui.Menu;
import ui.buttons.PlayButton;
import ui.buttons.QuitButton;
import ui.buttons.enums.ButtonSite;
import utility.IM;
import utility.shapes.Rect;

/**
 * The first menu the user sees.
 */
public class MainMenu extends Menu {

    BufferedImage title = IM.title;

    /**
     * Constructor.
     */
    public MainMenu() {
        super(
                ButtonSite.MAIN_MENU,
                IM.title,
                new Rect(
                        Main.FRAME_WIDTH / 2,
                        Main.FRAME_HEIGTH / 2,
                        Main.FRAME_WIDTH,
                        Main.FRAME_HEIGTH));
    }

    @Override
    public void init() {
        buttons.add(
                new PlayButton(
                        location,
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2 + 100,
                                IM.playButton.getWidth(),
                                IM.playButton.getHeight())));

        buttons.add(
                new QuitButton(
                        location,
                        new Rect(Main.FRAME_WIDTH / 2 - IM.quitButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2 + 170,
                                IM.quitButton.getWidth(),
                                IM.quitButton.getHeight())));
    }

    @Override
    public void update() {
        if (GameStateManager.getState() == GameState.MAIN_MENU) {
            super.update();
        }
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
    }
}
