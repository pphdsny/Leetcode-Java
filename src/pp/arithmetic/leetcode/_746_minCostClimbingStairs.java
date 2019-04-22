package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-04-22.
 * 746. 使用最小花费爬楼梯
 * <p>
 * 数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
 * <p>
 * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
 * <p>
 * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
 * <p>
 * 示例 1:
 * <p>
 * 输入: cost = [10, 15, 20]
 * 输出: 15
 * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
 * 示例 2:
 * <p>
 * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
 * 输出: 6
 * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
 * 注意：
 * <p>
 * cost 的长度将会在 [2, 1000]。
 * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
 *
 * @see <a href="https://leetcode-cn.com/problems/min-cost-climbing-stairs/">min-cost-climbing-stairs</a>
 */
public class _746_minCostClimbingStairs {

    public static void main(String[] args) {
        _746_minCostClimbingStairs stairs = new _746_minCostClimbingStairs();
        System.out.println(stairs.minCostClimbingStairs(new int[]{10, 15, 20}));
        System.out.println(stairs.minCostClimbingStairs(new int[]{1, 100, 1, 1, 1, 100, 1, 1, 100, 1}));
        System.out.println(stairs.minCostClimbingStairs(new int[]{2, 1000}));
    }

    /**
     * 解题思路：
     * 动态规划解题四部曲，可供参考
     * <p>
     * - 确认原问题与子问题=>原问题：走完楼梯花费的最小体力，子问题：第i步花的最小体力
     * - 确认状态=>本题的动态规划状态单一，第i个状态即为i阶台阶的所花费的最小体力
     * - 确认边界状态的值=>第1步=cost[0]，第2步=min(cost[0],cost[1])
     * - 确定状态转移方程=>dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int length = cost.length + 1;
        int[] dp = new int[length];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < length; i++) {
            dp[i] = Math.min(dp[i - 2] + cost[i - 2], dp[i - 1] + cost[i - 1]);
        }
        return dp[length - 1];
    }
}
