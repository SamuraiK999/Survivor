package utility;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Contains all of the game's images.
 */
public class ImageManager {
    
    private static BufferedImage m4Image;
    private static BufferedImage knifeImage;

    static {
        /*try {
            //pre-loading all the images

            m4Image = ImageIO.read(new File("m4.png"));
            knifeImage = ImageIO.read(new File("knife.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    /**
     * Draw an image rotated around a point.
     */
    public static void drawRotatedImage(Graphics g,
                                        BufferedImage image, 
                                        int x, int y, int width, int height, 
                                        double rotationAngle, 
                                        double rotationPointX, double rotationPointY) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.rotate(rotationAngle, rotationPointX, rotationPointY);

        g2d.drawImage(image, x, y, width, height, null);

        g2d.rotate(-rotationAngle, rotationPointX, rotationPointY);

    }

    /**
     * Draw an image rotated around a point using it's default width and height.
     */
    public static void drawRotatedImage(Graphics g, 
                                        BufferedImage image, 
                                        int x, int y, 
                                        double rotationAngle, 
                                        double rotationPointX, double rotationPointY) {

        Graphics2D g2d = (Graphics2D) g;

        g2d.rotate(rotationAngle, rotationPointX, rotationPointY);

        g2d.drawImage(image, x, y, null);

        g2d.rotate(-rotationAngle, rotationPointX, rotationPointY);

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
    
}
