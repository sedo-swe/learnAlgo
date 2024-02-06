package main.ltcode._01_arrays_hashing;

/**
 * 242. Valid Anagram (Easy)
 */
@FunctionalInterface
interface InfValidAnagram {
    boolean isAnagram(String s, String t);
}
public class ValidAnagram {

    /* Conditions
        -
     */

    /* First idea
        - first of all, length of both strings should be same
        - after sorting both strings, they should be same
            -> Time: O(n) with bubble sorting, Space: O(1)
     */

    /* Second idea
        - first of all, length of both strings should be same
        - read individual letters, count the number of each letter by using int array as both strings consist of lowercase Eng letters
            -> Time: O(n), Space: O(1) as int array of length 26
            ==> Accepted, Time: 5 ms (54.92%) / Memory: 41.6 MB (98.33%)
            ==> Optimized: Accepted, Time: 2 ms (98.91%) / Memory: 43.4 MB (56.82%)
     */

    InfValidAnagram validAnagram2nd = ((s, t) -> {
        if (s.length() != t.length()) {
            return false;
        }

        int[] cntS = new int[26];
        int[] cntT = new int[26];

        for (int i = 0; i < s.length(); i++) {
            cntS[s.charAt(i)-'a']++;
            cntT[t.charAt(i)-'a']++;
        }

        for (int i = 0; i < 26; i++) {
            if (cntS[i] != cntT[i]) {
                return false;
            }
        }

        return true;
    });

    InfValidAnagram validAnagram2ndOptimized = ((s, t) -> {
        if (s.length() != t.length()) {
            return false;
        }

        int[] cnt = new int[26];

        for (char c : s.toCharArray()) {
            cnt[c-'a']++;
        }

        for (char c : t.toCharArray()) {
            cnt[c-'a']--;
        }

        for (int i = 0; i < 26; i++) {
            if (cnt[i] != 0) {
                return false;
            }
        }
        return true;
    });

    public void test(InfValidAnagram func) {
        String s1 = "anagram", t1 = "nagaram";
        System.out.println(func.isAnagram(s1, t1) + ", [Expected]: true");
        String s2 = "rat", t2 = "car";
        System.out.println(func.isAnagram(s2, t2) + ", [Expected]: false");
    }


    public static void main(String[] args) {
        ValidAnagram validAnagram = new ValidAnagram();
        validAnagram.test(validAnagram.validAnagram2nd);
    }
}


/*
    ** The reference for HashMap keySet comparison
        HashMap<Character,Integer> lettersS =  new HashMap<>();
        HashMap<Character,Integer> lettersT =  new HashMap<>();
        for (Character letter: s.toCharArray()) {
            if (lettersS.containsKey(letter)){
                lettersS.put(letter,lettersS.get(letter)+1);
            }else {
                lettersS.put(letter,1);
            }
        }
        for (Character letter: t.toCharArray()) {
            if (lettersT.containsKey(letter)){
                lettersT.put(letter,lettersT.get(letter)+1);
            }else {
                lettersT.put(letter,1);
            }
        }
        if (lettersT.size()!=lettersS.size()) {
            return false;
        }
        for (Character letter: lettersS.keySet()) {
            if (lettersT.containsKey(letter)){
                if (!Objects.equals(lettersS.get(letter), lettersT.get(letter))){
                    return false;
                }
            }else {
                return false;
            }
        }
        return true;

    ** The reference for HashMap put
        Map<Character,Integer> s1 = new HashMap<>();
        Map<Character,Integer> t1 = new HashMap<>();
        if (s.length() != t.length())
            return false;
        for(int i = 0;i<s.length();++i){
            s1.put(s.charAt(i),s1.getOrDefault(s.charAt(i),0)+1);
            t1.put(t.charAt(i),t1.getOrDefault(t.charAt(i),0)+1);
        }
        return s1.equals(t1);

    ** Another tip
        if (s.length() != t.length()) return false;

        int[] char_counts = new int[26]; //26 letters in alphabet
        for (int i = 0; i < s.length(); i++) {
            char_counts[s.charAt(i) - 'a']++;
            char_counts[t.charAt(i) - 'a']--;
        }
        for (int count : char_counts) {
            if (count != 0) {
                return false;
            }
        }
        return true;
 */
