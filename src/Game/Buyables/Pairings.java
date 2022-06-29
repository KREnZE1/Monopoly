package Game.Buyables;

import Game.Player;

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

    public int getHouses() {
        return 0;
    }
    public String getName() {return this.name;}
    public int getBasePrice() {return this.basePrice;}
    public int getCurrPrice() {return 0;}
    public void action(Player player) {
        //TODO: Implement buying the property
    }
}
