package utility;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

/**
 * Image Manager. Contains all of the game's images.
 */
public class IM {
    
    public static ArrayList<BufferedImage> playerIdle = new ArrayList<>();
    public static ArrayList<BufferedImage> playerRunning = new ArrayList<>();
    public static ArrayList<BufferedImage> playerShooting = new ArrayList<>();
    public static ArrayList<BufferedImage> playerDying = new ArrayList<>();
    
    public static ArrayList<BufferedImage> enemyIdle = new ArrayList<>();
    public static ArrayList<BufferedImage> enemyWalking = new ArrayList<>();
    public static ArrayList<BufferedImage> enemyRunning = new ArrayList<>();
    public static ArrayList<BufferedImage> enemyAttacking = new ArrayList<>();
    public static ArrayList<BufferedImage> enemyDying = new ArrayList<>();

    // Pre-loading all images.
    static {
        try {
            // For the player:
            for (int i = 0; i < 7; i++) {
                playerIdle.add(
                    ImageIO.read(new File("img/player/idle/tile00" + i + ".png")));
            }
            for (int i = 0; i < 8; i++) {
                playerRunning.add(
                    ImageIO.read(new File("img/player/running/tile00" + i + ".png")));
            }
            for (int i = 0; i < 4; i++) {
                playerShooting.add(
                    ImageIO.read(new File("img/player/shooting/tile00" + i + ".png")));
            }
            for (int i = 0; i < 4; i++) {
                playerDying.add(
                    ImageIO.read(new File("img/player/dying/tile00" + i + ".png")));
            }

            // For a type 1 enemy:

            // For a type 2 enemy:

            // For a type 3 enemy:

        } catch (IOException e) {
            System.err.println("Error loading images: " + e.getMessage());
        }
    }

    /**
     * Draw an image rotated around its center.
     */
    public static void drawRotatedImage(Graphics g, 
                                        BufferedImage image, 
                                        int x, int y, int width, int height, 
                                        double rotationAngle) {

        Graphics2D g2d = (Graphics2D) g;

        double centerX = x + width / 2;
        double centerY = y + height / 2;

        g2d.rotate(rotationAngle, centerX, centerY);
        
        g2d.drawImage(image, x, y, width, height, null);

        g2d.rotate(-rotationAngle, centerX, centerY);

    }

    /**
     * Draw an image rotated around its center.
     */
    public static void drawRotatedImage(Graphics g, 
                                        BufferedImage image, 
                                        Point coordinates, 
                                        double scaleX,
                                        double scaleY,
                                        double rotationAngle) {

        Graphics2D g2d = (Graphics2D) g;

        double centerX = coordinates.x + image.getWidth() / 2;
        double centerY = coordinates.y + image.getHeight() / 2;

        g2d.rotate(rotationAngle, centerX, centerY);
        
        AffineTransform tx = AffineTransform.getTranslateInstance(
            coordinates.x - image.getWidth() * scaleX / 2, 
            coordinates.y - image.getHeight() * scaleY / 2
        );
        
        tx.scale(scaleX, scaleY);
        
        g2d.drawImage(image, tx, null);

        g2d.rotate(-rotationAngle, centerX, centerY);

    }
}
