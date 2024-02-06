package main.ltcode._10_backtracking;

import main.ltcode.utils.PrintUtils;

import java.util.ArrayList;
import java.util.List;

/**
 *  39. Combination Sum (Medium)
 */
@FunctionalInterface
interface IntCombinationSum {
    List<List<Integer>> combinationSum(int[] candidates, int target);
}

public class CombinationSum {
    IntCombinationSum intCombinationSum = ((candidates, target) -> {
        List<List<Integer>> combinations = new ArrayList<>();
        List<Integer> combination = new ArrayList<>();
        findCombination(combinations, 0, candidates, target, combination);
        return combinations;
    });

    public void findCombination(List<List<Integer>> combinations, int index, int[] candidates, int target, List<Integer> combination) {
        // base conditions
        int sum = combination.stream().reduce(0, (subTotal, element) -> subTotal + element);
        if (target == sum) {
            combinations.add(new ArrayList<>(combination));
        } else if (sum > target) {
            return;
        } else if (index >= candidates.length) {
            return;
        } else {
            combination.add(candidates[index]);
            findCombination(combinations, index, candidates, target, combination);
            combination.remove(combination.size() - 1);
            findCombination(combinations, index + 1, candidates, target, combination);
        }
    }

    public void findCombinationImproved(List<List<Integer>> combinations, int index, int[] candidates, int target, List<Integer> combination) {
        // base conditions
        if (target == 0) {
            combinations.add(new ArrayList<>(combination));
        } else if (target < 0 || index >= candidates.length) {
            return;
        } else {
            combination.add(candidates[index]);
            findCombination(combinations, index, candidates, target - candidates[index], combination);
            combination.remove(combination.size() - 1);
            findCombination(combinations, index + 1, candidates, target, combination);
        }
    }

    public void test(IntCombinationSum func) {
        System.out.println("Expected: [[2,2,3],[7]], Actual: "
                + PrintUtils.printListInListInteger(func.combinationSum(new int[] {2, 3, 6, 7}, 7)));

        System.out.println("Expected: [[2,2,2,2],[2,3,3],[3,5]], Actual: "
                + PrintUtils.printListInListInteger(func.combinationSum(new int[] {2, 3, 5}, 8)));

        System.out.println("Expected: [], Actual: "
                + PrintUtils.printListInListInteger(func.combinationSum(new int[] {2}, 1)));
    }

    public static void main(String[] args) {
        CombinationSum combinationSum = new CombinationSum();
        combinationSum.test(combinationSum.intCombinationSum);
    }
}
