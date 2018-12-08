package Generation;

import java.awt.geom.Point2D;
import java.util.ArrayList;

public class City {

    private int size;
    private String name;
    private Point2D position;
    private int unloadingWindow;
    private int edgeCounter;
    private int numOfOrders;
    private ArrayList<Point2D> endPoses = new ArrayList<>();

    public City(int size, String name, Point2D position) {
        this.size = size;
        this.name = name;
        this.position = position;
        edgeCounter = 0;
        numOfOrders = 0;
        //endPoses = new ArrayList<>();
    }

    public City(int size, String name, Point2D position, int unloadingWindow) {
        this.size = size;
        this.name = name;
        this.position = position;
        this.unloadingWindow = unloadingWindow;
    }

    public Point2D getPosition() {
        return position;
    }

    public int getUnloadingWindow() {
        return unloadingWindow;
    }

    public String toString() {
        return String.format("%s, velikost: %s, pozice: %.2f:%.2f, " +
                "vykladaci okno od: %d:%02d", name, size, position.getX(),
                position.getY(), (unloadingWindow / 3600),
                (unloadingWindow % 3600) / 60);
    }

    public boolean canOrder() {
        return size - numOfOrders != 0;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void incEdgeCounter() {
        edgeCounter++;
    }

    public int getEdgeCounter() {
        return edgeCounter;
    }

    public ArrayList getPositions() {
        return endPoses;
    }

    public void addInPositions(Point2D endPos) {
        endPoses.add(endPos);
    }

    public int getNumOfOrders() {
        return numOfOrders;
    }
    public void addOrders(int count) {
        numOfOrders += count;
    }

    public void resetNumOfOrders() {
        numOfOrders = 0;
    }
}
