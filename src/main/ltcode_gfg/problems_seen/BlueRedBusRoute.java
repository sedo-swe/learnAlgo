package main.ltcode_gfg.problems_seen;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface IntBlueRedBusRoute {
    List<Long> redOrBlue(List<Integer> red, List<Integer> blue, int blueCost);
}

public class BlueRedBusRoute {
    /*
            Check length of both red and blue
                if either of them are empty (null), then take the other available
            if both have values (same length)
            compare cost for the next city
                cost itself + extra cost to change from red to blue or start from blue (initially one time)

            ** As it can be sort of greedy, doesn't need to check all possilbe path,
            instead check min cost for next only
         */
    IntBlueRedBusRoute intBlueRedBusRouteInitial = ((red, blue, blueCost) -> {
        List<Long> ans = new ArrayList<>();
        // red [40, 20], blue [30,25], blueCost 5
        if (red != null && red.size() > 0 && blue != null && blue.size() > 0) {
            ans.add(0L);
            int length = red.size();
            char prev = '-';   // mean not yet start, 'r' == red was previous, 'b' == blue
            Long totalCost = 0L;
            for (int i=0; i<length; i++) {        // ans [0,35,55], prev : r, totalCost: 55
                int nextCostRed = red.get(i);       // 20
                int nextCostBlue = blue.get(i);     // 25: 25 + 0
                if (prev == '-' || prev == 'r')
                    nextCostBlue += blueCost;
                if (nextCostRed >= nextCostBlue) {  // stay blue if costs are same
                    ans.add(totalCost + nextCostBlue);
                    totalCost = totalCost + nextCostBlue;
                    prev = 'b';
                } else {
                    ans.add(totalCost + nextCostRed);
                    totalCost = totalCost + nextCostRed;
                    prev = 'r';
                }
            }
        }
        return ans;
    });

    IntBlueRedBusRoute intBlueRedBusRouteDP = ((red, blue, blueCost) -> {
        List<Long> ans = new ArrayList<>();
        // red [7,2,10], blue [2,3,5], blueCost 6
        if (red != null && red.size() > 0 && blue != null && blue.size() > 0) {
            ans.add(0L);
            int length = red.size();
            Long totalCostRed = 0L;
            Long totalCostBlue = (long) blueCost;
            for (int i=0; i<length; i++) {        // ans [0,7], tcR: 7, tcB: 8
                int nextCostRed = red.get(i);       // 7
                int nextCostBlue = blue.get(i);     // 2
                Long tcRed = Math.min(totalCostRed + nextCostRed, totalCostBlue + nextCostRed);   // 0+7, 6+2
                Long tcBlue = Math.min(totalCostBlue + nextCostBlue, totalCostRed + blueCost + nextCostBlue); // 6+2, 0+6+2
                totalCostRed = tcRed;
                totalCostBlue = tcBlue;
                ans.add(Math.min(totalCostRed, totalCostBlue));
            }
        }
        return ans;
    });

    IntBlueRedBusRoute minCost = ((red, blue, blueCost) -> {
        //0 -- red, 1 -- blue;
        int n = red.size();

        long[][] dp = new long[n + 1][2];
        dp[0][0] = 0l;
        dp[0][1] = (long) blueCost;
        long[] ans = new long[n + 1];

        for(int i = 1; i<= n; i++){

            dp[i][0] = Math.min(dp[i-1][0] + red.get(i-1), dp[i-1][1] + red.get(i-1));
            dp[i][1] = Math.min(dp[i-1][1] + blue.get(i-1), dp[i-1][0] + blue.get(i-1) + blueCost);
            ans[i] = Math.min(dp[i][1], dp[i][0]);

        }
        List<Long> ansLong = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            ansLong.add(ans[i]);
        }
        return ansLong;
    });

    public void test(IntBlueRedBusRoute func) {
        List<Integer> red = new ArrayList<>();
        List<Integer> blue = new ArrayList<>();
        red.add(122786415);
        red.add(479496160);
        red.add(566997962);
        red.add(281783677);
        red.add(731449417);
        red.add(734578288);
        red.add(178191598);

        blue.add(901184049);
        blue.add(250354719);
        blue.add(803279036);
        blue.add(619324485);
        blue.add(820536743);
        blue.add(434519133);
        blue.add(286388385);
        System.out.println(func.redOrBlue(red, blue, 661821));

        List<Integer> red1 = new ArrayList<>();
        List<Integer> blue1 = new ArrayList<>();
        red1.add(2);
        red1.add(3);
        red1.add(4);
        blue1.add(3);
        blue1.add(1);
        blue1.add(1);
        System.out.println(func.redOrBlue(red1, blue1, 2));

        List<Integer> red2 = new ArrayList<>();
        List<Integer> blue2 = new ArrayList<>();
        red2.add(2);
        red2.add(1);
        red2.add(4);
        red2.add(5);
        blue2.add(3);
        blue2.add(2);
        blue2.add(1);
        blue2.add(2);
        System.out.println(func.redOrBlue(red2, blue2, 2));

        List<Integer> red3 = new ArrayList<>();
        List<Integer> blue3 = new ArrayList<>();
        red3.add(7);
        red3.add(2);
        red3.add(10);
        blue3.add(2);
        blue3.add(3);
        blue3.add(5);
        System.out.println(func.redOrBlue(red3, blue3, 6));
    }


    public static void main(String[] args) {
        BlueRedBusRoute ts = new BlueRedBusRoute();
        ts.test(ts.intBlueRedBusRouteDP);
    }
}
