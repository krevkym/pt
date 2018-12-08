//import Generation.Edge;
import Generation.EdgeGenerator;
import Generation.PathFinder;
import Generation.CitiesGenerator;
import Generation.Simulation;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Random random = new Random();
        int quantity = 1999;
        if(args.length == 1) {
            if((Integer.parseInt(args[0]) < 500) || (Integer.parseInt(args[0]) > 2000)) {
                System.out.println(String.format("Incorrect number of mansions: %s!!! \n Generating 2000 cities", args[0]));
            }
            else {
                quantity = Integer.parseInt(args[0]);
            }
        }

        CitiesGenerator sg = new CitiesGenerator(quantity-1, random);

        sg.generate();
        /*for(int i = 0; i < sg.getCities().size(); i++) {
            System.out.print(sg.getCities().get(i).toString() + "\n");
        }*/

        EdgeGenerator eg = new EdgeGenerator(random);

        eg.generate(sg.getCities());

        /*
        ArrayList<Edge> edges = eg.getEdges();

        for (Edge edge: edges) {
            System.out.print(edge.toString() + " n. " + (edges.indexOf(edge) + 1) + "\n");
        }
        */
        /*int[][] matrixDis = eg.getwDistanceMatrix();
        int[][] matrixTime = eg.getwDistanceMatrixTime();
        for(int i = 0; i < matrixDis.length; i++) {
            for(int j = 0; j < matrixDis.length; j++) {
                if(matrixDis[i][j] != -1) {
                    System.out.println("Distance: " + matrixDis[i][j] + ", time: " + matrixTime[i][j]);
                }

            }
        }*/

        PathFinder pfDist = new PathFinder(eg.getwDistanceMatrix());
        PathFinder pfTime = new PathFinder(eg.getwDistanceMatrixTime());


        for(int i = 0; i < 10; i++) {
            int dist = random.nextInt(500);
            pfDist.findPath(0, dist);
            System.out.print(pfDist.getPath().toString() + " Dist: " + pfDist.findCostInDistMat(pfDist.getPath()));
            System.out.println(", Time: " + pfTime.findCostInDistMat(pfDist.getPath()));
            clearAllPaths(pfDist, pfTime);

            pfTime.findPath(0, dist);
            System.out.println(pfTime.getPath().toString() + " " + pfTime.findCostInDistMat(pfTime.getPath()));
            clearAllPaths(pfDist, pfTime);
            System.out.println();
        }



        /*pfDist.findPath(5, 499);
        System.out.println(pfDist.getPath().toString() + " " + pfDist.getCost());
        pfDist.clearPath();
        pfTime.findPath(5, 499);
        System.out.println(pfTime.getPath().toString() + " " + pfTime.getCost());
        pfTime.clearPath();*/

        Simulation s = new Simulation(sg.getCities());

    }
    public static void clearAllPaths(PathFinder pf1, PathFinder pf2) {
        pf1.clearCost();
        pf1.clearPath();
        pf2.clearCost();
        pf2.clearPath();
    }


}
