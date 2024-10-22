package ui;

import core.GameState;
import core.GameStateManager;
import core.states.Game;
import shapes.Rect;
import utility.IM;

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
        GameStateManager.setGame(new Game());
        GameStateManager.setState(GameState.GAME);
    }
}
