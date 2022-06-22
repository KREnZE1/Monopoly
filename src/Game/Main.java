package Game;

import Game.Buyables.Pairings;
import Game.Buyables.Street;
import Game.Cards.Chance;
import Game.Cards.CommunityChest;


public class Main {

    static Location[] board;
    static Player[] players;
    static Chance[] chanceCards;
    static CommunityChest[] communityChestCards;

    public static void main(String[] args) {
        setup();
    }

    public static void setup() {
        board = new Location[]{
                new Event("Los", "get4k"), //Go
                new Street("Badstraße", 1200, new int[]{40, 200, 600, 1800, 3200, 5000}, 1000, "dunkellila"),
                new Event("Gemeinschaftsfeld", "communityChest"),
                new Street("Turmstraße", 1200, new int[]{80, 400, 1200, 3600, 6400, 9000}, 1000, "dunkellila"),
                new Event("Einkommenssteuer", "pay4k"), //Einkommenssteuer
                new Pairings("Südbahnhof", 4000, new int[]{500, 1000, 2000, 4000}, "Train_Station"), //Südbahnhof
                new Street("Chausseestraße", 2000, new int[]{120, 600, 1800, 5400, 8000, 1100}, 1000, "hellblau"),
                new Event("Ereignis", "chance"),
                new Street("Elisenstraße", 2000, new int[]{120, 600, 1800, 5400, 8000, 11000}, 1000, "hellblau"),
                new Street("Poststraße", 2400, new int[]{160, 800, 2000, 6000, 9000, 12000}, 1000, "hellblau"),
                new Event("Gefängnisbesuch", "none"), //Gefängnisbesuch
                new Street("Seestraße", 2800, new int[]{200, 1000, 3000, 9000, 12500, 15000}, 2000, "helllila"),
                new Pairings("Elektrizitätswerk", 3000, new int[]{80, 200}, "Werk"), //Wasserwerk
                new Street("Hafenstraße", 2800, new int[]{200, 1000, 3000, 9000, 12500, 15000}, 2000, "helllila"),
                new Street("Neue Straße", 3200, new int[]{240, 1200, 3600, 10000, 14000, 18000}, 2000, "helllila"),
                new Pairings("Westbahnhof", 4000, new int[]{500, 1000, 2000, 4000}, "Train_Station"), //Westbahnhof
                new Street("Münchner Strasse", 3600, new int[]{280, 1400, 4000, 11000, 15000, 19000}, 2000, "orange"),
                new Event("Gemeinschaftsfeld", "community_chest"),
                new Street("Wiener Straße", 3600, new int[]{280, 1400, 4000, 11000, 15000, 19000}, 2000, "orange"),
                new Street("Berliner Straße", 4000, new int[]{320, 1600, 4400, 12000, 16000, 20000}, 2000, "orange"),
                new Event("Frei Parken", "none"), //Frei Parken
                new Street("Theaterstraße", 4400, new int[]{360, 1800, 5000, 14000, 17500, 21000}, 3000, "rot"),
                new Event("Ereignisfeld", "event_field"), //Ereignisfeld
                new Street("Museumsstraße", 4400, new int[]{360, 1800, 5000, 14000, 17500, 21000}, 3000, "rot"),
                new Street("Opernplatz", 4800, new int[]{400, 2000, 6000, 15000, 18500, 22000}, 3000, "rot"),
                new Pairings("Nordbahnhof", 4000, new int[]{500, 1000, 2000, 4000}, "Train_Station"), //Nordbahnhof
                new Street("Lessingstraße", 5200, new int[]{480, 2200, 6600, 16000, 19500, 23000}, 3000, "gelb"),
                new Street("Schillerstraße", 5200, new int[]{480, 2200, 6600, 16000, 19500, 23000}, 3000, "gelb"),
                new Pairings("Wasserwerk", 3000, new int[]{80, 200}, "Werk"), //Wasserwerk
                new Street("Goethestraße", 5600, new int[]{520, 2400, 7200, 17000, 20500, 24000}, 3000, "gelb"),
                new Event("Gehe ins Gefängnis", "to_prison"), //Gehe ins Gefängnis
                new Street("Rathausplatz", 6000, new int[]{520, 2600, 7800, 18000, 22000, 25500}, 3000, "grün"),
                new Street("Hauptstraße", 6000, new int[]{520, 2600, 7800, 18000, 22000, 25500}, 4000, "grün"),
                new Event("Community Chest", "community_chest"), //Community Chest
                new Street("Bahnhofstraße", 6400, new int[]{560, 3000, 9000, 20000, 24000, 28000}, 4000, "grün"),
                new Pairings("Ostbahnhof", 4000, new int[]{500, 1000, 2000, 4000}, "Train_Station"), //Ostbahnhof
                new Event("Ereignisfeld", "event_field"), //Ereignisfeld
                new Street("Parkstraße", 7000, new int[]{700, 3500, 10000, 22000, 26000, 30000}, 4000, "dunkelblau"),
                new Event("Zusatzsteuer", "pay2k"), //Zusatzsteuer
                new Street("Schlossallee", 8000, new int[]{1000, 4000, 12000, 28000, 34000, 40000}, 4000, "dunkelblau"),
                new Event("Gefängnis", "prison"), //Gefängnis
        };

        players = new Player[]{
                new Player("Player 1", 5000),
        };

        chanceCards = new Chance[]{
            new Chance("Du hast den 2. Preis in einer Schönheitskonkurrenz gewonnen. Ziehe 200 ein."),
            new Chance("Du hast in einem Kreuzworträtsel gewonnen. Ziege 2000 ein."),
            new Chance("Einkommenssteuer-Rückzahlung. Ziehe 400 ein."),
            new Chance("Zahle 3000 Schulgeld."),
            new Chance("Rücke vor bis auf Los."),
            new Chance("Arztkosten. Zahle 1000."),
            new Chance("Die Jahresrente wird fällig. Ziehe 2000 ein."),
            new Chance("Gehe in das Gefängnis. Begib dich direkt dorthin. Gehe nicht über Los. Ziehe nicht 4000 ein."),
            new Chance("Zahle 800 pro Haus und 2300 pro Hotel, das du besitzt."),
            new Chance("Du erhältst auf Aktien Dividende. Ziehe 900 ein."),
            new Chance("Du erbst 2000."),
            new Chance("Aus Lagerverkäufen erhältst du 500."),
            new Chance("Zahle 2000 an das Krankenhaus."),
            new Chance("Du kommst aus dem Gefängnis frei. Behalte diese Karte, bis du sie verwendest oder verkaufst."),
            new Chance("Es ist dein Geburtstag. Ziehe 1000 von jedem Spieler ein."),
            new Chance("Bank-Irrtum. Ziehe 4000 ein."),
        };

        communityChestCards = new CommunityChest[]{
            new CommunityChest("Rücke vor bis zum Opernplatz. Wenn du über Los kommst, ziehe 4000 ein.", "move"),
            new CommunityChest("Rücke vor bis zur Seestraße. Wenn du über Los kommst, ziehe 4000 ein.", "move"),
            new CommunityChest("Gehe zurück zur Badstraße.", "move"),
            new CommunityChest("Zahle eine Strafe von 200 oder nimm eine Gemeinschaftskarte.", "pay"),
            new CommunityChest("Gehe 3 Felder zurück.", "move"),
            new CommunityChest("Du kommst aus dem Gefängnis frei. Behalte diese Karte, bis du sie verwendest oder verkaufst.", "prison_free"),
            new CommunityChest("Gehe in das Gefängnis. Begib dich direkt dorthin. Gehe nicht über Los. Ziehe nicht 4000 ein.", "move"),
            new CommunityChest("Rücke vor bis zum nächsten Bahnhof. Der Eigentümer erhält die doppelte Miete. Wenn noch niemand diesen Bahnhof besitzt, kannst du ihn kaufen.", "move_special"),
            new CommunityChest("Rücke vor bis zur Schlossallee", "move"),
            new CommunityChest("Zahle 500 für jedes Haus und 2000 für jedes Hotel, das du besitzt.", "pay_special"),
            new CommunityChest("Du wurdest zum Vorstand gewählt. Zahle jedem Spieler 1000.", "pay"),
            new CommunityChest("Miete und Anleihezinsen werden fällig. Die Bank zahlt dir 3000.", "get"),
            new CommunityChest("Ziehe vor bis zum Südbahnhof. Wenn du über Los kommst, ziehe 4000 ein.", "move"),
            new CommunityChest("Die Bank zahlt dir eine Dividende von 1000.", "get"),
            new CommunityChest("Strafe für zu schnelles Fahren. zahle 300.", "pay"),
            new CommunityChest("Rücke bis auf Los vor.", "move"),
        };
    }
}
