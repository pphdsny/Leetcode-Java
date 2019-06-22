package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-06-22.
 * 50. Pow(x, n)
 * <p>
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2.00000, 10
 * 输出: 1024.00000
 * 示例 2:
 * <p>
 * 输入: 2.10000, 3
 * 输出: 9.26100
 * 示例 3:
 * <p>
 * 输入: 2.00000, -2
 * 输出: 0.25000
 * 解释: 2-2 = 1/22 = 1/4 = 0.25
 * 说明:
 * <p>
 * -100.0 < x < 100.0
 * n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/powx-n
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _50_myPow {

    public static void main(String[] args) {
        _50_myPow pow = new _50_myPow();
        System.out.println(pow.myPow(2.1, 3));
        System.out.println(pow.myPow(2, -2));
        System.out.println(pow.myPow(0.3, -2));
        System.out.println(pow.myPow(0.3, 1));
        System.out.println(pow.myPow(0.4127, 0));
        System.out.println(pow.myPow(0.00001, 2147483647));
    }

    /**
     * 解析思路：
     * 平方不就是n个数相乘么，n为负数的时候，先将x求倒数，再n次相乘(此方法会提交超时O(n))
     * <p>
     * 优化：
     * 将上述循环相乘的方法根据分治思想，一分为二（只需要求一半即可，另一半是相同的，奇数再*x），不断拆分至两个数相乘，时间复杂度优化到O(LogN)
     * 这个方法称为"快速幂乘法"
     *
     * @param x
     * @param n
     * @return
     */
    public double myPow(double x, int n) {
        if (n < 0) {
            x = 1f / x;
        }
        n = Math.abs(n);
        //分治求解
        return fastPow(x, n);
    }

    double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }

}
