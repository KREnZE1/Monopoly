package Game;

import Game.Buyables.Street;

public class Testing {

    public static void main(String[] args) {
        Player[] players = new Player[] {
                new Player("Test", 10000),
                new Player("Test2", 5000),
        };
        players[0].buy(new Street("Test", 100, new int[] {100, 600, 700, 800, 1000}, 100, "Test"), null, 100);
        players[0].getProperties().get(0).action(players[0]);
        System.out.println("Spieler: " + players[0].getName() + " | Geld: " + players[0].getMoney() + " | Position: " + players[0].getPosition());
        System.out.println("Spieler: " + players[1].getName() + " | Geld: " + players[1].getMoney() + " | Position: " + players[1].getPosition());
        players[0].getProperties().get(0).action(players[1]);
        System.out.println("Spieler: " + players[0].getName() + " | Geld: " + players[0].getMoney() + " | Position: " + players[0].getPosition());
        System.out.println("Spieler: " + players[1].getName() + " | Geld: " + players[1].getMoney() + " | Position: " + players[1].getPosition());
    }
}
