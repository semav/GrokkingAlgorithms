package semav.DynamicProgramming;

import java.util.*;

public class KnapsackProblem {
    private static final List<Item> items = new ArrayList<>();

    static {
        items.add(new Item("guitar", 1500, 1));
        items.add(new Item("stereo", 3000, 4));
        items.add(new Item("laptop", 2000, 3));
        items.add(new Item("diamond", 10000, 1));
        items.add(new Item("chair", 444, 3));
    }

    public static void main(String[] args) {
        int colTo = 5;
        int colFrom = items.stream().map(Item::getWeight).min(Integer::compare).orElse(0);
        Grid grid = new Grid();

        for (Item item : items) {
            grid.makeNewRow();
            for (int col = colFrom; col <= colTo; col++) {
                grid.addItem(col, item);
            }
        }

        grid.getCell(colTo).getItems().forEach(System.out::println);
        System.out.println(grid.getCell(colTo).getPrice());
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
        if (columnIndex < item.getWeight()) {
            Cell prevRowCell = prevRow.get(columnIndex);
            if (prevRowCell != null) {
                row.put(columnIndex, prevRowCell);
            }
            return;
        }

        row.compute(columnIndex, (i , cell) -> {
            Cell prevRowCell = null;
            Cell remainingSpaceCell = null;

            if (prevRow != null) {
                prevRowCell = prevRow.get(i);
                remainingSpaceCell = prevRow.get(i - item.getWeight());
            }

            int currentPrice = item.getPrice() + (remainingSpaceCell == null ? 0 : remainingSpaceCell.getPrice());

            if (prevRowCell == null || prevRowCell.getPrice() < currentPrice) {
                List<Item> items = new ArrayList<>();
                items.add(item);
                if (remainingSpaceCell != null)
                    items.addAll(remainingSpaceCell.getItems());

                return new Cell(items);
            }
            else {
                return new Cell(prevRowCell.getItems());
            }
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

    Cell(List<Item> items) {
        this.items.addAll(items);
        this.price = items.stream().map(Item::getPrice).reduce(Integer::sum).orElse(0);
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