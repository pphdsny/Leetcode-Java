package pp.arithmetic.leetcode;

import java.util.Stack;

/**
 * Created by wangpeng on 2019-04-12.
 * 32. 最长有效括号
 * <p>
 * 给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "(()"
 * 输出: 2
 * 解释: 最长有效括号子串为 "()"
 * 示例 2:
 * <p>
 * 输入: ")()())"
 * 输出: 4
 * 解释: 最长有效括号子串为 "()()"
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-valid-parentheses/">longest-valid-parentheses</a>
 */
public class _32_longestValidParentheses {

    public static void main(String[] args) {
        _32_longestValidParentheses parentheses = new _32_longestValidParentheses();
        System.out.println(parentheses.longestValidParentheses("(()"));
        System.out.println(parentheses.longestValidParentheses(")()())"));
        System.out.println(parentheses.longestValidParentheses("()()"));
        System.out.println(parentheses.longestValidParentheses2(")()())()()("));
    }

    /**
     * 解题思路：
     * 参考 {@link _20_isValid#isValid(String)}，但和其不同的是，求有效长度
     * 难度在于，你不知道什么时候才是有效长度结束，每个括号都有可能是后续某个括号的匹配
     * 1、使用Stack存储,(则入栈，)则在栈中进行寻找是否有对于的(
     * 2、找到对应的(则将(出栈，将数字2入栈
     * 3、如在找(的过程中遇到了数字，就将其累加到最大值中并使其出栈
     * <p>
     * 执行用时 : 22 ms, 在Longest Valid Parentheses的Java提交中击败了41.51% 的用户
     * 内存消耗 : 36.9 MB, 在Longest Valid Parentheses的Java提交中击败了86.21% 的用户
     * <p>
     * 提交结果并不是很棒，看话题关联有动态规划，于是再考虑一个动态规划的计算
     * 解法二 {@link _32_longestValidParentheses#longestValidParentheses2(String)}
     *
     * @param s
     * @return
     */
    public int longestValidParentheses(String s) {
        int retCount = 0;
        Stack stack = new Stack();
        int count;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else {
                count = 0;
                boolean isMatch = false;
                while (!stack.isEmpty()) {
                    Object peek = stack.peek();
                    if (peek instanceof Integer) {
                        count += (Integer) peek;
                        stack.pop();
                    } else {
                        char peekC = (char) peek;
                        if (peekC == ')' || isMatch) break;
                        isMatch = true;
                        count += 2;
                        stack.pop();
                    }
                }
                retCount = Math.max(retCount, count);
                stack.push(count);
                if (!isMatch) {
                    stack.push(c);
                }
            }
        }
        return retCount;
    }

    /**
     * 解法二：
     * 动态规划四步走
     * - 确认原问题与子问题: 原问题为求s中最长有效括号，子问题可拆解为前i个中最长有效括号。
     * - 确认状态: 本题的动态规划状态单一，第i个状态即为前i个字符串中最长括号数。
     * - 确认边界状态的值: dp[1]=0，从1开始
     * - 确定状态转移方程: 对于(())适用dp[i] = dp[i - 1] + 2;对于()()适用 dp[i] += dp[i - dp[i]];
     * 两者结合一起判断，防止()()(())这种情况
     * <p>
     * 执行用时 : 4 ms, 在Longest Valid Parentheses的Java提交中击败了98.68% 的用户
     * 内存消耗 : 37.6 MB, 在Longest Valid Parentheses的Java提交中击败了82.01% 的用户
     *
     * @param s
     * @return
     */
    public int longestValidParentheses2(String s) {
        if (s == null || s.equals(""))
            return 0;
        int maxlen = 0;
        //当前对于的最大的连续括号数
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + 2;
                }
                if (i - dp[i] >= 0 && dp[i - dp[i]] > 0) {
                    dp[i] += dp[i - dp[i]];
                }
                maxlen = dp[i] > maxlen ? dp[i] : maxlen;
            }
        }
        return maxlen;
    }
}
