package main.ltcode_gfg._05_binary_search;

/**
 *  33. Search in Rotated Sorted Array (Medium)
 */
@FunctionalInterface
interface IntSearchInRotatedSortedArray {
    int search(int[] nums, int target);
}

public class SearchInRotatedSortedArray {
    /*
        1st idea
        passed with 0ms
        time: O(log n), space: O(1)
     */
    IntSearchInRotatedSortedArray searchTargetInitial = ((nums, target) -> {
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            // edge case of [3, 1] or similar status
            if (right - left == 1) {
                if (nums[left] == target)
                    return left;
                else if (nums[right] == target)
                    return right;
                else
                    return -1;
            }

            int middle = (left + right) / 2;
            if (nums[middle] == target)
                return middle;
            else {
                if (nums[left]<nums[middle]) {
                    if (nums[left]<=target && target < nums[middle])
                        right = middle - 1;
                    else
                        left = middle + 1;
                } else {
                    if (nums[middle] < target && target <= nums[right])
                        left = middle + 1;
                    else
                        right = middle - 1;
                }
            }
        }
        return -1;
    });

    IntSearchInRotatedSortedArray searchTargetInitialSol = ((nums, target) -> {
        int n = nums.length;
        int left = 0, right = n - 1;

        while (left <= right) {
            int middle = (left + right) / 2;
            if (nums[middle] == target)
                return middle;
            else {
                if (nums[left]<=nums[middle]) {
                    if (nums[left]<=target && target < nums[middle])
                        right = middle - 1;
                    else
                        left = middle + 1;
                } else {
                    if (nums[middle] < target && target <= nums[right])
                        left = middle + 1;
                    else
                        right = middle - 1;
                }
            }
        }
        return -1;
    });

    public void test(IntSearchInRotatedSortedArray func) {
        System.out.println("Expected: 4, Actual: " + func.search(new int[]{4,5,6,7,0,1,2}, 0));
        System.out.println("Expected: -1, Actual: " + func.search(new int[]{4,5,6,7,0,1,2}, 3));
        System.out.println("Expected: -1, Actual: " + func.search(new int[]{1}, 0));
    }

    public static void main(String[] args) {
        SearchInRotatedSortedArray s = new SearchInRotatedSortedArray();
        s.test(s.searchTargetInitial);
    }
}
