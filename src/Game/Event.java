package Game;

public class Event extends Location {
    String name;
    String group;

    public Event(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public void doEffect(Player player) {
        switch (group) {
            case "Chance" -> player.chance();
            case "Community Chest" -> player.communityChest();
            case "pay2k" -> player.changeMoney(2000, false);
            case "pay4k" -> player.changeMoney(4000, false);
            case "to_prison" -> player.position = 40;
            case "get4k" -> player.changeMoney(4000, true);
        }
    }

}
