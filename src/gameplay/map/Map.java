package gameplay.map;

import core.states.Game;
import gameplay.entities.Player;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import utility.IM;
import utility.shapes.Rect;

/**
 * The map.
 */
public class Map {

    private Player player;

    private BufferedImage backgroundMap;
    private BufferedImage overlayMap;
    private ArrayList<Immovable> environment = new ArrayList<>();
    private ArrayList<BufferedImage> overlayObjects = new ArrayList<>();
    private ArrayList<Point> overlayObjectsCoordinates = new ArrayList<>();

    /**
     * Constructor.
     */
    public Map() {
        player = Game.getPlayer();

        backgroundMap = IM.backgroundMap;
        overlayMap = IM.overlayMap;
        overlayObjects = IM.overlayObjects;

        overlayObjectsCoordinates.add(
            translateCoordinates(
                new Point(
                    36 + overlayObjects.get(0).getWidth() / 2, 
                    641 + overlayObjects.get(0).getHeight() / 2)));

        overlayObjectsCoordinates.add(
            translateCoordinates(
                new Point(
                    41 + overlayObjects.get(1).getWidth() / 2, 
                    842 + overlayObjects.get(1).getHeight() / 2)));

        overlayObjectsCoordinates.add(
            translateCoordinates(
                new Point(
                    261 + overlayObjects.get(2).getWidth() / 2, 
                    512 + overlayObjects.get(2).getHeight() / 2)));

        try {
            loadRectsFromFile();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update all of the map's objects.
     */
    public void update() {
        for (Immovable e : environment) {
            e.update();
        }
    }

    /**
     * Draw the map and all its components.
     */
    public void draw(Graphics g) {
        IM.drawRotatedImage(g, backgroundMap,
                new Point((int) (Game.getCamera().x), (int) (Game.getCamera().y)),
                3, 3, 0);
    }

    /**
     * Draw the overlay of the map.
     */
    public void drawOverlay(Graphics g) {
        player = Game.getPlayer();

        IM.drawRotatedImage(g, overlayMap,
                new Point((int) (Game.getCamera().x), (int) (Game.getCamera().y)),
                3, 3, 0);
        for (int i = 0; i < overlayObjectsCoordinates.size(); i++) {
            if (player.getHitbox().y < overlayObjectsCoordinates.get(i).y - 30) {
                IM.drawRotatedImage(g, overlayObjects.get(i),
                        new Point(
                                (int) (overlayObjectsCoordinates.get(i).x + Game.getCamera().x),
                                (int) (overlayObjectsCoordinates.get(i).y + Game.getCamera().y)),
                        3, 3, 0);
            }
        }
    }

    /**
     * Loads the map's hitboxes into the objects array.
     */
    public void loadRectsFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("config/map.txt"));
        String line;

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");

            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            int width = Integer.parseInt(parts[2]);
            int height = Integer.parseInt(parts[3]);

            // multiply x and y by 3 to match the upscaling when drawing
            Rect rect = new Rect(
                    translateCoordinates(new Point(x, y)),
                    width * 3,
                    height * 3);

            environment.add(new Immovable(rect));
        }

        reader.close();
    }

    private Point translateCoordinates(Point p) {
        return new Point(
            (p.x - backgroundMap.getWidth() / 2) * 3, 
            (p.y - backgroundMap.getHeight() / 2) * 3); 
    }

    public ArrayList<Immovable> getEnvironment() {
        return environment;
    }

    public Point getBoundaties() {
        return new Point(backgroundMap.getWidth(), backgroundMap.getHeight());
    }

}
