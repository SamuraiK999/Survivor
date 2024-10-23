package core.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import core.Main;
import shapes.Rect;
import ui.Button;
import ui.PlayButton;
import ui.ReturnMenuButton;
import utility.EH;
import utility.IM;

/*
 * The menu when the player dies
 */
public class DeathMenu extends Menu {
    private int timer;

    public DeathMenu() {
        super();
    }


    @Override
    protected void initializeMenu(){
        buttons.add(
            new PlayButton(
                new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2, 
                    Main.FRAME_HEIGTH / 2 - 70, 
                    IM.playButton.getWidth(), 
                    IM.playButton.getHeight())));
        buttons.add(
            new ReturnMenuButton(
                new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2, 
                    Main.FRAME_HEIGTH / 2, 
                    IM.playButton.getWidth(), 
                    IM.playButton.getHeight())));    }

    @Override
    public void draw(Graphics g){
            Graphics2D g2d = (Graphics2D) g;
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGTH);
            super.draw(g);
    }
}
