package semav.DynamicProgramming;

import java.util.*;

public class KnapsackProblem {
    private static final List<Item> items = new ArrayList<>();

    static {
        items.add(new Item("guitar", 1500, 1));
        items.add(new Item("stereo", 3000, 4));
        items.add(new Item("laptop", 2000, 3));
    }

    public static void main(String[] args) {
        int colTo = items.stream().map(Item::getWeight).max(Integer::compare).orElse(0);
        int colFrom = 4;
        Grid grid = new Grid();

        for (Item item : items) {
            grid.makeNewRow();
            for (int col = colFrom; col <= colTo; col++) {
                grid.addItem(col, item);
            }
        }

        grid.getCell(colTo).getItems().forEach(System.out::println);
    }
}

final class Grid {
    private HashMap<Integer, Cell> prevRow;
    private HashMap<Integer, Cell> row;

    Cell getCell(int index) {
        return row.get(index);
    }

    void makeNewRow() {
        prevRow = row;
        row = new HashMap<>();
    }

    void addItem(int columnIndex, Item item) {
        if (columnIndex < item.getWeight())
            return;

        row.compute(columnIndex, (i , cell) -> {
            if (cell == null || item.getPrice() > cell.getPrice()) {
                return new Cell(item);
            }
            return cell;
        });
    }
}

final class Cell implements Comparable<Cell> {
    private List<Item> items = new ArrayList<>();
    private int price;

    Cell(Item item) {
        this.items.add(item);
        this.price = item.getPrice();
    }

    List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    int getPrice() {
        return price;
    }

    @Override
    public int compareTo(Cell cell) {
        if (cell == null)
            return 1;
        return price - cell.price;
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

    int getPrice() {
        return price;
    }

    int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}