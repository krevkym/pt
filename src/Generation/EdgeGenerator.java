package Generation;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

public class EdgeGenerator {

    private ArrayList<Edge> edges;

    private Random random;

    private int[][] wDistanceMatrix;

    private final String MATRIX_FILE = "w-distanceMatrix.csv";

    public EdgeGenerator(Random random) {
        this.random = random;
    }

    public void generate(ArrayList<City> cities) {
        City randomCity;

        Point2D startPosition;
        Point2D endPosition;
        wDistanceMatrix = createMatrix(cities.size());
        edges = new ArrayList<>();
        ArrayList<Point2D> endPositions = new ArrayList<>();

        int edgeCount;

        for (City city : cities) {
            startPosition = city.getPosition();
            edgeCount = 200 + random.nextInt(200);
            for(int i = 0; i < edgeCount; i++) {
                randomCity = cities.get(random.nextInt(cities.size()));
                endPosition = randomCity.getPosition();
                int edgeWeight = calculateWeight(startPosition, endPosition);

                while((randomCity == city) || (edgeWeight > 200)  || (endPositions.contains(endPosition))) {
                    randomCity = cities.get(random.nextInt(cities.size()));
                    endPosition = randomCity.getPosition();
                    edgeWeight = calculateWeight(startPosition, endPosition);
                }
                Edge edge = new Edge(startPosition, endPosition, edgeWeight);

                edges.add(edge);
                endPositions.add(endPosition);
                wDistanceMatrix[cities.indexOf(city)][cities.indexOf(randomCity)] = edgeWeight;
                //System.out.println(edges.indexOf(edge)+1 + " " + edge.toString()); //pomocny vypis
            }
            endPositions.clear();
        }
        saveMatrixToFile();
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

    public void saveMatrixToFile() {
        File matrixFile = new File(MATRIX_FILE);
        try {
            FileWriter fw = new FileWriter(matrixFile);
            PrintWriter pw = new PrintWriter(fw);
            for(int i = 0; i < wDistanceMatrix.length; i++) {
                for (int j = 0; j < wDistanceMatrix.length; j++) {
                    pw.write(wDistanceMatrix[i][j] + ";");
                }
                fw.write("\n");
            }
            pw.close();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int[][] getwDistanceMatrix() {
        return wDistanceMatrix;
    }
}
