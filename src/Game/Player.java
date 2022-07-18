package Game;

import Game.Buyables.Buyables;
import Game.Cards.Chance;
import Game.Cards.CommunityChest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Player {
    String name;
    int money;
    int position;
    ArrayList<Buyables> properties;
    int doubles;
    boolean ccFree;
    boolean cFree;

    static boolean loop;

    // region Cards
    static Chance[] chanceCards = new Chance[] {
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

    static CommunityChest[] communityChestCards = new CommunityChest[] {
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
            changeMoney(4000, true);
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

    // section Other
    public void move() {
            int r1 = 1 + (int) (Math.random() * 6);
            int r2 = 1 + (int) (Math.random() * 6);
            System.out.println(name + " rolled " + r1 + " and " + r2);
            moveAhead(r1 + r2);
            checkDoubles(r1, r2);
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
    public void chance() {
        int r = (int) (Math.random() * 16);
        chanceCards[r].doEffect(this);
    }
    public void communityChest() {
        int r = (int) (Math.random() * 16);
        communityChestCards[r].doEffect(this);
    }
    public void changeMoney(int amount, boolean add) {
        if (add) {this.money += amount;}
        else {this.money -= amount;}
        if (this.money < 0) {
            //TODO: Implement player removal due to insufficient cash
            //TODO: Implement paying in properties if the player has no cash
        }
    }
    public void buy(Buyables property, Player seller, int cost) {
        this.changeMoney(cost, false);
        properties.add(property);
        if (seller != null) {
            seller.removeProperty(property);
            seller.changeMoney(cost, true);
        }
    }
    public void removeProperty(Buyables property) {
        properties.remove(property);
    }
    public void imprisoned() {
        System.out.println("You are in prison.");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            loop = true;
            while (loop) {
                if (doubles >= 3) {
                    doubles = 0;
                    System.out.println("YOu couldn't free yourself. Now you need to pay");
                    this.changeMoney(50, false);
                    move();
                    break;
                }

                System.out.println("How do you want to free yourself?");
                System.out.println("1. Pay $50 to get out of prison.");
                System.out.println("2. Use a card from your hand.");
                System.out.println("3. Roll");
                String input = br.readLine();
                switch (input) {
                    case "1" -> {
                        this.changeMoney(50, false);
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
                            this.changeMoney(50, false);
                            move();
                            break;
                        }
                        int r1 = (int) (Math.random()*6)+1;
                        int r2 = (int) (Math.random()*6)+1;
                        System.out.println("You rolled a " + r1 + " and " + r2);
                        if (!(r1 == r2)) doubles++;
                        else {
                            doubles = 0;
                            moveAhead(r1+r2);
                            checkDoubles(r1,r2);
                        }
                    }
                    default -> System.out.println("Invalid input, please enter 1, 2 or 3");
                }
            }
        } catch (Exception e) {
            System.err.println("Error encountered");
        }
    }
    public void doAction() {

    }

}