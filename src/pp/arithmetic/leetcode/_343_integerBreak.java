package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-05-13.
 * 343. 整数拆分
 * <p>
 * 给定一个正整数 n，将其拆分为至少两个正整数的和，并使这些整数的乘积最大化。 返回你可以获得的最大乘积。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1。
 * 示例 2:
 * <p>
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 说明: 你可以假设 n 不小于 2 且不大于 58。
 *
 * @see <a href="https://leetcode-cn.com/problems/integer-break/">integer-break</a>
 */
public class _343_integerBreak {
    public static void main(String[] args) {
        _343_integerBreak integerBreak = new _343_integerBreak();
        System.out.println(integerBreak.integerBreak(8));
        System.out.println(integerBreak.integerBreak(10));
        System.out.println(integerBreak.integerBreak(14));
    }

    /**
     * 解题思路：
     * 手动模拟了从2-10的最大乘积数字拆解，发现了一个现象：
     * 对于数字n,n一直除以2到1为止，得到的数字就是最大的乘积，举例如下：
     * 数字n  2   3   4   5   6   7           8               9           10
     * 乘积   1,1 1,2 2,2 2,3 3,3 3,4(2,2)    4(2,2),4(2,2)   4,5(2,3)    5(2,3),5(2,3)
     * 发现到了后面的最大乘积可以利用之前的计算好的结果，从而得出动态规划转移方程
     * dp[i]=dp[i/2]*dp[i-i/2](i>3)
     * 上面有问题，例如8的最大值不是除以2得到4*4=16，而是3*2*3=18，所以得双重循环取所有情况的最大值
     * for (int j = 1; j <= i / 2; j++) {
     *  dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
     * }
     *
     * @param n
     * @return
     */
    public int integerBreak(int n) {
        if (n <= 3) return n - 1;
        int[] dp = new int[n + 1];
        //初始化,1,2,3特殊处理
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 3;
        for (int i = 4; i <= n; i++) {
            for (int j = 1; j <= i / 2; j++) {
                dp[i] = Math.max(dp[i], dp[j] * dp[i - j]);
            }
        }
        return dp[n];
    }
}
