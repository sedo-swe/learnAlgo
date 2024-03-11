package main.ltcode_gfg._13_1d_dynamic_programming;

/**
 * 5. Longest Palindromic Substring (Medium)
 */

@FunctionalInterface
interface IntLongestPalindromicSubstring {
    String longestPalindrome(String s);
}

public class LongestPalindromicSubstring {

    /*
        Solution: Check Palindrome from the middle to both outside
        ==> Passed, time: O (n^2), space: O (1)
     */
    IntLongestPalindromicSubstring checkFromMiddle = (s -> {
        if (s == null || s == "") return s;
        int length = s.length();
        if (length < 2) return s;

        int resultLen = 0;
        String result = "";

        for (int i=0; i<length; i++) {
            // Odd case
            int left = i, right = i;
            while (0<=left && right <length && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > resultLen) {
                    result = s.substring(left, right+1);
                    resultLen = right - left + 1;
                }
                left--;
                right++;
            }

            // Even case
            left = i;
            right = i+1;
            while (0<=left && right <length && s.charAt(left) == s.charAt(right)) {
                if (right - left + 1 > resultLen) {
                    result = s.substring(left, right+1);
                    resultLen = right - left + 1;
                }
                left--;
                right++;
            }
        }
        return result;
    });

    /*
        Improved Solution: Check Palindrome from the middle to both outside
        ==> Passed, time: O (n^2), space: O (1)
     */
    IntLongestPalindromicSubstring checkFromMiddleImproved = (s -> {
        int best = 0;
        int start = 0, end = 0;

        for (int i = 0; i < s.length(); i++) {
            int left = i - 1;
            // Skip same characters
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                ++i;
            }

            int right = i + 1;
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                --left;
                ++right;
            }

            if (right - left > best) {
                best = right - left;
                start = left + 1;
                end = right;
            }
        }
        return s.substring(start, end);
    });

    /*
        Solution: Dynamic Programming
        ==> Passed, time: O (n^2), space: O (n^2)
     */
    IntLongestPalindromicSubstring dp = (s -> {
        int len = s.length();
        int left = 0, right = 1, max = 0;

        boolean[][] isPalindrome = new boolean[len][len];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                if (i == j) {
                    isPalindrome[i][j] = true;
                } else if (s.charAt(i) == s.charAt(j)) {
                    if (j - i == 1) {
                        isPalindrome[i][j] = true;
                    } else {
                        isPalindrome[i][j] = isPalindrome[i + 1][j - 1];
                    }
                }

                if (isPalindrome[i][j] && j - i + 1 > max) {
                    max = j - i + 1;
                    left = i;
                    right = j + 1;
                }
            }
        }

        return s.substring(left, right);
    });

    public void test(IntLongestPalindromicSubstring func) {
        System.out.println("Expected: \"bab\", Actual: \"" + func.longestPalindrome("babad") + "\"");
        System.out.println("Expected: \"bb\", Actual: \"" + func.longestPalindrome("cbbd") + "\"");
    }

    public static void main(String[] args) {
        LongestPalindromicSubstring l = new LongestPalindromicSubstring();
        l.test(l.checkFromMiddle);
        l.test(l.checkFromMiddleImproved);
    }
}
