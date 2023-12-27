package main.ltcode.math_n_geometry;

import java.util.HashSet;
import java.util.Set;

@FunctionalInterface
interface IntHappyNumber {
    boolean isHappy(int n);
}

/**
 *  202. Happy Number (Easy)
 */
public class HappyNumber {
    IntHappyNumber intHappyNumber = ((n) -> {
        int digits = 0, tempSum = 0;
        Set<Integer> sum = new HashSet<>();

        while (true) {
            tempSum = 0;
            digits = getDigits(n);
            for (int i = 0; i < digits; i++) {
                tempSum += (int) Math.pow(getDigit(n, i + 1), 2);
            }
            System.out.println(tempSum);
            if (tempSum == 1) {
                return true;
            }
            else {
                if (sum.contains(tempSum)) { return false; }
                else { sum.add(tempSum); }
            }
            n = tempSum;
        }
    });

    private int getDigit(int n, int d) {
        return (int) (n / (int) Math.pow(10, d - 1)) % 10;
    }

    private int getDigits(int n) {
        int digits = 0;
        while (n > 0) {
            n = n / 10;
            digits++;
        }
        return digits;
    }

    IntHappyNumber intHappyNumberSol = ((n) -> {
        int tempSum = 0;
        Set<Integer> sum = new HashSet<>();

        while (sum.add(n)) {
            tempSum = 0;
            while (n > 0) {
                tempSum += (n % 10) * (n % 10);
                n /= 10;
            }
            if (tempSum == 1) {
                return true;
            } else {
                n = tempSum;
            }
        }
        return false;
    });

    public void test(IntHappyNumber func) {
        System.out.println("Expected: true, Actual: " + func.isHappy(19));
        System.out.println("Expected: false, Actual: " + func.isHappy(2));
    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        hn.test(hn.intHappyNumberSol);
    }
}
