package core;

import core.states.Game;
import core.states.MainMenu;
import utility.EH;

import java.awt.Graphics;

/**
 * Manages the states of the app.
 */
public class GameStateManager {

    private static GameStateManager instance;

    private static GameState currentGameState = GameState.MAIN_MENU;

    private static Game game;
    private static MainMenu mainMenu = new MainMenu();

    /**
     * Constructor.
     */
    private GameStateManager() {

    }

    /**
     * Update.
     */
    public void update() {
        switch (currentGameState) {
            case MAIN_MENU:
                mainMenu.update();
                break;

            case GAME:
                game.update();
                break;
        
            default:
                break;
        }
    }

    /**
     * Draw.
     */
    public void draw(Graphics g) {
        switch (currentGameState) {
            case MAIN_MENU:
                mainMenu.draw(g);
                break;

            case GAME:
                game.draw(g);
                break;
        
            default:
                break;
        }
    }

    /**
     * Getter for the instance.
     */
    public static GameStateManager getInstance() {
        if (instance == null) {
            instance = new GameStateManager();
        }
        return instance;
    }

    public static Game getGame() {
        return game;
    }

    public static void setGame(Game newGame) {
        game = newGame;
    }

    public static MainMenu getMainMenu() {
        return mainMenu;
    }

    public static GameState getState() {
        return currentGameState;
    }

    public static void setState(GameState newState) {
        EH.clearButtons();
        currentGameState = newState;
    }
}
