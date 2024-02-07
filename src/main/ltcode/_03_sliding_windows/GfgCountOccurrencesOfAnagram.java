package main.ltcode._03_sliding_windows;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a word and a text, return the count of occurrences of the anagrams of the word in the text.
 *  Input: text = gotxxotgxdogt, word = got
 *  Output : 3 (Words “got,” “otg” and “ogt” are anagrams of “got.”)
 *
 *  (similar with PermutationInString.java)
 *  https://builtin.com/data-science/sliding-window-algorithm
 */
@FunctionalInterface
interface IntCountOccurrencesOfAnagram {
    int countOccurrences(String text, String word);
}
public class GfgCountOccurrencesOfAnagram {
    IntCountOccurrencesOfAnagram intCountOccurrencesOfAnagram = ((text, word) -> {
        int count = 0;
        if (word.length() > text.length()) {
            return count;
        }

        int wordLen = word.length();
        int textLen = text.length();
        Map<Character, Integer> wordMap = new HashMap<>();
        Map<Character, Integer> textMap = new HashMap<>();
        for (char c : word.toCharArray()) {
            wordMap.put(c, wordMap.getOrDefault(c, 0) + 1);
        }

        int l = 0;
        for (int r = 0; r < textLen; r++) {
            textMap.put(text.charAt(r), textMap.getOrDefault(text.charAt(r), 0) + 1);
            if (r - l >= wordLen) {
                int temp = textMap.getOrDefault(text.charAt(l), 0) - 1;
                if (temp <= 0) {
                    textMap.remove(text.charAt(l));
                } else {
                    textMap.put(text.charAt(l), temp);
                }
                l++;
            }
            if (wordMap.equals(textMap)) {
                count++;
            }
        }

        return count;
    });

    public void test(IntCountOccurrencesOfAnagram func) {
        System.out.println("Expected: 3, Actual: " + func.countOccurrences("gotxxotgxdogt", "got"));
        System.out.println("Expected: 2, Actual: " + func.countOccurrences("gotxxotgxdogt", "xot"));
        System.out.println("Expected: 3, Actual: " + func.countOccurrences("tckcpcekcj", "ck"));
    }

    public static void main(String[] args) {
        GfgCountOccurrencesOfAnagram c = new GfgCountOccurrencesOfAnagram();
        c.test(c.intCountOccurrencesOfAnagram);
    }
}
