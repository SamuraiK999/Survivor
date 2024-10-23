package core.states;

import core.GameState;
import core.GameStateManager;
import core.Main;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import shapes.Rect;
import ui.DificultyButton;
import ui.Menu;
import ui.PlayButton;
import ui.QuitButton;
import utility.IM;

/**
 * The first menu the user sees.
 */
public class MainMenu extends Menu {

    BufferedImage mainMenuBackground = IM.mainMenuBackground;

    public MainMenu() {
        super();
    }

    @Override
    public void init() {
        buttons.add(
                new PlayButton(
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2 - 70,
                                IM.playButton.getWidth(),
                                IM.playButton.getHeight())));

        buttons.add(
                new DificultyButton(
                        new Rect(Main.FRAME_WIDTH / 2 - IM.difficultyButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2,
                                IM.difficultyButton.getWidth(),
                                IM.difficultyButton.getHeight())));

        buttons.add(
                new QuitButton(
                        new Rect(Main.FRAME_WIDTH / 2 - IM.quitButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2 + 70,
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
        g.drawImage(mainMenuBackground, 0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGTH, null);
        super.draw(g);
    }
}
