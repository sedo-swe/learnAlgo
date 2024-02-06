package main.ltcode._13_1d_dynamic_programming;

import java.util.HashMap;
import java.util.Map;

@FunctionalInterface
interface IntClimbingStairs {
    int climbStairs(int n);
}
public class ClimbingStairs {

    Map<Integer, Integer> mem = new HashMap<Integer, Integer>();
    IntClimbingStairs climbingStairs1st = ((n) -> {
        return this.climbingStaris1stRecursive(n);
    });

    private int climbingStaris1stRecursive(int n) {
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        if (mem.containsKey(n)) {
            return mem.get(n);
        }

        int result = climbingStaris1stRecursive(n -1) + climbingStaris1stRecursive(n-2);
        mem.put(n, result);
        return result;
    }

    Map<Integer, Integer> mem4Iter = new HashMap<Integer, Integer>();
    IntClimbingStairs climbingStairsIter = ((n) -> {
        mem4Iter.put(1, 1);
        mem4Iter.put(2, 2);
        if (n < 3) {
            return mem4Iter.get(n);
        }
        for (int i = 3; i <= n; i++) {
            int ith = mem4Iter.get(i - 2) + mem4Iter.get(i - 1);
            mem4Iter.put(i, ith);
        }
        return mem4Iter.get(n);
    });

    public void test(IntClimbingStairs func) {
        System.out.println("Expected: 2, Actual: " + func.climbStairs(2));
        System.out.println("Expected: 3, Actual: " + func.climbStairs(3));
        System.out.println("Expected: 5, Actual: " + func.climbStairs(4));
        System.out.println("Expected: 8, Actual: " + func.climbStairs(5));
        System.out.println("Expected: 13, Actual: " + func.climbStairs(6));
    }

    public static void main(String[] args) {
        ClimbingStairs climbingStairs = new ClimbingStairs();
        System.out.println("= Recursive =====");
        climbingStairs.test(climbingStairs.climbingStairs1st);
        System.out.println("= Iterate =======");
        climbingStairs.test(climbingStairs.climbingStairsIter);
    }
}
