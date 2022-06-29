package Game.Cards;

import Game.Buyables.Buyables;
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
                amount = Integer.parseInt(m.group());
                player.changeMoney(amount, false);
            }
            case "get" -> {
                p = Pattern.compile("\\d0{2,3}");
                m = p.matcher(effect);
                amount = Integer.parseInt(m.group());
                player.changeMoney(amount, true);
            }
            case "move" -> player.setPosition(0, true);
            case "prison" -> player.setPosition(40, false);
            case "prison_free" -> player.setCFree(true);
            case "pay_special" -> {
                int houses = 0;
                int hotels = 0;
                ArrayList<Buyables> properties = player.getProperties();
                for (Buyables property : properties) {
                    if (property != null) {
                        if (property.getHouses() == 5) {
                            hotels++;
                        } else {
                            houses += property.getHouses();
                        }
                    }
                }
                player.changeMoney(houses * 500 + hotels * 2000, false);
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
            }
        }
    }
}
