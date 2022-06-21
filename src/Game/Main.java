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
        board = new Location[41];
        board[0] = new Event(); //Go
        board[1] = new Street();
        board[2] = new CommunityField();
        board[3] = new Street();
        board[4] = new Event();
        board[5] = new Pairings(); //Südbahnhof
        board[6] = new Street();
        board[7] = new Event();
        board[8] = new Street();
        board[9] = new Street();
        board[10] = new Event(); //Gefängnisbesuch
        board[11] = new Street();
        board[12] = new Pairings(); //Wasserwerk
        board[13] = new Street();
        board[14] = new Street();
        board[15] = new Pairings(); //Westbahnhof
        board[16] = new Street();
        board[17] = new CommunityField();
        board[18] = new Street();
        board[19] = new Street();
        board[20] = new Event(); //Frei Parken
        board[21] = new Street();
        board[22] = new ChanceField();
        board[23] = new Street();
        board[24] = new Street();
        board[25] = new Pairings(); //Nordbahnhof
        board[26] = new Street();
        board[27] = new Street();
        board[28] = new Pairings(); //Wasserwerk
        board[29] = new Street();
        board[30] = new Event(); //Gehe ins Gefängnis
        board[31] = new Street();
        board[32] = new Street();
        board[33] = new CommunityField();
        board[34] = new Street();
        board[35] = new Pairings(); //Ostbahnhof
        board[36] = new ChanceField();
        board[37] = new Street();
        board[38] = new Event();
        board[39] = new Street();
        board[40] = new Event(); //Gefängnis

    }
}
