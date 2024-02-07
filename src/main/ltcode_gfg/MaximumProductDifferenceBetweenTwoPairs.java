package main.ltcode_gfg;

import java.util.Arrays;

/**
 *  1913. Maximum Product Difference Between Two Pairs (Easy)
 */
@FunctionalInterface
interface IntMaximumProductDifferenceBetweenTwoPairs {
    int maxProductDifference(int[] nums);
}
public class MaximumProductDifferenceBetweenTwoPairs {
    /*
        First idea.
            - Sort array, then pick up 1st, 2nd, the last, and previous of the last
            - Return the product difference of above 4 elements
            --> O(n log n), O(1)

        Second idea.
            - Use four pointers to find four elements
            --> O(n), O(1)
     */
    IntMaximumProductDifferenceBetweenTwoPairs intMaximumProductDifferenceBetweenTwoPairs1st = ((nums) -> {
        if (nums.length >= 4) {
            Arrays.sort(nums);
            return (nums[nums.length-1] * nums[nums.length-2] - nums[1]*nums[0]);
        } else {
            return -1;
        }
    });

    IntMaximumProductDifferenceBetweenTwoPairs intMaximumProductDifferenceBetweenTwoPairs2nd = ((nums) -> {
        int firstLarge = Integer.MIN_VALUE, secondLarge = Integer.MIN_VALUE, firstSmall = Integer.MAX_VALUE, secondSmall = Integer.MAX_VALUE;
        int temp = 0;

        for (int i=0; i<nums.length; i++) {
            temp = nums[i];
            if (temp > firstLarge) {
                secondLarge = firstLarge;
                firstLarge = temp;
            } else if (temp > secondLarge) {
                secondLarge = temp;
            }

            if (temp < firstSmall) {
                secondSmall = firstSmall;
                firstSmall = temp;
            } else if (temp < secondSmall)  {
                secondSmall = temp;
            }
        }
        return (firstLarge * secondLarge) - (firstSmall * secondSmall);
    });

    public void test(IntMaximumProductDifferenceBetweenTwoPairs func) {
        int[] nums1 = {5, 6, 2, 7, 4};
        System.out.println("Expected: 34, Actual: " + func.maxProductDifference(nums1));
    }

    public static void main(String[] args) {
        MaximumProductDifferenceBetweenTwoPairs maxProd = new MaximumProductDifferenceBetweenTwoPairs();
        maxProd.test(maxProd.intMaximumProductDifferenceBetweenTwoPairs1st);
        maxProd.test(maxProd.intMaximumProductDifferenceBetweenTwoPairs2nd);
    }
}
