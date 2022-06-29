package Game.Buyables;

import Game.Player;

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

    public void addHouse() {
        houses++;
    }

    public int getRent() {
        return rent[houses];
    }

    public int getHousePrice() {
        return housePrice;
    }
    public int getHouses() {
        return houses;
    }
    public String getName() {
        return name;
    }
    public int getBasePrice() {
        return basePrice;
    }
    public String getGroup() {
        return group;
    }

    public void action(Player player) {
        //TODO: Implement buying the property
    }
}
