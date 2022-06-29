package Game.Cards;

import Game.Buyables.Buyables;
import Game.Player;

import java.util.ArrayList;
import java.util.regex.*;

public class CommunityChest {
    String effect;
    String shorthand;

    Pattern p;
    Matcher m;
    int amount;

    public CommunityChest(String effect, String shorthand) {
        this.effect = effect;
        this.shorthand = shorthand;
    }

    public void doEffect(Player player) {
        switch (shorthand) {
            case "pay":
                p = Pattern.compile("\\d0{2,3}");
                m = p.matcher(effect);
                amount = Integer.parseInt(m.group());
                player.changeMoney(amount, false);
                break;
            case "get":
                p = Pattern.compile("\\d0{2,3}");
                m = p.matcher(effect);
                amount = Integer.parseInt(m.group());
                player.changeMoney(amount, true);
                break;
            case "move":
                if (effect.contains("Opernplatz")) {player.setPosition(24, true);}
                else if (effect.contains("Seestraße")) {player.setPosition(11, true);}
                else if (effect.contains("Badstraße")) {player.setPosition(1, false);}
                else if (effect.contains("3")) {player.setPosition(player.getPosition()-3, false);}
                else if (effect.contains("Gefängnis")) {player.setPosition(40, false);}
                else if (effect.contains("Schlossallee")) {player.setPosition(39, true);}
                else if (effect.contains("Südbahnhof")) {player.setPosition(5, true);}
                else if (effect.contains("Los")) {player.setPosition(0, true);}
                else {System.out.println("Error: Unknown move");}
                break;
            case "prison":
                player.setPosition(40, false);
                break;
            case "prison_free":
               player.setCCFree(true);
                break;
            case "pay_special":
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
                player.changeMoney(houses*500 + hotels*2000, false);
                break;
            case "move_special":
                if (player.getPosition()<5 || player.getPosition()>=35) {player.setPosition(5, true);}
                else if (player.getPosition()<15) {player.setPosition(15, true);}
                else if (player.getPosition()<25) {player.setPosition(25, true);}
                else if (player.getPosition()<35) {player.setPosition(35, true);}
                else {System.out.println("Error: Unknown move");}
                break;
        }
    }
}
