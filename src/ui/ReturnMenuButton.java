package ui;

import core.GameState;
import core.GameStateManager;
import core.Main;
import shapes.Rect;
import utility.IM;

public class ReturnMenuButton extends Button {
    
    public ReturnMenuButton(Rect body){
        super(IM.returnmenuButton, body);
    }

    @Override 
    public void use(){
        GameStateManager.setState(GameState.MAIN_MENU);;
    }
}
