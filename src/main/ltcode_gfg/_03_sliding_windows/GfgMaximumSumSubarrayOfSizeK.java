package main.ltcode_gfg._03_sliding_windows;

import java.util.ArrayList;

/**
 *  MAXIMUM SUM SUBARRAY OF SIZE K
 *      Given an array of positive integers and a positive numberk, find the maximum sum of any contiguous subarray of size k.
 *
 *      Input: [3, 5, 2, 1, 7], k=2
 *      Output: 8
 *
 *      The subarray [1, 7] is the sum of the maximum sum.
 *
 *  https://builtin.com/data-science/sliding-window-algorithm
 */
@FunctionalInterface
interface IntMaximumSumSubarrayOfSizeK {
    int findMaximumSumSubarrayOfSizeK(int[] nums, int k);
}

@FunctionalInterface
interface IntMaximumSumSubarrayOfSizeKGfg {
    long findMaximumSumSubarrayOfSizeK(int K, ArrayList<Integer> nums, int N);
}
public class GfgMaximumSumSubarrayOfSizeK {

    IntMaximumSumSubarrayOfSizeK intMaximumSumSubarrayOfSizeK = ((nums, k) -> {
        int max = Integer.MIN_VALUE, sum = 0;
        if (nums == null) {
            return max;
        }

        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            if (r - l >= k) {
                sum -= nums[l];
                l++;
            }
            max = Math.max(max, sum);
        }
        return max;
    });

    /*
    Both 1st and 2nd passed.

        The time complexity of the maximumSumSubarray method is O(N), where N is the size of the input array nums.
        This is because the method iterates through the array once, performing constant time operations for each element.

        The space complexity is O(1) because the method only uses a constant amount of extra space to store the maximum
        sum and the current sum. It does not use any additional data structures that grow with the input size.

    */
    IntMaximumSumSubarrayOfSizeKGfg findMaximumSumSubarrayOfSizeKGfg = ((K, nums, N) -> {
        long max = Integer.MIN_VALUE, sum = 0;
        if (nums == null) {
            return max;
        }

        int l = 0;
        for (int r = 0; r < N; r++) {
            sum += nums.get(r);
            if (r - l >= K) {
                sum -= nums.get(l);
                l++;
            }
            max = Math.max(max, sum);
        }
        return max;
    });

    public void test(IntMaximumSumSubarrayOfSizeK func) {
        System.out.println("Expected: 8, Actual: " + func.findMaximumSumSubarrayOfSizeK(new int[]{3, 5, 2, 1, 7}, 2));
        System.out.println("Expected: 7, Actual: " + func.findMaximumSumSubarrayOfSizeK(new int[]{3, 5, 2, 1, 7}, 1));
        System.out.println("Expected: 10, Actual: " + func.findMaximumSumSubarrayOfSizeK(new int[]{3, 5, 2, 1, 7}, 3));
        System.out.println("Expected: 21, Actual: " + func.findMaximumSumSubarrayOfSizeK(new int[]{3, 9, 12, 1, 7}, 2));
    }

    public void test(IntMaximumSumSubarrayOfSizeKGfg func) {
        ArrayList<Integer> t1 = new ArrayList<>();
        for (int i : new int[]{3, 5, 2, 1, 7})
            t1.add(i);
        System.out.println("Expected: 8, Actual: " + func.findMaximumSumSubarrayOfSizeK(2, t1, 5));
        ArrayList<Integer> t2 = new ArrayList<>();
        for (int i : new int[]{3, 9, 12, 1, 7})
            t2.add(i);
        System.out.println("Expected: 7, Actual: " + func.findMaximumSumSubarrayOfSizeK(1, t1, 5));
        System.out.println("Expected: 10, Actual: " + func.findMaximumSumSubarrayOfSizeK(3,t1, 5));
        System.out.println("Expected: 21, Actual: " + func.findMaximumSumSubarrayOfSizeK(2, t2, 5));
    }

    public static void main(String[] args) {
        GfgMaximumSumSubarrayOfSizeK m = new GfgMaximumSumSubarrayOfSizeK();
        m.test(m.intMaximumSumSubarrayOfSizeK);
        System.out.println("===========");
        m.test(m.findMaximumSumSubarrayOfSizeKGfg);
    }
}
