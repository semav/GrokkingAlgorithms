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

        while (!statesNeeded.isEmpty()) {
            String bestStation = getBestStation(stations, statesNeeded);

            statesNeeded.removeAll(stations.get(bestStation));
            finalStations.add(bestStation);
        }

        System.out.println(finalStations);
    }

    private static String getBestStation(Map<String, Set<String>> stations, Set<String> statesNeeded) {
        String bestStation = null;
        int statesCovered = 0;

        for (Map.Entry<String, Set<String>> station : stations.entrySet()) {
            Set<String> covered = new HashSet<>(statesNeeded);
            covered.retainAll(station.getValue());

            if (covered.size() > statesCovered) {
                bestStation = station.getKey();
                statesCovered = covered.size();
            }
        }

        return bestStation;
    }
}
