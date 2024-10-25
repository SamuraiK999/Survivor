package ui;

import core.GameState;
import core.GameStateManager;
import core.states.Game;
import shapes.Rect;
import utility.IM;

public class ResumeButton extends Button {
    /**
     * Constructor.
     */
    public ResumeButton(Rect body) {
        super(IM.playButton, body);
    }

    @Override
    public void use() {
        super.use();
        Game.deathMenu = null;
        GameStateManager.setPausedState(false);
    }
}
