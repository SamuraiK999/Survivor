package gameplay.map;

import core.states.Game;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import shapes.Rect;
import utility.IM;

/**
 * The map.
 */
public class Map {

    private BufferedImage backgroundMap;
    private BufferedImage overlayMap;
    private ArrayList<Immovable> environment = new ArrayList<>();

    /**
     * Constructor.
     */
    public Map() {
        backgroundMap = IM.backgroundMap;
        overlayMap = IM.overlayMap;
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
        for (Immovable e : environment) {
            e.draw(g);
        }
    }

    /**
     * Draw the overlay of the map.
     */
    public void drawOverlay(Graphics g) {
        IM.drawRotatedImage(g, overlayMap,
                new Point((int) (Game.getCamera().x), (int) (Game.getCamera().y)),
                3, 3, 0);
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
            Rect rect = new Rect((x - 480) * 3, (y - 493) * 3, width * 3, height * 3);
            environment.add(new Immovable(rect));
        }

        reader.close();
    }

    public ArrayList<Immovable> getEnvironment() {
        return environment;
    }

}
