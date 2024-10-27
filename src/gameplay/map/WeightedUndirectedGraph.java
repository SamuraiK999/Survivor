package gameplay.map;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import utility.Engine;
import utility.IM;
import utility.shapes.Circle;
import utility.shapes.Rect;

/**
 * An undirected graph.
 */
public class WeightedUndirectedGraph {
    // We use adjacency matrix since in our case the graph won't change
    private float[][] adjMatrix;
    private Circle[] vertices;

    /**
     * Constructor.
     * 
     * @param amountOfVertices is the amount of vertices that the graph will have.
     */
    public WeightedUndirectedGraph(int amountOfVertices) {
        // Initializing the graph.
        adjMatrix = new float[amountOfVertices][amountOfVertices];
        vertices = new Circle[amountOfVertices];

        for (int i = 0; i < amountOfVertices; i++) {
            for (int j = 0; j < amountOfVertices; j++) {
                // At the beginning we set every edge of the graph to positive infinity
                // which technically means that no vertices are connected.
                adjMatrix[i][j] = Float.POSITIVE_INFINITY;
            }
        }
    }

    /**
     * Draw the graph's vertices on the map.
     */
    public void draw(Graphics g) {
        for (Circle v : vertices) {
            if (v != null) {
                g.fillOval(
                        (int) v.getRelative().x,
                        (int) v.getRelative().y,
                        (int) v.r * 2,
                        (int) v.r * 2);
            }
        }
    }

    /**
     * Set the possition of a vertex on the map.
     * 
     * @param ind - the index of the vertex
     * @param v   - the position of a vertex on the grid
     */
    public void setVertex(int ind, Point2D.Float v) {
        // Multiply the coordinates by the vertex's diameter.
        v = new Point2D.Float(v.x * 32, v.y * 32);
        // Translate the coordinates.
        v = Map.translateCoordinates(v);
        // Set the vertex and upscale the radius my the map's scale
        vertices[ind] = new Circle(v.x, v.y, 16 * IM.UI_SCALE);
    }

    /**
     * Adds an edge (path) from one vertex to another in the graph.
     * 
     * @param v1     - first vertex
     * @param v2     - second vertex
     */
    public void addEdge(int v1, int v2) {
        float weight = Engine.distance(vertices[v1], vertices[v2]);
        adjMatrix[v1][v2] = weight;
        adjMatrix[v2][v1] = weight;
    }

    /**
     * Returns the weight of the edge between 2 vertices.
     * 
     * @param v1 - first vertex
     * @param v2 - second vertex
     * @return the weight of the edge between them
     */
    public float getEdgeWeight(int v1, int v2) {
        return adjMatrix[v1][v2];
    }

    /**
     * Returns the position of a vertex.
     */
    public Point2D.Float getVertex(int ind) {
        return new Point2D.Float(
                (int) (vertices[ind].x + vertices[ind].r),
                (int) (vertices[ind].y + vertices[ind].r));
    }

    /**
     * Returns the closest vertext to a given rectangle.
     * 
     * @param rect - the rectangle
     * @return the closest vertex to it
     */
    public int getClosestVertexTo(Rect rect) {
        float lowestDistance = Float.POSITIVE_INFINITY;

        // Check if vertices[] has been initialized.
        if (vertices != null) {
            Circle closestVertex = vertices[0];
            // Calculate the distance to each vertex.
            for (Circle v : vertices) {
                float distance = Engine.distance(rect, new Rect(v.x, v.y, v.r * 2, v.r * 2));
                // If a closer vertex is found, update the closest vertex.
                if (distance < lowestDistance) {
                    lowestDistance = distance;
                    closestVertex = v;
                }
            }
            for (int i = 0; i < vertices.length; i++) {
                if (closestVertex == vertices[i]) {
                    return i;
                }
            }
        }
        return 0;
    }

    /**
     * Returns the shortest path between two vertices using Dijkstra's algorithm.
     * 
     * @param v1 - first vertex
     * @param v2 - second vertex
     */
    public ArrayList<Integer> shortestPath(int v1, int v2) {
        float[] distance = new float[adjMatrix.length]; // Shortest distances to each vertex
        int[] previous = new int[adjMatrix.length]; // Previous vertex in the shortest path
        boolean[] visited = new boolean[adjMatrix.length]; // Visited vertices

        // Initialize distances to infinity, previous vertices to -1, and visited to
        // false
        for (int i = 0; i < adjMatrix.length; i++) {
            distance[i] = Float.POSITIVE_INFINITY;
            previous[i] = -1;
            visited[i] = false;
        }

        distance[v1] = 0;

        for (int count = 0; count < adjMatrix.length - 1; count++) {
            // Select the unvisited vertex with the smallest distance
            int u = -1;
            float minDist = Float.POSITIVE_INFINITY;
            for (int i = 0; i < adjMatrix.length; i++) {
                if (!visited[i] && distance[i] <= minDist) {
                    minDist = distance[i];
                    u = i;
                }
            }

            if (u == -1) { // All remaining vertices are inaccessible from the source
                break;
            }
            visited[u] = true;
            // Update distances of adjacent vertices
            for (int v = 0; v < adjMatrix.length; v++) {
                // Update distance[v] only if it's not visited, there's an edge from u to v,
                // and the total weight of the path from source to v through u is smaller than
                // current distance[v]
                if (!visited[v] && adjMatrix[u][v] != Float.POSITIVE_INFINITY
                        && distance[u] + adjMatrix[u][v] < distance[v]) {
                    distance[v] = distance[u] + adjMatrix[u][v];
                    previous[v] = u;
                }
            }
        }

        // Check if there's a path from v1 to v2
        if (distance[v2] == Float.POSITIVE_INFINITY) {
            System.out.println("No path between " + v1 + " and " + v2);
            ArrayList<Integer> failSafe = new ArrayList<>();
            failSafe.add(v1);
            return failSafe;
        } else {
            // Reconstruct the path from v2 to v1 using the previous array
            ArrayList<Integer> path = new ArrayList<>();
            int current = v2;
            while (current != -1) {
                path.add(current);
                current = previous[current];
            }
            return path;
        }
    }

}
