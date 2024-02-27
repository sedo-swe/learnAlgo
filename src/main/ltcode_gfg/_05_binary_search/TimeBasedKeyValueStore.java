package main.ltcode_gfg._05_binary_search;

import java.util.*;

public class TimeBasedKeyValueStore {
    Map<String, TreeMap<Integer, String>> mapOrg;
    Map<String, ArrayList<AbstractMap.SimpleEntry<Integer, String>>> mapOuter;

    public TimeBasedKeyValueStore() {
        this.mapOrg = new HashMap<>();
        this.mapOuter = new HashMap<>();
    }

    public void setOrg(String key, String value, int timestamp) {
//        HashMap<Integer, String> temp = mapOuter.getOrDefault(key, new HashMap<>());
//        temp.put(timestamp, value);
//        mapOuter.put(key, temp);
        mapOrg.computeIfAbsent(key, ignored -> new TreeMap<>()).put(timestamp, value);
    }

    public String getOrg(String key, int timestamp) {
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
//                largestTimestamp = keys[m];
                l = m + 1;
            } else {    // t < m
                r = m - 1;
            }
        }
//        return largestTimestamp < 1 ? "" : mapOuter.get(key).get(largestTimestamp);
        return mapOrg.get(key).get(largestTimestamp) == null ? "" : mapOrg.get(key).get(largestTimestamp);
    }

    public void set(String key, String value, int timestamp) {
        mapOuter.computeIfAbsent(key, ignored -> new ArrayList<>()).add(new AbstractMap.SimpleEntry<>(timestamp, value));
    }

    public String get(String key, int timestamp) {
        ArrayList<AbstractMap.SimpleEntry<Integer, String>> entries = mapOuter.get(key);

        int l =0, r = entries.size() - 1;
        int largestTimestamp = Integer.MIN_VALUE;
        String result = "";
        while (l < r) {
            int m = (l + r) / 2;

            if (entries.get(m).getKey() <= timestamp) {
                result = entries.get(m).getValue();
                l = m + 1;
            } else {    // t < m
                r = m - 1;
            }
        }
//        return largestTimestamp < 1 ? "" : mapOuter.get(key).get(largestTimestamp).getValue();
//        return mapOuter.get(key).get(l).getKey() <= timestamp ? mapOuter.get(key).get(l).getValue() : "";
        return result;
    }

    public static void main(String[] args) {
//        TimeBasedKeyValueStore tbk = new TimeBasedKeyValueStore();
//        tbk.set("foo", "bar", 1);
//        System.out.println("Expected: bar, Actual: "+tbk.get("foo", 1)+"<");
//        System.out.println("Expected: bar, Actual: "+tbk.get("foo", 2)+"<");
//        System.out.println("Expected: \"\", Actual: "+tbk.get("foo", 0)+"<");
//        tbk.set("foo", "bar2", 4);
//        System.out.println("Expected: bar, Actual: "+tbk.get("foo", 1)+"<");
//        System.out.println("Expected: bar2, Actual: "+tbk.get("foo", 4)+"<");
//        System.out.println("Expected: bar2, Actual: "+tbk.get("foo", 5)+"<");

//        TimeBasedKeyValueStore tbk2 = new TimeBasedKeyValueStore();
//        tbk2.setOrg("love", "high", 10);
//        tbk2.setOrg("love", "low", 20);
//        System.out.println("Expected: \"\", Actual: "+tbk2.getOrg("love", 5)+"<");
//        System.out.println("Expected: high, Actual: "+tbk2.getOrg("love", 10)+"<");
//        System.out.println("Expected: high, Actual: "+tbk2.getOrg("love", 15)+"<");
//        System.out.println("Expected: low, Actual: "+tbk2.getOrg("love", 20)+"<");
//        System.out.println("Expected: low, Actual: "+tbk2.getOrg("love", 25)+"<");

        TimeBasedKeyValueStore tbk2 = new TimeBasedKeyValueStore();
        tbk2.set("love", "high", 10);
        tbk2.set("love", "low", 20);
        System.out.println("Expected: \"\", Actual: "+tbk2.get("love", 5)+"<");
        System.out.println("Expected: high, Actual: "+tbk2.get("love", 10)+"<");
        System.out.println("Expected: high, Actual: "+tbk2.get("love", 15)+"<");
        System.out.println("Expected: low, Actual: "+tbk2.get("love", 20)+"<");
        System.out.println("Expected: low, Actual: "+tbk2.get("love", 25)+"<");
    }
}
