package Game;

public class Event extends Location {
    String name;
    String group;

    public Event(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public void action(Player player) {
        switch (this.group) {
            case "event_field" -> {
                System.out.println("You drew a chance card");
                player.chance();
            }
            case "community_chest" -> {
                System.out.println("You drew a community chest card");
                player.communityChest();
            }
            case "pay2k" -> {
                player.changeMoney(2000, false);
                System.out.println("You paid $2000");
            }
            case "pay4k" -> {
                player.changeMoney(4000, false);
                System.out.println("You paid $4000");
            }
            case "to_prison" -> {
                player.position = 40;
                System.out.println("You are now in prison");
            }
            case "get4k" -> {
                player.changeMoney(4000, true);
                System.out.println("You received $4000");
            }
        }
    }

    public String getName() {
        return name;
    }

}
