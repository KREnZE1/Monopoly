package Game;

import Game.Buyables.Buyables;
import Game.Buyables.Pairings;
import Game.Buyables.Street;

import java.io.*;
import java.util.Arrays;


public class Main {
    @SuppressWarnings("CanBeFinal")
    public static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    public static BufferedWriter bufferedWriter;
    static {
        try {
            bufferedWriter = new BufferedWriter(new FileWriter("output.txt", true));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Location[] board;
    static Player[] players;

    public static void main(String[] args) throws InterruptedException{
        setup();
        for (int i=0; i<10; i++) {
            round();
        }
    }

    public static void setup() {
        board = new Location[]{
                new Event("Los", "get4k"), //Go
                new Street("Badstraße", 1200, new int[]{40, 200, 600, 1800, 3200, 5000}, 1000, "dunkellila"),
                new Event("Gemeinschaftsfeld", "community_chest"),
                new Street("Turmstraße", 1200, new int[]{80, 400, 1200, 3600, 6400, 9000}, 1000, "dunkellila"),
                new Event("Einkommenssteuer", "pay4k"), //Einkommenssteuer
                new Pairings("Südbahnhof", 4000, new int[]{500, 1000, 2000, 4000}, "Train_Station"), //Südbahnhof
                new Street("Chausseestraße", 2000, new int[]{120, 600, 1800, 5400, 8000, 1100}, 1000, "hellblau"),
                new Event("Ereignis", "event_field"),
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
                new Player("Player 2", 8000),
        };

        try {
            bufferedWriter.write("\n-----------------\n\n");
        } catch (Exception e) {System.err.println(e.getMessage() + " | Exception beim Setup");}
    }

    public static void round() throws InterruptedException{
        for (Player player : players) {
            Main.print("\n");
            display(player);
            if (player.getPosition() == 40) player.imprisoned();
            else player.move();
            board[player.getPosition()].action(player);
            player.doAction();
            Thread.sleep(1000);
        }
    }

    public static Player[] getPlayers() {return players;}
    public static Player getPlayer(int i) {return players[i];}
    public static Location[] getBoard() {return board;}

    public static void display(Player player) {
        print("Spieler: " + player.getName() + " | Geld: " + player.getMoney() + " | Position: " + board[player.getPosition()].getName());
    }
    public static void displayAll() {
        for (Player player : Main.getPlayers()) display(player);
    }

    public static boolean getConfirmation(String message) {
        print(message);
        try {
            String input;
            do {
                System.out.print("[Y/N]: ");
                input = bufferedReader.readLine().strip().toUpperCase();
            } while (!input.equals("Y") && !input.equals("N"));
            return input.equals("Y");
        } catch (IOException e) {
            System.err.println(e.getMessage() + " | Exception with a confirmation");
        }
        return false;
    }

    public static void auction(Buyables property) {
        int highestBid = (int) Math.ceil(property.getBasePrice()*0.5);
        Player highestBidder = null;
        int bidders = players.length;
        while (bidders > 1) {
            bidders = players.length;
            for (Player player : players) {
                print(player.getName() + ": " + player.getMoney() + " | " + highestBid + " | What do you want to bid?");
                print("If your bid is not a number or lower than the highest bid you won't bid in this round");
                try {
                    String input = bufferedReader.readLine().strip();
                    if (Integer.parseInt(input) > highestBid) {
                        highestBid = Integer.parseInt(input);
                        highestBidder = player;
                    }
                    else bidders -= 1;
                } catch (NumberFormatException nfe) {
                    bidders -= 1;
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        }
        if (highestBidder == null) {
            print("No one bid on this property");
            return;
        }
        print(highestBidder.getName() + " bought the property for $" + highestBid);

    }

    public static int[] rollDice() {
        int[] dice = new int[2];
        dice[0] = (int) (Math.random() * 6) + 1;
        dice[1] = (int) (Math.random() * 6) + 1;
        print("You rolled a " + dice[0] + " and a " + dice[1]);
        return dice;
    }

    public static void remove(Player player) {
        boolean hit = false;
        for (int i=0; i<players.length; i++) {
            if (hit) {
                players[i-1] = players[i];
                players[i] = null;
            } else if (players[i].equals(player)) {
                hit = true;
            }
        }
        players = Arrays.copyOf(players, players.length-1);
        if (players.length == 1) gameOver();
    }

    public static void gameOver() {
        print("We have a winner!");
        print("The winner is "+players[0].getName()+" with $"+players[0].getMoney());
        print("Their properties are as follows:");
        for (Buyables buyables : players[0].getProperties()) {
            print(buyables.getName());
        }
        print("Thanks for playing!");
        System.exit(0);
    }

    public static void print(String message) {
        System.out.println(message);
        try {bufferedWriter.write(message);} catch(Exception e) {System.err.println(e.getMessage() + " | Exception with printing");}
    }
}