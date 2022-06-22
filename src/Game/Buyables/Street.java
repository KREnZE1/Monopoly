package Game.Buyables;

public class Street extends Buyables {
    String name;
    int basePrice;
    int[] rent;
    int housePrice;
    int houses;
    String group;

    public Street(String name, int basePrice, int[] rent, int housePrice, String group) {
        this.name = name;
        this.basePrice = basePrice;
        this.rent = rent;
        this.housePrice = housePrice;
        this.group = group;
        houses = 0;
    }
}
