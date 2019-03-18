package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2018-12-04.
 * 123. 买卖股票的最佳时机 III
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [3,3,5,0,0,3,1,4]
 * 输出: 6
 * 解释: 在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 * 随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 * 示例 2:
 * <p>
 * 输入: [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 * 注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。
 * 因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 * 示例 3:
 * <p>
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这个情况下, 没有交易完成, 所以最大利润为 0。
 *
 * @see <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/description/">best-time-to-buy-and-sell-stock-iii</a>
 */
public class _123_maxProfit {
    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{3, 3, 5, 0, 0, 3, 1, 4}));
        System.out.println(maxProfit(new int[]{1, 2, 3, 4, 5}));
        System.out.println(maxProfit(new int[]{7, 6, 4, 3, 1}));
        //多次起伏的情况
        System.out.println(maxProfit(new int[]{1, 2, 4, 2, 5, 7, 2, 4, 9, 0}));
        System.out.println(maxProfit(new int[]{2, 3, 5, 1, 4, 7, 9, 3}));
        Util.printDivideLine();
        System.out.println(maxProfit2(new int[]{1, 2, 0, 5, 1}));
    }

    /**
     * 还是利用波峰波谷的特性
     *
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int buy1 = Integer.MIN_VALUE, sell1 = 0;
        int buy2 = Integer.MIN_VALUE, sell2 = 0;
        for (int price : prices) {
            // 第一次购买
            if (buy1 < -price) {
                buy1 = -price;
            }
            // 第一次卖出去的可能的最大利润
            if (sell1 < buy1 + price) {
                sell1 = buy1 + price;
            }
            //注意第二次的利润已经包含第一次的了
            //同理，第二次购买,注意这里是
            if (buy2 < sell1 - price) {
                buy2 = sell1 - price;
            }
            //最后一次
            if (sell2 < buy2 + price) {
                sell2 = buy2 + price;
            }
        }
        return sell2;
    }

    /**
     * 采用DP的方式，二维的
     * 拆分问题：第i次卖第j天最多赚取的钱
     * 第i次循环的起始资金是建立在i-1次上的，所以会越来越多
     *
     * @param prices
     * @return
     */
    public static int maxProfit2(int[] prices) {
        if (prices.length <= 1) return 0;
        int m = 2;
        int n = prices.length;
        int[][] dp = new int[m + 1][n];
        for (int i = 0; i < n; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i <= m; i++) {
            //第i次循环的起始资金是建立在i-1次上的，所以会越来越多

            int maxdiff = Integer.MIN_VALUE; //maxdiff is maximum difference of dp[i-1][k] - prices[k] before and include j-2;
            for (int j = 1; j < n; j++) {
                //找价格最低的一天
                maxdiff = Math.max(maxdiff, dp[i - 1][j - 1] - prices[j - 1]);
                //获取最大的获利
                dp[i][j] = Math.max(dp[i][j - 1], prices[j] + maxdiff);
            }
        }
        return dp[m][n - 1];
    }
}
