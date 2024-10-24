package ui;

import shapes.Rect;
import utility.IM;

/**
 * Button that switches the game difficulty.
 */
public class DificultyButton extends Button {

    /**
     * Constructor.
     */
    public DificultyButton(Rect body) {
        super(IM.difficultyButton, body);
    }
       
    @Override
    public void use() {
        super.use();
        //Changes the dificulty
    }

}
