package main.ltcode_gfg.problems_seen;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface IntLeftRightRace {
    List<Long> leftRightFaster(List<Integer> left, List<Integer> right, int rightCost);
}

public class LeftRightRace {
    /*
            Check length of both left and right
                if either of them are empty (null), then take the other available
            if both have values (same length)
            compare cost for the next city
                cost itself + extra cost to change from left to right or start from right (initially one time)

            ** As it can be sort of greedy, doesn't need to check all possilbe path,
            instead check min cost for next only
         */
    IntLeftRightRace intLeftRightRaceInitial = ((left, right, rightCost) -> {
        List<Long> ans = new ArrayList<>();
        // left [40, 20], right [30,25], rightCost 5
        if (left != null && left.size() > 0 && right != null && right.size() > 0) {
            ans.add(0L);
            int length = left.size();
            char prev = '-';   // mean not yet start, 'r' == red was previous, 'b' == right
            Long totalCost = 0L;
            for (int i=0; i<length; i++) {        // ans [0,35,55], prev : r, totalCost: 55
                int nextCostLeft = left.get(i);       // 20
                int nextCostRight = right.get(i);     // 25: 25 + 0
                if (prev == '-' || prev == 'r')
                    nextCostRight += rightCost;
                if (nextCostLeft >= nextCostRight) {  // stay right if costs are same
                    ans.add(totalCost + nextCostRight);
                    totalCost = totalCost + nextCostRight;
                    prev = 'b';
                } else {
                    ans.add(totalCost + nextCostLeft);
                    totalCost = totalCost + nextCostLeft;
                    prev = 'r';
                }
            }
        }
        return ans;
    });

    IntLeftRightRace intLeftRightRaceDP = ((left, right, rightCost) -> {
        List<Long> ans = new ArrayList<>();
        // left [7,2,10], right [2,3,5], rightCost 6
        if (left != null && left.size() > 0 && right != null && right.size() > 0) {
            ans.add(0L);
            int length = left.size();
            Long totalCostLeft = 0L;
            Long totalCostRight = (long) rightCost;
            for (int i=0; i<length; i++) {        // ans [0,7], tcR: 7, tcB: 8
                int nextCostLeft = left.get(i);       // 7
                int nextCostRight = right.get(i);     // 2
                Long tcLeft = Math.min(totalCostLeft + nextCostLeft, totalCostRight + nextCostLeft);   // 0+7, 6+2
                Long tcRight = Math.min(totalCostLeft + rightCost + nextCostRight, totalCostRight + nextCostRight); // 6+2, 0+6+2
                totalCostLeft = tcLeft;
                totalCostRight = tcRight;
                ans.add(Math.min(totalCostLeft, totalCostRight));
            }
        }
        return ans;
    });

    IntLeftRightRace minCost = ((left, right, rightCost) -> {
        //0 -- left, 1 -- right;
        int n = left.size();

        long[][] dp = new long[n + 1][2];
        dp[0][0] = 0l;
        dp[0][1] = (long) rightCost;
        long[] ans = new long[n + 1];

        for(int i = 1; i<= n; i++){

            dp[i][0] = Math.min(dp[i-1][0] + left.get(i-1), dp[i-1][1] + left.get(i-1));
            dp[i][1] = Math.min(dp[i-1][1] + right.get(i-1), dp[i-1][0] + right.get(i-1) + rightCost);
            ans[i] = Math.min(dp[i][1], dp[i][0]);

        }
        List<Long> ansLong = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            ansLong.add(ans[i]);
        }
        return ansLong;
    });

    public void test(IntLeftRightRace func) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        left.add(122786415);
        left.add(479496160);
        left.add(566997962);
        left.add(281783677);
        left.add(731449417);
        left.add(734578288);
        left.add(178191598);

        right.add(901184049);
        right.add(250354719);
        right.add(803279036);
        right.add(619324485);
        right.add(820536743);
        right.add(434519133);
        right.add(286388385);
        System.out.println(func.leftRightFaster(left, right, 661821));

        List<Integer> left1 = new ArrayList<>();
        List<Integer> right1 = new ArrayList<>();
        left1.add(2);
        left1.add(3);
        left1.add(4);
        right1.add(3);
        right1.add(1);
        right1.add(1);
        System.out.println(func.leftRightFaster(left1, right1, 2));

        List<Integer> left2 = new ArrayList<>();
        List<Integer> right2 = new ArrayList<>();
        left2.add(2);
        left2.add(1);
        left2.add(4);
        left2.add(5);
        right2.add(3);
        right2.add(2);
        right2.add(1);
        right2.add(2);
        System.out.println(func.leftRightFaster(left2, right2, 2));

        List<Integer> left3 = new ArrayList<>();
        List<Integer> right3 = new ArrayList<>();
        left3.add(7);
        left3.add(2);
        left3.add(10);
        right3.add(2);
        right3.add(3);
        right3.add(5);
        System.out.println(func.leftRightFaster(left3, right3, 6));
    }


    public static void main(String[] args) {
        LeftRightRace ts = new LeftRightRace();
        ts.test(ts.intLeftRightRaceDP);
    }
}
