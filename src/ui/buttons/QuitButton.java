package ui.buttons;

import utility.IM;
import utility.shapes.Rect;

/**
 * Exit app button.
 */
public class QuitButton extends Button {

    /**
     * Constructor.
     */
    public QuitButton(Rect body) {
        super(IM.quitButton, body);
    }

    @Override
    public void use() {
        System.exit(0);
    }
}