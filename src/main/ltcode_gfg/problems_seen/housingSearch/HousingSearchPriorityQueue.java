package main.ltcode_gfg.problems_seen.housingSearch;

import java.util.*;

class HouseInfo {
    protected String description;
    protected int id;
    protected int matchCount;

    public HouseInfo(String desc, int id) {
        this.description = desc;
        this.id = id;
        this.matchCount = 0;
    }
}

public class HousingSearchPriorityQueue {
    Map<Integer, HouseInfo> houses = new HashMap<>();

    // O(n) : n is the number of houses
    public void storeHouse(String description, int id) {
        houses.put(id, new HouseInfo(description, id));
    }

    public String performSearch(String search) {
        StringBuffer result = new StringBuffer();

        /*
            // Get match count
                1. Split string based on "," separator
                2. Store it in List to use contains
            // Store at PriorityQueue
                3. Use comparator (lambda) - match count > id
            // Get top 10 (5)
                4. Extract - format : id "match count"
         */
        String[] queries = Arrays.stream(search.split(",")).map(String::trim).toArray(String[]::new);
        Set<Integer> keysInHouses = houses.keySet();
        PriorityQueue<HouseInfo> hq = new PriorityQueue<>((h1, h2) -> {
            if (h1.matchCount > h2.matchCount) {
                return -1;
            } else if (h1.matchCount < h2.matchCount) {
                return 1;
            } else {
                if (h1.id > h2.id) {
                    return -1;
                } else if (h1.id < h2.id) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });
        for (Integer i : keysInHouses) {
//            String[] keywords = houses.get(i).description.split(",");
//            List<String> queriesList = Arrays.asList(queries);
            HouseInfo h = houses.get(i);
            /*List<String> keywordsList = Arrays.stream(h.description.split(","))
                                            .map(s -> s.trim())
                                            .collect(Collectors.toList());
            int matchCount = 0;
            for (String s : queries) {
                if (keywordsList.contains(s)) {
                    matchCount++;
                }
            }*/
            int matchCount = 0;
            for (String s : queries) {
                if (houses.get(i).description.contains(s)) {
                    matchCount++;
                }
            }
            h.matchCount = matchCount;
            hq.offer(h);
        }

        for (int i = 0; i < 5; i++) {
            HouseInfo h = hq.poll();
            result.append(h.id + " " + h.matchCount + " : " + h.description + "\n");
        }

        return result.toString();
    }

    public String performSearchImproved(String search) {
        String[] searchTerms = search.split(", ");
        PriorityQueue<HouseInfo> houseScoresPQ = new PriorityQueue<>((h1, h2) -> {
            if (h1.matchCount > h2.matchCount) {
                return 1;
            } else if (h1.matchCount < h2.matchCount) {
                return -1;
            } else {
                if (h1.id > h2.id) {
                    return 1;
                } else if (h1.id < h2.id) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });

        Set<Integer> houseIDs = houses.keySet();
        for (Integer houseID : houseIDs) {
            HouseInfo temp = houses.get(houseID);
            for (final String searchTerm : searchTerms) {
                if (temp.description.contains(searchTerm)) {
                    temp.matchCount++;
                }
            }
            houseScoresPQ.offer(temp);
            if (houseScoresPQ.size() > 10) {
                houseScoresPQ.poll();
            }
        }

        StringBuffer sb = new StringBuffer();
        for (int i=0; i<10; i++) {
            HouseInfo t = houseScoresPQ.poll();
            sb.insert(0, t.id + " " + t.matchCount + "\n");
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        HousingSearchPriorityQueue hs = new HousingSearchPriorityQueue();
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
        System.out.println();
        System.out.println(hs.performSearchImproved("3 bedroom home, 2 bathroom, San Francisco, CA"));
    }
}
