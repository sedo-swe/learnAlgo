package main.practice.strings;

import java.util.*;

class Document2 {
    HashSet<String> description;
    int id;

    public Document2() {}

    public HashSet<String> getDescription() {
        return this.description;
    }

    public void setDescription(String description) {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private HashSet<String> parseDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("description should be neither null nor empty.");
        }

        HashSet<String> keywords = new HashSet<>();
        String[] words = description.split("\\s+");
        for (String word : words) {
            keywords.add(word);
        }

        return keywords;
    }
    public Document2(String description, int id) {
        this.description = this.parseDescription(description);
        this.id = id;
    }
}

public class ProgEx2 {

    /*
     Need a document class to store
        - String for document
        - int for documentNumber (a.k.a id)

    *** how can I use previous result for next query???
     */

    List<Document2> documents;

    public ProgEx2() {
        this.documents = new LinkedList<>();
    }

    public void storeDocument(final String document, final int documentNumber) {
        // Store string and documentNumber into array with document class
        documents.add(new Document2(document, documentNumber));
    }

    public String performSearch(final String search) {
        String result = "-1";
        if (search == null || search.trim().isEmpty()) {
            return result;
        }

        // find match count & save it in array
        String[] queries = search.split(" ");

        int numOfDocuments = this.documents.size();
        // double array: first has matchCount as index, second has id as index
        HashMap<Integer, LinkedList<Integer>> matchResults = new HashMap<>();


        for (int i = 0; i < numOfDocuments; i++) {
            int count = 0;
            Document2 current = this.documents.get(i);
            for (String query : queries) {
                if (current.description.contains(query)) {
                    count++;
                }
            }

            if (count > 0) {
                if (matchResults.containsKey(Integer.valueOf(count))) {
                    matchResults.get(Integer.valueOf(count)).add(Integer.valueOf(i));
                } else {
                    LinkedList<Integer> temp = new LinkedList<>();
                    temp.add(Integer.valueOf(i));
                    matchResults.put(Integer.valueOf(count), temp);
                }
            }
        }

        // sort array & read non -1 element until 10
        if (matchResults.keySet().size() == 0) {
            return "-1";
        } else {
            Set<Integer> keys = matchResults.keySet();
            Integer[] array = keys.toArray(new Integer[]{});
            result = "";
            for (int j = array.length - 1; j >= 0; j--) {
                int cnt = 0;
                while (!matchResults.get(array[j]).isEmpty()) {
                    Integer i = matchResults.get(array[j]).remove();
                    result += i.toString() + " ";
                    if (++cnt == 10) {
                        return result;
                    }
                }
            }
        }


        return result;

    }

    public static void main(String args[] ) throws Exception {

//        String[] testDocuments = {"experienced software developer python", "experienced java", "software java"};
        String[] testDocuments = {"experienced software 01", "experienced software 02", "experienced software 03", "experienced software 04", "experienced software 05",
                "experienced software 06", "experienced software 07", "experienced software 08", "experienced software 09 java", "experienced software 10",
                "experienced software software 11"};

        ProgEx2 progEx2 = new ProgEx2();

        final int N = testDocuments.length;
        for (int i = 0; i < N; i++) {
            progEx2.storeDocument(testDocuments[i], i);
        }

        // Read searches
//        String[] searches = {"software", "experienced java", "software java", "software developer", "css"};
        String[] searches = {"experienced java", "software", "03", "python"};
        final int M = searches.length;
        for (int j = 0; j < M; j++) {
            System.out.println(progEx2.performSearch(searches[j]));
        }

    }
}

