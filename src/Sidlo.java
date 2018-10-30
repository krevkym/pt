public class Sidlo {

    private SidlaVycet size;
    private String name;

    public Sidlo(SidlaVycet sv, String name) {
        size = sv;
        this.name = name;
    }

    public String toString() {

        return name + ", velikost: " + size;
    }
}
