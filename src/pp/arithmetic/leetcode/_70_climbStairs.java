package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/9/21.
 * 70.爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 * @see <a href="https://leetcode-cn.com/problems/climbing-stairs/description/">climbing-stairs</a>
 */
public class _70_climbStairs {
    public static void main(String[] args) {
        int num = 40;
        long start = System.currentTimeMillis();
        int i = climbStairs(num);
        System.out.println(i);
        long end = System.currentTimeMillis();
        //604ms
        System.out.println("time:" + (end - start));
        start = System.currentTimeMillis();
        int i2 = climbStairs2(num);
        System.out.println(i2);
        end = System.currentTimeMillis();
        //0ms
        System.out.println("time2:" + (end - start));
    }

    /**
     * 时间太慢了，分析一下：
     * <p>
     * 时间复杂度：(这里我们假设一下n-1约等于n-2)
     * T(n)=2O(n-1)
     * =2*2O(n-2)
     * ...
     * =2^mO(1)
     * =2^m
     * m = n-2
     * =2^n
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 1 || n == 2) {
            return n;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 动态规划分解问题
     *
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        if (n <= 2) {
            return n;
        }
        int[] count = new int[n];
        count[0] = 1;
        count[1] = 2;
        for (int i = 2; i < n; i++) {
            count[i] = count[i - 1] + count[i - 2];
        }
        return count[n - 1];
    }
}
