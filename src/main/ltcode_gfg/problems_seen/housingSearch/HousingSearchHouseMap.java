package main.ltcode_gfg.problems_seen.housingSearch;

import java.util.*;
import java.util.stream.Collectors;

class HouseInfoCT implements Comparable<HouseInfoCT> {
    int houseId;
    int matchCount;
    String description;

    public HouseInfoCT(int houseId, int matchCount, String description) {
        this.houseId = houseId;
        this.matchCount = matchCount;
        this.description = description;
    }

    @Override
    public int compareTo(final HouseInfoCT that) {
        if (this.matchCount > that.matchCount) {
            return -1;
        } else if (this.matchCount < that.matchCount) {
            return 1;
        } else {
            if (this.houseId > that.houseId) {
                return -1;
            } else if (this.houseId < that.houseId) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}

public class HousingSearchHouseMap {
    /*
        When new house description is added with its id,
            1. Split the description into terms
            2. Store
     */
    Map<String, Set<Integer>> houseInfosMap = new HashMap<>(); // feature, set of houseIds
    public void storeHouse(String description, int houseId) {   // Time Complexity : O(n), n: length of description
        String[] houseFeatures = description.split(", ");   // T.C : O(n), n: length of description
        // Use Set in order to remove duplicated features in a description
        Set<String> houseFeaturesSet = new HashSet<>(Arrays.asList(houseFeatures));  // T.C : O(k), k: n

        for (final String feature : houseFeaturesSet) { // T.C : O(k), k: number of unique features from description
            houseInfosMap.computeIfAbsent(feature, ignored -> new HashSet<>()).add(houseId);    // T.C: O(1)
        }
    }

    public String performSearch(final String search) {
        Map<Integer, Integer> scoreMap = new HashMap<>();
        String[] searchTerms = search.split(", ");  // T.C : O(n), n: length of search
        for (String searchTerm : searchTerms) { // T.C : O(k), k: number of unique searchTerm from search
            Set<Integer> houseIDs = houseInfosMap.getOrDefault(searchTerm, Collections.emptySet()); // O(1)
            houseIDs.stream()   // O(m), m: number of unique IDs that have the searchTerm
                    .forEach(houseID -> scoreMap.merge(houseID, 1, (matchCount1, matchCount2) -> matchCount1 + matchCount2));
        }
        if (scoreMap.isEmpty()) {
            return "-1";
        }
        return scoreMap.entrySet().stream()
                .map(entry -> new HouseInfoCT(entry.getKey(), entry.getValue(), ""))
                .sorted()
                .map(houseInfoCT -> houseInfoCT.houseId + " " + houseInfoCT.matchCount + "\n")
                .limit(10)
                .collect(Collectors.joining(" "));
    }

    public static void main(String[] args) {
        HousingSearchHouseMap hs = new HousingSearchHouseMap();
        String[] houses = {
                "3 bedroom home, 2 bathroom, San Francisco, CA",
                "2 bedroom home, 2 bathroom, San Francisco, CA",
                "2 bedroom home, 1 bathroom, San Francisco, CA",
                "2 bedroom apartment, 2 bathroom, San Francisco, CA",
                "3 bedroom home, 1 bathroom, Houston, TX",
                "3 bedroom home, 2 bathroom, Dallas, TX",
                "1 bedroom apartment, 1 bathroom, Seattle, WA",
                "2 bedroom home, 1 bathroom, Austin, TX",
                "1 bedroom home, 1 bathroom, Austin, TX",
                "2 bedroom home, 1 bathroom, Great Neck, NY",
                "2 bedroom apartment, 1 bathroom, Manhattan, NY",
                "1 bedroom apartment, 1 bathroom, Manhattan, NY",
                "4 bedroom home, 2 bathroom, Stony Brook, NY"
        };
        int id = 1;
        for (String s : houses) {
            hs.storeHouse(s, id++);
        }

        System.out.println(hs.performSearch("3 bedroom home, 2 bathroom, San Francisco, CA"));
    }
}
