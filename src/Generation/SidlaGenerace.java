package Generation;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class SidlaGenerace {

    private ArrayList<Sidlo> sidla;
    //private ArrayList<Point2D> positions = new ArrayList<>();
    private int quantity;

    private int width;
    private int height;

    private Random random;

    public SidlaGenerace(int quantity, Random random) {
        this.quantity = quantity;
        if (quantity < 1000) {
            this.width = 200;
            this.height = 200;
        } else if (quantity < 1500) {
            this.width = 300;
            this.height = 300;
        } else {
            this.width = 400;
            this.height = 400;
        }
        this.sidla = new ArrayList<>();
        Sidlo hlavni = new Sidlo(SidlaVycet.SIDLO_HLAVNI, "Hlavni sidlo", new Point2D.Double(width / 2, height / 2));
        this.sidla.add(hlavni);
        this.random = random;
    }

    public void generate() {
        String name = "Sidlo_";
        SidlaVycet type;
        int quantityLocal;
        for(int j = 0; j < 7; j++) {
            switch(j) {
                case 0:
                    quantityLocal = (int) (quantity * 0.25);
                    type = SidlaVycet.SIDLO_SIZE_1;
                    break;

                case 1:
                    quantityLocal = (int) (quantity * 0.25);
                    type = SidlaVycet.SIDLO_SIZE_2;
                    break;
                case 2:
                    quantityLocal = (int) (quantity * 0.2);
                    type = SidlaVycet.SIDLO_SIZE_3;
                    break;
                case 3:
                    quantityLocal = (int) (quantity * 0.15);
                    type = SidlaVycet.SIDLO_SIZE_4;
                    break;
                case 4:
                    quantityLocal = (int) (quantity * 0.1);
                    type = SidlaVycet.SIDLO_SIZE_5;
                    break;
                case 5:
                    quantityLocal = (int) (quantity * 0.05);
                    type = SidlaVycet.SIDLO_SIZE_6;
                    break;
                default:
                    quantityLocal = quantity - sidla.size();
                    type = SidlaVycet.SIDLO_SIZE_3;
                    break;
            }

            for(int i = 0; i < quantityLocal; i++) {
                Sidlo sidlo = new Sidlo(type, name + (sidla.size()), generatePosition());
                sidla.add(sidlo);
            }
        }
    }

    private Point2D generatePosition() {
        int x = random.nextInt(width); // Mozna budouci sirka platna, kdybysme chteli vykreslovat
        int y = random.nextInt(height); // Mozna budouci vyska platna, kdybysme chteli vykreslovat
        Point2D position = new Point2D.Double(x, y);

        for(int i = 0; i < sidla.size(); i++) {
            if(position.distance(sidla.get(i).getPosition()) < 5) {
                x = random.nextInt(width);
                y = random.nextInt(height);
                position = new Point2D.Double(x, y);
                i = 0;
            }

        }
        //positions.add(position);
        return position;
    }

    public ArrayList<Sidlo> getSidla() {
        return sidla;
    }

    public int getQuantity() {
        return quantity;
    }
}
