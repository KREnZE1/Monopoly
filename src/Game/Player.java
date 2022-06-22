package Game;

import Game.Buyables.Buyables;
import Game.Cards.Chance;
import Game.Cards.CommunityChest;

public class Player {
    String name;
    int money;
    int position;
    Buyables[] properties;
    int doubles;
    boolean ccFree;
    boolean cFree;

    public Player(String name, int money) {
        this.name = name;
        this.money = money;
        this.position = 0;
        this.properties = new Buyables[22];
        doubles = 0;
        cFree = false;
        ccFree = false;
    }

    public void move() {
        int r1 = (int) (Math.random() * 6);
        int r2 = (int) (Math.random() * 6);

        this.position += (r1 + r2);
        if (this.position > 39) {
            this.position -= 40;
        }

        if (r1 == r2) {
            this.doubles++;
            if (doubles == 3) {
                this.position = 40;
            }
            move();
        } else {
            this.doubles = 0;
        }
    }
    public void chance() {
        //TODO
    }
    public void communityChest() {
        //TODO
    }
    public void changeMoney(int amount, boolean add) {
        if (add) {this.money += amount;}
        else {this.money -= amount;}
    }
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
    public Buyables[] getProperties() {return this.properties;}

}
