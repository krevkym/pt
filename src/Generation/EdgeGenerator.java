package Generation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class EdgeGenerator {

    private ArrayList<Edge> edges;

    private Random random;


    public EdgeGenerator(Random random) {
        this.random = random;
    }

    public void generate(ArrayList<Sidlo> sidla) {
        Sidlo randomSidlo;

        Point2D startPosition;
        Point2D endPosition;

        edges = new ArrayList<>();
        ArrayList<Point2D> endPositions = new ArrayList<>();

        for (Sidlo sidlo: sidla) {
            startPosition = sidlo.getPosition();
            for(int i = 0; i < 200; i++) {
                randomSidlo = sidla.get(random.nextInt(sidla.size()));
                endPosition = randomSidlo.getPosition();
                int edgeWeight = calculateWeight(startPosition, endPosition);

                while((randomSidlo == sidlo) || (edgeWeight > 300)  || (endPositions.contains(endPosition))) {
                    randomSidlo = sidla.get(random.nextInt(sidla.size()));
                    endPosition = randomSidlo.getPosition();
                    edgeWeight = calculateWeight(startPosition, endPosition);
                }
                Edge edge = new Edge(startPosition, endPosition, edgeWeight);
                edges.add(edge);
                endPositions.add(endPosition);
                System.out.println(edges.indexOf(edge)+1 + " " + edge.toString()); //pomocny vypis

            }
            endPositions.clear();
        }
    }

    private int calculateWeight(Point2D startPosition, Point2D endPosition) {
        double ux = endPosition.getX() - startPosition.getX();
        double uy = endPosition.getY() - startPosition.getY();

        return (int) (Math.round(Math.sqrt(ux * ux + uy * uy)));
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
