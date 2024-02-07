package main.ltcode_gfg;

@FunctionalInterface
interface IntSearchInsertPosition {
    int findPosition(int[] nums, int target);
}
public class SearchInsertPosition {

    /*
        First idea
            - Use binary search first to find element in integer array
              , then if it doesn't exist, then do binary search with range to find its potential index
     */
    IntSearchInsertPosition findPosition1st = ((nums, target) -> {
        int result = -1;

        // Edge case that target is greater than the last element
        if (target > nums[nums.length - 1]) {
            return nums.length;
        }

        int start = 0, end = nums.length - 1;
        int mid = 0;

        // Regular Binary Search to find an index of target if it exists
        while (start <= end) {
            mid = (start + end) / 2;
            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                result = mid;
                break;
            }
        }

        if (result >= 0) {
            return result;
        }

        // Binary Search with range to find an index where target is supposed to be (not exist)
        start = 0;
        end = nums.length - 1;

        while (start <= end) {
            mid = (start + end) / 2;
            if (target < nums[mid]) {
                result = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return result;
    });

    IntSearchInsertPosition findPosition2nd = ((nums, target) -> {

        int start = 0, end = nums.length - 1;
        int mid = 0;

        // Regular Binary Search to find an index of target if it exists
        while (start <= end) {              // start: 2, end: 1
            mid = (start + end) / 2;        // mid: (2+2)/2 = 2
            if (target < nums[mid]) {       // target: 4, nums[mid]: 5
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                return mid;
            }
        }

        if (end < 0) {
            return end + 1;
        }
        else if (target <= nums[end]) {
            return end;
        }
        else  {
            return end + 1;
        }
    });

    public void test(IntSearchInsertPosition func) {
        int[] nums = {1, 3, 5, 6};
        int target = 5;
        System.out.println(func.findPosition(nums, target) + ", [Expected]: 2");

        target = 2;
        System.out.println(func.findPosition(nums, target) + ", [Expected]: 1");

        target = 7;
        System.out.println(func.findPosition(nums, target) + ", [Expected]: 4");

        target = 0;
        System.out.println(func.findPosition(nums, target) + ", [Expected]: 0");

        target = 4;
        System.out.println(func.findPosition(nums, target) + ", [Expected]: 2");

        int[] nums1 = {1, 3};
        target = 2;
        System.out.println(func.findPosition(nums1, target) + ", [Expected]: 1");
    }

    public static void main(String[] args) {
        SearchInsertPosition searchInsertPosition = new SearchInsertPosition();
        searchInsertPosition.test(searchInsertPosition.findPosition2nd);
    }
}
