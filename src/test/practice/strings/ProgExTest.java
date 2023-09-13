package test.practice.strings;

import main.practice.strings.ProgEx;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProgExTest {
    @Test
    public void givenDocumentsNSearches_WhenSearch_ThenReturns1() {

        String[] testDocuments = {"experienced software developer python", "experienced java", "software java"};

        ProgEx progEx = new ProgEx();

        final int N = testDocuments.length;
        for (int i = 0; i < N; i++) {
            progEx.storeDocument(testDocuments[i], i);
        }

        // Read searches
        String[] searches = {"software", "experienced java", "software java", "software developer", "css"};
        String[] expected = {
                "0 2",
                "1 0 2",
                "2 0 1",
                "0 2",
                "-1"
        };
        final int M = searches.length;
        for (int j = 0; j < M; j++) {
            String result = progEx.performSearch(searches[j]).trim();
            System.out.println(result.equals(expected[j]) + " : " + result);
            assertTrue(result.equals(expected[j]));
        }
    }

    @Test
    public void givenDocumentsNSearches_WhenSearch_ThenReturns2() {

        String[] testDocuments = {"experienced software 01", "experienced software 02", "experienced software 03", "experienced software 04", "experienced software 05",
                "experienced software 06", "experienced software 07", "experienced software 08", "experienced software 09 java", "experienced software 10",
                "experienced software software 11"};

        ProgEx progEx = new ProgEx();

        final int N = testDocuments.length;
        for (int i = 0; i < N; i++) {
            progEx.storeDocument(testDocuments[i], i);
        }

        // Read searches
        String[] searches = {"experienced java", "software", "03", "python"};
        String[] expected = {
                "8 0 1 2 3 4 5 6 7 9 10",
                "0 1 2 3 4 5 6 7 8 9",
                "2",
                "-1"
        };
        final int M = searches.length;
        for (int j = 0; j < M; j++) {
            String result = progEx.performSearch(searches[j]).trim();
            System.out.println(result.equals(expected[j]) + " : " + result);
            assertTrue(result.equals(expected[j]));
        }
    }
}
