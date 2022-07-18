package Game.Buyables;

import Game.Location;
import Game.Player;

public abstract class Buyables extends Location {
    String name;
    int basePrice;
    int[] rent;
    String group;
    Player owner;


    public abstract int getHouses();
    public abstract String getName();
    public abstract int getBasePrice();
    public abstract Player getOwner();
    public abstract void action(Player player);
}
