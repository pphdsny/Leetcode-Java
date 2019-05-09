package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-05-09.
 * 279. 完全平方数
 * <p>
 * 给定正整数 n，找到若干个完全平方数（比如 1, 4, 9, 16, ...）使得它们的和等于 n。你需要让组成和的完全平方数的个数最少。
 * <p>
 * 示例 1:
 * <p>
 * 输入: n = 12
 * 输出: 3
 * 解释: 12 = 4 + 4 + 4.
 * 示例 2:
 * <p>
 * 输入: n = 13
 * 输出: 2
 * 解释: 13 = 4 + 9.
 *
 * @see <a href="https://leetcode-cn.com/problems/perfect-squares/">perfect-squares</a>
 */
public class _279_numSquares {

    public static void main(String[] args) {
        _279_numSquares numSquares = new _279_numSquares();
        System.out.println(numSquares.numSquares(12));
        System.out.println(numSquares.numSquares(13));
    }

    /**
     * 直接思路：找出N最接近的平方数，再循环找出剩余最接近的平方数集合（结果可能不是最优）
     * 比如：12->9+1+1+1，最优的是12->4+4+4
     * 所以，上面的思路还得把所有的情况都求出来，再选出最少的，性能较差
     * <p>
     * 优化思路：利用之前计算的步数，转换为动态规划方程
     * dp[i]代表第i需要的最少步骤，遍历所有的情况，从而找出最优解
     * for (int j = 1; i - j * j >= 0; j++) {
     *   dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
     * }
     *
     *
     * @param n
     * @return
     */
    public int numSquares(int n) {
        //利用动态规划 定义长度为n+1的数组 对应索引所对应的数装最少的步数
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i; //先假设到这一步的最大的步数为每次+1
            for (int j = 1; i - j * j >= 0; j++) { //i-j*j>=0 找到最大的j j*j就是i里面最大的完全平方数
                //dp[i-j*j]+1 表示d[i-j*j]的步数＋1 1即j*j这个完全平方数只需要一步
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        return dp[n];
    }
}
