package main.ltcode;

import java.util.function.Predicate;

/**
 * Easy, 9. Palindrome Number
 */
public class PalindromeNumber {

    private int getDigitsOfNumber(int number) {
        int digits = 0;
        int quotient = 0;
        if (number == 0) {
            return 1;
        }

        int divisor = 0;
        while (true) {
            divisor = (int) Math.pow(10, digits);
            if (divisor == Integer.MAX_VALUE) {
                return digits;
            }
            quotient = number / divisor;
            if (quotient == 0) {
                break;
            } else {
                digits++;
            }
        }

        return digits;
    }


    /**
     * Accepted (runtime 60.10%, memory 98.04%)
     */
    Predicate<Integer> isPalindrome = x -> {

        // return false if number is negative
        if (x < 0) {
            return false;
        }

        // get the number of digits
        int digits = this.getDigitsOfNumber(x);

        // do verification while comparing
        int[] numbers = new int[digits];
        int divisor = -1, dividend = x;
        for (int i = digits - 1; i >= 0; i--) {
            divisor = (int) Math.pow(10, i);
            numbers[i] = dividend / divisor;
            dividend = dividend % divisor;
        }

        int left = digits - 1;
        for (int i = 0; i < Math.round(digits / 2); i++) {;
            if (numbers[left - i] != numbers[i]) {
                return false;
            }
        }
        return true;
    };

    /**
     * Solution (runtime 87.73%, memory 57.63%)
     */
    Predicate<Integer> isPalindromeFastest = x -> {
        if (x < 0 || (x != 0 && x % 10 == 0)) {
            return false;
        }

        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x = x / 10;
        }
        return (x == reversed || x == reversed / 10);
    };

    public void test(Predicate<Integer> predicate) {
        System.out.println("121:\t" + (predicate.test(121) == true ? "PASSED" : "FAILED"));
        System.out.println("-121:\t" + (predicate.test(-121) == false ? "PASSED" : "FAILED"));
        System.out.println("10:\t" + (predicate.test(10) == false ? "PASSED" : "FAILED"));
        System.out.println("0:\t" + (predicate.test(0) == true ? "PASSED" : "FAILED"));
        System.out.println("5:\t" + (predicate.test(5) == true ? "PASSED" : "FAILED"));
        System.out.println("22:\t" + (predicate.test(22) == true ? "PASSED" : "FAILED"));
        System.out.println("1221:\t" + (predicate.test(1221) == true ? "PASSED" : "FAILED"));
        System.out.println("12211:\t" + (predicate.test(12211) == false ? "PASSED" : "FAILED"));
        System.out.println("123454321:\t" + (predicate.test(123454321) == true ? "PASSED" : "FAILED"));
        System.out.println("2147483646:\t" + (predicate.test(2147483646) == false ? "PASSED" : "FAILED"));
        System.out.println("2147483647:\t" + (predicate.test(2147483647) == false ? "PASSED" : "FAILED"));
        System.out.println("2147447412:\t" + (predicate.test(2147447412) == true ? "PASSED" : "FAILED"));
    }

    public static void main(String[] args) {
        PalindromeNumber pn = new PalindromeNumber();
        pn.test(pn.isPalindrome);
        pn.test(pn.isPalindromeFastest);
    }
}
