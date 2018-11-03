package Generation;

import java.awt.geom.Point2D;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class EdgeGenerator {

    private ArrayList<Edge> edges;

    private Random random;
    private int[][] wDistanceMatrix;
    public EdgeGenerator(Random random) {
        this.random = random;
    }

    public void generate(ArrayList<Sidlo> sidla) {
        Sidlo randomSidlo;

        Point2D startPosition;
        Point2D endPosition;
        wDistanceMatrix = createMatrix(sidla.size());
        edges = new ArrayList<>();
        ArrayList<Point2D> endPositions = new ArrayList<>();

        int edgeCount;

        for (Sidlo sidlo: sidla) {
            startPosition = sidlo.getPosition();
            edgeCount = 200 + random.nextInt(200);
            for(int i = 0; i < edgeCount; i++) {
                randomSidlo = sidla.get(random.nextInt(sidla.size()));
                endPosition = randomSidlo.getPosition();
                int edgeWeight = calculateWeight(startPosition, endPosition);

                while((randomSidlo == sidlo) || (edgeWeight > 200)  || (endPositions.contains(endPosition))) {
                    randomSidlo = sidla.get(random.nextInt(sidla.size()));
                    endPosition = randomSidlo.getPosition();
                    edgeWeight = calculateWeight(startPosition, endPosition);
                }
                Edge edge = new Edge(startPosition, endPosition, edgeWeight);
                edges.add(edge);
                endPositions.add(endPosition);
              //  wDistanceMatrix[sidla.indexOf(sidlo)][sidla.indexOf(randomSidlo)] = edgeWeight;
                //System.out.println(edges.indexOf(edge)+1 + " " + edge.toString()); //pomocny vypis
            }
            endPositions.clear();
        }
        matrixToFile();
    }

    private int calculateWeight(Point2D startPosition, Point2D endPosition) {
        double ux = endPosition.getX() - startPosition.getX();
        double uy = endPosition.getY() - startPosition.getY();

        return (int) (Math.round(Math.sqrt(ux * ux + uy * uy)));
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }

    public int[][] createMatrix(int n) {
        int matrix[][] = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    matrix[i][j] = 0;
                }
                else {
                    matrix[i][j] = -1;
                }

            }
        }
        return matrix;
    }

    public void matrixToFile() {
        File matrixFile = new File("w-distanceMatrix.txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(matrixFile);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < wDistanceMatrix.length; i++) {
               // bw.write(i + " ");
                for (int j = 0; j < wDistanceMatrix.length; j++) {
                    bw.write(wDistanceMatrix[i][j] + ";");

                }
                bw.write("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
