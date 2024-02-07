package main.ltcode_gfg._01_arrays_hashing;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.ListIterator;

public class FindLongestPalindromicSubstring {

    public String findLongestPalindromicSubstring(String src) {
        String longest = "";
        Hashtable<Character, List<Integer>> hashtable = new Hashtable<>();

        char[] cs = src.toCharArray();
        Character temp;
        List<Integer> t;

        for (int i = 0; i < cs.length; i++) {
            temp = Character.valueOf(cs[i]);
            if (hashtable.containsKey(temp)) {
                t = hashtable.get(temp);

                ListIterator<Integer> iterator = t.listIterator();
                while (iterator.hasNext()) {
                    Integer prev = iterator.next();
                    boolean isPalindrome = this.checkPalindrome(cs, prev.intValue(), i);
                    if (isPalindrome) {
                        String tempString = src.substring(prev.intValue(), i+1);
                        if (longest.length() <= tempString.length()) {
                            longest = tempString;
                            break;
                        }
                    }
                }

            } else {
                t = new ArrayList<>();
            }
            t.add(Integer.valueOf(i));
            hashtable.put(temp, t);

        }

        return longest;
    }

    private boolean checkPalindrome(char[] cs, int left, int right) {
        while (left <= right) {
            if (cs[left] == cs[right]) {
                left++;
                right--;
            } else {
                return false;
            }
        }

        return true;
    }
    public String findLongestPalindromicSubstring1(String src) {
        String longest = "";
        int duplicated = 0;

        Hashtable<Character, Integer> hashtable = new Hashtable<>();
        char[] cs = src.toCharArray();
        int left = 0;

        for (int i = 0; i < cs.length; i++) {
            Character tempChar = Character.valueOf(cs[i]);

            if (hashtable.containsKey(tempChar)) {
                hashtable.put(tempChar, hashtable.get(tempChar) + 1);
                duplicated--;
            } else {
                hashtable.put(tempChar, 1);
                duplicated++;
            }

            if (duplicated <= 0) {
                //  Call check palindrome
                if (this.checkPalindrome1(cs, left, i)) {

                }
            }
        }

        return longest;
    }

    private boolean checkPalindrome1(char[] cs, int left, int right) {
        int l = left, r = right;
        for (int i = 0; i < right - left + 1; i++) {
            if (cs[l] == cs[r]) {
                l++;
                r--;
            } else {
                return false;
            }
        }

        return true;

    }

    public void testCase() {
//        System.out.println(this.findLongestPalindromicSubstring1("bab"));
        System.out.println(">"+this.findLongestPalindromicSubstring("ba")+"<");             //
        System.out.println(">"+this.findLongestPalindromicSubstring("bb")+"<");             // bb
        System.out.println(">"+this.findLongestPalindromicSubstring("cbbd")+"<");           // bb
        System.out.println(">"+this.findLongestPalindromicSubstring("bab")+"<");            // bab
        System.out.println(">"+this.findLongestPalindromicSubstring("babad")+"<");          // aba
        System.out.println(">"+this.findLongestPalindromicSubstring("pbabakababt")+"<");    // babakabab
        System.out.println(">"+this.findLongestPalindromicSubstring("ptbabakabzbt")+"<");   // bakab
        System.out.println(">"+this.findLongestPalindromicSubstring("ptbabykybabt")+"<");   // tbabykybabt
        System.out.println(">"+this.findLongestPalindromicSubstring("ptbabykybabtqwertyuioppoiuytrewq")+"<");   // qwertyuioppoiuytrewq
        System.out.println(">"+this.findLongestPalindromicSubstring("ptbabykybabttbabykybazz")+"<"); // abykybabttbabykyba
    }

    public static void main(String[] args) {
        FindLongestPalindromicSubstring f = new FindLongestPalindromicSubstring();
        f.testCase();
    }
}
