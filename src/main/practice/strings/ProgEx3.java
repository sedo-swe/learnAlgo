package main.practice.strings;

import java.util.*;
import java.util.stream.Collectors;


public class ProgEx3 {

    Hashtable<String, LinkedList<Integer>> hashtable;
    int numberOfDocuments;

    public ProgEx3() {
        this.hashtable = new Hashtable<>();
        this.numberOfDocuments = 0;
    }

    public void storeDocument(final String document, final int documentNumber) {
        if (document == null || document.isEmpty()) {
            throw new IllegalArgumentException("description should be neither null nor empty.");
        }

        this.numberOfDocuments++;

        String[] words = document.split("\\s+");
        for (String word : words) {
//            System.out.println("word: " + word + " " + (this.hashtable.get(word) == null));
            LinkedList<Integer> temp = this.hashtable.get(word);
            if (temp == null) {
                temp = new LinkedList<>();
                temp.add(Integer.valueOf(documentNumber));
                this.hashtable.put(word, temp);
            } else {
                Integer tInteger = Integer.valueOf(documentNumber);
                if (!temp.contains(tInteger)) {
                    temp.add(tInteger);
                }
            }
        }
//        System.out.println();
    }

    public String performSearch(final String search) {
        String result = "-1";

        if (search == null || search.trim().isEmpty()) {
            return result;
        }

        // find match count & save it in array
        String[] queries = search.split("\\s+");
//        System.out.println("this.numberOfDocuments: "+this.numberOfDocuments);
        int[] matchDocument = new int[this.numberOfDocuments];
        for (int i = 0; i < this.numberOfDocuments; i++) {
            matchDocument[i] = 0;
        }
        for (String query : queries) {
            if (this.hashtable.get(query) != null) {
//                Iterator<Integer> iter = this.hashtable.get(query).iterator();
//
//                while (iter.hasNext()) {
//                    Integer integer = iter.next();
//                    matchDocument[integer.intValue()]++;
//                }
                LinkedList<Integer> t = this.hashtable.get(query);
                for (Integer integer : t) {
                    matchDocument[integer.intValue()]++;
                }
            }
        }

//        for (int k = 0; k < matchDocument.length; k++) {
//            System.out.println(k + " " + matchDocument[k]);
//        }
//        System.out.println("==");


        Hashtable<Integer, String> matchCounts = new Hashtable<>();
        for (int k = 0; k < matchDocument.length; k++) {
            if (matchDocument[k] > 0) {
//                System.out.println(k + " " + matchDocument[k]);
                String temp = matchCounts.get(Integer.valueOf(matchDocument[k]));
//                System.out.println("temp: " +temp + ", matchDocument[k]: " + matchDocument[k]);
                if (temp == null) {
                    temp = String.valueOf(k);
                    matchCounts.put(Integer.valueOf(matchDocument[k]), temp);
                } else {
                    temp += " " + String.valueOf(k);
                    matchCounts.put(Integer.valueOf(matchDocument[k]), temp);
                }
            }
        }
//        System.out.println(matchCounts.toString());

        Set<Integer> matchPoints = matchCounts.keySet();
        Integer[] arr = matchPoints.toArray(new Integer[]{});
//        for (Integer integer : arr) {
//            System.out.print(integer + " ");
//        }
        List<Integer> a1 = Arrays.stream(arr).sorted((p1, p2)->p2.compareTo(p1)).collect(Collectors.toList());
//        System.out.println();
//        for (Integer integer : a1) {
//            System.out.print(integer + " ");
//        }
//        System.out.println();

        if (a1.size() > 0) {
            result = "";
            String tempStr = "";
            int totalCnt = 0;
            for (Integer integer : a1) {
                tempStr = matchCounts.get(integer);
                String[] docs = tempStr.split("\\s+");
                int cnt = docs.length;
                if (totalCnt + cnt <= 10) {
                    result += matchCounts.get(integer) + " ";
                } else {
                    int tempCnt = 10 - totalCnt;
                    for (int p = 0; p < tempCnt; p++) {
                        result += docs[p] + " ";
                    }
                }
            }
        }

        return result.trim();

    }

    public static void main(String args[] ) throws Exception {

        String[] testDocuments = {"experienced software developer python", "experienced java", "software java"};
//        String[] testDocuments = {"experienced software 01", "experienced software 02", "experienced software 03", "experienced software 04", "experienced software 05",
//                "experienced software 06", "experienced software 07", "experienced software 08", "experienced software 09 java", "experienced software 10",
//                "experienced software software 11"};
//        String[] testDocuments = {"A B C", "B C D", "C E", "A B C D"};

        ProgEx3 progEx3 = new ProgEx3();

        final int N = testDocuments.length;
        for (int i = 0; i < N; i++) {
            progEx3.storeDocument(testDocuments[i], i);
        }

        System.out.println("After store: "+progEx3.hashtable);
        // Read searches
        String[] searches = {"software", "experienced java", "software java", "software developer", "css"};
//        String[] searches = {"software"};
//        String[] searches = {"experienced java", "software", "03", "python"};
//        String[] searches = {"B C D"};
        final int M = searches.length;
        for (int j = 0; j < M; j++) {
            System.out.println(progEx3.performSearch(searches[j]));
        }

    }
}

