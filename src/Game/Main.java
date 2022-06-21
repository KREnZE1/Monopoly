package Game;

import Game.Buyables.Pairings;
import Game.Buyables.Street;
import Game.NonBuyables.ChanceField;
import Game.NonBuyables.CommunityField;
import Game.NonBuyables.Event;


public class Main {

    static Location[] board;
    public static void main(String[] args) {

    }

    public static void setup() {
        board = new Location[42];
        board[0] = new Event();
        board[1] = new Street();
        board[2] = new CommunityField();
        board[3] = new Street();
        board[4] = new Event();
        board[5] = new Pairings();
        board[6] = new Street();
        board[7] = new ChanceField();
        board[8] = new Street();
        board[9] = new Street();
    }
}
