package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/11/1.
 * 7. 反转整数
 * <p>
 * 给定一个 32 位有符号整数，将整数中的数字进行反转。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 123
 * 输出: 321
 * 示例 2:
 * <p>
 * 输入: -123
 * 输出: -321
 * 示例 3:
 * <p>
 * 输入: 120
 * 输出: 21
 * 注意:
 * <p>
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。根据这个假设，如果反转后的整数溢出，则返回 0。
 *
 * @see <a href="https://leetcode-cn.com/problems/reverse-integer/description/">reverse-integer</a>
 */
public class _7_reverse {
    public static void main(String[] args) {
        int reverse = reverse(1534236469);
        System.out.println(reverse);
        int reverse1 = reverse(-123);
        System.out.println(reverse1);
    }

    public static int reverse(int x) {
        boolean isNegative = false;
        if (x < 0) {
            isNegative = true;
            x = -x;
        }
        int result = 0;
        while (x > 0) {
            //注意溢出问题
            if (result * 10L + x % 10 > Integer.MAX_VALUE) {
                return 0;
            }
            result = result * 10 + x % 10;
            x = x / 10;
        }
        return isNegative ? -result : result;
    }
}
