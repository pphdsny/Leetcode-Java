package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-08-05.
 *
 * 剑指 Offer 10- II. 青蛙跳台阶问题
 *
 *
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：2
 * 示例 2：
 *
 * 输入：n = 7
 * 输出：21
 * 示例 3：
 *
 * 输入：n = 0
 * 输出：1
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _10_2_numWays {

    public static void main(String[] args) {
        _10_2_numWays numWays = new _10_2_numWays();
        System.out.println(numWays.numWays(2));
        System.out.println(numWays.numWays(3));
        System.out.println(numWays.numWays(4));
        System.out.println(numWays.numWays(5));
        System.out.println(numWays.numWays(6));
        System.out.println(numWays.numWays(40));
    }

    /**
     * 解题思路：借鉴动态规划思路
     * 1.dp[0]=1，dp[1]=1，d[2]=dp[0]+d[1]
     * 2.dp[n]=dp[n-1]+dp[n-2]
     *
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1]+dp[i-2])%1000000007;
        }

        return dp[n];
    }
}
