package main.ltcode_gfg._02_two_pointers;

@FunctionalInterface
interface InfValidPalindrome {
    boolean isItPalindrome(String s);
}

public class ValidPalindrome {
    InfValidPalindrome interFunction1st = ((s) -> {
        if (s.trim().isEmpty()) return true;
        s = s.toLowerCase();

        int l = 0, r = s.length() - 1;
        boolean isL = false, isR = false;
                                                                // s: aba
        while (l <= r) {                                        // l: 0, r: 2
            if (!isL) {                                         // isL: false
                if (Character.isLetterOrDigit(s.charAt(l))) {   // s.charAt(l): a
                    isL = true;
                } else {
                    l++;
                }
            }
            if (!isR) {                                         // isR: false
                if (Character.isLetterOrDigit(s.charAt(r))) {   // s.charAt(r): a
                    isR = true;
                } else {
                    r--;
                }
            }

            if (isL && isR) {
                if (s.charAt(l) == s.charAt(r)) {
                    isL = false;
                    isR = false;
                    l++;
                    r--;
                } else {
                    return false;
                }
            }
        }
        return true;
    });

    public void test(InfValidPalindrome interFunction) {
//        System.out.println("[Expected]: true,  [Actual]: " + interFunction.isItPalindrome("aba"));
//        System.out.println("[Expected]: false, [Actual]: " + interFunction.isItPalindrome("abc"));
        System.out.println("[Expected]: true, [Actual]: " + interFunction.isItPalindrome("A man, a plan, a canal: Panama"));
        System.out.println("[Expected]: false, [Actual]: " + interFunction.isItPalindrome("race a car"));
        System.out.println("[Expected]: true, [Actual]: " + interFunction.isItPalindrome(" "));
    }

    public static void main(String[] args) {

        ValidPalindrome validPalindrome = new ValidPalindrome();
        validPalindrome.test(validPalindrome.interFunction1st);
    }
}
