package main.main.com.jackie.algorithm;

/**
 * 买卖股票的最佳时机 II
 *
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 作者：力扣 (LeetCode)
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/x2zsx1/
 */
public class StockBestPriceFirst {

    public static void main(String[] args) {
        // 7,1,5,3,6,4
        int [] arr = new int[]{1,2,3,4,5};
        int [] arr2 = new int[]{7,1,5,3,6,4};
        maxProfit(arr2);
    }


    public static int maxProfit(int[] prices) {
        int length = prices.length;
        if(length == 0){
            return 0;
        }
       int profits = 0;
        for(int i=0;i<length;i++){

        }

        return profits;
    }
}
