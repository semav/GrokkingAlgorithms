package semav.Dijkstra;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("start", "a", 6);
        graph.addEdge("start", "b", 2);
        //graph.addEdge("start", "c", 1);

        graph.addEdge("a", "fin", 1);
        graph.addEdge("b", "a", 3);
        graph.addEdge("b", "fin",5);
        //graph.addEdge("c", "fin",1);

        System.out.println(graph.pathExists("start", "fin"));
    }
}

class Edge {
    int weight;
    String node;

    Edge(String node, int weight) {
        this.weight = weight;
        this.node = node;
    }
}

class Graph {
    private HashMap<String, ArrayList<Edge>> edges = new HashMap<>();

    int pathExists(String start, String finish) {
        HashMap<String, Integer> costs = new HashMap<>();
        HashMap<String, String> parents = new HashMap<>();
        HashSet<String> processed = new HashSet<>();

        edges.get(start).forEach(e->{
            costs.put(e.node, e.weight);
            parents.put(e.node, start);
        });

        String lowestCostNode = getLowestCostNode(costs, processed);
        while (lowestCostNode != null) {
            int cost = costs.get(lowestCostNode);
            ArrayList<Edge> neighbors = edges.get(lowestCostNode);

            if (neighbors != null) {
                for (Edge neighbor : neighbors) {
                    final int newCost = cost + neighbor.weight;
                    final String parentNode = lowestCostNode;

                    costs.compute(neighbor.node, (k, c) -> {
                        if (c == null || newCost < c) {
                            parents.put(neighbor.node, parentNode);
                            return newCost;
                        }
                        return c;
                    });
                }
            }

            processed.add(lowestCostNode);
            lowestCostNode = getLowestCostNode(costs, processed);
        }

        return costs.get(finish);
    }

    private String getLowestCostNode(HashMap<String, Integer> costs, HashSet<String> processed) {
        String node = null;
        int cost = Integer.MAX_VALUE;

        for(HashMap.Entry<String, Integer> entry : costs.entrySet()) {
            if (entry.getValue() < cost && !processed.contains(entry.getKey())) {
                node = entry.getKey();
                cost = entry.getValue();
            }
        }

        return node;
    }

    void addEdge(String from, String to, int weight) {
        edges.compute(from, (k, e) -> {
            if (e == null)
                e = new ArrayList<>();
            e.add(new Edge(to, weight));
            return e;
        });
    }
}
