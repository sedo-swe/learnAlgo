package main.ltcode_gfg.problems_seen;

import java.util.ArrayList;
import java.util.List;

@FunctionalInterface
interface IntBlueGreenTrain {
    List<Long> blueOrGreen(List<Integer> blue, List<Integer> green, int greenCost);
}

public class BlueGreenTrain {
    /*
            Check length of both left and right
                if either of them are empty (null), then take the other available
            if both have values (same length)
            compare cost for the next city
                cost itself + extra cost to change from left to right or start from right (initially one time)

            ** As it can be sort of greedy, doesn't need to check all possilbe path,
            instead check min cost for next only
         */
    IntBlueGreenTrain intBlueGreenTrainInitial = ((blue, green, greenCost) -> {
        List<Long> ans = new ArrayList<>();
        // left [40, 20], right [30,25], rightCost 5
        if (blue != null && blue.size() > 0 && green != null && green.size() > 0) {
            ans.add(0L);
            int length = blue.size();
            char prev = '-';   // mean not yet start, 'r' == red was previous, 'b' == right
            Long totalCost = 0L;
            for (int i=0; i<length; i++) {        // ans [0,35,55], prev : r, totalCost: 55
                int nextCostBlue = blue.get(i);       // 20
                int nextCostGreen = green.get(i);     // 25: 25 + 0
                if (prev == '-' || prev == 'r')
                    nextCostGreen += greenCost;
                if (nextCostBlue >= nextCostGreen) {  // stay green if costs are same
                    ans.add(totalCost + nextCostGreen);
                    totalCost = totalCost + nextCostGreen;
                    prev = 'b';
                } else {
                    ans.add(totalCost + nextCostBlue);
                    totalCost = totalCost + nextCostBlue;
                    prev = 'r';
                }
            }
        }
        return ans;
    });

    IntBlueGreenTrain intBlueGreenTrainDP = ((blue, green, greenCost) -> {
        int length = blue.size();
        List<Long> ans = new ArrayList<>();
        // left [7,2,10], right [2,3,5], rightCost 6
        ans.add(0l);
        Long totalCostBlue = 0l;
        Long totalCostGreen = 0l;
        for (int i=1; i<=length; i++) {        // ans [0,7], tcR: 7, tcB: 8
            Long nextOptimalBlue = Math.min(totalCostBlue, totalCostGreen) + blue.get(i-1);   // 0+7, 6+2
            Long nextOptimalGreen = Math.min(totalCostBlue + greenCost, totalCostGreen) + green.get(i-1); // 6+2, 0+6+2
            totalCostBlue = nextOptimalBlue;
            totalCostGreen = nextOptimalGreen;
            ans.add(Math.min(totalCostBlue, totalCostGreen));
        }
        return ans;
    });

    IntBlueGreenTrain minCost = ((blue, green, greenCost) -> {
        //0 -- blue, 1 -- green;
        int n = blue.size();

        long[][] dp = new long[n + 1][2];
        dp[0][0] = 0l;
        dp[0][1] = 0l;
        long[] ans = new long[n + 1];

        for(int i = 1; i<= n; i++){
            dp[i][0] = Math.min(dp[i-1][0], dp[i-1][1]) + blue.get(i-1);
            dp[i][1] = Math.min(dp[i-1][1], dp[i-1][0] + greenCost) + green.get(i-1);
            ans[i] = Math.min(dp[i][1], dp[i][0]);
        }
        List<Long> ansLong = new ArrayList<>();
        for (int i = 0; i < ans.length; i++) {
            ansLong.add(ans[i]);
        }
        return ansLong;
    });

    public void test(IntBlueGreenTrain func) {
        List<Integer> blue = new ArrayList<>();
        List<Integer> green = new ArrayList<>();
        blue.add(122786415);
        blue.add(479496160);
        blue.add(566997962);
        blue.add(281783677);
        blue.add(731449417);
        blue.add(734578288);
        blue.add(178191598);

        green.add(901184049);
        green.add(2503547);
        green.add(198032790);
        green.add(366193244);
        green.add(858205367);
        green.add(43434519);
        green.add(133286388);
        System.out.println(func.blueOrGreen(blue, green, 385661821));

        List<Integer> blue1 = new ArrayList<>();
        List<Integer> green1 = new ArrayList<>();
        blue1.add(2);
        blue1.add(3);
        blue1.add(4);
        green1.add(3);
        green1.add(1);
        green1.add(1);
        System.out.println(func.blueOrGreen(blue1, green1, 2));

        List<Integer> blue2 = new ArrayList<>();
        List<Integer> green2 = new ArrayList<>();
        blue2.add(2);
        blue2.add(1);
        blue2.add(4);
        blue2.add(5);
        green2.add(3);
        green2.add(2);
        green2.add(1);
        green2.add(2);
        System.out.println(func.blueOrGreen(blue2, green2, 2));

        List<Integer> blue3 = new ArrayList<>();
        List<Integer> green3 = new ArrayList<>();
        blue3.add(7);
        blue3.add(2);
        blue3.add(10);
        green3.add(2);
        green3.add(3);
        green3.add(5);
        System.out.println(func.blueOrGreen(blue3, green3, 6));
    }


    public static void main(String[] args) {
        BlueGreenTrain ts = new BlueGreenTrain();
        ts.test(ts.intBlueGreenTrainInitial);
        ts.test(ts.intBlueGreenTrainDP);
        ts.test(ts.minCost);
    }
}
