package main.ltcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum (Medium)
 */
@FunctionalInterface
interface IntThreeSum {
    List<List<Integer>> threeSum(int[] nums);
}

public class ThreeSum {
    /*
        1st idea.
            - Sort nums, Use two pointers and three values (first, second, third)
            - Pick up first, then run two sum with the rest of arrays
     */
    IntThreeSum intThreeSum = (nums -> {
        if (nums == null || nums.length < 3) {
            return null;
        }
        int numsLength = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> answers = new ArrayList<>();
        int first = 0, second = 0, third = 0;
        for (int i = 0; i < numsLength - 2; i++) {
            first = i;
            second = first + 1;
            third = second + 1;
            while (second <= numsLength - 2 && third < numsLength) {
                System.out.println("f: "+first+", s: "+second+", t: "+third);
                if (nums[first] + nums[second] + nums[third] == 0) {
                    List<Integer> answer = new ArrayList<>();
                    answer.add(nums[first]);
                    answer.add(nums[second]);
                    answer.add(nums[third]);
                    if (!answers.contains(answer)) {
                        answers.add(answer);
                    }
                    second++;
                    third = second + 1;
                    continue;
                } else {
                    if (nums[second] < -1 * nums[first]) {
                        if (nums[third] == -1 * nums[first] - nums[second]) {
                            List<Integer> answer = new ArrayList<>();
                            answer.add(nums[first]);
                            answer.add(nums[second]);
                            answer.add(nums[third]);
                            if (!answers.contains(answer)) {
                                answers.add(answer);
                            }
                            second++;
                            third = second + 1;
                            continue;
                        } else if (nums[third] < -1 * nums[first] - nums[second]) {
                            third++;
                        } else {
                            second++;
                            third = second + 1;
                        }
                    } else {
                        second++;
                        third = second + 1;
                    }
                }
                if (third == numsLength) {
                    second++;
                    third = second + 1;
                }
            }
        }
        return answers;
    });

    public void test(IntThreeSum func) {
//        System.out.println("Expected: [[-1,-1,2],[-1,0,1]], Actual: " + func.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
//        System.out.println("Expected: [], Actual: " + func.threeSum(new int[]{0, 1, 1}));
//        System.out.println("Expected: [[0,0,0]], Actual: " + func.threeSum(new int[]{0, 0, 0}));
        System.out.println("Expected: [[-4,0,4],[-4,1,3],[-3,-1,4],[-3,0,3],[-3,1,2],[-2,-1,3],[-2,0,2],[-1,-1,2],[-1,0,1]], " +
                "Actual: " + func.threeSum(new int[]{-1,0,1,2,-1,-4,-2,-3,3,0,4}));
    }

    public static void main(String[] args) {
        ThreeSum ts = new ThreeSum();
        ts.test(ts.intThreeSum);
    }
}
