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

    public void generate() {
        String name = "Sidlo_";
        SidlaVycet type;
        int quantityLocal = quantity;
        for(int j = 0; j < 7; j++) {
            switch (j) {
                case 0: quantityLocal = (int) (quantity * 0.25); type = SidlaVycet.SIDLO_SIZE_1; break;
                case 1: quantityLocal = (int) (quantity * 0.25); type = SidlaVycet.SIDLO_SIZE_2; break;
                case 2: quantityLocal = (int) (quantity * 0.2); type = SidlaVycet.SIDLO_SIZE_3; break;
                case 3: quantityLocal = (int) (quantity * 0.15); type = SidlaVycet.SIDLO_SIZE_4; break;
                case 4: quantityLocal = (int) (quantity * 0.1); type = SidlaVycet.SIDLO_SIZE_5; break;
                case 5: quantityLocal = (int) (quantity * 0.05); type = SidlaVycet.SIDLO_SIZE_6; break;
                case 6: quantityLocal = quantity - sidla.size(); type = SidlaVycet.SIDLO_SIZE_3; break;
                default: type = SidlaVycet.SIDLO_SIZE_2;
            }

            for(int i = 0; i < quantityLocal; i++) {
                Sidlo sidlo = new Sidlo(type, name + (sidla.size()), generatePosition());
                sidla.add(sidlo);
            }
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
