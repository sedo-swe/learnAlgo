package main.ltcode;

import java.util.Arrays;

@FunctionalInterface
interface IntSortArrayByParity {
    int[] sortArrayByParity(int[] nums);
}
public class SortArrayByParity {
    /*
        1st idea
            - Use two pointers
     */
    IntSortArrayByParity intSortArrayByParity1st = ((nums) -> {
        int leftIdx = 0, rightIdx = nums.length - 1;
        while (leftIdx < rightIdx) {
            if (nums[leftIdx] % 2 == 0) {
                leftIdx++;
            } else if (nums[rightIdx] % 2 == 1) {
                rightIdx--;
            } else {
                int temp = nums[rightIdx];
                nums[rightIdx] = nums[leftIdx];
                nums[leftIdx] = temp;
            }
        }
        return nums;
    });

    IntSortArrayByParity intSortArrayByParity2nd = ((nums) -> {
        if (nums == null || nums.length == 0) {
            return nums;
        }

        if (nums.length == 1) {
            return nums;
        }
        int leftIdx = 0;
        while (leftIdx < nums.length && nums[leftIdx] % 2 == 0) {
            leftIdx++;
        }
        int rightIdx = leftIdx + 1;
        while (rightIdx < nums.length) {
            if (nums[rightIdx] % 2 == 0) {
                int temp = nums[rightIdx];
                nums[rightIdx] = nums[leftIdx];
                nums[leftIdx] = temp;
                leftIdx++;
                while (nums[leftIdx] % 2 == 0) {
                    leftIdx++;
                }
                rightIdx = leftIdx;
            }
            rightIdx++;
        }
        return nums;
    });

    public void test(IntSortArrayByParity func) {
        int[] test1 = {3, 1, 2, 4};
        Arrays.stream(func.sortArrayByParity(test1)).forEach((a) -> System.out.print(a + " "));
        System.out.println("\n= 1 ====");

        int[] test2 = {0};
        Arrays.stream(func.sortArrayByParity(test2)).forEach((a) -> System.out.print(a + " "));
        System.out.println("\n= 2 ====");

        int[] test3 = {0, 2};
        Arrays.stream(func.sortArrayByParity(test3)).forEach((a) -> System.out.print(a + " "));
        System.out.println("\n= 3 ====");
    }

    public static void main(String[] args) {
        SortArrayByParity sortArrayByParity = new SortArrayByParity();
        sortArrayByParity.test(sortArrayByParity.intSortArrayByParity1st);
    }
}
