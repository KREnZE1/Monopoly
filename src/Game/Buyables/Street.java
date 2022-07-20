package Game.Buyables;

import Game.Main;
import Game.Player;

public class Street extends Buyables {
    final String name;
    final int basePrice;
    final int[] rent;
    final int housePrice;
    int houses;
    final String group;
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
    public void setOwner(Player owner) {this.owner = owner;}

    // section Other
    public void addHouse() {
        houses++;
    }
    public void removeHouse() {
        houses--;
    }
    public int getCurrPrice() {
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
                Main.print("You don't have enough money to buy "+this.getName());
            }
        } else {
            if (this.getOwner() == player) {
                Main.print("You own this property");
            } else {
                player.changeMoney(-this.getCurrPrice(), new Player[]{this.getOwner()});
                Main.print(player.getName() + " paid $" + this.getCurrPrice() + " to " + this.getOwner().getName() + " because " + this.getOwner().getName() + " owns " + this.getName());
            }
        }
    }
}
