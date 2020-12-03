package main.com.jackie.algorithm;

/**
 * 买卖股票的最佳时机 II
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 * <p>
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
 */
public class StockBestPriceFirst {

    public static void main(String[] args) {
        // 7,1,5,3,6,4
        int[] arr = new int[]{1, 2, 3, 4, 5};
        int[] arr2 = new int[]{7, 1, 5, 3, 6, 4,1,12,1,12};
        int result = maxProfit(arr2);
        System.out.println(result);
        System.out.println(maxProfitByDynamic(arr2));
    }

    /**
     * 贪心解法-  因为可以频繁交易，只要今天股价比昨天高就交易。
     *  但是等价的是 ，如果股票价格一直上升，第一天买入，最后一天卖出 等价于  每次买入，第二天卖出。
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int length = prices.length;
        if (length < 2) {
            return 0;
        }
        int profits = 0;
        for (int i = 1; i < length; i++) {
            int dif = prices[i] - prices[i-1];
            if(dif > 0){
                profits += dif;
            }
        }

        return profits;
    }

    /**
     * 动态规划解法
     * dp[i][0] = nax  dp[i-1][0], dp[i-1][1] + prices[i]  前一天不持有，或者前一天持有，当天卖出
     * dp[i][1] =表示当天持有，  max: dp[i-1][1] , dp[i-1][0]-price[i]  从前一天持有股票  或者 前一天不持有，当天买入
     * 初始状态 dp[0][0] = 0 ,dp[0][1] = -prices[i],  由于最后一天需要结算利润，最后一天不能持有股票。
     * 求dp[i-1][0]
     * @param arr
     * @return
     */
    public static int maxProfitByDynamic(int[] arr){
        int length = arr.length;
        if(length <2)return 0;
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -arr[0];
        for(int i=1;i<length;i++){
            dp[i][0] = Math.max(dp[i-1][0],dp[i-1][1]+arr[i]);
            dp[i][1] = Math.max(dp[i-1][1],dp[i-1][0]-arr[i]);
        }

        return dp[length-1][0];
    }


}
