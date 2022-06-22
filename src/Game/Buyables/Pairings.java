package Game.Buyables;

public class Pairings extends Buyables {
    String name;
    int basePrice;
    int[] rent;
    String group;

    public Pairings(String name, int basePrice, int[] rent, String group) {
        this.name = name;
        this.basePrice = basePrice;
        this.rent = rent;
        this.group = group;
    }
}
