package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-03-15.
 * 69. x 的平方根
 * <p>
 * 实现 int sqrt(int x) 函数。
 * <p>
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 * <p>
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 4
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 * 由于返回类型是整数，小数部分将被舍去。
 *
 * @see <a href="https://leetcode-cn.com/problems/sqrtx/">sqrtx</a>
 */
public class _69_mySqrt {
    public static void main(String[] args) {
        _69_mySqrt mySqrt = new _69_mySqrt();
        System.out.println(mySqrt.mySqrt(8));
    }

    public int mySqrt(int x) {
        double sqrt = Math.sqrt(x);
        return (int) sqrt;
    }
}
