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
    
    public static ArrayList<ArrayList<BufferedImage>> enemyIdle = new ArrayList<>();
    public static ArrayList<ArrayList<BufferedImage>> enemyWalking = new ArrayList<>();
    public static ArrayList<ArrayList<BufferedImage>> enemyRunning = new ArrayList<>();
    public static ArrayList<ArrayList<BufferedImage>> enemyAttacking = new ArrayList<>();
    public static ArrayList<ArrayList<BufferedImage>> enemyDying = new ArrayList<>();

    public static BufferedImage playButton;
    public static BufferedImage difficultyButton;
    public static BufferedImage quitButton;
    public static BufferedImage background;
    public static BufferedImage returnmenuButton;

    public static BufferedImage backgroundMap;
    public static BufferedImage overlayMap;

    private static String playerPath = "img/game/entities/player/";
    private static String enemyPath = "img/game/entities/enemy/";

    // Pre-loading all images.
    static {
        try {
            // For the player:
            for (int i = 0; i < 7; i++) {
                playerIdle.add(
                    ImageIO.read(
                        new File(playerPath + "idle/tile00" + i + ".png")));
            }
            for (int i = 0; i < 8; i++) {
                playerRunning.add(
                    ImageIO.read(
                        new File(playerPath + "running/tile00" + i + ".png")));
            }
            for (int i = 0; i < 2; i++) {
                playerShooting.add(
                    ImageIO.read(
                        new File(playerPath + "shooting/tile00" + i + ".png")));
            }
            for (int i = 0; i < 4; i++) {
                playerDying.add(
                    ImageIO.read(
                        new File(playerPath + "dying/tile00" + i + ".png")));
            }

            for (int i = 0; i < 3; i++) {
                enemyIdle.add(new ArrayList<>());
                enemyRunning.add(new ArrayList<>());
                enemyAttacking.add(new ArrayList<>());
                enemyDying.add(new ArrayList<>());
            }
            
            // For a type 1 enemy:
            for (int i = 0; i < 9; i++) {
                enemyIdle.get(0).add(
                    ImageIO.read(
                        new File(enemyPath + "type1/idle/tile00" + i + ".png")));
            }
            for (int i = 0; i < 8; i++) {
                enemyRunning.get(0).add(
                    ImageIO.read(
                        new File(enemyPath + "type1/running/tile00" + i + ".png")));
            }
            for (int i = 0; i < 12; i++) {
                enemyAttacking.get(0).add(
                    ImageIO.read(
                        new File(enemyPath + "type1/attacking/tile00" + i + ".png")));
            }
            for (int i = 0; i < 5; i++) {
                enemyDying.get(0).add(
                    ImageIO.read(
                        new File(enemyPath + "type1/dying/tile00" + i + ".png")));
            }

            // For a type 2 enemy:
            for (int i = 0; i < 8; i++) {
                enemyIdle.get(1).add(
                    ImageIO.read(
                        new File(enemyPath + "type2/idle/tile00" + i + ".png")));
            }
            for (int i = 0; i < 7; i++) {
                enemyRunning.get(1).add(
                    ImageIO.read(
                        new File(enemyPath + "type2/running/tile00" + i + ".png")));
            }
            for (int i = 0; i < 14; i++) {
                enemyAttacking.get(1).add(
                    ImageIO.read(
                        new File(enemyPath + "type2/attacking/tile00" + i + ".png")));
            }
            for (int i = 0; i < 5; i++) {
                enemyDying.get(1).add(
                    ImageIO.read(
                        new File(enemyPath + "type2/dying/tile00" + i + ".png")));
            }

            // For a type 3 enemy:
            for (int i = 0; i < 5; i++) {
                enemyIdle.get(2).add(
                    ImageIO.read(
                        new File(enemyPath + "type3/idle/tile00" + i + ".png")));
            }
            for (int i = 0; i < 7; i++) {
                enemyRunning.get(2).add(
                    ImageIO.read(
                        new File(enemyPath + "type3/running/tile00" + i + ".png")));
            }
            for (int i = 0; i < 12; i++) {
                enemyAttacking.get(2).add(
                    ImageIO.read(
                        new File(enemyPath + "type3/attacking/tile00" + i + ".png")));
            }
            for (int i = 0; i < 5; i++) {
                enemyDying.get(2).add(
                    ImageIO.read(
                        new File(enemyPath + "type3/dying/tile00" + i + ".png")));
            }

            // For the map:
            backgroundMap = ImageIO.read(new File("img/game/map/map.png"));
            overlayMap = ImageIO.read(new File("img/game/map/map overlay.png"));


            // For the ui:

            // For the play button:
            playButton = ImageIO.read(new File("img/ui/buttons/playButton.png"));

            // For the difficulty button:
            difficultyButton = ImageIO.read(new File("img/ui/buttons/difficultyButton.png"));

            // For the quit button:
            quitButton = ImageIO.read(new File("img/ui/buttons/quitButton.png"));

            //For the return menu Button
            returnmenuButton = ImageIO.read(new File("img/ui/buttons/returnMenuButton.png"));

            // For the background
            background = ImageIO.read(new File("img/ui/background.png"));

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
