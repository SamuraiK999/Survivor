package utility;

import core.GameStateManager;
import core.Main;
import core.states.Game;
import core.states.PauseMenu;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;
import ui.Button;
import ui.buttons.enums.ButtonSite;

/**
 * EventHandler
 * Abbreviated as EH for beter readability and easier usage
 * Handles game updates and input.
 */
public class EH implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    private static PauseMenu pauseMenu;
    private static int tick = 0;
    private Timer timer = new Timer(10, this);
    private static EH instance;
    private static boolean[] isPressed = new boolean[256];
    private static Point mouseCoordinates = new Point(0, 0);
    private static boolean isMousePressed;

    // basically listeners for the callbacks
    private static List<Button> buttons = new ArrayList<>();

    /**
     * Constructor.
     * Start updating and get reference to the game object
     */
    private EH() {
        timer.start();
    }

    /**
     * Used obly inside the Button constructor i.e. don't use.
     */
    public static void addButton(Button button) {
        buttons.add(button);
    }

    public static void setPauseMenu(PauseMenu pm) {
        pauseMenu = pm;
    }

    public static boolean isKeyPressed(int keyCode) {
        return isPressed[keyCode];
    }

    public static boolean isMousePressed() {
        return isMousePressed;
    }

    public static Point getMouseCoordinates() {
        return mouseCoordinates;
    }

    /**
     * Returns the mouse coordinates relative to the camera.
     */
    public static Point getMouseCoordinatesRelative() {
        return new Point(mouseCoordinates.x - Main.frame.getWidth() / 2,
                mouseCoordinates.y - Main.frame.getHeight() / 2);
    }

    /**
     * Used to access the instance.
     */
    public static EH getInstance() {
        if (instance == null) {
            instance = new EH();
        }
        return instance;
    }

    public static int getTick() {
        return tick;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // leave empty
    }

    @Override
    public void keyPressed(KeyEvent e) {
        isPressed[e.getKeyCode()] = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        isPressed[e.getKeyCode()] = false;
        if (pauseMenu != null) {
            pauseMenu.onKeyReleased(e.getKeyCode());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GameStateManager.getInstance().update();
        tick++;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mouseCoordinates.x = e.getX();
        mouseCoordinates.y = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mouseCoordinates.x = e.getX();
        mouseCoordinates.y = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;

        if (!buttons.isEmpty()) {
            switch (GameStateManager.getState()) {
                case MAIN_MENU:
                    for (Button b : buttons) {
                        if (b.getLocation() == ButtonSite.MAIN_MENU) {
                            b.onMouseReleased();
                        }
                    }
                    break;

                case GAME:
                    for (Button b : buttons) {
                        if (Game.getPauseState()) {
                            if (b.getLocation() == ButtonSite.PAUSE_MENU) {
                                b.onMouseReleased();
                            }
                        }
                        if (!Game.getPlayerState()) {
                            if (b.getLocation() == ButtonSite.DEATH_MENU) {
                                b.onMouseReleased();
                            }
                        }
                    }
                    break;

                default:
                    break;
            }
        }

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Auto-generated method stub
    }
}
