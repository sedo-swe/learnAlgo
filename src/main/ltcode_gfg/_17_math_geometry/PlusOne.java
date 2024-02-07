package main.ltcode_gfg._17_math_geometry;

/**
 *  66. Plus One (Easy)
 */
@FunctionalInterface
interface IntPlusOne {
    int[] plusOne(int[] digits);
}
public class PlusOne {

    IntPlusOne plusOne1st = ((digits) -> {
        if (digits == null || digits.length == 0) {
            return null;
        }

        int currentIndex = digits.length-1;
        boolean continuous = true;
        while (continuous) {
            if (currentIndex < 0) {
                int[] newArray = new int[digits.length + 1];
                System.arraycopy(digits, 0, newArray, 1, digits.length);
                digits = newArray;
                currentIndex = 0;
            }
            if (digits[currentIndex] + 1 < 10) {
                digits[currentIndex] = digits[currentIndex] + 1;
                continuous = false;
            } else {
                digits[currentIndex] = 0;
                currentIndex--;
            }
        }
        return digits;
    });

    IntPlusOne plusOneSolSimple = ((digits) -> {
        if (digits == null || digits.length == 0) {
            return null;
        }

        int len = digits.length;
        for (int i = len - 1; i >= 0; i--) {
            if (digits[i] + 1 < 10) {
                digits[i]++;
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    });

    IntPlusOne plusOneSolComplete = ((digits) -> {
        if (digits == null || digits.length == 0) {
            return null;
        }

        final int len = digits.length;
        int[] newDigits = new int[len + 1];
        int carry = 1;
        int currSum = 0;
        for (int i = len - 1; i >= 0; i--) {
            currSum = digits[i] + carry;
            if (currSum > 9) {
                digits[i] = currSum % 10;
                newDigits[i + 1] = digits[i];
                carry = 1;
            } else {
                digits[i] = currSum;
                newDigits[i + 1] = digits[i];
                carry = 0;
                break;
            }
        }

        if (carry == 1) {
            newDigits[0] = 1;
            return newDigits;
        }
        return digits;
    });

    public void test(IntPlusOne func) {
        int[] test1 = {1, 2, 3};
        System.out.println("Expected: 124, Actual: " + this.toNumbers(func.plusOne(test1)));

        int[] test2 = {1, 2, 9};
        System.out.println("Expected: 130, Actual: " + this.toNumbers(func.plusOne(test2)));

        int[] test3 = {9};
        System.out.println("Expected: 10, Actual: " + this.toNumbers(func.plusOne(test3)));

        int[] test4 = {9,9,9,9,9};
        System.out.println("Expected: 100000, Actual: " + this.toNumbers(func.plusOne(test4)));
    }

    private int toNumbers(int[] digits) {
        int result = 0;
        if (digits == null || digits.length == 0) {
            return 0;
        }
        for (int i = (digits.length - 1); i > -1 ; i--) {
            result = result +  digits[i] * (int) Math.pow(10, (digits.length - 1 - i));
        }
        return result;
    }

    public static void main(String[] args) {
        PlusOne plusOne = new PlusOne();
        plusOne.test(plusOne.plusOne1st);

        System.out.println();
    }
}
