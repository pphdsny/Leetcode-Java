package pp.arithmetic.offer;

/**
 * Created by wangpeng on 2020-08-04.
 *
 * 剑指 Offer 10- I. 斐波那契数列
 *
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 * 示例 2：
 *
 * 输入：n = 5
 * 输出：5
 *  
 *
 * 提示：
 *
 * 0 <= n <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fei-bo-na-qi-shu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _10_fib {

    public static void main(String[] args) {
        _10_fib fib = new _10_fib();
        System.out.println(fib.fib(2));
        System.out.println(fib.fib(5));
        System.out.println(fib.fib(100));
    }

    /**
     * 解题思路：
     * 有两个方案：
     *  一、是从n开始递归求解f(n)=f(n-1)+f(n-2)，这种递归效率较低，当n比较大时候存在大量重复的计算
     *  二、从0开始计算，存储下每次计算的结果，逐步计算到n，借助动态规划
     *
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i-1]+dp[i-2])%1000000007;
        }
        return dp[n];
    }
}
