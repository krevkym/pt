package Generation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class SidlaGenerace {

    private ArrayList<Sidlo> sidla;
    private int quantity;

    private static Random random;

    public SidlaGenerace(int quantity) {
        sidla = new ArrayList<>();
        Sidlo hlavni = new Sidlo(SidlaVycet.SIDLO_HLAVNI, "Hlavni sidlo", new Point2D.Double(250, 250));
        sidla.add(hlavni);
        this.quantity = quantity;

    }

    public void generuj(int count, SidlaVycet typ) {
        String name = "Sidlo_";

        random = new Random();

        for(int i = 0; i < count; i++) {
            Sidlo sidlo = new Sidlo(typ, name + (sidla.size()), generatePosition());
            sidla.add(sidlo);
        }
    }

    private Point2D generatePosition() {
        int x = random.nextInt(800); // Mozna budouci sirka platna, kdybysme chteli vykreslovat
        int y = random.nextInt(600); // Mozna budouci vyska platna, kdybysme chteli vykreslovat

        return new Point2D.Double(x, y);
    }

    public ArrayList<Sidlo> getSidla() {
        return sidla;
    }

    public int getQuantity() {
        return quantity;
    }
}
