package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-02-01.
 * 415. 字符串相加
 * <p>
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和。
 * <p>
 * 注意：
 * <p>
 * num1 和num2 的长度都小于 5100.
 * num1 和num2 都只包含数字 0-9.
 * num1 和num2 都不包含任何前导零。
 * 你不能使用任何內建 BigInteger 库， 也不能直接将输入的字符串转换为整数形式。
 *
 * @see <a href="https://leetcode-cn.com/problems/add-strings/">add-strings</a>
 */
public class _415_addStrings {

    public static void main(String[] args) {
        System.out.println(addStrings("1000", "8000"));
    }

    public static String addStrings(String num1, String num2) {
        int i = 0;
        int nextAdd = 0;
        char[] chars = new char[Math.max(num1.length(), num2.length()) + 1];
        for (int j = 0; j < chars.length; j++) {
            chars[j] = '0';
        }
        while (i < num1.length() && i < num2.length()) {
            int i1 = num1.charAt(num1.length() - i - 1) - '0';
            int i2 = num2.charAt(num2.length() - i - 1) - '0';
            int current = i1 + i2 + nextAdd;
            nextAdd = current / 10;
            current = current % 10;
            chars[chars.length - i - 1] = (char) (current + '0');
            i++;
        }
        while (i < num1.length()) {
            int i1 = num1.charAt(num1.length() - i - 1) - '0';
            int current = i1 + nextAdd;
            nextAdd = current / 10;
            current = current % 10;
            chars[chars.length - i - 1] = (char) (current + '0');
            i++;
        }
        while (i < num2.length()) {
            int i2 = num2.charAt(num2.length() - i - 1) - '0';
            int current = i2 + nextAdd;
            nextAdd = current / 10;
            current = current % 10;
            chars[chars.length - i - 1] = (char) (current + '0');
            i++;
        }
        if (nextAdd > 0) {
            chars[chars.length - i - 1] = (char) (nextAdd + '0');
        }
        if (chars[0] == '0') {
            return new String(chars).substring(1);
        }
        return new String(chars);
    }
}
