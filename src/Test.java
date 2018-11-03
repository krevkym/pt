import Generation.Edge;
import Generation.EdgeGenerator;
import Generation.SidlaGenerace;
import Generation.SidlaVycet;

import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        if((Integer.parseInt(args[0]) < 500) || (Integer.parseInt(args[0]) > 2000))
            throw new IllegalArgumentException(String.format(
                    "Incorrect number of mansions: %s!!!", args[0]));

        SidlaGenerace sg = new SidlaGenerace(Integer.parseInt(args[0]));
        // Collections.shuffle(sg.getSidla());
        // tuhle generaci by to chtelo udelat na jedno zavolani, podobne jako to mam s tema hranama, pokud je to mozne...
        sg.generuj((int) (sg.getQuantity() * 0.25), SidlaVycet.SIDLO_SIZE_1);
        sg.generuj((int) (sg.getQuantity() * 0.25), SidlaVycet.SIDLO_SIZE_2);
        sg.generuj((int) (sg.getQuantity() * 0.2), SidlaVycet.SIDLO_SIZE_3);
        sg.generuj((int) (sg.getQuantity() * 0.15), SidlaVycet.SIDLO_SIZE_4);
        sg.generuj((int) (sg.getQuantity() * 0.1), SidlaVycet.SIDLO_SIZE_5);
        sg.generuj((int) (sg.getQuantity() * 0.05), SidlaVycet.SIDLO_SIZE_6);
        sg.generuj(sg.getQuantity() - sg.getSidla().size() + 1, SidlaVycet.SIDLO_SIZE_3);
        for(int i = 0; i < sg.getSidla().size(); i++) {
            System.out.print(sg.getSidla().get(i).toString() + "\n");
        }

        EdgeGenerator eg = new EdgeGenerator();

        eg.generate(sg.getSidla());

        ArrayList<Edge> edges = eg.getEdges();

        /*for (Edge edge: edges) {
            System.out.print(edge.toString() + " n. " + edges.indexOf(edge) + "\n");
        }*/
    }
}
