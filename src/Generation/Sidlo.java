package Generation;

import java.awt.geom.Point2D;

public class Sidlo {

    private SidlaVycet size;
    private String name;
    private Point2D position;

    public Sidlo(SidlaVycet sv, String name, Point2D position) {
        this.size = sv;
        this.name = name;
        this.position = position;
    }

    public Point2D getPosition() {
        return position;
    }

    public String toString() {
        return name + ", velikost: " + size + ", pozice: " + position.getX() + ":" + position.getY();
    }
}
