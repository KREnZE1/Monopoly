package Game;

import Game.Buyables.Pairings;
import Game.Buyables.Street;


public class Main {

    static Location[] board;
    public static void main(String[] args) {
        setup();
    }

    public static void setup() {
        board = new Location[41];
        board[0] = new Event("Los", "get4k"); //Go
        board[1] = new Street("Badstraße", 1200, new int[]{40, 200, 600, 1800, 3200, 5000}, 1000, "dunkellila" );
        board[2] = new Event("Gemeinschaftsfeld", "communityChest");
        board[3] = new Street("Turmstraße", 1200, new int[]{80,400,1200,3600,6400,9000}, 1000, "dunkellila");
        board[4] = new Event("Einkommenssteuer", "pay4k"); //Einkommenssteuer
        board[5] = new Pairings("Südbahnhof", 4000, new int[]{500, 1000, 2000, 4000}, "Train_Station"); //Südbahnhof
        board[6] = new Street("Chausseestraße", 2000, new int[]{120,600,1800,5400,8000,1100}, 1000, "hellblau");
        board[7] = new Event("Ereignis", "chance");
        board[8] = new Street("Elisenstraße", 2000, new int[]{120, 600, 1800, 5400, 8000, 11000}, 1000, "hellblau");
        board[9] = new Street("Poststraße", 2400, new int[] {160, 800, 2000, 6000, 9000, 12000}, 1000, "hellblau");
        board[10] = new Event("Gefängnisbesuch", "visit_prison"); //Gefängnisbesuch
        board[11] = new Street("Seestraße", 2800, new int[]{200, 1000, 3000, 9000, 12500, 15000}, 2000, "helllila");
        board[12] = new Pairings("Elektrizitätswerk", 3000, new int[]{80, 200}, "Werk"); //Wasserwerk
        board[13] = new Street("Hafenstraße", 2800, new int[]{200, 1000, 3000, 9000, 12500, 15000}, 2000, "helllila");
        board[14] = new Street("Neue Straße", 3200, new int[]{240, 1200, 3600, 10000, 14000, 18000}, 2000, "helllila");
        board[15] = new Pairings("Westbahnhof", 4000, new int[]{500, 1000, 2000, 4000}, "Train_Station"); //Westbahnhof
        board[16] = new Street("Münchner Strasse", 3600, new int[]{280, 1400, 4000, 11000, 15000, 19000}, 2000, "orange");
        board[17] = new Event("Gemeinschaftsfeld", "community_chest");
        board[18] = new Street("Wiener Straße", 3600, new int[]{280, 1400, 4000, 11000, 15000, 19000}, 2000, "orange");
        board[19] = new Street("Berliner Straße", 4000, new int[]{320, 1600, 4400, 12000, 16000, 20000}, 2000, "orange");
        board[20] = new Event("Frei Parken", "free_parking"); //Frei Parken
        board[21] = new Street("Theaterstraße", 4400, new int[]{360, 1800, 5000, 14000, 17500, 21000}, 3000, "rot");
        board[22] = new Event("Ereignisfeld", "event_field"); //Ereignisfeld
        board[23] = new Street("Museumsstraße",4400, new int[]{360, 1800, 5000, 14000, 17500, 21000}, 3000, "rot");
        board[24] = new Street("Opernplatz", 4800, new int[]{400, 2000, 6000, 15000, 18500, 22000}, 3000, "rot");
        board[25] = new Pairings("Nordbahnhof", 4000, new int[]{500, 1000, 2000, 4000}, "Train_Station"); //Nordbahnhof
        board[26] = new Street("Lessingstraße", 5200, new int[]{480, 2200, 6600, 16000, 19500, 23000}, 3000, "gelb");
        board[27] = new Street("Schillerstraße", 5200, new int[]{480, 2200, 6600, 16000, 19500, 23000}, 3000, "gelb");
        board[28] = new Pairings("Wasserwerk", 3000, new int[]{80, 200}, "Werk"); //Wasserwerk
        board[29] = new Street("Goethestraße", 5600, new int[]{520, 2400, 7200, 17000, 20500, 24000}, 3000, "gelb");
        board[30] = new Event("Gehe ins Gefängnis", "to_prison"); //Gehe ins Gefängnis
        board[31] = new Street("Rathausplatz", 6000, new int[]{520, 2600, 7800, 18000, 22000, 25500}, 3000, "grün");
        board[32] = new Street("Hauptstraße", 6000, new int[]{520, 2600, 7800, 18000, 22000, 25500}, 4000, "grün");
        board[33] = new Event("Community Chest", "community_chest"); //Community Chest
        board[34] = new Street("Bahnhofstraße", 6400, new int[]{560, 3000, 9000, 20000, 24000, 28000}, 4000, "grün");
        board[35] = new Pairings("Ostbahnhof", 4000, new int[]{500, 1000, 2000, 4000}, "Train_Station"); //Ostbahnhof
        board[36] = new Event("Ereignisfeld", "event_field"); //Ereignisfeld
        board[37] = new Street("Parkstraße", 7000, new int[]{700, 3500, 10000, 22000, 26000, 30000}, 4000, "dunkelblau");
        board[38] = new Event("Zusatzsteuer", "pay2k"); //Zusatzsteuer
        board[39] = new Street("Schlossallee", 8000, new int[]{1000, 4000, 12000, 28000, 34000, 40000}, 4000, "dunkelblau");
        board[40] = new Event("Gefängnis", "prison"); //Gefängnis

    }
}
