import Generation.Edge;
import Generation.EdgeGenerator;
import Generation.PathFinder;
import Generation.CitiesGenerator;

import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Random random = new Random();
        int quantity = 2000;
        if(args.length == 1) {
            if((Integer.parseInt(args[0]) < 500) || (Integer.parseInt(args[0]) > 2000)) {
                System.out.println(String.format("Incorrect number of mansions: %s!!! \n Generating 2000 cities", args[0]));
            }
            else {
                quantity = Integer.parseInt(args[0]);
            }
        }

        CitiesGenerator sg = new CitiesGenerator(quantity, random);

        sg.generate();
        for(int i = 0; i < sg.getCities().size(); i++) {
            System.out.print(sg.getCities().get(i).toString() + "\n");
        }

        EdgeGenerator eg = new EdgeGenerator(random);

        eg.generate(sg.getCities());

        /*
        ArrayList<Edge> edges = eg.getEdges();

        for (Edge edge: edges) {
            System.out.print(edge.toString() + " n. " + (edges.indexOf(edge) + 1) + "\n");
        }
        */

        PathFinder pf = new PathFinder(eg.getwDistanceMatrix());
        pf.createMatrixOfPredecessors(eg.getwDistanceMatrix());
        pf.findPath(8, 354);
        System.out.println(pf.getPath().toString() + " " + pf.getCost());
        pf.clearPath();
        pf.findPath(5, 212);
        System.out.println(pf.getPath().toString() + " " + pf.getCost());
        pf.clearPath();
        pf.findPath(9, 175);
        System.out.println(pf.getPath().toString() + " " + pf.getCost());
        pf.clearPath();
        pf.findPath(56, 74);
        System.out.println(pf.getPath().toString() + " " + pf.getCost());
        pf.clearPath();
    }
}
