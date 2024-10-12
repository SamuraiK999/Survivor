package shapes;

import java.awt.*;
import javax.swing.*;

/**
 * Class for the buttons in the game
 */

public class Button extends Rect {
    private String label;

    /**
     * Constuctor
     */

    public Button(String label, float x, float y, float width , float height) {
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
