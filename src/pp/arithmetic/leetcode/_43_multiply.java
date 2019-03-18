package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-02-01.
 * 43. 字符串相乘
 * <p>
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 *
 * @see <a href="https://leetcode-cn.com/problems/multiply-strings/">multiply-strings</a>
 */
public class _43_multiply {

    public static void main(String[] args) {
        System.out.println(multiply("2", "3"));
        System.out.println(multiply("123", "456"));
        System.out.println(multiply("0", "0"));
        System.out.println(multiply("9", "9"));
    }

    public static String multiply(String num1, String num2) {
        char[] chars = new char[num1.length() + num2.length()];
        for (int i = 0; i < chars.length; i++) {
            chars[i] = '0';
        }
        for (int i = 0; i < num1.length(); i++) {
            int i1 = num1.charAt(num1.length() - i - 1) - '0';
            int next = 0;
            for (int j = 0; j < num2.length(); j++) {
                int i2 = num2.charAt(num2.length() - j - 1) - '0';
                int charInt = chars[chars.length - i - j - 1] - '0';
                int current = i1 * i2 + charInt + next;
                next = current / 10;
                current = current % 10;
                chars[chars.length - i - j - 1] = (char) (current + '0');
            }
            if (next > 0) {
                chars[chars.length - i - num2.length() - 1] = (char) (next + '0');
            }
        }
        int i = 0;
        while (i < chars.length && chars[i] == '0') {
            i++;
        }
        if (i >= chars.length) {
            return "0";
        }
        return new String(chars).substring(i);
    }
}
