package main.ltcode._03_sliding_windows;

import java.util.*;

/**
 * FIND THE LONGEST SUBSTRING OF A STRING CONTAINING ‘K’ DISTINCT CHARACTERS
 *      Input: s = 'abcbdbdbbdcdabd'
 *      k = 2
 *      Output: bdbdbbd
 *
 * https://builtin.com/data-science/sliding-window-algorithm
 */

@FunctionalInterface
interface IntFindLongestSubstringOfKDistinctCharacters {
    int findLongestSub(String s, int k);
}

public class GfgFindLongestSubstringOfKDistinctCharacters {
    /*
        1st idea
        ==> 988 /1111 (Time Limit Exceeded)
     */
    IntFindLongestSubstringOfKDistinctCharacters findLongestSub1st = ((s, k) -> {
        Map<Character, Integer> window = new HashMap<>();
        PriorityQueue<String> pq = new PriorityQueue<>((a, b) -> Integer.compare(b.length(), a.length()));

        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char rCur = s.charAt(r);
            window.put(rCur, window.getOrDefault(rCur, 0) + 1);
            if (window.size() == k) {
                pq.offer(s.substring(l, r+1));
            } else if (window.size() > k) {
                while (window.size() > k) {
                    char lChar = s.charAt(l);
                    int temp = window.get(lChar) - 1;
                    if (temp > 0) {
                        window.put(lChar, temp);
                    } else {
                        window.remove(lChar);
                    }
                    l++;
                }
            }
        }
        return pq.peek() != null ? pq.peek().length() : -1;
    });

    /*
        2nd idea
            - Remove PriorityQueue from 1st idea to improve speed
            ==> passed

            The time complexity of this code is O(n), where n is the length of the input string "s".
            This is because we iterate through the string once, and for each character, we perform
            constant time operations such as accessing and updating the HashMap.

            The space complexity is O(k), where k is the number of distinct characters in the input string "s".
            This is because the HashMap "window" stores at most k characters at any given time.
     */
    IntFindLongestSubstringOfKDistinctCharacters findLongestSub2nd = ((s, k) -> {
        Map<Character, Integer> window = new HashMap<>();
        String longest = "";

        int l = 0;
        for (int r = 0; r < s.length(); r++) {
            char rCur = s.charAt(r);
            window.put(rCur, window.getOrDefault(rCur, 0) + 1);
            if (window.size() == k) {
                if (longest.length() < s.substring(l, r + 1).length()) {
                    longest = s.substring(l, r+1);
                }
            } else if (window.size() > k) {
                while (window.size() > k) {
                    char lChar = s.charAt(l);
                    int temp = window.get(lChar) - 1;
                    if (temp > 0) {
                        window.put(lChar, temp);
                    } else {
                        window.remove(lChar);
                    }
                    l++;
                }
            }
        }
        return longest.length() > 0 ? longest.length() : -1;
    });

    public void test(IntFindLongestSubstringOfKDistinctCharacters func) {
        System.out.println("Expected: \"bdbdbbd\", Actual: " + func.findLongestSub("abcbdbdbbdcdabd", 2));
        System.out.println("Expected: \"aa\", Actual: " + func.findLongestSub("aabbcc", 1));
        System.out.println("Expected: \"aabb\", Actual: " + func.findLongestSub("aabbcc", 2));
        System.out.println("Expected: \"aabbcc\", Actual: " + func.findLongestSub("aabbcc", 3));
        System.out.println("Expected: \"\", Actual: " + func.findLongestSub("aaabbb", 3));
    }

    public static void main(String[] args) {
        GfgFindLongestSubstringOfKDistinctCharacters findLongest = new GfgFindLongestSubstringOfKDistinctCharacters();
        findLongest.test(findLongest.findLongestSub1st);
    }
}
