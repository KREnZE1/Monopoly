package Game.Buyables;

import Game.Main;
import Game.Player;

public class Street extends Buyables {
    String name;
    int basePrice;
    int[] rent;
    int housePrice;
    int houses;
    String group;
    Player owner;

    // section Constructor
    public Street(String name, int basePrice, int[] rent, int housePrice, String group) {
        this.name = name;
        this.basePrice = basePrice;
        this.rent = rent;
        this.housePrice = housePrice;
        this.group = group;
        houses = 0;
    }

    // section Getter & Setter
    public int getHousePrice() {
        return this.housePrice;
    }
    public int getHouses() {
        return this.houses;
    }
    public String getName() {
        return this.name;
    }
    public int getBasePrice() {
        return this.basePrice;
    }
    public String getGroup() {
        return this.group;
    }
    public Player getOwner() {
        return this.owner;
    }

    // section Other
    public void addHouse() {
        houses++;
    }
    public int getRent() {
        return rent[houses];
    }
    public void action(Player player) {
        if (this.getOwner() == null) {
            if (player.getMoney() >= basePrice) {
                if (Main.getConfirmation("Do you want to buy " + this.name + " for $" + this.basePrice + "? You have $" + player.getMoney())) {
                    player.buy(this, null, this.basePrice);
                }
                else Main.auction(this);
            } else {
                System.out.println("You don't have enough money to buy a house");
            }
        } else {
            if (this.getOwner() == player) {
                System.out.println("You own this property");
            } else {
                this.getOwner().changeMoney(this.getRent(), true);
                player.changeMoney(this.getRent(), false);
            }
        }
    }
}
