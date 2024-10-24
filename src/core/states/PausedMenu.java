package core.states;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import core.GameStateManager;
import core.Main;
import shapes.Rect;
import ui.Menu;
import ui.ResumeButton;
import ui.ReturnMenuButton;
import utility.EH;
import utility.IM;

public class PausedMenu extends Menu {
    
    
    public PausedMenu(){
        super();
    }
    
    @Override
    public void init(){
        EH.setPauseMenu(this);
        buttons.add(
                new ResumeButton(
                    new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2 -70,
                                IM.playButton.getWidth(),
                                IM.playButton.getHeight())));
        
        buttons.add(
                new ReturnMenuButton(
                        new Rect(Main.FRAME_WIDTH / 2 - IM.playButton.getWidth() / 2,
                                Main.FRAME_HEIGTH / 2,
                                IM.playButton.getWidth(),
                                IM.playButton.getHeight())));
    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.20f));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGTH);
        super.draw(g);
    }

    @Override
    public void update(){
        super.update();
    }

    /**
     * Called a key when a key release.
     * @param keyCode
     * 
     */
    public void OnKeyReleased(int keyCode) {
        if(keyCode == KeyEvent.VK_P || keyCode == KeyEvent.VK_ESCAPE){
            GameStateManager.setPausedState(!GameStateManager.getPauseState());
            System.out.println("kur\n\n\n\n");
        }
    }
}
