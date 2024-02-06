package main.ltcode._01_arrays_hashing;

import main.ltcode.utils.PrintUtils;

import java.util.Arrays;

/**
 * 238. Product of Array Except Self (Medium)
 *      You must write an algorithm that runs in O(n) time and without using the division operation.
 */
@FunctionalInterface
interface IntProductOfArrayExceptSelf {
    int[] productExceptSelf(int[] nums);
}

public class ProductOfArrayExceptSelf {
    /*
        1st idea.
            - Using recursive
            - time: O(n), space: O(1)
     */
    IntProductOfArrayExceptSelf findProductsWithRecursive = (nums -> {
        int[] answer = new int[nums.length];
        Arrays.fill(answer, 0);

        answer[0] = productOthers(nums[0], 1, nums, answer);
        return answer;
    });

    private int productOthers(int productOfBefore, int index, int[] nums, int[] answer) {
        // Base condition: if it's the last element in nums
        if (index == nums.length - 1) {
            answer[index] = productOfBefore;
            return nums[index];
        }
        int productOfAfter = productOthers(productOfBefore * nums[index], index+1, nums, answer);
        answer[index] = productOfBefore * productOfAfter;
        return nums[index] * productOfAfter;
    };

    /*
        Solution
            time: O(n), space: O(1)
     */
    IntProductOfArrayExceptSelf solution = (nums -> {
        int[] answer = new int[nums.length];
        int prefix = 1, suffix = 1;
        for (int i = 0; i < nums.length; i++) {
            answer[i] = prefix;
            prefix *= nums[i];
        }

        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] *= suffix;
            suffix *= nums[i];
        }

        return answer;
    });

    public void test(IntProductOfArrayExceptSelf func) {
        System.out.println("Expected: [24, 12, 8, 6], Actual: "
                + PrintUtils.printIntArrayString(func.productExceptSelf(new int[] {1, 2, 3, 4})));
        System.out.println("Expected: [0, 0, 9, 0, 0], Actual: "
                + PrintUtils.printIntArrayString(func.productExceptSelf(new int[] {-1, 1, 0, -3, 3})));
    }

    public static void main(String[] args) {
        ProductOfArrayExceptSelf p = new ProductOfArrayExceptSelf();
        p.test(p.findProductsWithRecursive);
        p.test(p.solution);
    }
}
