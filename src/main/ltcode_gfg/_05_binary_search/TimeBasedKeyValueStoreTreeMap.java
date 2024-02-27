package main.ltcode_gfg._05_binary_search;

import java.util.*;

@FunctionalInterface
interface IntTimeBasedKeyValueStoreTreeMapSet {
    void set(String key, String value, int timestamp);
}

@FunctionalInterface
interface IntTimeBasedKeyValueStoreTreeMapGet {
    String get(String key, int timestamp);
}

public class TimeBasedKeyValueStoreTreeMap {
    Map<String, TreeMap<Integer, String>> mapOrg;
    Map<String, TreeMap<Integer, String>> mapSol;
    Map<String, ArrayList<AbstractMap.SimpleEntry<Integer, String>>> mapWeb;
    Map<String, ArrayList<AbstractMap.SimpleEntry<Integer, String>>> mapNeet;
    public TimeBasedKeyValueStoreTreeMap() {
        this.mapOrg = new HashMap<>();
        this.mapSol = new HashMap<>();
        this.mapWeb = new HashMap<>();
        this.mapNeet = new HashMap<>();
    }

    /*
     *  1st idea.
     *  passed 44 / 51 test cases
     *  failed to finish due to Time Limit Exceeded
     */
    IntTimeBasedKeyValueStoreTreeMapSet setTreeMap = ((key, value, timestamp) -> {
        /*HashMap<Integer, String> temp = mapOrg.getOrDefault(key, new HashMap<>());
        temp.put(timestamp, value);
        mapOrg.put(key, temp);*/
        mapOrg.computeIfAbsent(key, ignored -> new TreeMap<>()).put(timestamp, value);
    });

    IntTimeBasedKeyValueStoreTreeMapGet getOrg = ((key, timestamp) -> {
        Integer[] keys = new Integer[mapOrg.get(key).keySet().size()];
        keys = mapOrg.get(key).keySet().toArray(keys);
//        Arrays.sort(keys);

//        List<Integer> keys = new ArrayList<>(mapOuter.get(key).keySet());
//        Collections.sort(keys);

        int l =0, r = keys.length - 1;
        int largestTimestamp = Integer.MIN_VALUE;
        while (l <= r) {
            int m = (l + r) / 2;
            if (keys[m] == timestamp) {
//                largestTimestamp = Math.max(largestTimestamp, keys[m]);
                largestTimestamp = keys[m];
                break;
            } else if (keys[m] < timestamp) {
                largestTimestamp = Math.max(largestTimestamp, keys[m]);
                l = m + 1;
            } else {    // t < m
                r = m - 1;
            }
        }
        return mapOrg.get(key).get(largestTimestamp) == null ? "" : mapOrg.get(key).get(largestTimestamp);
    });

    /*
     *  2nd idea.
     *      Using TreeMap's floorKey method
     *  passed 44 / 51 test cases
     *  failed to finish due to Time Limit Exceeded
     */
    IntTimeBasedKeyValueStoreTreeMapGet getSol = ((key, timestamp) -> {
        if (!mapSol.containsKey(key))
            return "";
        TreeMap<Integer, String> tree = mapSol.get(key);
        Integer t = tree.floorKey(timestamp);
        if (t == null) {
            return "";
        }
        return tree.get(t);
    });

    /*
        3rd idea
        Leet solution
        Use HashMap with ArrayList

     */
    IntTimeBasedKeyValueStoreTreeMapSet setWeb = ((key, value, timestamp) -> {
        mapWeb.computeIfAbsent(key, ignored -> new ArrayList<>()).add(new AbstractMap.SimpleEntry<>(timestamp, value));
    });

    IntTimeBasedKeyValueStoreTreeMapGet getWeb = ((key, timestamp) -> {
        if (!mapWeb.containsKey(key))
            return "";
        List<AbstractMap.SimpleEntry<Integer, String>> list = mapWeb.get(key);

        int l =0, r = list.size() - 1;
        if (timestamp < list.get(l).getKey()) return "";
        if (timestamp > list.get(r).getKey()) return list.get(r).getValue();
        while (l + 1 < r) {
            int m = (l + r) / 2;
            if (list.get(m).getKey() <= timestamp) {
                if (m + 1 > r)
                    return list.get(m).getValue();
                if (m + 1 <= r) {
                    if (timestamp < list.get(m + 1).getKey())
                        return list.get(m).getValue();
                    l = m;
                }
            } else {    // t < m
                r = m;
            }
        }
        if (list.get(r).getKey() <= timestamp) return list.get(r).getValue();
        return list.get(l).getValue();
    });

    IntTimeBasedKeyValueStoreTreeMapSet setNeet = ((key, value, timestamp) -> {
        mapNeet.computeIfAbsent(key, ignored -> new ArrayList<>()).add(new AbstractMap.SimpleEntry<>(timestamp, value));
    });

    IntTimeBasedKeyValueStoreTreeMapGet getNeet = ((key, timestamp) -> {
        if (!mapNeet.containsKey(key))
            return "";
        List<AbstractMap.SimpleEntry<Integer, String>> list = mapNeet.get(key);

        int l =0, r = list.size() - 1;
        String value = "";
        if (timestamp < list.get(l).getKey()) return "";
        if (timestamp > list.get(r).getKey()) return list.get(r).getValue();
        while (l <= r) {
            int m = (l + r) / 2;
            if (list.get(m).getKey() <= timestamp) {
                value = list.get(m).getValue();
                l = m + 1;
            } else {    // t < m
                r = m - 1;
            }
        }
        return value;
    });

    public void test(IntTimeBasedKeyValueStoreTreeMapGet funcGet, IntTimeBasedKeyValueStoreTreeMapSet funcSet) {
//        funcGet.set("foo", "bar", 1);
//        System.out.println("Expected: bar, Actual: "+funcGet.get("foo", 1));
//        System.out.println("Expected: bar, Actual: "+funcGet.get("foo", 2));
//        System.out.println("Expected: \"\", Actual: "+funcGet.get("foo", 0));
//        funcGet.set("foo", "bar2", 4);
//        System.out.println("Expected: bar, Actual: "+funcGet.get("foo", 1));
//        System.out.println("Expected: bar2, Actual: "+funcGet.get("foo", 4));
//        System.out.println("Expected: bar2, Actual: "+funcGet.get("foo", 5));

        funcSet.set("love", "high", 10);
        funcSet.set("love", "low", 20);
        System.out.println("Expected: \"\", Actual: "+funcGet.get("love", 5)+"<");
        System.out.println("Expected: high, Actual: "+funcGet.get("love", 10));
        System.out.println("Expected: high, Actual: "+funcGet.get("love", 15));
        System.out.println("Expected: low, Actual: "+funcGet.get("love", 20));
        System.out.println("Expected: low, Actual: "+funcGet.get("love", 25));
    }

    public static void main(String[] args) {
        TimeBasedKeyValueStoreTreeMap treeMap = new TimeBasedKeyValueStoreTreeMap();
//        treeMap.test(treeMap.getOrg, treeMap.setOrg);
//        System.out.println();
        treeMap.test(treeMap.getWeb, treeMap.setWeb);
        System.out.println();
        treeMap.test(treeMap.getNeet, treeMap.setNeet);
    }
}
