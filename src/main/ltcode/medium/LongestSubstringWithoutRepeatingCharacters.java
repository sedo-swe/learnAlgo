package main.ltcode.medium;

import java.util.HashSet;

/**
 *  3. Longest Substring Without Repeating Characters (medium)
 */
@FunctionalInterface
interface IntLongestSubstringNoRepeatChar {
    int lengthOfLongestSubstring(String s);
}
public class LongestSubstringWithoutRepeatingCharacters {
    IntLongestSubstringNoRepeatChar intLongestSubstringNoRepeatChar = (s -> {
        if (s.equals("")) {
            return 0;
        }
        int start = 0, last = -1, prevLongest = 0;
        HashSet<Character> repeats = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (repeats.contains(s.charAt(i))) {
                if (s.charAt(i - 1) == s.charAt(i)) {
                    prevLongest = Math.max(prevLongest, last - start + 1);
                    last = i;
                    start = i;
                    repeats = new HashSet<>();
                    repeats.add(s.charAt(i));
                } else {
                    prevLongest = Math.max(prevLongest, last - start + 1);
                    last++;
                    start++;
                }
            } else {
                repeats.add(s.charAt(i));
                last++;
            }
        }
        return Math.max(prevLongest, last - start + 1);
    });

    IntLongestSubstringNoRepeatChar intLongestSubstringNoRepeatChar2nd = (s -> {
        if (s == null) {
            return 0;
        }
        int start = 0, last = -1, prevLongest = 0;
        HashSet<Character> repeats = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (repeats.contains(s.charAt(i))) {
                prevLongest = Math.max(prevLongest, last - start + 1);
                if (s.charAt(i - 1) == s.charAt(i)) {
                    last = i;
                    start = i;
                    repeats = new HashSet<>();
                    repeats.add(s.charAt(i));
                } else {
                    while (start < last) {
                        if (s.charAt(start++) == s.charAt(i)) {
                            break;
                        } else {
                            repeats.remove(s.charAt(start - 1));
                        }
                    }
                    last++;
                }
            } else {
                repeats.add(s.charAt(i));
                last++;
            }
        }
        return Math.max(prevLongest, last - start + 1);
    });

    public void test(IntLongestSubstringNoRepeatChar func) {
        System.out.println("Expected: 3, Actual: " + func.lengthOfLongestSubstring("abcabcbb"));    // abc
        System.out.println("Expected: 1, Actual: " + func.lengthOfLongestSubstring("bbbbb"));       // b
        System.out.println("Expected: 3, Actual: " + func.lengthOfLongestSubstring("pwwkew"));      // wke
        System.out.println("Expected: 0, Actual: " + func.lengthOfLongestSubstring(""));            // ""
        System.out.println("Expected: 1, Actual: " + func.lengthOfLongestSubstring(" "));           // " "
        System.out.println("Expected: 2, Actual: " + func.lengthOfLongestSubstring("aab"));         // "ab"
        System.out.println("Expected: 5, Actual: " + func.lengthOfLongestSubstring("ckilbkd"));     // "ckilb"
        System.out.println("Expected: 6, Actual: " + func.lengthOfLongestSubstring("ckilbkda"));     // "ilbkda"
        System.out.println("Expected: 6, Actual: " + func.lengthOfLongestSubstring("wobgrovw"));     // "ilbkda"

    }

    public static void main(String[] args) {
        LongestSubstringWithoutRepeatingCharacters longest = new LongestSubstringWithoutRepeatingCharacters();
        longest.test(longest.intLongestSubstringNoRepeatChar2nd);
    }
}
