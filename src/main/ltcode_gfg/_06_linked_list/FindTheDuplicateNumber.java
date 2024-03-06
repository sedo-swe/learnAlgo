package main.ltcode_gfg._06_linked_list;

import java.util.HashSet;
import java.util.Set;

/**
 *  287. Find the Duplicate Number (Medium)
 *  --> Cyclic sort
 *      https://blog.stackademic.com/coding-pattern-cyclic-sort-96511b0f60ac
 *      https://www.youtube.com/watch?v=JfinxytTYFQ
 *      https://www.educative.io/answers/what-is-a-cyclic-sort-algorithm
 */
@FunctionalInterface
interface IntFindTheDuplicateNumber {
    int findDuplicate(int[] nums);
}

public class FindTheDuplicateNumber {
    /*
        1st idea
            Use sum
            ==> Failed when [2,2,2,2,2], time: O (n), space: O (1)
     */
    IntFindTheDuplicateNumber findDuplicated = (nums -> {
        int sum = 0;
        for (int n : nums)
            sum += n;

        System.out.println(sum);
        for (int i=1; i<=nums.length - 1; i++)
            sum -= i;
        System.out.println(sum);
        return sum;
    });

    /*
        2nd idea
            Use Set to check duplication
            ==> Passed, time: O (n), space: O (n)
     */
    IntFindTheDuplicateNumber findDuplicatedSet = (nums -> {
        Set<Integer> unique = new HashSet<>();
        for (int n : nums) {
            if (unique.contains(n)) {
                return n;
            } else {
                unique.add(n);
            }
        }
        return -1;
    });

    /*
        3rd idea.
            Use Cyclic Sort to find duplicate number
            When given an unsorted array containing numbers taken from the range 1 to n
            ==> Passed, time: O (n), space: O (1)

            * // nums[i] < n needs especially when finding missing
            with given array containing n numbers out of n + 1 numbers (range 0 to n)
     */
    IntFindTheDuplicateNumber findDuplicatedCyclic = (nums -> {
        int i = 0, n = nums.length;
        while (i < n) {
            int c = nums[i] - 1;
            if (nums[i] < n && nums[c] != nums[i]) {
                int temp = nums[i];
                nums[i] = nums[c];
                nums[c] = temp;
            } else {
                i += 1;
            }
        }

        // List<Integer> duplicates = new ArrayList<>();
        for (int k = 0; k < n; k++) {
            if (nums[k] != (k + 1)) {
//                return (k + 1);   // Return missing number
//                duplicates.add(k + 1); // Find all missing number and return after for loop
                return nums[k];     // Return duplicate number
            }
        }
        return -1;
    });

    /*
        Solution: Fastest, but space is not O (1)
        ==> Passed, time: O (n), space: O (n)
     */
    IntFindTheDuplicateNumber findDuplicatedSol = (nums -> {
        boolean[] checked = new boolean[nums.length];
        for (int n : nums) {
            if (checked[n])
                return n;
            checked[n] = true;
        }
        return -1;
    });

    public void test(IntFindTheDuplicateNumber func) {
        System.out.println("Expected: 2, Actual: " + func.findDuplicate(new int[]{1,3,4,2,2}));
        System.out.println("Expected: 3, Actual: " + func.findDuplicate(new int[]{3,1,3,4,2}));
        System.out.println("Expected: 2, Actual: " + func.findDuplicate(new int[]{2,2,2,2,2}));
    }

    public static void main(String[] args) {
        FindTheDuplicateNumber f = new FindTheDuplicateNumber();
//        f.test(f.findDuplicatedSet);
        f.test(f.findDuplicatedCyclic);
//        f.test(f.findDuplicatedSol);
    }
}
