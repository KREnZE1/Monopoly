package Game;

public class Event extends Location {
    final String name;
    final String group;

    public Event(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public void action(Player player) {
        switch (this.group) {
            case "event_field" -> {
                Main.print("You drew a chance card");
                player.chance();
            }
            case "community_chest" -> {
                Main.print("You drew a community chest card");
                player.communityChest();
            }
            case "pay2k" -> {
                player.changeMoney(-2000);
                Main.print("You paid $2000");
            }
            case "pay4k" -> {
                player.changeMoney(-4000);
                Main.print("You paid $4000");
            }
            case "to_prison" -> {
                player.position = 40;
                Main.print("You are now in prison");
            }
            case "get4k" -> {
                player.changeMoney(4000);
                Main.print("You received $4000");
            }
        }
    }

    public String getName() {
        return name;
    }

}
