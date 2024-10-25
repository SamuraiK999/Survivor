package ui.buttons;

import core.GameStateManager;
import core.enums.GameState;
import ui.Button;
import ui.buttons.enums.ButtonSite;
import utility.IM;
import utility.shapes.Rect;

/**
 * A button that sends the user back to the main menu.
 */
public class ReturnMenuButton extends Button {

    public ReturnMenuButton(ButtonSite location, Rect body) {
        super(location, IM.returnMenuButton, body);
    }

    @Override
    public void use() {
        GameStateManager.setState(GameState.MAIN_MENU);
    }
}
