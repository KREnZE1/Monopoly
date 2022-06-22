package Game;

public class Event extends Location {
    String name;
    String group;

    public Event(String name, String group) {
        this.name = name;
        this.group = group;
    }

    public void doEffect() {
        // TODO
        if (group.equals("Chance")) {
            // TODO
        } else if (group.equals("Community Chest")) {
            // TODO
        }
    }
}
