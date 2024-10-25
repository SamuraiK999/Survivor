package ui;

import core.Main;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import ui.buttons.enums.ButtonSite;
import utility.shapes.Rect;

/**
 * The main class from which all menus derive.
 */
public abstract class Menu {
    public ArrayList<Button> buttons = new ArrayList<>();

    protected ButtonSite location;

    private BufferedImage backgroundImage;
    private Rect backgroundRect;

    /**
     * Constructor.
     */
    public Menu(ButtonSite location, BufferedImage backgroundImage, Rect backgroundRect) {
        this.location = location;
        this.backgroundImage = backgroundImage;
        this.backgroundRect = backgroundRect;
        init();
    }

    protected void init() {

    }

    /**
     * Update.
     */
    public void update() {
        for (Button b : buttons) {
            b.update();
        }
    }

    /**
     * Draw.
     */
    public void draw(Graphics g) {
        g.drawImage(
                backgroundImage,
                backgroundRect.toDrawCentered().x,
                backgroundRect.toDrawCentered().y,
                (int) backgroundRect.width,
                (int) backgroundRect.height,
                null);
                
        for (Button b : buttons) {
            b.draw(g);
        }
    }

    /**
     * Tint the background.
     */
    protected void tintBackground(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, Main.FRAME_WIDTH, Main.FRAME_HEIGTH);
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1));
    }
}
