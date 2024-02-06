package main.ltcode.medium;

import java.util.Arrays;

/**
 * 424. Longest Repeating Character Replacement (Medium)
 */
@FunctionalInterface
interface IntLongestRepeatingCharacterReplacement {
    int characterReplacement(String s, int k);
}

public class LongestRepeatingCharacterReplacement {
    /*
        1st idea.
            1. move right until found k+1 th different letter
            2. Check max length
            3. move left by 1 toward right
            4. Check left and right pointing out same letter
            5. If same, go back to step 1, and repeat
            6. If not, go back to step 3, and repeat
     */
    IntLongestRepeatingCharacterReplacement intLongestRepeatingCharacterReplacement = ((s, k) -> {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int left = 0, right = 0, maxLength = 0, difference = 0;
        while (right < s.length()) {
//            System.out.println("l: "+left+", r:"+right);

            char l = s.charAt(left);
            char r = s.charAt(right);
            if (l == r) {
                right++;
            } else {
                difference++;

                if (right == s.length() - 1) {
                    maxLength = Math.max(maxLength, right - left + 1);
                    return maxLength;
                } else if (difference == k + 1) {
                    left++;
                    if (s.charAt(left) == s.charAt(right)) {
                        difference--;
                    }
                    maxLength = Math.max(maxLength, right - left - 1);
                } else {
                }
                right++;
            }
        }
        return maxLength;
    });

    IntLongestRepeatingCharacterReplacement intSol = ((s, k) -> {
        int[] arr = new int[26];
        Arrays.fill(arr, 0);
        int ans = 0, max = 0, i = 0;
        for (int j = 0; j < s.length(); j++) {
            arr[s.charAt(j) - 'A']++;
            max = Math.max(max, arr[s.charAt(j) - 'A']);
            if (j - i + 1 - max > k) {
                arr[s.charAt(i) - 'A']--;
                i++;
            }
            ans = Math.max(max, j - i + 1);
        }
        return ans;
    });

    public void test(IntLongestRepeatingCharacterReplacement func) {
        System.out.println("Expected: 4, Actual: " + func.characterReplacement("ABAB", 2));
        System.out.println("Expected: 4, Actual: " + func.characterReplacement("AABABBA", 1));
    }

    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement l = new LongestRepeatingCharacterReplacement();
        l.test(l.intLongestRepeatingCharacterReplacement);
        l.test(l.intSol);
    }
}
