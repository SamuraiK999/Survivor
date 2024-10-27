package gameplay.map;

import core.states.Game;
import gameplay.Camera;
import gameplay.entities.Player;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Point2D;
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

    private static WeightedUndirectedGraph graph = new WeightedUndirectedGraph(17);

    private Player player;

    private static BufferedImage backgroundMap;
    private BufferedImage overlayMap;
    
    private static ArrayList<Immovable> environment = new ArrayList<>();
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

        initGraph();

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
                new Point((int) (Camera.getCoordinates().x), (int) (Camera.getCoordinates().y)),
                IM.UI_SCALE, IM.UI_SCALE, 0);
    }

    /**
     * Draw the overlay of the map.
     */
    public void drawOverlay(Graphics g) {
        player = Game.getPlayer();

        IM.drawRotatedImage(g, overlayMap,
                new Point((int) (Camera.getCoordinates().x), (int) (Camera.getCoordinates().y)),
                IM.UI_SCALE, IM.UI_SCALE, 0);
        for (int i = 0; i < overlayObjectsCoordinates.size(); i++) {
            if (player.getHitbox().y < overlayObjectsCoordinates.get(i).y - 30) {
                IM.drawRotatedImage(g, overlayObjects.get(i),
                        new Point(
                                (int) (overlayObjectsCoordinates.get(i).x
                                        + Camera.getCoordinates().x),
                                (int) (overlayObjectsCoordinates.get(i).y
                                        + Camera.getCoordinates().y)),
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
                    width * IM.UI_SCALE,
                    height * IM.UI_SCALE);

            environment.add(new Immovable(rect));
        }

        reader.close();
    }

    /**
     * Translate coordinates according to the map.
     */
    public static Point translateCoordinates(Point p) {
        return new Point(
                (p.x - backgroundMap.getWidth() / 2) * IM.UI_SCALE,
                (p.y - backgroundMap.getHeight() / 2) * IM.UI_SCALE);
    }

    /**
     * Same, but for Point2D.Float
     */
    public static Point2D.Float translateCoordinates(Point2D.Float p) {
        return new Point2D.Float(
                (p.x - backgroundMap.getWidth() / 2) * IM.UI_SCALE,
                (p.y - backgroundMap.getHeight() / 2) * IM.UI_SCALE);
    }

    public static ArrayList<Immovable> getEnvironment() {
        return environment;
    }

    public static Point getBoundaties() {
        return new Point(backgroundMap.getWidth(), backgroundMap.getHeight());
    }

    public static WeightedUndirectedGraph getGraph() {
        return graph;
    }

    // Sadly unused since we created it too late, otherwise it would improve 
    // both the code and the setting of the map's hitboxes in the map.txt file
    /*private void addImmovable(Rect object) {
        object = new Rect(
                translateCoordinates(new Point((int) object.x * 32, (int) object.y * 32)),
                object.width * 32 * IM.UI_SCALE,
                object.height * 32 * IM.UI_SCALE);
        environment.add(new Immovable(object));
    }*/

    private void initGraph() {
        graph.setVertex(0, new Point2D.Float(8, 7));
        graph.setVertex(1, new Point2D.Float(10, 7));
        graph.setVertex(2, new Point2D.Float(15, 12));
        graph.setVertex(3, new Point2D.Float(17, 12));
        graph.setVertex(4, new Point2D.Float(21, 12));
        graph.setVertex(5, new Point2D.Float(23, 12));
        graph.setVertex(6, new Point2D.Float(23, 15.5f));
        graph.setVertex(7, new Point2D.Float(23, 19));
        graph.setVertex(8, new Point2D.Float(21, 19));
        graph.setVertex(9, new Point2D.Float(17, 19));
        graph.setVertex(10, new Point2D.Float(15, 19));
        graph.setVertex(11, new Point2D.Float(10, 19.5f));
        graph.setVertex(12, new Point2D.Float(8, 19.5f));
        graph.setVertex(13, new Point2D.Float(10, 13.5f));
        graph.setVertex(14, new Point2D.Float(15, 15.5f));
        graph.setVertex(15, new Point2D.Float(17, 15.5f));
        graph.setVertex(16, new Point2D.Float(21, 15.5f));

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 5);
        graph.addEdge(1, 10);
        graph.addEdge(1, 13);
        graph.addEdge(1, 14);
        graph.addEdge(2, 3);
        graph.addEdge(2, 11);
        graph.addEdge(2, 13);
        graph.addEdge(2, 14);
        graph.addEdge(3, 4);
        graph.addEdge(3, 8);
        graph.addEdge(3, 15);
        graph.addEdge(3, 16);
        graph.addEdge(4, 5);
        graph.addEdge(4, 9);
        graph.addEdge(4, 15);
        graph.addEdge(4, 16);
        graph.addEdge(4, 16);
        graph.addEdge(5, 6);
        graph.addEdge(5, 6);
        graph.addEdge(6, 7);
        graph.addEdge(7, 8);
        graph.addEdge(8, 9);
        graph.addEdge(8, 15);
        graph.addEdge(8, 16);
        graph.addEdge(9, 10);
        graph.addEdge(9, 15);
        graph.addEdge(9, 16);
        graph.addEdge(10, 11);
        graph.addEdge(10, 13);
        graph.addEdge(10, 14);
        graph.addEdge(11, 12);
        graph.addEdge(11, 13);
        graph.addEdge(11, 14);
        graph.addEdge(13, 14);
        graph.addEdge(15, 16);
    }

}
