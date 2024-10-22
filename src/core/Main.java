package core;

import java.awt.*;
import javax.swing.*;
import utility.EH;

/**
 * Main.
 */
public class Main extends JPanel {

    public static JFrame frame = new JFrame("MAZNA");
    public static final int FRAME_WIDTH = 1920;
    public static final int FRAME_HEIGTH = 1080;

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

        GraphicsDevice gd = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice();

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
