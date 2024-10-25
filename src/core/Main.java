package core;

import java.awt.*;
import java.awt.geom.AffineTransform;
import javax.swing.*;
import utility.EH;

/**
 * Main.
 */
public class Main extends JPanel {

    public static JFrame frame = new JFrame("MAZNA");
    public static int FRAME_WIDTH = 1920;
    public static int FRAME_HEIGTH = 1080;
    public static double scaleX;
    public static double scaleY;

    /**
     * Constructor.
     */
    public Main() {
        setUpWindow();
        setFocusable(true);
        addKeyListener(EH.getInstance());
        addMouseListener(EH.getInstance());
        addMouseMotionListener(EH.getInstance());
    }

    /**
     * Setting up the game window using swing.
     */
    public void setUpWindow() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        GraphicsConfiguration gc = gd.getDefaultConfiguration();
        AffineTransform at = gc.getDefaultTransform();

        scaleX = at.getScaleX();
        scaleY = at.getScaleY();

        // Adjust the size of the window according to the DPI scaling factor
        FRAME_WIDTH = (int) (1920 / scaleX);
        FRAME_HEIGTH = (int) (1080 / scaleY);

        frame.setUndecorated(true);
        setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGTH));

        frame.add(this);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        GameStateManager.getInstance().draw(g);

        repaint();
    }

    public static void main(String[] args) {
        new Main();
    }
}
