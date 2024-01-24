package main.ltcode.medium;

/**
 * 198. House Robber (Medium)
 */
@FunctionalInterface
interface IntHouseRobber {
    int rob(int[] nums);
}

public class HouseRobber {
    IntHouseRobber intHouseRobber = ((nums) -> {
//        if (nums == null || nums.length == 0) return 0;
//        if (nums.length == 1) return nums[0];
//        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int robWithOutIncludingLastHouse = 0, robWithIncludingLastHouse = 0;

        // original nums: [n, n+1, n+2, ...]
        // conceptual nums: [robWithOutIncludingLastHouse, robWithIncludingLastHouse, n, n+1, n+2, ...]
        // 2nd round:       [robWithOutIncludingLastHouse, robWithIncludingLastHouse, n+1, n+2, ...]
        for (int n : nums) {
            int temp = Math.max(n + robWithOutIncludingLastHouse, robWithIncludingLastHouse);
            robWithOutIncludingLastHouse = robWithIncludingLastHouse;
            robWithIncludingLastHouse = temp;
        }
        return robWithIncludingLastHouse;
    });

    IntHouseRobber dp = ((nums) -> {
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[dp.length - 1];
    });

    // DP with O(1) space
    IntHouseRobber dp2 = ((nums) -> {
        if (nums == null || nums.length == 0) return 0;

        int dp0 = 0, dp1 = 0, curr;

        for (int i = 0; i < nums.length; i++) {
            curr = Math.max(dp0 + nums[i], dp1);
            dp0 = dp1;
            dp1 = curr;
        }
//        return Math.max(dp0, dp1);
        return dp1;
    });

    public void test(IntHouseRobber func) {
        System.out.println("Expected: 4, Actual: " + func.rob(new int[]{1, 2, 3, 1}));
        System.out.println("Expected: 12, Actual: " + func.rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println("Expected: 6, Actual: " + func.rob(new int[]{2, 1, 3, 4}));
        System.out.println("Expected: 2, Actual: " + func.rob(new int[]{2}));
        System.out.println("Expected: 3, Actual: " + func.rob(new int[]{3, 1}));
    }

    public static void main(String[] args) {
        HouseRobber hr = new HouseRobber();
        System.out.println("= 1 =");
        hr.test(hr.intHouseRobber);
        System.out.println("= 2 =");
        hr.test(hr.dp);
        System.out.println("= 3 =");
        hr.test(hr.dp2);
    }
}
