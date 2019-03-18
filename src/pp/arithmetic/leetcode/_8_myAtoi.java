package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/10/25.
 * 8. 字符串转整数 (atoi)
 * <p>
 * 实现 atoi，将字符串转为整数。
 * <p>
 * 该函数首先根据需要丢弃任意多的空格字符，直到找到第一个非空格字符为止。
 * 如果第一个非空字符是正号或负号，选取该符号，并将其与后面尽可能多的连续的数字组合起来，这部分字符即为整数的值。
 * 如果第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * <p>
 * 字符串可以在形成整数的字符后面包括多余的字符，这些字符可以被忽略，它们对于函数没有影响。
 * <p>
 * 当字符串中的第一个非空字符序列不是个有效的整数；或字符串为空；或字符串仅包含空白字符时，则不进行转换。
 * <p>
 * 若函数不能执行有效的转换，返回 0。
 * <p>
 * 说明：
 * <p>
 * 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−231,  231 − 1]。如果数值超过可表示的范围，则返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "42"
 * 输出: 42
 * 示例 2:
 * <p>
 * 输入: "   -42"
 * 输出: -42
 * 解释: 第一个非空白字符为 '-', 它是一个负号。
 * 我们尽可能将负号与后面所有连续出现的数字组合起来，最后得到 -42 。
 * 示例 3:
 * <p>
 * 输入: "4193 with words"
 * 输出: 4193
 * 解释: 转换截止于数字 '3' ，因为它的下一个字符不为数字。
 * 示例 4:
 * <p>
 * 输入: "words and 987"
 * 输出: 0
 * 解释: 第一个非空字符是 'w', 但它不是数字或正、负号。
 * 因此无法执行有效的转换。
 * 示例 5:
 * <p>
 * 输入: "-91283472332"
 * 输出: -2147483648
 * 解释: 数字 "-91283472332" 超过 32 位有符号整数范围。
 * 因此返回 INT_MIN (−231) 。
 *
 * @see <a href="https://leetcode-cn.com/problems/string-to-integer-atoi/description/">string-to-integer-atoi</a>
 */
public class _8_myAtoi {
    public static void main(String[] args) {
        int i = myAtoi("20000000000000000000");
        System.out.println(i);
    }

    public static int myAtoi(String str) {
        char[] chars = str.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if ((aChar >= '0' && aChar <= '9')
                    || aChar == '-'
                    || aChar == '+') {
                if (builder.length() == 0) {
                    builder.append(aChar);
                    continue;
                } else if (aChar >= '0' && aChar <= '9') {
                    builder.append(aChar);
                    continue;
                }
            }
            if (aChar == ' ' && builder.length() == 0) {
                continue;
            }
            break;
        }
        //自己处理越界数组问题
        return parseInt(builder.toString());
    }

    private static int parseInt(String s) {
        int radix = 10;
        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { // Possible leading "+" or "-"
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+') {
                    return 0;
                }
                if (len == 1) // Cannot have lone "+" or "-"
                    return 0;
                i++;
            }
            multmin = limit / radix;
            while (i < len) {
                // Accumulating negatively avoids surprises near MAX_VALUE
                digit = Character.digit(s.charAt(i++), radix);
                if (digit < 0) {
                    return 0;
                }
                if (result < multmin) {
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result *= radix;
                if (result < limit + digit) {
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result -= digit;
            }
        } else {
            return 0;
        }
        return negative ? result : -result;
    }

    /**
     * 优秀解法，不借助Builder，一次遍历结束
     *
     * @param str
     * @return
     */
    public static int myAtoiByOther(String str) {
        int len = str.length();
        if (len == 0) {
            return 0;
        }
        char[] cs = str.toCharArray();
        int i = 0;
        while (i < len && cs[i++] == ' ') {

        }
        i--;
        char c1 = cs[i];
        int sig = 1;
        if ((c1 > '9' || c1 < '0')) {
            if (c1 == '-') {
                sig = -1;
                i++;
            } else if (c1 == '+') {
                i++;
            } else {
                return 0;
            }
        }
        long v = 0;
        for (; i < len; i++) {
            char c = cs[i];
            if (c < '0' || c > '9') {
                break;
            }
            v = v * 10 + (c - '0');
            if (v * sig > Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            } else if (v * sig < Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
        }
        return (int) (v * sig);
    }
}
