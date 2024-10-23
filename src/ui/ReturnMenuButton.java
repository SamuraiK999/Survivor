package ui;

import core.GameState;
import core.GameStateManager;
import shapes.Rect;
import utility.IM;

/**
 * A button that sends the user back to the main menu.
 */
public class ReturnMenuButton extends Button {

    public ReturnMenuButton(Rect body) {
        super(IM.returnMenuButton, body);
    }

    @Override
    public void use() {
        GameStateManager.setState(GameState.MAIN_MENU);
    }
}
