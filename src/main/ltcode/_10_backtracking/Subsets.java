package main.ltcode._10_backtracking;

import main.ltcode.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 78. Subsets (Medium)
 */
@FunctionalInterface
interface IntSubsets {
    List<List<Integer>> subsets(int[] nums);
}

public class Subsets {
    IntSubsets intSubsets = ((nums) -> {
        List<List<Integer>> subsets = new ArrayList<>();
        List<Integer> subset = new ArrayList<>();
        helper(subsets, 0, nums, subset);
        return subsets;
    });

    public void helper(List<List<Integer>> subsets, int start, int[] nums, List<Integer> subset) {
        if (start >= nums.length) {
            subsets.add(new ArrayList<>(subset));
        } else {
            subset.add(nums[start]);
            helper(subsets, start + 1, nums, subset);
            subset.remove(subset.size() - 1);
            helper(subsets, start + 1, nums, subset);
        }
    }

    public void test(IntSubsets func) {
        System.out.println("Expected: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]], Actual: "
                + PrintUtils.printListInListInteger(func.subsets(new int[] {1, 2, 3})));

        System.out.println("Expected: [[],[0]], Actual: "
                + PrintUtils.printListInListInteger(func.subsets(new int[] {0})));
    }


    public static void main(String[] args) {
        Subsets subsets = new Subsets();
        subsets.test(subsets.intSubsets);
    }
}
