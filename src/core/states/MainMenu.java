package core.states;

import core.Main;
import java.awt.Graphics;
import java.util.ArrayList;
import shapes.Rect;
import ui.Button;
import ui.DificultyButton;
import ui.PlayButton;
import ui.QuitButton;
import utility.IM;

/**
 * The first menu the user sees.
 */
public class MainMenu {
    public static ArrayList<Button> buttons = new ArrayList<>();

    public MainMenu() {
        initializeMenu();
    }

    private void initializeMenu() {
        buttons.add(
            new PlayButton(
                new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2, 
                    Main.FRAME_HEIGTH / 2 - 70, 
                    IM.playButton.getWidth(), 
                    IM.playButton.getHeight())));
        
        buttons.add(
            new DificultyButton(
                new Rect(Main.FRAME_WIDTH / 2 - IM.difficultyButton.getWidth() / 2, 
                    Main.FRAME_HEIGTH / 2, 
                    IM.difficultyButton.getWidth(), 
                    IM.difficultyButton.getHeight())));
        
        buttons.add(
            new QuitButton(
                new Rect(Main.FRAME_WIDTH / 2 - IM.quitButton.getWidth() / 2, 
                    Main.FRAME_HEIGTH / 2 + 70, 
                    IM.quitButton.getWidth(), 
                    IM.quitButton.getHeight())));
    }

    /**
     * Update.
     */
    public void update() {
        for (Button b : buttons) {
            b.update();
        }
    }

    /**
     * Draw.
     */
    public void draw(Graphics g) {
        for (Button b : buttons) {
            b.draw(g);
        }
    }
}
