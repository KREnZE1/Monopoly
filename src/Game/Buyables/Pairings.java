package Game.Buyables;

import Game.Main;
import Game.Player;

public class Pairings extends Buyables {
    String name;
    int basePrice;
    int[] rent;
    String group;
    Player owner;

    public Pairings(String name, int basePrice, int[] rent, String group) {
        this.name = name;
        this.basePrice = basePrice;
        this.rent = rent;
        this.group = group;
    }

    public String getName() {return this.name;}
    public int getBasePrice() {return this.basePrice;}
    public int getCurrPrice() {return 0;}
    public Player getOwner() {return this.owner;}
    public void action(Player player) {
        if (this.getOwner() == null) {
            if (player.getMoney() >= basePrice) {
                if (Main.getConfirmation("Do you want to buy " + this.name + " for $" + this.basePrice + "? You have $" + player.getMoney())) {
                    player.buy(this, null, this.basePrice);
                }
            } else {
                System.out.println("You don't have enough money to buy a house");
            }
        } else {
            if (this.getOwner() == player) {
                System.out.println("You own this property");
            } else {
                this.getOwner().changeMoney(this.getCurrPrice(), true);
                player.changeMoney(this.getCurrPrice(), false);
                System.out.println("You paid $" + this.getCurrPrice() + " to " + this.getOwner().getName());
            }
        }
    }
}
//TODO: Implement calculation of the current rent