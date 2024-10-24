package ui;

import shapes.Rect;
import utility.IM;

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
        super.use();
        System.exit(0);
    }
}