package pp.arithmetic.leetcode;

import java.util.Stack;

/**
 * Created by wangpeng on 2018/9/6.
 * 402.移除第k个数字
 * 给定一个以字符串表示的非负整数 num，移除这个数中的 k 位数字，使得剩下的数字最小。
 * <p>
 * 注意:
 * <p>
 * num 的长度小于 10002 且 ≥ k。
 * num 不会包含任何前导零。
 * 示例 1 :
 * <p>
 * 输入: num = "1432219", k = 3
 * 输出: "1219"
 * 解释: 移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219。
 * 示例 2 :
 * <p>
 * 输入: num = "10200", k = 1
 * 输出: "200"
 * 解释: 移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 :
 * <p>
 * 输入: num = "10", k = 2
 * 输出: "0"
 * 解释: 从原数字移除所有的数字，剩余为空就是0。
 *
 * @see <a herf="https://leetcode-cn.com/problems/remove-k-digits/description/">remove-k-digits</a>
 */
public class _402_removeKdigits {

    public static void main(String[] args) {
        String s1 = removeKdigits("1432219", 3);
        System.out.println(s1);
        String s2 = removeKdigits("112", 1);
        System.out.println(s2);
        String s3 = removeKdigits("10", 2);
        System.out.println(s3);
    }

    /**
     * 贪心规律：k+1中拿到最小的，和后续的进行拼接(错的)
     * 正确的：
     * 去掉每一位，都应该使其最高位、次高位...都最小
     *
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        if (k >= num.length()) {
            return "0";
        }
        Stack<Character> stack = new Stack<>();
        char[] chars = num.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            while (!stack.isEmpty() && k > 0 && c < stack.peek()) {
                stack.pop();
                k--;
            }
            if (c != '0' || !stack.isEmpty()) {
                stack.push(c);
            }
        }
        while (!stack.isEmpty() && k > 0) {
            stack.pop();
            k--;
        }
        if (stack.isEmpty()){
            return "0";
        }
        char[] retChar = new char[stack.size()];
        int i = 0;
        while (!stack.isEmpty()) {
            retChar[retChar.length - i - 1] = stack.pop();
            i++;
        }
        return new String(retChar);
    }
}
