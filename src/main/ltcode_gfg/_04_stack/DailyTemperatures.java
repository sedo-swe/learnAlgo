package main.ltcode_gfg._04_stack;

import main.ltcode_gfg.utils.PrintUtils;

import java.util.Arrays;
import java.util.Stack;

/**
 * 739. Daily Temperatures (Medium)
 */
@FunctionalInterface
interface IntDailyTemperatures {
    int[] dailyTemperatures(int[] temperatures);
}

public class DailyTemperatures {
    /*
        1st idea.
            naive version
            ==> time limit exceeded (47/48)
     */
    IntDailyTemperatures dailyTemperaturesNaive = (temperatures -> {
        int[] answer = new int[temperatures.length];
        Arrays.fill(answer, 0);

        for (int i = 0; i < temperatures.length; i++) {
            int temperature = temperatures[i];
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperature < temperatures[j]) {
                    answer[i] = j - i;
                    break;
                }
            }
        }
        return answer;
    });

    IntDailyTemperatures dailyTemperaturesStack = (temperatures -> {
        int[] answer = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();
        for (int i = 0; i < temperatures.length; i++) {
            while (!stack.isEmpty() && stack.peek()[0] < temperatures[i]) {
                int[] t = stack.pop();
                if (t[0] < temperatures[i]) {
                    answer[t[1]] = i - t[1];
                }
            }
            stack.push(new int[]{temperatures[i], i});
        }
        return answer;
    });

    IntDailyTemperatures dailyTemperaturesWhile = (temperatures -> {
        int n = temperatures.length;
        int[] answer = new int[n];
        int hottest = 0;

        for (int cur=n-1; cur>=0; cur--) {
            int currTemp = temperatures[cur];
            // if it is sorted by ascending order
            if (currTemp >= hottest) {
                hottest = currTemp;
                continue;
            }
            int days = 1;
            while (temperatures[cur+days] < currTemp) {
                days += answer[cur+days];
            }
            answer[cur] = days;
        }
        return answer;
    });

    public void test(IntDailyTemperatures func) {
        System.out.println("Expected: [1,1,4,2,1,1,0,0], Actual: " + PrintUtils.printIntArrayString(func.dailyTemperatures(new int[]{73,74,75,71,69,72,76,73})));
        System.out.println("Expected: [1,1,1,0], Actual: " + PrintUtils.printIntArrayString(func.dailyTemperatures(new int[]{30,40,50,60})));
        System.out.println("Expected: [1,1,0], Actual: " + PrintUtils.printIntArrayString(func.dailyTemperatures(new int[]{30,60,90})));
    }

    public static void main(String[] args) {
        DailyTemperatures dt = new DailyTemperatures();
        dt.test(dt.dailyTemperaturesNaive);
        System.out.println();
        dt.test(dt.dailyTemperaturesStack);
        System.out.println();
        dt.test(dt.dailyTemperaturesWhile);
    }
}
