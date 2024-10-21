package shapes;

import core.Game;

public class ExitButton extends Button {

    
    public ExitButton(){
        super("EXIT", new Rect(300, 350, 200, 50));   
    }

    @Override
    public void use(){
        System.exit(0);
    }
}