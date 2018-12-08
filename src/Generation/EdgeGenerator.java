package Generation;

import java.awt.geom.Point2D;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class EdgeGenerator {

    //private ArrayList<Edge> edges;

    private Random random;

    private int[][] wDistanceMatrix;

    private int[][] wDistanceMatrixTime;


    private final String MATRIX_FILE = "w-distanceMatrix.csv";

    public EdgeGenerator(Random random) {
        this.random = random;
    }

    public void generate(ArrayList<City> cities) {
        City endCity;

        Point2D startPosition;
        Point2D endPosition;
        wDistanceMatrix = createMatrix(cities.size());
        wDistanceMatrixTime = createMatrix(cities.size());
        //edges = new ArrayList<>();

        int edgeCount;

        for (City startCity : cities) {
            startPosition = startCity.getPosition();
            edgeCount = 200 + random.nextInt(200) - startCity.getPositions().size();

            int counter = 0;
            for(int i = 0; i < edgeCount; i++) {
                endCity = cities.get(random.nextInt(cities.size()));
                endPosition = endCity.getPosition();
                int edgeWeight = calculateWeight(startPosition, endPosition);

                while(endCity == startCity || edgeWeight > 200 || startCity.getPositions().contains(endCity.getPosition()) || endCity.getPositions().size() - edgeCount == 0) {
                    endCity = cities.get(random.nextInt(cities.size()));
                    endPosition = endCity.getPosition();
                    edgeWeight = calculateWeight(startPosition, endPosition);
                }

                int ceil = (int) Math.ceil(edgeWeight*0.01);
                int time = (edgeWeight - ceil + random.nextInt(ceil*2));
                startCity.addInPositions(endPosition);
                endCity.addInPositions(startPosition);
                wDistanceMatrix[cities.indexOf(startCity)][cities.indexOf(endCity)] = edgeWeight;
                wDistanceMatrix[cities.indexOf(endCity)][cities.indexOf(startCity)] = edgeWeight;
                wDistanceMatrixTime[cities.indexOf(startCity)][cities.indexOf(endCity)] = time;
                wDistanceMatrixTime[cities.indexOf(endCity)][cities.indexOf(startCity)] = time;
                //System.out.println(edges.indexOf(edge)+1 + " " + edge.toString()); //pomocny vypis
                /*counter++;
                System.out.print(counter + " " + "[" + cities.indexOf(startCity) + ", " + cities.indexOf(endCity) + "] = " + edgeWeight + " - ");
                counter++;
                System.out.println(counter + " " + "[" + cities.indexOf(endCity) + ", " + cities.indexOf(startCity) + "] = " + edgeWeight);*/

            }

            //System.out.println(startCity.getEdgeCounter());
            int counter2 = 0;
            for(int p = 0; p < wDistanceMatrix.length; p++) {
                if(wDistanceMatrix[cities.indexOf(startCity)][p] != -1) {
                    counter2++;
                }
            }
            System.out.println(cities.indexOf(startCity) + ", " + (counter2-1));
           //endPositions.clear();

        }
        saveMatrixToFile();


        //test(wDistanceMatrix);
    }

    private void test(int[][] wDistanceMatrix) {
        int edgecount = 0;
        for(int i = 0; i < wDistanceMatrix.length; i++) {
            for(int j = 0; j < wDistanceMatrix.length; j++) {
                if(wDistanceMatrix[i][j] != -1) {
                    edgecount++;
                }
            }
            System.out.println(edgecount-1);
            edgecount = 0;
        }

    }

    private int calculateWeight(Point2D startPosition, Point2D endPosition) {
        double ux = endPosition.getX() - startPosition.getX();
        double uy = endPosition.getY() - startPosition.getY();

        return (int) (Math.round(Math.sqrt(ux * ux + uy * uy)));
    }

    /*public ArrayList<Edge> getEdges() {
        return edges;
    }*/

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
    public int[][] getwDistanceMatrixTime() {
        return wDistanceMatrixTime;
    }


}
