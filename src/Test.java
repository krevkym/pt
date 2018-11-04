import Generation.Edge;
import Generation.EdgeGenerator;
import Generation.SidlaGenerace;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Test {

    public static void main(String[] args) {
        if((Integer.parseInt(args[0]) < 500) || (Integer.parseInt(args[0]) > 2000))
            throw new IllegalArgumentException(String.format(
                    "Incorrect number of mansions: %s!!!", args[0]));

        Random random = new Random();

        SidlaGenerace sg = new SidlaGenerace(Integer.parseInt(args[0]), random);
        // Collections.shuffle(sg.getSidla());
        sg.generate();
        for(int i = 0; i < sg.getSidla().size(); i++) {
            System.out.print(sg.getSidla().get(i).toString() + "\n");
        }

        EdgeGenerator eg = new EdgeGenerator(random);

        eg.generate(sg.getSidla());

        ArrayList<Edge> edges = eg.getEdges();

        for (Edge edge: edges) {
            System.out.print(edge.toString() + " n. " + (edges.indexOf(edge) + 1) + "\n");
        }


    }
}
