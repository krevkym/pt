import java.util.ArrayList;
import java.util.Collections;

public class Generace {

    private ArrayList<Sidlo> sidla;


    public Generace(int quantity) {
        sidla = new ArrayList<>();
        int nameCount = 1;
        Sidlo hlavni = new Sidlo(SidlaVycet.SIDLO_HLAVNI, "Hlavni");
        sidla.add(hlavni);
        generuj((int) (quantity*0.25), SidlaVycet.SIDLO_SIZE_1);
        generuj((int) (quantity*0.25), SidlaVycet.SIDLO_SIZE_2);
        generuj((int) (quantity*0.2), SidlaVycet.SIDLO_SIZE_3);
        generuj((int) (quantity*0.15), SidlaVycet.SIDLO_SIZE_4);
        generuj((int) (quantity*0.1), SidlaVycet.SIDLO_SIZE_5);
        generuj((int) (quantity*0.05), SidlaVycet.SIDLO_SIZE_6);
        generuj(quantity-sidla.size()+1, SidlaVycet.SIDLO_SIZE_3);
        
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
        for(int i = 0; i < g.getSidla().size(); i++) {
            System.out.print(g.sidla.get(i).toString() + "\n");
        }


    }

    public ArrayList<Sidlo> getSidla() {
        return sidla;
    }
}
