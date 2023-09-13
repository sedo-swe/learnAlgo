package main.tricks;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;

public class HashMapTricks {
    public static void main(String[] args) {
        HashMap<Integer, LinkedList<String>> hashMap = new HashMap<>();
        LinkedList<String> match1 = new LinkedList<>();
        match1.add("D1");
        hashMap.put(Integer.valueOf(1), match1);

        LinkedList<String> match2 = new LinkedList<>();
        match2.add("D3");
        hashMap.put(Integer.valueOf(2), match2);

        LinkedList<String> match3 = new LinkedList<>();
        match3.add("D2");
        match3.add("D4");
        hashMap.put(Integer.valueOf(3), match3);

        Set<Integer> set = hashMap.keySet();
        System.out.println(set);

    }

}
