package main.ltcode_gfg._03_sliding_windows;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 567. Permutation in String (Medium)
 */
@FunctionalInterface
interface IntPermutationInString {
    boolean checkInclusion(String s1, String s2);
}
public class PermutationInString {
    /*
        1st idea.

        ==> The time complexity of this solution is O(n), where n is the length of s2.
            This is because we iterate through each character in s2 once.
            The space complexity is O(m), where m is the length of s1. This is because
            we use a HashMap to store the frequency of characters in s1, which can have at most m distinct characters.
     */
    IntPermutationInString intPermutationInString = ((s1, s2) -> {
        if (s1 == null || s2 == null)
            return false;
        if (s1.length() == 1 && (s2.length() >= 1 && s2.contains(s1)))
            return true;
        Map<Character, Integer> letters = new HashMap<>();
        for (int m=0; m<s1.length(); m++) {
            letters.put(s1.charAt(m), letters.getOrDefault(s1.charAt(m), 0) + 1);
        }

        Map<Character, Integer> lettersT = new HashMap<>();
        lettersT.putAll(letters);
        int windowSize = s1.length();
        int i = 0;
        for (int j=0; j<s2.length(); j++) {
            if (!lettersT.containsKey(s2.charAt(j))) {
                i = j + 1;
                continue;
            } else {
                if (j - i + 1 >= windowSize) {
                    for (int k = i; k < i + windowSize; k++) {
                        char cur = s2.charAt(k);
                        if (lettersT.containsKey(cur)) {
                            int l = lettersT.get(cur) - 1;
                            if (l > 0) {
                                lettersT.put(cur, l);
                            } else {
                                lettersT.remove(cur);
                            }
                        } else {
                            i++;
                            lettersT.clear();
                            lettersT.putAll(letters);
                            break;
                        }
                    }
                    if (lettersT.size() == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    });

     IntPermutationInString intPermutationInStringSolPy = ((s1, s2) -> {
         if (s1.length() > s2.length()) {
             return false;
         }

         Map<Character, Integer> map1 = new HashMap<>();
         Map<Character, Integer> map2 = new HashMap<>();
         for (char c : s1.toCharArray()) {
             map1.put(c, map1.getOrDefault(c, 0) + 1);
             map2.put(c, map2.getOrDefault(c, 0) + 1);
         }

         int matches = 0;
         for (int i = 0; i < 26; i++) {
             char c = (char) ('a' + i);
             matches += map1.get(c) == map2.get(c) ? 1 : 0;
         }

         System.out.println(matches);
         int l = 0;
         for (int r = s1.length(); r < s2.length(); r++) {
             if (matches == 26) {
                 return true;
             }
             char index = s2.charAt(r);
             map2.put(index, map2.getOrDefault(index, 0) + 1);
             if (map1.get(index) == map2.get(index)) {
                 matches += 1;
             } else if (map1.get(index) + 1 == map2.get(index)) {
                 matches -= 1;
             }

             index = s2.charAt(l);
             map2.put(index, map2.getOrDefault(index, 0) - 1);
             if (map1.get(index) == map2.get(index)) {
                 matches += 1;
             } else if (map1.get(index) - 1 == map2.get(index)) {
                 matches -= 1;
             }
             l++;
         }
         return matches == 26;
     });

     /*
        The time complexity of this solution is O(m), where m is the length of s2.
        This is because we iterate through each character in s2 once.

        The space complexity is O(1) because the size of the freq and freq2 arrays is fixed at 26,
        regardless of the length of the input strings.
      */
    IntPermutationInString intPermutationInStringSol = ((s1, s2) -> {
        if (s1.length() > s2.length()) {
            return false;
        }

        int n = s1.length();
        int[] s1nts = new int[26];
        int m = s2.length();
        for (int i = 0; i < n; i++) {
            s1nts[s1.charAt(i) - 'a']++;
        }

        int[] s2Cnts = new int[26];
        for (int r = 0; r < m; r++) {
            s2Cnts[s2.charAt(r) - 'a']++;
            if (r >= n) {
                s2Cnts[s2.charAt(r - n) - 'a']--;
            }
            if (Arrays.equals(s1nts, s2Cnts)) {
                return true;
            }
        }
        return false;
    });

    public void test(IntPermutationInString func) {
        System.out.println("Expected: true, Actual: " + func.checkInclusion("ab", "eidbaooo"));
        System.out.println("Expected: false, Actual: " + func.checkInclusion("ab", "eidboaoo"));
        System.out.println("Expected: true, Actual: " + func.checkInclusion("adc", "dcda"));
        System.out.println("Expected: false, Actual: " + func.checkInclusion("hello", "ooolleoooleh"));
    }

    public static void main(String[] args) {
        PermutationInString permutationInString = new PermutationInString();
        permutationInString.test(permutationInString.intPermutationInString);
        System.out.println("=======");
        permutationInString.test(permutationInString.intPermutationInStringSol);
    }
}
