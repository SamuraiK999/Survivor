package core;

import java.awt.*;
import javax.swing.*;
import utility.EH;

/**
 * Main.
 */
public class Main extends JPanel {

    public static JFrame frame = new JFrame("MAZNA");
    public static Game game;
    public static GameMenu menu = new GameMenu();
    public static GameState gameState = GameState.START_MENU;
    /**
     *  Constructor.
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

        setPreferredSize(new Dimension(1000, 800));

        frame.add(this);

        frame.pack();
        frame.setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        switch (gameState) {
            case START_MENU:
                menu.draw(g);
                break;
            case GAME:
                game.draw(g);
            default:
                break;
        }
        repaint();
    }

    public static void main(String[] args) {
        new Main();
    }
}
