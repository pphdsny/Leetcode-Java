package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

/**
 * Created by wangpeng on 2019-12-13.
 * 115. 不同的子序列
 *
 * 给定一个字符串 S 和一个字符串 T，计算在 S 的子序列中 T 出现的个数。
 *
 * 一个字符串的一个子序列是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串。（例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 *
 * 示例 1:
 *
 * 输入: S = "rabbbit", T = "rabbit"
 * 输出: 3
 * 解释:
 *
 * 如下图所示, 有 3 种可以从 S 中得到 "rabbit" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 *
 * rabbbit
 * ^^^^ ^^
 * rabbbit
 * ^^ ^^^^
 * rabbbit
 * ^^^ ^^^
 * 示例 2:
 *
 * 输入: S = "babgbag", T = "bag"
 * 输出: 5
 * 解释:
 *
 * 如下图所示, 有 5 种可以从 S 中得到 "bag" 的方案。
 * (上箭头符号 ^ 表示选取的字母)
 *
 * babgbag
 * ^^ ^
 * babgbag
 * ^^    ^
 * babgbag
 * ^    ^^
 * babgbag
 *   ^  ^^
 * babgbag
 *     ^^^
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/distinct-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _115_numDistinct {

    public static void main(String[] args) {
        _115_numDistinct numDistinct = new _115_numDistinct();
        System.out.println(numDistinct.numDistinct("aaa", "aa"));
        System.out.println(numDistinct.numDistinct("rabbbit", "rabbit"));
        System.out.println(numDistinct.numDistinct("babgbag", "bag"));
        System.out.println(numDistinct.numDistinct("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc", "bcddceeeebecbc"));
        Util.printDivideLine();
        System.out.println(numDistinct.numDistinct2("babgbag", "bag"));
        System.out.println(numDistinct.numDistinct2("aaa", "aa"));
        System.out.println(numDistinct.numDistinct2("rabbbit", "rabbit"));
        System.out.println(numDistinct.numDistinct2("adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc", "bcddceeeebecbc"));
    }

    /**
     * 解题思路：
     * 通过模拟题意中的过程，感知有点回溯的感觉：
     * 1、优先匹配前面的
     * 2、匹配上了之后，再匹配后面的
     * 3、直到匹配到最后一个，向后搜索匹配的，直到末尾
     * 4、最后一位匹配到末尾之后，倒数第二位向后搜索匹配
     * 5、如此循环，直至第一位也匹配到末尾结束
     *
     * 可解题，遇到复杂的提交超时：
     * 比如这跟case：
     * "adbdadeecadeadeccaeaabdabdbcdabddddabcaaadbabaaedeeddeaeebcdeabcaaaeeaeeabcddcebddebeebedaecccbdcbcedbdaeaedcdebeecdaaedaacadbdccabddaddacdddc"
     * "bcddceeeebecbc"
     * 本地跑出结果：700531452
     *
     * 优化思考：是不是可以考虑将遍历过程中的一些结果保存起来，而不是每次凑重新计算==>动态规划{@link _115_numDistinct#numDistinct2(String, String)}
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct(String s, String t) {
        if (s.length() < t.length()) return 0;
        int sum = 0;
        int ti = 0;
        int si = 0;
        while (si < s.length() && ti < t.length()) {
            if (s.charAt(si) == t.charAt(ti)) {
                if (si + 1 < s.length() && ti + 1 < t.length()) {
                    sum += numDistinct(s.substring(si + 1), t.substring(ti + 1));
                } else {
                    if (ti == t.length() - 1) {
                        sum += 1;
                    }
                }
            }
            si++;
        }

        return sum;
    }

    /**
     * 优化求解：找递进规律（s:babgbag,t:bag)
     * T/S "" b a b g b a g
     * ""   1 1 1 1 1 1 1 1
     * b    0 1 1 2 2 3 3 3
     * a    0 0 1 1 1 1 4 4
     * g    0 0 0 0 1 1 1 5
     *
     * dp[t.length() + 1][s.length() + 1] : dp[i][j]代表t[0-i]在s[0-j]中的出现的次数
     * 如果某一位t[i]==s[j]，则此时的次数=dp[i-1][j-1]+dp[i][j-1]
     * 如果某一位t[i]!=s[j]，则此时的次数=dp[i][j-1]
     *
     * 执行用时 :7 ms, 在所有 java 提交中击败了79.83%的用户
     * 内存消耗 :35.8 MB, 在所有 java 提交中击败了85.40%的用户
     *
     * @param s
     * @param t
     * @return
     */
    public int numDistinct2(String s, String t) {
        int[][] dp = new int[t.length() + 1][s.length() + 1];
        for (int j = 0; j < s.length() + 1; j++) dp[0][j] = 1;
        for (int i = 1; i < t.length() + 1; i++) {
            for (int j = 1; j < s.length() + 1; j++) {
                if (t.charAt(i - 1) == s.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i][j - 1];
                } else {
                    dp[i][j] = dp[i][j - 1];
                }
            }
        }
        return dp[t.length()][s.length()];
    }
}
