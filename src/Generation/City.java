package Generation;

import java.awt.geom.Point2D;

public class City {

    private CityEnum size;
    private String name;
    private Point2D position;
    private int unloadingWindow;

    public City(CityEnum sv, String name, Point2D position) {
        this.size = sv;
        this.name = name;
        this.position = position;
    }

    public City(CityEnum sv, String name, Point2D position, int unloadingWindow) {
        this.size = sv;
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

    public String getName() {
        return name;
    }

    public CityEnum getSize() {
        return size;
    }

}
