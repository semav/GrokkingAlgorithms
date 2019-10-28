package semav.DynamicProgramming;

import java.util.ArrayList;
import java.util.List;

public class KnapsackProblem {
    private static final List<Item> items = new ArrayList<>();

    static {
        items.add(new Item("stereo", 3000, 4));
        items.add(new Item("laptop", 2000, 3));
        items.add(new Item("guitar", 1500, 1));
    }

    public static void main(String[] args) {

    }
}

final class Item {
    private String name;
    private int price;
    private int weight;

    Item(String name, int price, int weight) {
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public int getWeight() {
        return weight;
    }
}