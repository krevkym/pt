package Generation;

public class Order {

    private int destination;
    private int amount;

    public Order(int destination, int amount) {
        this.destination = destination;
        this.amount = amount;
    }

    public String toString() {
        return "City n." + destination + ", " + amount + " pallets";
    }

    public int getAmount() {
        return amount;
    }
}
