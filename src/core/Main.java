package core;

import java.awt.*;
import javax.swing.*;
import utility.EH;

/**
 * Main.
 */
public class Main extends JPanel {

    public static JFrame frame = new JFrame("MAZNA");
    Game game = new Game();

    /**
     *  Constructor.
     */
    public Main() {
        setUpWindow();
        setFocusable(true);
        EH.setGameReference(game);
        addKeyListener(EH.getInstance());
        addMouseListener(EH.getInstance());
        addMouseMotionListener(EH.getInstance());
    }

    /**
     * Setting up the game window using swing.
     */
    public void setUpWindow() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setPreferredSize(new Dimension(800, 600));

        frame.add(this);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        game.draw(g);
        repaint();
    }

    public static void main(String[] args) {
        new Main();
    }
}
