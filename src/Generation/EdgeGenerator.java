package Generation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class EdgeGenerator {

    private ArrayList<Edge> edges;

    private static Random random;

    public void generate(ArrayList<Sidlo> sidla) {
        int randomSidlo;
        random = new Random();

        Point2D startPosition;
        Point2D endPosition;

        edges = new ArrayList<>();


        for (Sidlo sidlo: sidla) {
            randomSidlo = random.nextInt(sidla.size());
            startPosition = sidlo.getPosition();
            endPosition = sidla.get(randomSidlo).getPosition();
            edges.add(new Edge(startPosition, endPosition, calculateWeight(startPosition, endPosition)));
        }
    }

    private double calculateWeight(Point2D startPosition, Point2D endPosition) {
        double ux = endPosition.getX() - startPosition.getX();
        double uy = endPosition.getY() - startPosition.getY();

        return random.nextInt((int) Math.abs(Math.sqrt(ux * ux + uy * uy)));
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
