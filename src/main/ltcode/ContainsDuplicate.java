package main.ltcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 217. Contains Duplicate (Easy)
 */


@FunctionalInterface
interface InternalFunction {
    boolean verifyThereIsDuplicate(int[] nums);
}

public class ContainsDuplicate {

    /* Conditions
        - not sorted (based on example)
     */

    /* First idea
        - use hashmap while visiting all elements. If it exists in hashmap, then there is at least one duplication.
            --> Time: O(n), Space: O(n)

            ==> Accepted, 10ms (86%) / 55.3MB (59%)
            ==> Optimized Version
                Accepted, 10ms (86%) / 54.4MB (96%)

       Second idea
        - do sorting elements, and visit all elements from the beginning. Can find consequent duplication
            --> Time: O(n), Space: O(1)

       Third idea
        - ???
     */

    InternalFunction checkDuplicationExists1st = ((nums) -> {
        boolean isDuplicated = false;

        // If null, empty or 1 element in array
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return isDuplicated;
        }

        HashSet<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(Integer.valueOf(nums[i]))) {
                isDuplicated = true;
                break;
            } else {
                hashSet.add(Integer.valueOf(nums[i]));
            }
        }

        return isDuplicated;
    });

    InternalFunction checkDuplicationExists1stOptimized = ((nums) -> {
        boolean isDuplicated = false;

        // If null, empty or 1 element in array
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return isDuplicated;
        }

        Set<Integer> hashSet = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (hashSet.contains(nums[i])) {
                isDuplicated = true;
                break;
            } else {
                hashSet.add(nums[i]);
            }
        }

        return isDuplicated;
    });

    // Accepted, 2ms (99.99%) / 57.9MB (9.33%)
    InternalFunction checkSolution1ms = ((nums) -> {
        for (int i = 1; i < nums.length; i++) {
            int current = nums[i];
            int j = i - 1;

            while (j >= 0 && current < nums[j]) {
                nums[j+1] = nums[j];
                j--;
            }

            if (j >= 0 && current == nums[j]) {
                return true;
            }

            nums[j+1] = current;
        }
        return false;
    });

    public void test(InternalFunction func) {
        int[] nums1 = {1,2,3,1};
        System.out.println(func.verifyThereIsDuplicate(nums1) + ", [Expected]: true");
        int[] nums2 = {1,2,3,4};
        System.out.println(func.verifyThereIsDuplicate(nums2) + ", [Expected]: false");
        int[] nums3 = {1,1,1,3,3,4,3,2,4,2};
        System.out.println(func.verifyThereIsDuplicate(nums3) + ", [Expected]: true");
    }

    public static void main(String[] args) {
        ContainsDuplicate containsDuplicate = new ContainsDuplicate();
        containsDuplicate.test(containsDuplicate.checkSolution1ms);
    }
}
