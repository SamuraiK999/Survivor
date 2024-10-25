package ui.buttons;

import ui.Button;
import ui.buttons.enums.ButtonSite;
import utility.IM;
import utility.shapes.Rect;

/**
 * Button that switches the game difficulty.
 */
public class DificultyButton extends Button {

    /**
     * Constructor.
     */
    public DificultyButton(ButtonSite location, Rect body) {
        super(location, IM.difficultyButton, body);
    }
       
    @Override
    public void use() {
        //Changes the dificulty
    }

}
