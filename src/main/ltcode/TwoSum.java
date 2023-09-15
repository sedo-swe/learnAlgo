package main.ltcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@FunctionalInterface
interface InfTwoSum {
    int[] findTwoSum(int[] nums, int target);
}
public class TwoSum {
    /* Assumption & Condition
        - not sorted
     */

    /* First idea
        Checking from both sides after sorting
        0. Copy original array (n)
        1. Sort array (n log n)
        2. Handle edge cases
            - target is negative or zero, but first element is positive
            - target is positive or zero, but last element is negative
        3. Check sum of left and right
        4. If sum == target, return left & right
        5. If not, move one side until left < right
            If sum < target, move left by 1
            If sum > target, move right by -1
        6. If there is a pair making target, find original index from original array

        -> Time: O(n log n) because of sorting, Space: O(n), where n is a size of array
        ==> Accepted, Time: 3 ms (67.52%)/ Memory: 43.6 MB (60.26%)
     */

    /* Second idea
        1. Put all elements into HashMap one by one (key: nums[i], value: i)
        2. Check that (target - nums[i]) exists in keySet
        3. If exists, return {HashMap[target - nums[i]], i}
        4. If not until end of array, return empty

        -> Time: O(n), Space: O(n), where n is a size of array
        ==> Accepted, Time: 2 ms (82.63%)/ Memory: 43.4 MB (93.35%)
     */
    InfTwoSum infTwoSum1st = ((nums, target) -> {
        int[] result = null;

        int[] original = Arrays.copyOf(nums, nums.length);
        Arrays.sort(nums);

        int left = 0, right = nums.length - 1;

        if (target <= 0 && nums[left] > 0) {
            return new int[] {};
        }

        if (target >= 0 && nums[right] < 0) {
            return new int[] {};
        }

        int tempSum = 0;
        while (left < right) {
            tempSum = nums[left] + nums[right];
            if (tempSum < target) {
                left++;
            } else if (tempSum > target) {
                right--;
            } else {
                result = new int[]{left, right};
                break;
            }
        }

        if (result == null) {
            return new int[]{};
        } else {
            int first = Integer.MIN_VALUE, second = Integer.MIN_VALUE;

            for (int i = 0; i < original.length; i++) {
                if (first == Integer.MIN_VALUE) {
                    if (original[i] == nums[result[0]] || original[i] == nums[result[1]]) {
                        first = i;
                    }
                } else {
                    if (original[i] == nums[result[0]] || original[i] == nums[result[1]]) {
                        second = i;
                        break;
                    }
                }
            }
            return new int[]{first, second};
        }
    });

    InfTwoSum infTwoSum2nd = ((nums, target) -> {
        Map<Integer, Integer> values = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (values.containsKey(target - nums[i])) {
                return new int[]{values.get(target - nums[i]), i};
            } else {
                values.put(nums[i], i);
            }
        }
        return new int[]{};
    });

    InfTwoSum infTwoSumSolution = ((nums, target) -> {
        int first = 0, second = 1, window = 1;          // first: 0, second: 2, window: 2, nums.length-1: 3
        while(true) {
            if(nums[first] + nums[second] == target) {  // sum: 26, target: 22
                break;
            } else if (second == nums.length - 1) {
                window++;
                first = 0;
                second = window;
            } else {
                first++;
                second++;
            }
        }
        return new int[]{first, second};
    });


    public void test(InfTwoSum func) {
//        int[] nums1 = {2, 7, 11, 15};
//        int target1 = -1;
//        System.out.println(this.printArray(func.findTwoSum(nums1, target1)) + ", [Expected]: []");
//
//        int[] nums2 = {-2, -7, -11, -15};
//        int target2 = 9;
//        System.out.println(this.printArray(func.findTwoSum(nums2, target2)) + ", [Expected]: []");

        int[] nums3 = {2, 7, 11, 15};
        int target3 = 9;
//        System.out.println(Arrays.equals(func.findTwoSum(nums3, target3), new int[] {0, 1}) + ", [Expected]: [0,1]");
        System.out.println(this.printArray(func.findTwoSum(nums3, target3)) + ", [Expected]: [0, 1]");

        int[] nums4 = {3, 2, 4};
        int target4 = 6;
        System.out.println(this.printArray(func.findTwoSum(nums4, target4)) + ", [Expected]: [1, 2]");

        int[] nums5 = {3, 3};
        int target5 = 6;
        System.out.println(this.printArray(func.findTwoSum(nums5, target5)) + ", [Expected]: [0, 1]");

        int[] nums6 = {3, 4, 5, 1, 9, 14, 6, 11};
        int target6 = 13;
        System.out.println(this.printArray(func.findTwoSum(nums6, target6)) + ", [Expected]: [1, 4]");

        int[] nums7 = {-7, -1, 3, 4, 5, 1, 14, 0, 6, 11};
        int target7 = 13;
        System.out.println(this.printArray(func.findTwoSum(nums7, target7)) + ", [Expected]: [1, 6]");

        int[] nums8 = {11, 6, 0, 14, 1, 5, 4, 3, -1, -7};
        int target8 = 13;
        System.out.println(this.printArray(func.findTwoSum(nums8, target8)) + ", [Expected]: [3, 8]");
    }

    private String printArray(int[] nums) {
        String t = "[";
        if (nums != null && nums.length == 2) {
            t = t + nums[0] + ", " + nums[1];
        }
        t = t + "]";
        return t;
    }

    public static void main(String[] args) {
        TwoSum twoSum = new TwoSum();
//        twoSum.test(twoSum.infTwoSum1st);
//        twoSum.test(twoSum.infTwoSum2nd);
        twoSum.test(twoSum.infTwoSumSolution);
    }
}
