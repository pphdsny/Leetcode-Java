package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-04-28.
 * 44. 通配符匹配
 * <p>
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 * <p>
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 * 示例 1:
 * <p>
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 * 示例 2:
 * <p>
 * 输入:
 * s = "aa"
 * p = "*"
 * 输出: true
 * 解释: '*' 可以匹配任意字符串。
 * 示例 3:
 * <p>
 * 输入:
 * s = "cb"
 * p = "?a"
 * 输出: false
 * 解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。
 * 示例 4:
 * <p>
 * 输入:
 * s = "adceb"
 * p = "*a*b"
 * 输出: true
 * 解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".
 * 示例 5:
 * <p>
 * 输入:
 * s = "acdcb"
 * p = "a*c?b"
 * 输入: false
 *
 * @see <a href="https://leetcode-cn.com/problems/wildcard-matching/">wildcard-matching</a>
 */
public class _44_isMatch {
    public static void main(String[] args) {
        _44_isMatch isMatch = new _44_isMatch();
        //解法一
        System.out.println(isMatch.isMatch("aa","a"));
        System.out.println(isMatch.isMatch("aa","*"));
        System.out.println(isMatch.isMatch("cb","?a"));
        System.out.println(isMatch.isMatch("adceb","*a*b"));
        System.out.println(isMatch.isMatch("acdcb","a*c?b"));
    }

    /**
     * 这是一道贪心+回溯+动态规划的难题，可以考虑用几种方式实现下
     * 解题1（动态规划）：
     * 存在一个匹配的问题，用一个二维的数组进行保存匹配结果
     *  1. p.charAt(j) == '?' || s.charAt(i) == p.charAt(j)。
     *     含义是如果p为此处字符为?或者p此处的字符与s此处的字符相同，
     *     则显然dp[i+1][j+1] = dp[i][j]
     *  2. p.charAt(j) == '*'。这种情况较为复杂。分情况讨论。
     *      A. *代表0个字符，则此时dp[i+1][j+1]=dp[i+1][j]
     *      B. *代表1个字符，则此时dp[i+1][j+1]=dp[i][j]
     *      C. *代表2个字符，则此时dp[i+1][j+1]=dp[i-1][j]
     *      D. *代表3个字符，则此时dp[i+1][j+1]=dp[i-2][j]
     *      ...
     *      因此，最后的统一结果为
     *      dp[i+1][j+1]=dp[i+1][j] || dp[i][j] || dp[i-1][j] ||
     *                   dp[i-2][j] || ... || dp[0][j]
     *      需要注意的是按照上述公式，可以得到
     *      dp[i][j+1] = dp[i][j] || dp[i-1][j] || dp[i-2][j] ||
     *                   dp[i-3][j] || ... || dp[0][j]
     *      因此dp[i+1][j+1] = dp[i+1][j] || dp[i][j+1]
     *  3. s.charAt(i) != p.charAt(j) 不匹配
     *      dp[i+1][j+1] = false;
     *
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] dp = new boolean[sLen + 1][pLen + 1];
        dp[0][0] = true;
        for (int j = 0; j < pLen; j++) {
            //初始化第一行数据
            dp[0][j + 1] = dp[0][j] && (p.charAt(j) == '*');

            for (int i = 0; i < sLen; i++) {
                //情况1
                if (p.charAt(j) == '?' || p.charAt(j) == s.charAt(i)) {
                    dp[i + 1][j + 1] = dp[i][j];
                }
                //情况2
                else if (p.charAt(j) == '*') {
                    dp[i + 1][j + 1] = (dp[i][j + 1] || dp[i + 1][j]);
                }
                //not match
                else {
                    dp[i + 1][j + 1] = false;
                }
            }
        }

        return dp[sLen][pLen];
    }

}
