package ui.buttons;

import ui.Button;
import ui.buttons.enums.ButtonSite;
import utility.IM;
import utility.shapes.Rect;

/**
 * Exit app button.
 */
public class QuitButton extends Button {

    /**
     * Constructor.
     */
    public QuitButton(ButtonSite location, Rect body) {
        super(location, IM.quitButton, body);
    }

    @Override
    public void use() {
        System.exit(0);
    }
}