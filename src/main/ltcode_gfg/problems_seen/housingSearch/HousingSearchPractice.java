package main.ltcode_gfg.problems_seen.housingSearch;


import java.util.*;
import java.util.stream.Collectors;

class HouseInfoT implements Comparable<HouseInfoT> {
    int houseID;
    int matchCount;

    public HouseInfoT(int houseID, int matchCount) {
        this.houseID = houseID;
        this.matchCount = matchCount;
    }

    public int compareTo(final HouseInfoT that) {
        if (this.matchCount > that.matchCount) {
            return -1;
        } else if (this.matchCount < that.matchCount) {
            return 1;
        } else {
            if (this.houseID > that.houseID) {
                return -1;
            } else if (this.houseID < that.houseID) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}

public class HousingSearchPractice {

    Map<String, Set<Integer>> featureHouseIDMap = new HashMap<>();  // feature in description : set of houseIDs containing the feature

    /*
        storeHouse itself would be O(k) which k is the number of distinct features in the description.
        However, it will run O(n) which n is the number of houses, therefore, final big-O for the storeHouse would be
        O(n * k).
     */
    public void storeHouse(String description, int houseID) {   // O(k) : k is number of distinct features
        String[] featuresInDesc = description.split(", ");  // split into features
        Set<String> featuresDistinct = new HashSet<>(Arrays.asList(featuresInDesc));    // remove duplicated features
        for (String feature : featuresDistinct) {
            featureHouseIDMap.computeIfAbsent(feature, ignored -> new HashSet<>()).add(houseID);
        }
    }

    public String performSearch(String search) {
        Map<Integer, Integer> houseScoreMap = new HashMap<>();
        String[] searchTerms = search.split(", ");
        // O(s * m)
        for (String searchTerm : searchTerms) { // O(s) : s is number of distinct searchTerms
            Set<Integer> houseIDs = featureHouseIDMap.getOrDefault(searchTerm, new HashSet<>());
            houseIDs.stream()   // O(m) : m is number of houseIDs containing searchTerm
                    .forEach(houseID -> houseScoreMap.merge(houseID, 1, (score1, score2) -> score1 + score2));
        }

        if (houseScoreMap.isEmpty())
            return "-1";

        return houseScoreMap.entrySet().stream()
                .map(entry -> new HouseInfoT(entry.getKey(), entry.getValue()))
                .sorted()   // O(n * lg n) : n is number of houses
                .limit(10)
                .map(houseInfoT -> houseInfoT.houseID + " " + houseInfoT.matchCount)
                .collect(Collectors.joining("\n"));
    }

    public static void main(String[] args) {
        HousingSearchPractice hsp = new HousingSearchPractice();
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
            hsp.storeHouse(s, id++);
        }

        System.out.println(hsp.performSearch("3 bedroom home, 2 bathroom, San Francisco, CA"));
    }
}
