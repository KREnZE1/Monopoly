package Game;

import Game.Buyables.Buyables;

public class Player {
    String name;
    int money;
    int position;
    Buyables[] properties;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.position = 0;
        this.properties = new Buyables[22];

    }
}
