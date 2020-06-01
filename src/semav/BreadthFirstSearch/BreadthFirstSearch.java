package semav.BreadthFirstSearch;

import java.util.*;

public class BreadthFirstSearch {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(2, 4);
        graph.addEdge(3, 4);
        graph.addEdge(3, 6);
        graph.addEdge(6, 7);
        graph.addEdge(4, 5);
        graph.addEdge(8, 9);
        graph.addEdge(9, 10);

        System.out.println(graph.pathExists(3, 7));
        System.out.println("Anna's Test");
    }
}

class Graph {
    private HashMap<Integer, ArrayList<Integer>> edges = new HashMap<>();

    boolean pathExists(Integer a, Integer b) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>(edges.get(a));

        while (!queue.isEmpty()) {
            Integer node = queue.poll();

            if (visited.contains(node)){
                continue;
            }

            if (node.equals(b)){
                return true;
            }

            visited.add(node);
            queue.addAll(edges.get(node));
        }

        return false;
    }

    void addEdge(Integer a, Integer b) {
        edges.compute(a, (k, v) -> {
            if (v == null)
                v = new ArrayList<>();
            v.add(b);
            return v;
        });

        edges.compute(b, (k, v) -> {
            if (v == null)
                v = new ArrayList<>();
            v.add(a);
            return v;
        });
    }
}
