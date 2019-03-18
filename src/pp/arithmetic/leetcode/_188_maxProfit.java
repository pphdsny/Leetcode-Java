package pp.arithmetic.leetcode;

import java.util.Random;

/**
 * Created by wangpeng on 2018-12-05.
 * 188. 买卖股票的最佳时机 IV
 * <p>
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 * <p>
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 k 笔交易。
 * <p>
 * 注意: 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * <p>
 * 示例 1:
 * <p>
 * 输入: [2,4,1], k = 2
 * 输出: 2
 * 解释: 在第 1 天 (股票价格 = 2) 的时候买入，在第 2 天 (股票价格 = 4) 的时候卖出，这笔交易所能获得利润 = 4-2 = 2 。
 * 示例 2:
 * <p>
 * 输入: [3,2,6,5,0,3], k = 2
 * 输出: 7
 * 解释: 在第 2 天 (股票价格 = 2) 的时候买入，在第 3 天 (股票价格 = 6) 的时候卖出, 这笔交易所能获得利润 = 6-2 = 4 。
 * 随后，在第 5 天 (股票价格 = 0) 的时候买入，在第 6 天 (股票价格 = 3) 的时候卖出, 这笔交易所能获得利润 = 3-0 = 3 。
 *
 * @see <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/description/">best-time-to-buy-and-sell-stock-iv</a>
 */
public class _188_maxProfit {

    public static void main(String[] args) {
        System.out.println(maxProfit(2, new int[]{2, 4, 1}));
        System.out.println(maxProfit(2, new int[]{3, 2, 6, 5, 0, 3}));
        //LeetCode BT
        System.out.println(maxProfit(1000000000,
                generate(10000)));
    }

    private static int[] generate(int num) {
        int[] nums = new int[num];
        for (int j = 0; j < num; j++) {
            nums[j] = new Random().nextInt(1000);
        }
        return nums;
    }

    /**
     * @param k
     * @param prices
     * @return
     * @see _123_maxProfit#maxProfit2(int[])
     */
    public static int maxProfit(int k, int[] prices) {
        if (k < 1 || prices.length <= 1) {
            return 0;
        }
        //k>len/2，任意两天>0的加和既是
        if (k > prices.length / 2) {
            int sum = 0;
            for (int i = 1; i < prices.length; i++) {
                if (prices[i] > prices[i - 1]) {
                    sum += prices[i] - prices[i - 1];
                }
            }
            return sum;
        }

        int m = k + 1;
        int n = prices.length;
        int[][] dp = new int[m][n];
        //init
        for (int i = 0; i < n; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            dp[i][0] = 0;
        }
        for (int i = 1; i < m; i++) {
            //最小
            int min = Integer.MIN_VALUE;
            for (int j = 1; j < n; j++) {
                min = Math.max(min, dp[i - 1][j - 1] - prices[j - 1]);
                dp[i][j] = Math.max(dp[i][j - 1], min + prices[j]);
            }
        }
        return dp[m - 1][n - 1];
    }
}
