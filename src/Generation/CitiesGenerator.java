package Generation;

import java.awt.geom.Point2D;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class CitiesGenerator {

    private final String CITES_FILE = "cities.csv";
    private ArrayList<City> cities;

    private int quantity;

    private int width;
    private int height;

    private Random random;

    public CitiesGenerator(int quantity, Random random) {
        this.quantity = quantity;
        if (quantity < 1000) {
            this.width = 200;
            this.height = 200;
        } else if (quantity < 1500) {
            this.width = 250;
            this.height = 250;
        } else {
            this.width = 300;
            this.height = 300;
        }
        this.cities = new ArrayList<>();
        City mainCity = new City(0, "Main city",
                new Point2D.Double(width / 2.0, height / 2.0));
        this.cities.add(mainCity);
        this.random = random;
    }

    public void generate() {
        System.out.println("Cities generation");
        String name = "City_";
        int sizeOfCity;
        int quantityLocal;
        for(int j = 0; j < 7; j++) {
            switch(j) {
                case 0:
                    quantityLocal = (int) (quantity * 0.25);
                    sizeOfCity = 1;
                    break;

                case 1:
                    quantityLocal = (int) (quantity * 0.25);
                    sizeOfCity = 2;
                    break;
                case 2:
                    quantityLocal = (int) (quantity * 0.2);
                    sizeOfCity = 3;
                    break;
                case 3:
                    quantityLocal = (int) (quantity * 0.15);
                    sizeOfCity = 4;
                    break;
                case 4:
                    quantityLocal = (int) (quantity * 0.1);
                    sizeOfCity = 5;
                    break;
                case 5:
                    quantityLocal = (int) (quantity * 0.05);
                    sizeOfCity = 6;
                    break;
                default:
                    quantityLocal = quantity - cities.size() + 1;
                    sizeOfCity = 3;
                    break;
            }

            for(int i = 0; i < quantityLocal; i++) {
                City city = new City(sizeOfCity, name + (cities.size()),
                        generatePosition(), generateUnloadingWindow());
                cities.add(city);
            }
            citiesToFile(CITES_FILE);
        }
    }

    private void citiesToFile(String fileName) {
        try {
            FileWriter fw = new FileWriter(new File(fileName));
            PrintWriter pw = new PrintWriter(fw);
            //int size = 0;
            for(City city: cities) {
                /*switch (city.getSize()) {
                    case MAIN_CITY: size = 0; break;
                    case CITY_SIZE_1: size = 1; break;
                    case CITY_SIZE_2: size = 2; break;
                    case CITY_SIZE_3: size = 3; break;
                    case CITY_SIZE_4: size = 4; break;
                    case CITY_SIZE_5: size = 5; break;
                    case CITY_SIZE_6: size = 6; break;
                }*/
                pw.write(city.getName() + ";" + city.getSize() + ";" + city.getPosition().getX() + ";" + city.getPosition().getY() + "\n");
            }
            pw.close();
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private Point2D generatePosition() {
        int x = random.nextInt(width); // Mozna budouci sirka platna, kdybysme chteli vykreslovat
        int y = random.nextInt(height); // Mozna budouci vyska platna, kdybysme chteli vykreslovat
        Point2D position = new Point2D.Double(x, y);

        for(int i = 0; i < cities.size(); i++) {
            if(position.distance(cities.get(i).getPosition()) < 5) {
                x = random.nextInt(width);
                y = random.nextInt(height);
                position = new Point2D.Double(x, y);
                i = 0;
            }

        }
        return position;
    }

    private int generateUnloadingWindow() {
        int offset = 28800; // 8:00 in seconds

        return offset + random.nextInt(43200);
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public int getQuantity() {
        return quantity;
    }
}
