package main.ltcode_gfg._05_binary_search;

/**
 * 153. Find Minimum in Rotated Sorted Array (Medium)
 */

@FunctionalInterface
interface IntFindMinimumInRotatedSortedArray {
    int findMin(int[] nums);
}

public class FindMinimumInRotatedSortedArray {
    IntFindMinimumInRotatedSortedArray findMinInitial = (nums -> {
        int min = 0;
        int n = nums.length;
        if (nums[0]<nums[n - 1])
            return nums[0];

        min = Math.min(nums[0], nums[n - 1]);
        int mid = (1 + n) / 2;
        int nextFirst = nums[mid];
        int nextLast = nums[mid - 1];
        min = Math.min(min, nextFirst);
        min = Math.min(min, nextLast);
        return min;
    });

    /*
        O(n), O(1)
     */
    IntFindMinimumInRotatedSortedArray findMinNeet = (nums -> {
        // [4,5,6,7,0,1,2] [3,4,5,1,2]
        int min = nums[0];
        int n = nums.length;

        int left = 0, right = n - 1;
        while (left <= right) {
            if (nums[left] < nums[right]) {
                min = Math.min(min, nums[left]);
                break;
            }
            int mid = (left + right) / 2;   // l: 4, r: 6, m: 3, nums[m]: 7, nums[l]: 4
            min = Math.min(min, nums[mid]);
            if (nums[mid] >= nums[left]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return min;
    });

    public void test(IntFindMinimumInRotatedSortedArray func) {
        System.out.println("Expected: 1, Actual: " + func.findMin(new int[]{3,4,5,1,2}));
        System.out.println("Expected: 0, Actual: " + func.findMin(new int[]{4,5,6,7,0,1,2}));
        System.out.println("Expected: 11, Actual: " + func.findMin(new int[]{11,13,15,17}));
        System.out.println("Expected: 11, Actual: " + func.findMin(new int[]{11}));
    }

    public static void main(String[] args) {
        FindMinimumInRotatedSortedArray f = new FindMinimumInRotatedSortedArray();
        f.test(f.findMinNeet);
    }
}
