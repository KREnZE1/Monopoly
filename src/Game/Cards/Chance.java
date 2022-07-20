package Game.Cards;

import Game.Buyables.Buyables;
import Game.Buyables.Street;
import Game.Main;
import Game.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Chance {
    final String effect;
    final String shorthand;

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
                player.changeMoney(-amount);
                Main.print("You paid $" + amount);
            }
            case "get" -> {
                p = Pattern.compile("\\d0{2,3}");
                m = p.matcher(effect);
                if (m.find()) amount = Integer.parseInt(m.group());
                player.changeMoney(amount);
                Main.print("You received $" + amount);
            }
            case "move" -> {
                player.setPosition(0, true);
                Main.print("You moved to Go");
            }
            case "prison" -> {
                player.setPosition(40, false);
                Main.print("You are now in prison");
            }
            case "prison_free" -> {
                player.setCFree(true);
                Main.print("You received a Get Out of Jail Free card");
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
                player.changeMoney(-(houses * 500 + hotels * 2000));
                Main.print("You paid $" + (houses * 500 + hotels * 2000) + " for " + houses + " houses and " + hotels + " hotels");
            }
            case "get_special" -> {
                //Get 1000 from every player
                player.changeMoney(1000, Main.getPlayers());
                for (int i=0; i<Main.getPlayers().length-1; i++) player.changeMoney(1000);
                Main.print("You received $1000 from each player");
            }
        }
    }
    public String getEffect() {return this.effect;}
}
