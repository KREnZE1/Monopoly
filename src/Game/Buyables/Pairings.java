package Game.Buyables;

import Game.Main;
import Game.Player;


public class Pairings extends Buyables {
    final String name;
    final int basePrice;
    final int[] rent;
    final String group;
    Player owner;

    public Pairings(String name, int basePrice, int[] rent, String group) {
        this.name = name;
        this.basePrice = basePrice;
        this.rent = rent;
        this.group = group;
    }

    public String getName() {return this.name;}
    public String getGroup() {return this.group;}
    public int getBasePrice() {return this.basePrice;}
    public int getCurrPrice() {
        int price = 0;
        if (this.getGroup().equals("Train_Station")) {
            int counter = 0;
            if (this.getOwner().getProperties().contains(Main.getBoard()[5])) {
                counter++;
            } if (this.getOwner().getProperties().contains(Main.getBoard()[15])) {
                counter++;
            } if (this.getOwner().getProperties().contains(Main.getBoard()[25])) {
                counter++;
            } if (this.getOwner().getProperties().contains(Main.getBoard()[35])) {
                counter++;
            }
            price = this.rent[counter];
        } else if (this.getGroup().equals("Werk")) {
            if (this.getOwner().getProperties().contains(Main.getBoard()[12]) && this.getOwner().getProperties().contains(Main.getBoard()[28])) {
                price = this.rent[1];
            } else {
                price = this.rent[0];
            }
        } else System.err.println("Fehlerhafter Gruppenname bei " + this.getName());
        return price;
    }
    public Player getOwner() {return this.owner;}
    public void setOwner(Player owner) {this.owner = owner;}
    public void action(Player player) {
        if (this.getOwner() == null) {
            if (player.getMoney() >= basePrice) {
                if (Main.getConfirmation("Do you want to buy " + this.name + " for $" + this.basePrice + "? You have $" + player.getMoney())) {
                    player.buy(this, null, this.basePrice);
                }
            } else {
                Main.print("You don't have enough money to buy "+this.getName());
            }
        } else {
            if (this.getOwner() == player) {
                Main.print("You own this property");
            } else {
                player.changeMoney(-this.getCurrPrice(), new Player[]{this.getOwner()});
                Main.print("You paid $" + this.getCurrPrice() + " to " + this.getOwner().getName());
            }
        }
    }
}