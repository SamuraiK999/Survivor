package utility;

import core.Game;
import core.Main;
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
import shapes.Button;

/**
 * EventHandler
 * Abbreviated as EH for beter readability and easier usage
 * Handles game updates and input.
 */
public class EH implements ActionListener, KeyListener, MouseListener, MouseMotionListener {

    private Timer timer = new Timer(10, this);
    private static EH instance;
    private static boolean[] isPressed = new boolean[256];
    private static Point mouseCoordinates = new Point(0, 0);
    private static boolean isMousePressed;
    private static Game game;

    // basically listeners for the callbacks
    private static List<Button> buttons = new ArrayList<>();

    /**
     * Constructor.
     * Start updating and get reference to the game object
     */
    private EH() {
        timer.start();
    }

    public void addButton(Button button) {
        buttons.add(button);
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

    public static void setGameReference(Game g) {
        game = g;
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
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        game.update();
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
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        isMousePressed = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        isMousePressed = false;
        if (buttons.size() > 0) {
            for (Button b : buttons) {
                b.onMouseReleased();
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}
