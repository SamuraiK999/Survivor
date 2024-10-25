package ui.buttons;

import core.GameStateManager;
import core.enums.GameState;
import core.states.Game;
import utility.IM;
import utility.shapes.Rect;

/**
 * Start game button.
 */
public class PlayButton extends Button {
    /**
     * Constructor.
     */
    public PlayButton(Rect body) {
        super(IM.playButton, body);
    }

    @Override
    public void use() {
        Game.deathMenu = null;
        GameStateManager.setGame(new Game());
        GameStateManager.setState(GameState.GAME);
    }
}
