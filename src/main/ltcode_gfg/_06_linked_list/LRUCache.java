package main.ltcode_gfg._06_linked_list;

import java.util.*;

/**
 *  146. LRU Cache
 */
public class LRUCache {
    List<Integer> list;
    Map<Integer, Integer> map;
    int capacity;
    public LRUCache(int capacity) {
        this.capacity = capacity;
        list  = new ArrayList<>(capacity);
        map = new HashMap<>();
    }

    public int get(int key) {
        if (map.containsKey(key)) {
            int value = map.get(key);
            list.remove(list.indexOf(key));
            list.add(key);
            return value;
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        // Update
        if (map.containsKey(key)) {
            this.get(key);
            map.put(key, value);
        }
        else { // insert
            if (list.size() > this.capacity) {
                int lruKey = list.remove(0);
                map.remove(lruKey);
            }
            list.add(key);
            map.put(key, value);
        }
    }

    public static void main(String[] args) {
        LRUCache lc = new LRUCache(2);
        /*lc.put(1, 1);
        lc.put(2, 2);
        System.out.println(lc.get(1));
        lc.put(3, 3);
        System.out.println(lc.get(2));
        lc.put(4, 4);
        System.out.println(lc.get(1));
        System.out.println(lc.get(3));
        System.out.println(lc.get(4));*/

        System.out.println(lc.get(2));
        lc.put(2, 6);
        System.out.println(lc.get(1));
        lc.put(1, 5);
        lc.put(1, 2);
        System.out.println(lc.get(1));
        System.out.println(lc.get(2));
    }
}
