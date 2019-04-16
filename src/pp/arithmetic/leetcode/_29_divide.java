package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-04-15.
 * 29. 两数相除
 * <p>
 * 给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数 dividend 除以除数 divisor 得到的商。
 * <p>
 * 示例 1:
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 示例 2:
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 说明:
 * <p>
 * 被除数和除数均为 32 位有符号整数。
 * 除数不为 0。
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。
 *
 * @see <a href="https://leetcode-cn.com/problems/divide-two-integers/">divide-two-integers</a>
 */
public class _29_divide {

    public static void main(String[] args) {
        _29_divide divide = new _29_divide();
        System.out.println(divide.divide2(10, 3));
        System.out.println(divide.divide2(7, -3));
        System.out.println(divide.divide2(-7, -3));
        System.out.println(divide.divide2(-2147483648, -1));
        System.out.println(divide.divide2(-2, 2));
        System.out.println(divide.divide2(2, 2));
        System.out.println(divide.divide2(Integer.MAX_VALUE, 2));
    }

    /**
     * 解法二
     * 除法的本质起始是看除数中包含多少个被除数，可以利用乘法或者位移使被除数不断变大，直到大于除数
     * 由于题目中不允许使用乘法，所以考虑用位移的方式进行计算 <<1 <==> *2
     * 1.divisor << 1，直至 > dividend，得到cnt
     * 2.dividend = dividend-divisor<<(cnt-1)
     * 3.重复第一步拿到最终结果
     * 要注意正负值和边界情况
     * 时间复杂度O(logn)
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide2(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (dividend == Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
            return -dividend;
        }
        int ret = 0;
        long absDividend = Math.abs((long)dividend);
        long absDivisor = Math.abs((long)divisor);
        while (absDividend >= absDivisor) {
            int cnt = 1;
            while ((absDivisor << cnt) <= absDividend)
                cnt++;
            ret += 1 << (cnt - 1);
            absDividend -= absDivisor << (cnt - 1);
        }
        return ((dividend ^ divisor) < 0) ? -ret : ret;
    }

    /**
     * 解题思路：
     * 1、不使用乘法、除法和 mod 运算符，那就只能使用加减法了
     * 2、for循环不断相减得到结果
     * 要注意正负值和边界情况
     * <p>
     * 此种解法，对于超级大的dividend和超级小的divisor的话，耗时会很长=>O(n)
     * 见解法二 {@link _29_divide#divide2(int, int)}
     *
     * @param dividend
     * @param divisor
     * @return
     */
    public int divide(int dividend, int divisor) {
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (dividend == Integer.MAX_VALUE) {
                return Integer.MIN_VALUE;
            }
            return -dividend;
        }

        int ret = 0;
        //异或
        int xor = dividend ^ divisor;
        if (xor == 0) {
            return 1;
        } else if (xor > 0) {
            //相同运算符
            while ((dividend ^ divisor) > 0) {
                dividend -= divisor;
                if ((dividend ^ divisor) >= 0 || dividend == 0) {
                    ret++;
                }
            }
        } else {
            //不同运算符
            while ((dividend ^ divisor) < 0) {
                dividend += divisor;
                if ((dividend ^ divisor) <= 0 || dividend == 0) {
                    ret--;
                }
            }
        }

        return ret;
    }
}
