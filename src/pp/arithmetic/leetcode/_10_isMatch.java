package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/11/13.
 * 10. 正则表达式匹配
 * <p>
 * 给定一个字符串 (s) 和一个字符模式 (p)。实现支持 '.' 和 '*' 的正则表达式匹配。
 * <p>
 * '.' 匹配任意单个字符。
 * '*' 匹配零个或多个前面的元素。
 * 匹配应该覆盖整个字符串 (s) ，而不是部分字符串。
 * <p>
 * 说明:
 * <p>
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。
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
 * p = "a*"
 * 输出: true
 * 解释: '*' 代表可匹配零个或多个前面的元素, 即可以匹配 'a' 。因此, 重复 'a' 一次, 字符串可变为 "aa"。
 * 示例 3:
 * <p>
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个('*')任意字符('.')。
 * 示例 4:
 * <p>
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 'c' 可以不被重复, 'a' 可以被重复一次。因此可以匹配字符串 "aab"。
 * 示例 5:
 * <p>
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * @see <a href="https://leetcode-cn.com/problems/regular-expression-matching/description/">regular-expression-matching</a>
 */
public class _10_isMatch {
    private static final int FRONT = -1;

    public static void main(String[] args) {
        //false
        System.out.println(isMatch("aa", "aaa"));
        //true
        System.out.println(isMatch("aa", "aa"));
        //true
        System.out.println(isMatch("ab", ".*"));
        //true
        System.out.println(isMatch("aab", "c*a*b"));
        //false
        System.out.println(isMatch("mississippi", "mis*is*p*."));
    }

    /**
     * 采用回溯法，当你发现从前开始遍历的时候比较难判断，不妨试试从后开始遍历
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        return myMatch(s, s.length() - 1, p, p.length() - 1);
    }

    private static boolean myMatch(String s, int i, String p, int j) {
        if (j == FRONT) {
            if (i == FRONT)
                return true;
            else
                return false;
        } else if (p.charAt(j) == '*') {
            if (i > FRONT && (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i)))
                if (myMatch(s, i - 1, p, j))
                    return true;
            return myMatch(s, i, p, j - 2);
        } else if (i > FRONT && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))) {
            return myMatch(s, i - 1, p, j - 1);
        }
        return false;
    }
}
