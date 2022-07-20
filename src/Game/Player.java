package Game;

import Game.Buyables.Buyables;
import Game.Buyables.Street;
import Game.Cards.Chance;
import Game.Cards.CommunityChest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Locale;

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
    static String input;

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
        Main.print("You are in prison.");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            loop = true;
            while (loop) {
                if (doubles >= 3) {
                    doubles = 0;
                    Main.print("You couldn't free yourself. Now you need to pay");
                    this.changeMoney(-1000);
                    move();
                    break;
                }

                Main.print("How do you want to free yourself?");
                Main.print("1. Pay $1000 to get out of prison.");
                Main.print("2. Use a card from your hand.");
                Main.print("3. Roll");
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
                        } else Main.print("You have no card to free yourself from prison");
                    }
                    case "3" -> {
                        loop = false;
                        if (doubles >= 3) {
                            doubles = 0;
                            Main.print("Du hast dich nicht befreien können. Nun musst du bezahlen.");
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
                    default -> Main.print("Invalid input, please enter 1, 2 or 3");
                }
            }
        } catch (Exception e) {
            System.err.println("Error encountered");
        }
    }

    // section Other
    public void chance() {
        int r = (int) (Math.random() * 16);
        Main.print(chanceCards[r].getEffect());
        chanceCards[r].doEffect(this);
    }
    public void communityChest() {
        int r = (int) (Math.random() * 16);
        Main.print(communityChestCards[r].getEffect());
        communityChestCards[r].doEffect(this);
    }
    public void changeMoney(int amount, Player[] recipients) {
        //Recipient ist der Spieler, der Geld bekommt, wenn mehrere Spieler involviert sind. In dem Fall sollte Amount negativ sein
        for (Player player : recipients) player.changeMoney(-amount);
        this.changeMoney(amount);
    }
    public void changeMoney(int amount) {
        this.money += amount;

        while (this.money<0 && this.properties.size()>0) {
            Main.print("You are in debt. You have to pay $" + Math.abs(this.money));
            Main.print("On which property do you want to make money?");
            for (Buyables property : this.properties) {
                Main.print(property.getName());
            }
            try {
                input = Main.bufferedReader.readLine();
            } catch (Exception e) {System.err.println("Error encountered with bankruptcy");}
            for (Buyables property : this.properties) {
                if (property.getName().equals(input)) {
                    if (property instanceof Street && ((Street) property).getHouses() > 0) {
                        ((Street) property).removeHouse();
                        this.changeMoney(((Street) property).getHousePrice()/2);
                    } else {
                        this.changeMoney(property.getBasePrice());
                        this.properties.remove(property);
                    }
                    break;
                }
            }
        }
        if (this.money<0) {
            Main.print("You have no more properties to pay off. You are bankrupt.");
            Main.remove(this);
        }
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
            Main.display(this);
            Main.print("1. Do you want to check your properties?");
            Main.print("2. Do you want to build houses/hotels?");
            try {
                while (!((input = Main.bufferedReader.readLine()).equals("1") || input.equals("2"))) {
                    Main.print("Invalid input, please enter 1 or 2");
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
        Main.print("Which property do you want to build houses/a hotel on?");
        try {
            input = Main.bufferedReader.readLine().strip().toLowerCase();
            for (Buyables property : properties) {
                if (property.getName().toLowerCase().equals(input) && (property instanceof Street) && ((Street) property).getHouses() < 4) {
                    loop = false;
                    this.changeMoney(-((Street) property).getHousePrice());
                    ((Street) property).addHouse();
                    return;
                }
            }
        } catch (IOException e) {
            System.err.println("Error encountered");
        }
        Main.print("Your input wasn't a valid street");
    }
    private void checkProperties() {
        Main.print("You own the following properties:");
        for (Buyables property : properties) {
            if (property instanceof Street) {
                Main.print(property.getName() + " with " + ((Street) property).getHouses() + " houses");
            } else {
                Main.print(property.getName());
            }
            Main.print("The rent is currently $" + property.getCurrPrice());
        }
    }
}