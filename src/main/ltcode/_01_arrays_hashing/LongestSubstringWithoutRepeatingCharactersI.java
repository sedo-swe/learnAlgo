package main.ltcode._01_arrays_hashing;

import java.util.Enumeration;
import java.util.Hashtable;

public class LongestSubstringWithoutRepeatingCharactersI {

    public String findLongestSubsWithoutRepeatingCharacters(String src) {
        String longest = "";

        if (src == null || src.length() == 0) {
            System.out.println("Not a valid string");
            return null;
        }

        int left = 0;
        char[] cs = src.toCharArray();
        Hashtable<Character, Integer> htable = new Hashtable<>();
        Character curChar = null;
        String tempStr = null;
        int dupIdx = -1;
        boolean noDup = true;

        for (int current = 0; current < cs.length; current++) {

            curChar = Character.valueOf(cs[current]);
            if (htable.containsKey(curChar)) {

                tempStr = src.substring(left, current);

                if (cs[current-1] == cs[current]) {
                    left = current;
                    htable.clear();
                } else {
                    dupIdx = htable.get(curChar);
                    if (dupIdx >= left) {
                        left = dupIdx + 1;
                    }
                    htable.remove(curChar);
                    Enumeration<Character> keys = htable.keys();
                    while (keys.hasMoreElements()) {
                        Character c = keys.nextElement();
                        if (htable.get(c) < left) {
                            htable.remove(c);
                        }
                    }
                }
                htable.put(curChar, Integer.valueOf(current));
//                System.out.println("\t" + " l: "+left+", r: "+current + ", tempStr: " + tempStr);
                noDup = false;

                if (longest.length() < tempStr.length()) {
//                    System.out.println("\tlongest: " + longest + ", tempStr: " + tempStr + ", curChar: " + curChar + ", current: " + current);
                    longest = tempStr;
                }

            } else {
                htable.put(curChar, Integer.valueOf(current));
                noDup = true;
            }
        }

        if (noDup) {
            tempStr = src.substring(left, src.length());
            if (longest.length() < tempStr.length()) {
                longest = tempStr;
            }
        }

//        System.out.println("Longest Substring w/o repeating: " + longest);
//        return longest.length();
        return longest;
    }


    public void testCases() {
        String[][] src = {
                {"abcabcbb", "abc"},
                {"bbbbb", "b"},
                {"pwwkew", "wke"},
                {"aabcd", "abcd"},
                {"aabccdef", "cdef"},
                {"aakedbccdef", "akedbc"},
                {"ABDEFGABEF", "ABDEFG"},
                {"GEEKSFORGEEKS", "EKSFORG"},
                {"GEEEEEEEKSFORGEEKS", "EKSFORG"},
                {"GEE", "GE"},
                {"GEG", "GE"},
                {"GE", "GE"},
                {"abcde", "abcde"},
                {"aab", "ab"},
                {"aabbccdeabbcc", "cdeab"},
                {"tmmzuxt", "mzuxt"},
                {"wobgrovw", "bgrovw"}
        };
        String result = null;
        for (String[] s : src) {
            result = this.findLongestSubsWithoutRepeatingCharacters(s[0]);
            System.out.println("Expected longest: " + s[1] + ", pass/fail: " + s[1].equals(result));
        }

    }

    public void testHashtable() {
        Hashtable<Character, Integer> h = new Hashtable<>();
        h.put(new Character('G'), new Integer(0));
        h.put(new Character('E'), new Integer(1));
        h.put(new Character('E'), new Integer(2));

        Enumeration<Character> keys = h.keys();
        while (keys.hasMoreElements()) {
            Character ckey = keys.nextElement();
            System.out.println(ckey + ": " + h.get(ckey));
        }
    }

    public static void main(String[] args) {

        LongestSubstringWithoutRepeatingCharactersI l = new LongestSubstringWithoutRepeatingCharactersI();
        l.testCases();
//        l.testHashtable();
    }
}
