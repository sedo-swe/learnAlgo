package main.practice.strings;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

class Document {
    String description;
    int id;

    public Document() {}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Document(String description, int id) {
        this.description = description;
        this.id = id;
    }
}

public class ProgEx {

    /*
     Need a document class to store
        - String for document
        - int for documentNumber (a.k.a id)

    *** how can I use previous result for next query???
     */

    List<Document> documents;

    public ProgEx() {
        this.documents = new LinkedList<>();
    }


    public void storeDocument(final String document, final int documentNumber) {
        // Store string and documentNumber into array with document class
        documents.add(new Document(document, documentNumber));
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
            Document current = this.documents.get(i);
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


    /*
    Optimization notes: Due to hackerrank global timeout definitions per language, programs
    except for the most optimized will have a tough time passing the last test case.
    This is known and you will not be penalized for it.

    The below code handles input and output and should not require any modification - you should focus on completing the functions defined above.
     */
    public static void main(String args[] ) throws Exception {
        /*// Read input from STDIN. Print output to STDOUT
        final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());
        // Read documents
        for (int i = 0; i < N; i++) {
            storeDocument(br.readLine(), i);
        }

        final BufferedOutputStream output = new BufferedOutputStream(System.out);

        final int M = Integer.parseInt(br.readLine());
        // Read searches
        for (int j = 0; j < M; j++) {
            output.write((performSearch(br.readLine()) + "\n").getBytes());
        }

        output.flush();

        output.close();
        br.close();*/

        String[] testDocuments = {"experienced software developer python", "experienced java", "software java"};
//        String[] testDocuments = {
//            "experienced software 01", "experienced software 02", "experienced software 03",
//            "experienced software 04", "experienced software 05", "experienced software 06",
//            "experienced software 07", "experienced software 08", "experienced software 09 java",
//            "experienced software 10", "experienced software software 11"
//        };

        ProgEx progEx = new ProgEx();

        final int N = testDocuments.length;
        for (int i = 0; i < N; i++) {
            progEx.storeDocument(testDocuments[i], i);
        }

        // Read searches
        String[] searches = {"software", "experienced java", "software java", "software developer", "css"};
//        String[] searches = {"experienced java", "software", "03", "python"};
        final int M = searches.length;
        for (int j = 0; j < M; j++) {
            System.out.println(progEx.performSearch(searches[j]));
        }

    }
}
