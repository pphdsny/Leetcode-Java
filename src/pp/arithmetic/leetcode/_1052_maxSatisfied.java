package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-06-06.
 * 1052. 爱生气的书店老板
 * <p>
 * 今天，书店老板有一家店打算试营业 customers.length 分钟。每分钟都有一些顾客（customers[i]）会进入书店，所有这些顾客都会在那一分钟结束后离开。
 * <p>
 * 在某些时候，书店老板会生气。 如果书店老板在第 i 分钟生气，那么 grumpy[i] = 1，否则 grumpy[i] = 0。 当书店老板生气时，那一分钟的顾客就会不满意，不生气则他们是满意的。
 * <p>
 * 书店老板知道一个秘密技巧，能抑制自己的情绪，可以让自己连续 X 分钟不生气，但却只能使用一次。
 * <p>
 * 请你返回这一天营业下来，最多有多少客户能够感到满意的数量。
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：customers = [1,0,1,2,1,1,7,5], grumpy = [0,1,0,1,0,1,0,1], X = 3
 * 输出：16
 * 解释：
 * 书店老板在最后 3 分钟保持冷静。
 * 感到满意的最大客户数量 = 1 + 1 + 1 + 1 + 7 + 5 = 16.
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= X <= customers.length == grumpy.length <= 20000
 * 0 <= customers[i] <= 1000
 * 0 <= grumpy[i] <= 1
 *
 * @see <a href="https://leetcode-cn.com/problems/grumpy-bookstore-owner/">grumpy-bookstore-owner</a>
 */
public class _1052_maxSatisfied {
    public static void main(String[] args) {
        _1052_maxSatisfied maxSatisfied = new _1052_maxSatisfied();
        System.out.println(maxSatisfied.maxSatisfied(new int[]{1, 0, 1, 2, 1, 1, 7, 5}, new int[]{0, 1, 0, 1, 0, 1, 0, 1}, 3));
        System.out.println(maxSatisfied.maxSatisfied(new int[]{1}, new int[]{0}, 1));
        System.out.println(maxSatisfied.maxSatisfied(new int[]{4, 10, 10}, new int[]{1, 1, 0}, 2));
    }

    /**
     * 解题思路（动态规划+窗口）：
     * 1.定义两个数组dp和zoreDp
     * 2.dp保存窗口长度为X的满意度，防止重复计算
     * 3.zoreDp保存不生气时候的满意总和数
     * 4.第I位的最大值为窗口值+窗口前后的zoreDp之和
     *
     * @param customers
     * @param grumpy
     * @param X
     * @return
     */
    public int maxSatisfied(int[] customers, int[] grumpy, int X) {
        //存储第I位维持X位的满意数，防止重复计算
        int[] dp = new int[customers.length + 1];
        //存储不生气的满意总和数
        int[] zoreDp = new int[customers.length + 1];
        //初始化
        int max = X > customers.length ? customers.length : X;
        int sum = 0;
        for (int i = 0; i < customers.length; i++) {
            if (i < X) {
                dp[i + 1] = sum += customers[i];
            }
            zoreDp[i + 1] = (grumpy[i] == 0) ? customers[i] + zoreDp[i] : zoreDp[i];
        }
        int retMax = dp[max] + zoreDp[customers.length] - zoreDp[max];
        for (int i = X; i < customers.length; i++) {
            dp[i + 1] = dp[i] - customers[i - X] + customers[i];
            int otherZore = zoreDp[customers.length] - zoreDp[i + 1] + zoreDp[i - X + 1];
            retMax = Math.max(retMax, dp[i + 1] + otherZore);
        }

        return retMax;
    }
}
