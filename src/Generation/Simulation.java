package Generation;


import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {

    private ArrayList<City> cities;
    private static Random rd;
    private int time;
    private int nejakejcounter;
    private final double LAMBDA = 1/300.0;
    private int testexp;
    private int[] orderRozdeleni = {25, 25, 20, 15, 10 ,5};
    private final ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

    public Simulation(ArrayList<City> cities) {
        rd = new Random();
        this.cities = cities;
        time = 350;
        ses.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                simulate();
                time++;
                if(time == 1440) nextDay();

            }
        }, 0, 10, TimeUnit.MILLISECONDS);
        //startSimulation();
    }

    private void nextDay() {
        time = 0;
        for (City city : cities) {
            city.resetNumOfOrders();
        }
    }

    private void simulate() {
        if(time < 960 && time >= 360 && time%5 == 0) {
            System.out.format("%02d:%02d    %d\n", time/60, time%60, testexp);
            checkOrders();
            nejakejcounter++;

            testexp = 0;
        }



    }

    private void checkOrders() {
        double chance = ((LAMBDA*Math.exp(-nejakejcounter*LAMBDA)))*1000;
        for(int i = 1; i < cities.size(); i++) {
            if(cities.get(i).canOrder()) {
                int chancePoint = rd.nextInt(1000);
                if(chancePoint<=chance) {
                    testexp++;
                    Order order = new Order(i, getRandomAmount(cities.get(i).getSize()));
                    System.out.println(cities.get(i).toString() + "\t" + order.getAmount() + " pallets");
                }
            }

        }

    }

    private int getRandomAmount(int size) {

        int hundred = 0;
        int amount = 1;
        for(int i = 0; i < size; i++) {
            hundred += orderRozdeleni[i];
        }
        int chance = 1 + rd.nextInt(hundred);
        System.out.println("Chance: " + chance);
        int pom = 0;
        for(int i = 0; i < size; i++) {
            if(orderRozdeleni[i] + pom < chance) {
                amount++;
                pom += orderRozdeleni[i];
            } else {
                break;
            }
        }

        return amount;
    }

}
