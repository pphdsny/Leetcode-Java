package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-04-23.
 * 91. 解码方法
 * <p>
 * 一条包含字母 A-Z 的消息通过以下方式进行了编码：
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * 给定一个只包含数字的非空字符串，请计算解码方法的总数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "12"
 * 输出: 2
 * 解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
 * 示例 2:
 * <p>
 * 输入: "226"
 * 输出: 3
 * 解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
 *
 * @see <a href="https://leetcode-cn.com/problems/decode-ways/">decode-ways</a>
 */
public class _91_numDecodings {
    public static void main(String[] args) {
        _91_numDecodings numDecodings = new _91_numDecodings();
        System.out.println(numDecodings.numDecodings("100"));
        System.out.println(numDecodings.numDecodings("101"));
        System.out.println(numDecodings.numDecodings("110"));
        System.out.println(numDecodings.numDecodings("230"));
        System.out.println(numDecodings.numDecodings("226"));
        System.out.println(numDecodings.numDecodings("2261"));
    }

    /**
     * 解题思路：
     * 字符可以1个或者2个对应字母，2个最大是26，一个最小是1
     * dp代表当前i可以解码的个数
     * <p>
     * 题目很坑，注意异常数字（0）非常多
     * 理清楚递推逻辑还是很难的。
     *
     * @param s
     * @return
     */
    public int numDecodings(String s) {
        if (s.length() == 0 || (s.charAt(0) == '0')) return 0;
        if (s.length() == 1) return 1;
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        for (int i = 0; i < s.length(); ++i) {
            dp[i + 1] = s.charAt(i) == '0' ? 0 : dp[i];
            if (i > 0 && (s.charAt(i - 1) == '1' || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6'))) {
                dp[i + 1] += dp[i - 1];
            }
        }
        return dp[s.length()];
    }
}
