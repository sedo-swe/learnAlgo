package main.ltcode.medium;

import java.util.Arrays;

/**
 * 371. Sum of Two Integers (Medium)
 */

@FunctionalInterface
interface IntSumOfTwoIntegers {
    int getSum(int a, int b);
}

public class SumOfTwoIntegers {
    /*
        1st idea
            - Cannot handle negative number
        Solution
            - Use bit operation (&) and shift (<<1) to handle carry over
     */
    IntSumOfTwoIntegers intSumOfTwoIntegers = ((a, b) -> {
        int sum = 0;

        String a1 = Integer.toBinaryString(a);
        String b1 = Integer.toBinaryString(b);
        System.out.println("a1: "+a1+", b1: "+b1);
        char[] s = new char[Math.max(a, b)+1];
        Arrays.fill(s, '0');
        int idxA = a1.length() - 1, idxB = b1.length() - 1, idx = s.length - 1, reOrg = 0;
        while (idxA >= 0  || idxB >= 0 ) {
            if (idxA >= 0 && idxB >= 0) {
                char achar = a1.charAt(idxA);
                char bchar = b1.charAt(idxB);
                if (achar == bchar) {
                    if (reOrg == 1) {
                        s[idx] = '1';
                    } else {
                        s[idx] = '0';
                        reOrg = 1;
                    }
                    if (achar == '0') {
                        reOrg = 0;
                    }
                } else {
                    s[idx] = reOrg == 1 ? '0' : '1';
                }
                idxA--;
                idxB--;
            } else if (idxA >= 0) {
                char achar = a1.charAt(idxA);
                if (achar == '1') {
                    s[idx] = reOrg == 1 ? '0' : '1';
                } else {
                    s[idx] = reOrg == 1 ? '1' : '0';
                    if (reOrg == 1) {
                        reOrg = 0;
                    }
                }
                idxA--;
            } else if (idxB >= 0) {
                char bchar = b1.charAt(idxB);
                if (bchar == '1') {
                    s[idx] = reOrg == 1 ? '0' : '1';
                } else {
                    s[idx] = reOrg == 1 ? '1' : '0';
                    if (reOrg == 1) {
                        reOrg = 0;
                    }
                }
                idxB--;
            }
            idx--;
        }
        if (reOrg == 1) {
            s[idx] = '1';
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i <s.length; i++) {
            System.out.print(s[i]);
            sb.append(s[i]);
        }
        sum = Integer.parseInt(sb.toString(), 2);
        return sum;
    });

    IntSumOfTwoIntegers sol = ((a, b) -> {
        int sum = 0;
        int carryOver = 0;
        while (true) {
            int xor = a ^ b;
            carryOver = (a & b) << 1;
            if (carryOver == 0) {
                sum = xor;
                break;
            }
            a = xor;
            b = carryOver;
        }
        return sum;
    });

    IntSumOfTwoIntegers solClean = ((a, b) -> {
        while (b != 0) {
            int carryOver = (a & b) << 1;
            a = a ^ b;
            b = carryOver;
        }
        return a;
    });

    public void test(IntSumOfTwoIntegers func) {
        System.out.println("Expected: 3, Actual: " + func.getSum(1, 2));
        System.out.println("Expected: 5, Actual: " + func.getSum(2, 3));
        System.out.println("Expected: 0, Actual: " + func.getSum(-1, 1));
        System.out.println("Expected: -2, Actual: " + func.getSum(-1, -1));
    }

    public static void main(String[] args) {
        SumOfTwoIntegers sum = new SumOfTwoIntegers();
//        sum.test(sum.intSumOfTwoIntegers);
        sum.test(sum.sol);
        sum.test(sum.solClean);
    }
}
