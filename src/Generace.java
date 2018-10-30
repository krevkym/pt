import java.util.ArrayList;
import java.util.Collections;

public class Generace {

    private ArrayList<Sidlo> sidla;
    private int quantity;

    public Generace(int quantity) {
        sidla = new ArrayList<>();
        Sidlo hlavni = new Sidlo(SidlaVycet.SIDLO_HLAVNI, "Hlavni");
        sidla.add(hlavni);
        this.quantity = quantity;

    }

    public void generuj(int count, SidlaVycet typ) {
        String name = "Sidlo_";
        for(int i = 0; i < count; i++) {
            Sidlo sidlo = new Sidlo(typ, name+(sidla.size()));
            sidla.add(sidlo);
        }
    }


    public static void main(String[] args) {
        Generace g = new Generace(Integer.parseInt(args[0]));
        //Collections.shuffle(g.getSidla());
        g.generuj((int) (g.getQuantity()*0.25), SidlaVycet.SIDLO_SIZE_1);
        g.generuj((int) (g.getQuantity()*0.25), SidlaVycet.SIDLO_SIZE_2);
        g.generuj((int) (g.getQuantity()*0.2), SidlaVycet.SIDLO_SIZE_3);
        g.generuj((int) (g.getQuantity()*0.15), SidlaVycet.SIDLO_SIZE_4);
        g.generuj((int) (g.getQuantity()*0.1), SidlaVycet.SIDLO_SIZE_5);
        g.generuj((int) (g.getQuantity()*0.05), SidlaVycet.SIDLO_SIZE_6);
        g.generuj(g.getQuantity()-g.getSidla().size()+1, SidlaVycet.SIDLO_SIZE_3);
        for(int i = 0; i < g.getSidla().size(); i++) {
            System.out.print(g.sidla.get(i).toString() + "\n");
        }
    }

    public ArrayList<Sidlo> getSidla() {
        return sidla;
    }

    public int getQuantity() {
        return quantity;
    }
}
