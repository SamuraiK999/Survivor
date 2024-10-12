package shapes;

import java.awt.*;
import javax.swing.*;

/**
 * Class for the buttons in the game
 */

public class Button1 extends Rect {
    private String label;

    /**
     * Constuctor
     */

    public Button1(String label, float x, float y, float width , float height) {
        super(x, y, width, height);
        this.label = label;
    }

    @Override
    public void draw (Graphics g) {
        super.draw(g);
        g.setColor(Color.white);
        g.drawString(
            label, (int) super.getCenterredCordinates().x,
                   (int) super.getCenterredCordinates().y);
    }

    private void use() {

    }

    public void update() {
        
    }
}
