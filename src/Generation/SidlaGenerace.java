package Generation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class SidlaGenerace {

    private ArrayList<Sidlo> sidla;
    private int quantity;

    private Random random;

    public SidlaGenerace(int quantity, Random random) {
        this.sidla = new ArrayList<>();
        Sidlo hlavni = new Sidlo(SidlaVycet.SIDLO_HLAVNI, "Hlavni sidlo", new Point2D.Double(250, 250));
        this.sidla.add(hlavni);
        this.quantity = quantity;
        this.random = random;

    }

    public void generuj(int count, SidlaVycet typ) {
        String name = "Sidlo_";

        for(int i = 0; i < count; i++) {
            Sidlo sidlo = new Sidlo(typ, name + (sidla.size()), generatePosition());
            sidla.add(sidlo);
        }
    }

    private Point2D generatePosition() {
        int x = random.nextInt(400); // Mozna budouci sirka platna, kdybysme chteli vykreslovat
        int y = random.nextInt(400); // Mozna budouci vyska platna, kdybysme chteli vykreslovat
        Point2D position = new Point2D.Double(x, y);

        for (Sidlo sidlo: sidla) {
            double distance = position.distance(sidlo.getPosition());
            while(distance < 20) {
                x = random.nextInt(400);
                y = random.nextInt(400);
                position = new Point2D.Double(x, y);
                distance = position.distance(sidlo.getPosition());
            }
        }

        return position;
    }

    public ArrayList<Sidlo> getSidla() {
        return sidla;
    }

    public int getQuantity() {
        return quantity;
    }
}
