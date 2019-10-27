package semav.GreedyAlgorithms;

import java.util.*;

public class SetCovering {
    public static void main(String[] args) {
        Set<String> statesNeeded = new HashSet<>(Arrays.asList("mt", "wa", "or", "id", "nv", "ut", "ca", "az"));
        Map<String, Set<String>> stations = new HashMap<String, Set<String>>() {
            {
                put("kone", new HashSet<>(Arrays.asList("id", "nv", "ut")));
                put("ktwo", new HashSet<>(Arrays.asList("wa", "id", "mt")));
                put("kthree", new HashSet<>(Arrays.asList("or", "nv", "ca")));
                put("kfour", new HashSet<>(Arrays.asList("nv", "ut")));
                put("kfive", new HashSet<>(Arrays.asList("ca", "az")));
            }
        };


        Set<String> finalStations = new HashSet<>();

        System.out.println(finalStations);
    }
}
