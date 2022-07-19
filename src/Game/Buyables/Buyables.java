package Game.Buyables;

import Game.Location;
import Game.Player;

public abstract class Buyables extends Location {


    public abstract String getName();
    public abstract int getBasePrice();
    public abstract int getCurrPrice();
    public abstract Player getOwner();
    public abstract void setOwner(Player player);
    public abstract void action(Player player);
}
