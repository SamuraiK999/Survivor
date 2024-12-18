package ui.buttons;

import core.GameStateManager;
import core.enums.GameState;
import core.states.Game;
import ui.Button;
import ui.buttons.enums.ButtonSite;
import utility.IM;
import utility.shapes.Rect;

/**
 * Start game button.
 */
public class PlayButton extends Button {
    /**
     * Constructor.
     */
    public PlayButton(ButtonSite location, Rect body) {
        super(location, IM.playButton, body);
    }

    @Override
    public void use() {
        Game.setPlayerState(true);
        GameStateManager.setGame(new Game());
        GameStateManager.setState(GameState.GAME);
    }
}
