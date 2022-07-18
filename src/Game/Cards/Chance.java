package Game.Cards;

import Game.Buyables.Buyables;
import Game.Buyables.Street;
import Game.Main;
import Game.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chance {
    String effect;
    String shorthand;

    Pattern p;
    Matcher m;
    int amount;

    public Chance(String effect, String shorthand) {
        this.effect = effect;
        this.shorthand = shorthand;
    }

    public void doEffect(Player player) {
        switch (shorthand) {
            case "pay" -> {
                p = Pattern.compile("\\d0{2,3}");
                m = p.matcher(effect);
                if (m.find()) amount = Integer.parseInt(m.group());
                player.changeMoney(amount, false);
                System.out.println("You paid $" + amount);
            }
            case "get" -> {
                p = Pattern.compile("\\d0{2,3}");
                m = p.matcher(effect);
                if (m.find()) amount = Integer.parseInt(m.group());
                player.changeMoney(amount, true);
                System.out.println("You received $" + amount);
            }
            case "move" -> {
                player.setPosition(0, true);
                System.out.println("You moved to Go");
            }
            case "prison" -> {
                player.setPosition(40, false);
                System.out.println("You are now in prison");
            }
            case "prison_free" -> {
                player.setCFree(true);
                System.out.println("You received a Get Out of Jail Free card");
            }
            case "pay_special" -> {
                int houses = 0;
                int hotels = 0;
                ArrayList<Buyables> properties = player.getProperties();
                for (Buyables property : properties) {
                    if (property instanceof Street) {
                        if (((Street) property).getHouses() == 5) {
                            hotels++;
                        } else {
                            houses += ((Street) property).getHouses();
                        }
                    }
                }
                player.changeMoney(houses * 500 + hotels * 2000, false);
                System.out.println("You paid $" + (houses * 500 + hotels * 2000) + " for " + houses + " houses and " + hotels + " hotels");
            }
            case "get_special" -> {
                Player[] players = Main.getPlayers();
                for (Player player1 : players) {
                    if (player1.equals(player)) {
                        player.changeMoney((players.length - 1) * 1000, true);
                    } else {
                        player1.changeMoney(1000, false);
                    }
                }
                System.out.println("You received $1000 from each player");
            }
        }
    }
}
