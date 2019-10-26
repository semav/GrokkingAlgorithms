package semav.Dijkstra;

import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.addEdge("start", "a", 6);
        graph.addEdge("start", "b", 2);
        graph.addEdge("start", "c", 2);

        graph.addEdge("a", "fin", 1);
        graph.addEdge("b", "a", 3);
        graph.addEdge("b", "fin",5);
        graph.addEdge("c", "fin",20);

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
        HashMap<String, Integer> weights = new HashMap<>();
        HashMap<String, String> parents = new HashMap<>();
        HashSet<String> processed = new HashSet<>();

        // Initialize weights and parents for neighbours of start node
        edges.get(start).forEach(e -> {
            weights.put(e.node, e.weight);
            parents.put(e.node, start);
        });

        String lowestWeightNode = getLowestWeightNode(weights, processed);

        while (lowestWeightNode != null) {
            int weight = weights.get(lowestWeightNode);
            ArrayList<Edge> neighbors = edges.get(lowestWeightNode);

            if (neighbors != null) {
                for (Edge neighbor : neighbors) {
                    final int neighborWeight = weight + neighbor.weight;
                    final String parentNode = lowestWeightNode;

                    weights.compute(neighbor.node, (k, w) -> {
                        if (w == null || neighborWeight < w) {
                            parents.put(neighbor.node, parentNode);
                            return neighborWeight;
                        }
                        return w;
                    });
                }
            }

            processed.add(lowestWeightNode);
            lowestWeightNode = getLowestWeightNode(weights, processed);
        }

        return weights.get(finish);
    }

    /**
    Returns a node that has the lowest weight and isn't processed
     */
    private String getLowestWeightNode(HashMap<String, Integer> weights, HashSet<String> processed) {
        String lowestWeightNode = null;
        int lowestWeight = Integer.MAX_VALUE;

        for(HashMap.Entry<String, Integer> entry : weights.entrySet()) {
            int weight = entry.getValue();
            String node = entry.getKey();
            if (weight < lowestWeight && !processed.contains(node)) {
                lowestWeightNode = node;
                lowestWeight = weight;
            }
        }

        return lowestWeightNode;
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
