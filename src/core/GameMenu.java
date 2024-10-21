package core;

import java.awt.Graphics;
import java.util.ArrayList;

import shapes.Rect;
import shapes.StartButton;
import shapes.Button;
import shapes.DificultyButton;
import shapes.ExitButton;

public class GameMenu {
    public static ArrayList<Button> buttons = new ArrayList<>();

    public GameMenu(){
        initializeMenu();
    }

    private void initializeMenu() {
        buttons.add(new StartButton());
        buttons.add(new DificultyButton());
        buttons.add(new ExitButton());
    }

    public void update(){
        for (Button index: buttons){
            index.update();;
        }
    }

    public void draw(Graphics g){
        for (Button index: buttons){
            index.draw(g);
        }
    }
}
