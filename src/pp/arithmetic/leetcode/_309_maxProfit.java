package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-05-10.
 * 309. 最佳买卖股票时机含冷冻期
 * <p>
 * 给定一个整数数组，其中第 i 个元素代表了第 i 天的股票价格 。​
 * <p>
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * <p>
 * 你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 示例:
 * <p>
 * 输入: [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * @see <a href="https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/">best-time-to-buy-and-sell-stock-with-cooldown</a>
 */
public class _309_maxProfit {
    public static void main(String[] args) {
        _309_maxProfit maxProfit = new _309_maxProfit();
        System.out.println(maxProfit.maxProfit(new int[]{1, 2, 3, 0, 2}));
        System.out.println(maxProfit.maxProfit2(new int[]{1, 2, 3, 0, 2}));
    }

    /**
     * 解题思路（动态规划）：
     * 可以将问题拆解为前i天获利最大，如果股票在手上没有卖出去，获利应该算亏本吧？
     * 第i+1天的获利最大情况：第i天买入，第i-1天买入...第1天买入，求出个最大值
     * 假设第i天买入，此时上一次买入时间必须是i-2天，可以用此次的获利+dp[i-2]得到总获利值（注意i>=2）
     * 得出状态转移方程
     * if (j >= 2) {
     * dp[i] = Math.max(diffPrice + dp[j - 2], dp[i]);
     * } else {
     * dp[i] = Math.max(diffPrice, dp[i]);
     * }
     * 提交结果：
     * 执行用时 : 52 ms, 在Best Time to Buy and Sell Stock with Cooldown的Java提交中击败了5.67% 的用户
     * 内存消耗 : 34.5 MB, 在Best Time to Buy and Sell Stock with Cooldown的Java提交中击败了87.95% 的用户
     * 执行耗时并不是很理想，思考耗时在哪？
     * 综合分析，整体时间复杂度在O(n^2)级别，待优化{@link _309_maxProfit#maxProfit(int[])}
     *
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) return 0;
        if (prices.length == 2) return Math.max(prices[1] - prices[0], 0);

        int[] dp = new int[prices.length];
        dp[0] = 0;
        for (int i = 1; i < prices.length; i++) {
            //当前不执行任何操作
            dp[i] = dp[i - 1];
            //当前执行卖出操作
            for (int j = i - 1; j >= 0; j--) {
                int diffPrice = prices[i] - prices[j];
                if (diffPrice <= 0) continue;
                //如果j买入的话，上一次卖出获利必须是j-2日
                if (j >= 2) {
                    dp[i] = Math.max(diffPrice + dp[j - 2], dp[i]);
                } else {
                    dp[i] = Math.max(diffPrice, dp[i]);
                }
            }
        }

        return dp[prices.length - 1];
    }

    /**
     * 解题二：
     * 优化{@link _309_maxProfit#maxProfit(int[])}中的问题
     * 使用两个数组进行存储，一个是持有成本，一个是卖出获利
     *
     * @param prices
     * @return
     */
    public int maxProfit2(int[] prices) {
        if (prices == null || prices.length <= 1) {
            return 0;
        }
        //持有成本
        int[] hold = new int[prices.length];
        //卖出获利
        int[] profit = new int[prices.length];
        hold[0] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (i == 1) {
                hold[i] = Math.max(hold[i - 1], -prices[1]);
            } else {
                hold[i] = Math.max(hold[i - 1], profit[i - 2] - prices[i]);
            }
            //当前不卖出，或者卖出，取最大值
            profit[i] = Math.max(profit[i - 1], hold[i - 1] + prices[i]);
        }
        return profit[prices.length - 1];
    }
}
