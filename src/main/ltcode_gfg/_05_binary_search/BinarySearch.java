package main.ltcode_gfg._05_binary_search;

@FunctionalInterface
interface IntBinarySearch {
    int search(int[] nums, int target);
}

/**
    704. Binary Search (Easy)
 */
public class BinarySearch {
    /*
        First idea
            - Use while loop
            ==> 0 ms (100%), 44.69 MB (62.89%)

        Second idea
            - Use recursive call
            ==> 0 ms (100%), 44.50 MB (80.16%)
     */
    IntBinarySearch search1st = ((nums, target) -> {
        int targetIndex = -1;
        int start = 0, end = nums.length - 1;
        int mid = 0;

        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] < target) {
                start = mid + 1;
            } else if (nums[mid] > target) {
                end = mid - 1;
            } else {
                targetIndex = mid;
                break;
            }
        }

        return targetIndex;
    });

    IntBinarySearch searchWithRecursive = ((nums, target) -> {
        return searchRecursive(nums, 0, nums.length - 1, target);
    });

    private int searchRecursive(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }
        int mid = (start + end) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] < target) {
            return searchRecursive(nums, mid + 1, end, target);
        } else {
            return searchRecursive(nums, start, mid - 1, target);
        }
    }

    public void test(IntBinarySearch func) {
        int[] nums1 = {-1, 0, 3, 5, 9, 12};
        System.out.println("Expected: 4, Actual: " + func.search(nums1, 9));

        int[] nums2 = {-1, 0, 3, 5, 9, 12};
        System.out.println("Expected: -1, Actual: " + func.search(nums2, 2));
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        binarySearch.test(binarySearch.search1st);
        binarySearch.test(binarySearch.searchWithRecursive);
    }
}
