package core;

import core.enums.GameState;
import core.states.Game;
import core.states.MainMenu;
import core.states.PausedMenu;

import java.awt.Graphics;
import utility.EH;

/**
 * Manages the states of the app.
 */
public class GameStateManager {

    private static GameStateManager instance;
    private static boolean pausedGame = true;

    private static GameState currentGameState = GameState.MAIN_MENU;

    private static Game game;
    private static MainMenu mainMenu = new MainMenu();
    private static PausedMenu pausedMenu = new PausedMenu();

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
                if(!pausedGame){
                    game.update();
                } else {
                    pausedMenu.update();
                }
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
                if (pausedGame) {
                    pausedMenu.draw(g);
                }
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

    /**
     * Switch the current game state.
     */
    public static void setState(GameState newState) {
        EH.clearButtons();
        currentGameState = newState;

        pausedGame = true;

        switch (currentGameState) {
            case MAIN_MENU:
                mainMenu.init();
                break;

            case GAME:
                pausedGame = false;
                game.init();
                pausedMenu.init();
                break;
        
            default:
                break;
        }
    }


    /*
     * This sets the game to be paused
     */
    public static void setPausedState(boolean state){
        pausedGame = state;
    }

    /*
     * This return the if the game is paused or not
     */
    public static boolean getPauseState(){
        return pausedGame;
    }
}
