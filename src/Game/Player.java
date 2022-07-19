package Game;

import Game.Buyables.Buyables;
import Game.Buyables.Street;
import Game.Cards.Chance;
import Game.Cards.CommunityChest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Player {
    final String name;
    int money;
    int position;
    @SuppressWarnings("CanBeFinal")
    ArrayList<Buyables> properties;
    int doubles;
    boolean ccFree;
    boolean cFree;

    static boolean loop;

    // region Cards
    final static Chance[] chanceCards = new Chance[] {
            new Chance("Du hast den 2. Preis in einer Schönheitskonkurrenz gewonnen. Ziehe 200 ein.", "get"),
            new Chance("Du hast in einem Kreuzworträtsel gewonnen. Ziege 2000 ein.", "get"),
            new Chance("Einkommenssteuer-Rückzahlung. Ziehe 400 ein.", "get"),
            new Chance("Zahle 3000 Schulgeld.", "pay"),
            new Chance("Rücke vor bis auf Los.", "move"),
            new Chance("Arztkosten. Zahle 1000.", "pay"),
            new Chance("Die Jahresrente wird fällig. Ziehe 2000 ein.", "get"),
            new Chance("Gehe in das Gefängnis. Begib dich direkt dorthin. Gehe nicht über Los. Ziehe nicht 4000 ein.", "prison"),
            new Chance("Zahle 800 pro Haus und 2300 pro Hotel, das du besitzt.", "pay_special"),
            new Chance("Du erhältst auf Aktien Dividende. Ziehe 900 ein.", "get"),
            new Chance("Du erbst 2000.", "get"),
            new Chance("Aus Lagerverkäufen erhältst du 500.", "get"),
            new Chance("Zahle 2000 an das Krankenhaus.", "pay"),
            new Chance("Du kommst aus dem Gefängnis frei. Behalte diese Karte, bis du sie verwendest oder verkaufst.", "prison_free"),
            new Chance("Es ist dein Geburtstag. Ziehe 1000 von jedem Spieler ein.", "get_special"),
            new Chance("Bank-Irrtum. Ziehe 4000 ein.", "get"),
    };

    final static CommunityChest[] communityChestCards = new CommunityChest[] {
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
    // endregion

    // section Constructor
    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.position = 0;
        properties = new ArrayList<>();
        doubles = 0;
        cFree = false;
        ccFree = false;
    }

    // section Getter & Setter
    public void setPosition(int position, boolean forward) {
        if (forward && this.position > position) {
            this.position = position;
            changeMoney(4000);
        } else {
            this.position = position;
        }
    }
    public int getPosition() {
        return this.position;
    }
    public void setCCFree(boolean input) {this.ccFree = input;}
    public void setCFree(boolean input) {this.cFree = input;}
    public ArrayList<Buyables> getProperties() {return this.properties;}
    public String getName() {return this.name;}
    public int getMoney() {return this.money;}

    // section Movement
    public void move() {
            int[] r = Main.rollDice();
            moveAhead(r[0] + r[1]);
            checkDoubles(r[0], r[1]);
    }
    public void moveAhead(int steps) {
        this.position += steps;
        if (this.position > 39) {
            this.position -= 40;
        }
    }
    public void checkDoubles(int x, int y) {
        if (x == y) {
            this.doubles++;
            if (doubles == 3) {
                this.position = 40;
            }
            Main.board[this.getPosition()].action(this);
            this.doAction();
            move();
        } else {
            this.doubles = 0;
        }
    }
    public void imprisoned() {
        System.out.println("You are in prison.");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            loop = true;
            while (loop) {
                if (doubles >= 3) {
                    doubles = 0;
                    System.out.println("You couldn't free yourself. Now you need to pay");
                    this.changeMoney(-1000);
                    move();
                    break;
                }

                System.out.println("How do you want to free yourself?");
                System.out.println("1. Pay $1000 to get out of prison.");
                System.out.println("2. Use a card from your hand.");
                System.out.println("3. Roll");
                String input = br.readLine();
                switch (input) {
                    case "1" -> {
                        this.changeMoney(-1000);
                        loop = false;
                    }
                    case "2" -> {
                        if (this.ccFree) {
                            this.ccFree = false;
                            loop = false;
                        } else if (this.cFree) {
                            this.cFree = false;
                            loop = false;
                        } else System.out.println("You have no card to free yourself from prison");
                    }
                    case "3" -> {
                        loop = false;
                        if (doubles >= 3) {
                            doubles = 0;
                            System.out.println("Du hast dich nicht befreien können. Nun musst du bezahlen.");
                            this.changeMoney(-1000);
                            move();
                            break;
                        }
                        int[] r = Main.rollDice();
                        if (!(r[0] == r[1])) doubles++;
                        else {
                            doubles = 0;
                            moveAhead(r[0]+r[1]);
                            checkDoubles(r[0],r[1]);
                        }
                    }
                    default -> System.out.println("Invalid input, please enter 1, 2 or 3");
                }
            }
        } catch (Exception e) {
            System.err.println("Error encountered");
        }
    }

    // section Other
    public void chance() {
        int r = (int) (Math.random() * 16);
        chanceCards[r].doEffect(this);
    }
    public void communityChest() {
        int r = (int) (Math.random() * 16);
        communityChestCards[r].doEffect(this);
    }
    public void changeMoney(int amount, Player[] recipients) {
        //Recipient ist der Spieler, der Geld bekommt, wenn mehrere Spieler involviert sind. In dem Fall sollte Amount negativ sein
        for (Player player : recipients) player.changeMoney(-amount);
        this.changeMoney(amount);
    }
    public void changeMoney(int amount) {
        this.money += amount;
        //TODO: Logic for bankruptcy
    }
    public void buy(Buyables property, Player seller, int cost) {
        this.changeMoney(-cost);
        property.setOwner(this);
        properties.add(property);
        if (seller != null) {
            seller.removeProperty(property);
            seller.changeMoney(cost);
        }
    }
    public void removeProperty(Buyables property) {
        properties.remove(property);
    }


    //section Action after turn
    public void doAction() {
        while (Main.getConfirmation("Do you want to do something?") && !properties.isEmpty()) {
            System.out.println("1. Do you want to check your properties?");
            System.out.println("2. Do you want to build houses/hotels?");
            String input;
            try {
                while (!((input = Main.bufferedReader.readLine()).equals("1") || input.equals("2"))) {
                    System.out.println("Invalid input, please enter 1 or 2");
                }
                if (input.equals("1")) {
                    this.checkProperties();
                } else {
                    this.build();
                }
            } catch (Exception e) {
                System.err.println("Error encountered with the player doing an action after their turn");
            }
        }
    }
    private void build() {
        System.out.println("Which property do you want to build houses/a hotel on?");
        loop = true;
        while (loop) {
            try {
                String input = Main.bufferedReader.readLine();
                for (Buyables property : properties) {
                    if (property.getName().equals(input) && (property instanceof Street) && ((Street) property).getHouses() < 4) {
                        loop = false;
                        this.changeMoney(-((Street) property).getHousePrice());
                        ((Street) property).addHouse();
                    }
                }
            } catch (IOException e) {
                System.err.println("Error encountered");
            }
        }
    }
    private void checkProperties() {
        System.out.println("You own the following properties:");
        for (Buyables property : properties) {
            if (property instanceof Street) {
                System.out.println(property.getName() + " with " + ((Street) property).getHouses() + " houses");
            } else {
                System.out.println(property.getName());
            }
            System.out.println("The rent is currently $" + property.getCurrPrice());
        }
    }
}