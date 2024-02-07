package main.ltcode_gfg._03_sliding_windows;

/**
 * DIFFERENCE BETWEEN THE MAXIMUM AND MINIMUM AVERAGE OF ALL K-LENGTH CONTINUOUS SUBARRAYS
 *      Input: arr[ ] = {3, 8, 9, 15}, K = 2
 *      Output: 6.5
 *      All subarrays of length 2 are {3, 8}, {8, 9}, {9, 15} and their averages are
 *      (3+8)/2 = 5.5, (8+9)/2 = 8.5, and (9+15)/2 = 12.0 respectively.
 *
 *      Therefore, the difference between the maximum(=12.0) and minimum(=5.5) is 12.0 -5.5 = 6.5.
 *
 * https://builtin.com/data-science/sliding-window-algorithm
 */
@FunctionalInterface
interface IntDifferenceBetweenTheMaxNMinAverOfAllKConSubarrays {
    float getMinMaxDiff(int[] arr, int k);
}

public class GfgDifferenceBetweenTheMaxNMinAverOfAllKConSubarrays {
    IntDifferenceBetweenTheMaxNMinAverOfAllKConSubarrays firstTry = ((nums, k) -> {
        int sum = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        int l = 0;
        for (int r = 0; r < nums.length; r++) {
            sum += nums[r];
            if (r - l + 1 == k) {
                min = Math.min(min, sum);
                max = Math.max(max, sum);
                sum -= nums[l];
                l++;
            }
        }
        return (float) max/k - (float) min/k;
    });

    public void test(IntDifferenceBetweenTheMaxNMinAverOfAllKConSubarrays func) {
        System.out.println("Expected: 6.5, Actual: " + func.getMinMaxDiff(new int[]{3,8,9,15}, 2));
        System.out.println("Expected: 9.0, Actual: " + func.getMinMaxDiff(new int[]{3,1,2,5,8,11,9,13}, 3));
    }

    public static void main(String[] args) {
        GfgDifferenceBetweenTheMaxNMinAverOfAllKConSubarrays d = new GfgDifferenceBetweenTheMaxNMinAverOfAllKConSubarrays();
        d.test(d.firstTry);
    }
}
