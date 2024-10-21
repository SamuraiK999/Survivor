package shapes;

import core.Game;
import core.GameState;
import core.Main;
import utility.EH;

public class StartButton extends Button {
    Rect body;
    Game game;
    
    public StartButton(){
        super("START" , new Rect(300, 150, 200, 50) );
    }

    @Override
    public void use(){
        Main.game = new Game();
        Main.gameState = GameState.GAME;
    }
}
