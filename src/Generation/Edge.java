package Generation;

import java.awt.geom.Point2D;

public class Edge {
    private Point2D startPos;
    private Point2D endPos;

    private int weight;

    public Edge(Point2D startPos, Point2D endPos, int weight) {
        this.startPos = startPos;
        this.endPos = endPos;
        this.weight = weight;
    }

    public Point2D getStartPos() {
        return startPos;
    }

    public void setStartPos(Point2D startPos) {
        this.startPos = startPos;
    }

    public Point2D getEndPos() {
        return endPos;
    }

    public void setEndPos(Point2D endPos) {
        this.endPos = endPos;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Edge: " + "startPos = " + startPos + ", endPos = " + endPos + ", weight=" + weight;
    }
}
