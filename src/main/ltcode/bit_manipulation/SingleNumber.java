package main.ltcode.bit_manipulation;

import java.util.HashSet;

@FunctionalInterface
interface IntSingleNumber {
    int singleNumber(int[] nums);
}

/**
 * 136. Single Number (Easy)
 */
public class SingleNumber {
    IntSingleNumber singleNumber1st = ((nums) -> {
        if (nums.length == 1) {
            return nums[0];
        }
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
        }
        return result;
    });

    public void test(IntSingleNumber func) {
        int[] test1 = {2, 2, 1};
        System.out.println("Expected: 1, Actual: " + func.singleNumber(test1));

        int[] test2 = {4, 1, 2, 1, 2};
        System.out.println("Expected: 4, Actual: " + func.singleNumber(test2));

        int[] test3 = {1};
        System.out.println("Expected: 1, Actual: " + func.singleNumber(test3));
    }

    public static void main(String[] args) {
        SingleNumber singleNumber = new SingleNumber();
        singleNumber.test(singleNumber.singleNumber1st);
    }
}
