package main.ltcode_gfg._02_two_pointers;

import main.ltcode_gfg.utils.PrintUtils;

import java.util.*;

/**
 * 167. Two Sum II - Input Array Is Sorted (Median)
 */
@FunctionalInterface
interface IntTwoSumII {
    int[] twoSum(int[] numbers, int target);
}

public class TwoSumII {

    /*
        1st idea.
            - use HashMap & LinkedList
     */
    IntTwoSumII intTwoSumII = ((numbers, target) -> {
        int[] pair = new int[2];
        Arrays.fill(pair, 0);
        HashMap<Integer, List<Integer>> nums = new HashMap<>();
        for (int i = 0; i < numbers.length; i++) {
            List<Integer> indexes = null;
            if (nums.containsKey(numbers[i])) {
                indexes = nums.get(numbers[i]);
            } else {
                indexes = new LinkedList();
            }
            indexes.add(i);
            nums.put(numbers[i], indexes);
        }

        for (int i = 0; i < numbers.length; i++) {
            int required = target - numbers[i];
            if (nums.containsKey(required)) {
                if (required == numbers[i]) {
                    if (nums.get(required).size() > 1) {
                        int idx = nums.get(required).get(1);
                        pair[0] = Math.min(i, idx) + 1;
                        pair[1] = Math.max(i, idx) + 1;
                        return pair;
                    }
                } else {
                    int idx = nums.get(required).get(0);
                    pair[0] = Math.min(i, idx) + 1;
                    pair[1] = Math.max(i, idx) + 1;
                    return pair;
                }
            }
        }
        return pair;
    });

    /*
        2nd idea
            - Use two pointers (left from the first element, right from the last element)
     */
    IntTwoSumII intTwoSumII2nd = ((numbers, target) -> {
        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                int[] pair = new int[2];
                pair[0] = left + 1;
                pair[1] = right + 1;
                return pair;
            } else if (sum > target) {
                right--;
            } else {
                left++;
            }
        }

        return null;
    });

    public void test(IntTwoSumII func) {
        System.out.println("Expected: [1,2], Actual: " + PrintUtils.printIntArrayString(func.twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println("Expected: [1,3], Actual: " + PrintUtils.printIntArrayString(func.twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println("Expected: [1,2], Actual: " + PrintUtils.printIntArrayString(func.twoSum(new int[]{-1, 0}, -1)));
        System.out.println("Expected: [1,2], Actual: " + PrintUtils.printIntArrayString(func.twoSum(new int[]{0, 0, 3, 4}, 0)));
        System.out.println("Expected: [1,2], Actual: " + PrintUtils.printIntArrayString(func.twoSum(new int[]{-1, -1, 1, 1, 1, 1}, -2)));
    }

    public static void main(String[] args) {
        TwoSumII twoSumII = new TwoSumII();
//        twoSumII.test(twoSumII.intTwoSumII);
        twoSumII.test(twoSumII.intTwoSumII2nd);
    }
}
