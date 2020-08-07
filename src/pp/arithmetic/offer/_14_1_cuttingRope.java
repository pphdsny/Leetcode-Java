package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-08-07.
 *
 * 剑指 Offer 14- I. 剪绳子
 *
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _14_1_cuttingRope {

    public static void main(String[] args) {
        _14_1_cuttingRope cuttingRope = new _14_1_cuttingRope();
        System.out.println(cuttingRope.cuttingRope(8));
        System.out.println(cuttingRope.cuttingRope(10));
        System.out.println(cuttingRope.cuttingRope(14));
        System.out.println(cuttingRope.cuttingRope(58));
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
    public int cuttingRope(int n) {
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
