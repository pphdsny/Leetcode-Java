package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.Arrays;

/**
 * Created by wangpeng on 2018/9/24.
 * 322.零钱兑换
 * <p>
 * 给定不同面额的硬币 coins 和一个总金额 amount。
 * 编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
 * 如果没有任何一种硬币组合能组成总金额，返回 -1。
 * <p>
 * 示例 1:
 * <p>
 * 输入: coins = [1, 2, 5], amount = 11
 * 输出: 3
 * 解释: 11 = 5 + 5 + 1
 * 示例 2:
 * <p>
 * 输入: coins = [2], amount = 3
 * 输出: -1
 * 说明:
 * 你可以认为每种硬币的数量是无限的。
 *
 * @see <a href="https://leetcode-cn.com/problems/coin-change/description/">coin-change</a>
 */
public class _322_coinChange {
    public static void main(String[] args) {

        int max = 1000000;
        long start = System.currentTimeMillis();
        for (int j = 0; j < max; j++) {
            int i = coinChange(new int[]{1, 3, 4, 5}, 7);
        }
        long end = System.currentTimeMillis();
        System.out.println("time1:" + (end - start));
//        System.out.println(i);
        //不存在的处理
        int i2 = coinChange(new int[]{3}, 4);
        System.out.println(i2);
        //正好除尽
        int i3 = coinChange(new int[]{2, 5, 10, 1}, 27);
        System.out.println(i3);
        Util.printDivideLine();
        start = System.currentTimeMillis();
        //更优解法
        for (int i = 0; i < max; i++) {
            int i4 = coinChange2(new int[]{1, 3, 4, 5}, 7);
//        System.out.println(i4);
        }
        end = System.currentTimeMillis();
        System.out.println("time2:" + (end - start));

    }

    /**
     * DP实现，实际复杂度：O(M*N)
     * M = amount
     * N = coins.length
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        if (coins == null || coins.length == 0) {
            return -1;
        }
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        for (int i = 1; i < amount + 1; i++) {
            dp[i] = -1;
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && dp[i - coins[j]] != -1) {
                    if (dp[i] == -1 || dp[i] > dp[i - coins[j]] + 1) {
                        dp[i] = dp[i - coins[j]] + 1;
                    }
                }
            }
        }
        return dp[amount];
    }

    static int total;

    /**
     * 递归实现（更优），时间复杂度：不好算，估计也有N*M，但是循环次数会少些
     *
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange2(int[] coins, int amount) {
        total = Integer.MAX_VALUE;
        if (amount == 0) return 0;
        Arrays.sort(coins);
        count(amount, coins.length - 1, coins, 0);
        return total == Integer.MAX_VALUE ? -1 : total;
    }

    private static void count(int amount, int index, int[] coins, int count) {
        if (index < 0 || count >= total - 1) return;
        int c = amount / coins[index];
        for (int i = c; i >= 0; i--) {
            int newCount = count + i;
            int rem = amount - i * coins[index];
            if (rem > 0 && newCount < total)
                count(rem, index - 1, coins, newCount);
            else if (newCount < total)
                total = newCount;
            else if (newCount >= total - 1)
                break;
        }

    }
}
