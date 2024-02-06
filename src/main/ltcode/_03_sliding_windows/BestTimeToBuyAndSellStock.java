package main.ltcode._03_sliding_windows;

import java.util.Arrays;

@FunctionalInterface
interface IntBestTimeToBuyAndSellStock {
    int getMaxProfit(int[] prices);
}

/**
 *  121. Best Time to Buy and Sell Stock (Easy)
 */
public class BestTimeToBuyAndSellStock {

    /*
        First idea
            - Use nested loop
                find next element bigger than current and record its difference
                if there is another pair that has bigger difference, then replace it
                ==> Time Limit Exceeded
     */

    /*
        Second idea
            - Pick up current, sort remaining, and compare with max, record its difference
            ==> Time Limit Exceeded
     */
    IntBestTimeToBuyAndSellStock intBestTimeToBuyAndSellStock1st = ((prices) -> {
        int result = 0, temp = 0;
        int c = 0, window = 1;
                                                    // prices: 7, 1, 5, 3, 6, 4
        while (c < prices.length - 1) {             // c: 0, window: 6
            if (prices[c] < prices[window]) {       // p[c]: 7, p[w]: 5
                temp = prices[window] - prices[c];  // temp: 1, result: 1
                if (result < temp) {
                    result = temp;
                } else {
                    window++;
                }
            } else {
                window++;
            }

            if (window > prices.length - 1) {
                c++;
                window = c+1;
            }
        }

        return result;
    });

    IntBestTimeToBuyAndSellStock intBestTimeToBuyAndSellStock2nd = ((prices) -> {
        int result = 0;
        int[] prices2 = null;

        for (int i = 0; i < prices.length - 1; i++) {
            prices2 = Arrays.copyOfRange(prices, i + 1, prices.length);
            Arrays.sort(prices2);
            if (prices[i] < prices2[prices2.length - 1]) {
                if (result < (prices2[prices2.length - 1] - prices[i])) {
                    result = prices2[prices2.length - 1] - prices[i];
                }
            }
        }

        return result;
    });

    IntBestTimeToBuyAndSellStock intBestTimeToBuyAndSellStockSolution = ((prices) -> {
        int left = 0, right = 1;
        int maxProfit = 0;
        while (right < prices.length) {
            if (prices[left] < prices[right]) {
                maxProfit = Math.max(maxProfit, prices[right] - prices[left]);
                right++;
            } else {
                left = right;
                right++;
            }
        }
        return maxProfit;
    });

    public void test(IntBestTimeToBuyAndSellStock func) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        System.out.println("[Expected]: 5, [Actual]: " + func.getMaxProfit(prices1));

        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println("[Expected]: 0, [Actual]: " + func.getMaxProfit(prices2));

        int[] prices3 = {7, 29, 1, 5, 3, 6, 4, 19};
        System.out.println("[Expected]: 18, [Actual]: " + func.getMaxProfit(prices3));
    }

    public static void main(String[] args) {
        BestTimeToBuyAndSellStock bestTimeToBuyAndSellStock = new BestTimeToBuyAndSellStock();
        bestTimeToBuyAndSellStock.test(bestTimeToBuyAndSellStock.intBestTimeToBuyAndSellStockSolution);
    }
}
