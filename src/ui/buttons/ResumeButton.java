package ui.buttons;

import core.states.Game;
import ui.Button;
import ui.buttons.enums.ButtonSite;
import utility.IM;
import utility.shapes.Rect;

/**
 * The Resume button in the pause menu.
 */
public class ResumeButton extends Button {

    /**
     * Constructor.
     */
    public ResumeButton(ButtonSite location, Rect body) {
        super(location, IM.playButton, body);
    }

    @Override
    public void use() {
        Game.setPlayerState(true);
        Game.setPauseState(false);
    }
}
