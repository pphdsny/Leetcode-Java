package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-12-27.
 * 132. 分割回文串 II
 *
 * 给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。
 *
 * 返回符合要求的最少分割次数。
 *
 * 示例:
 *
 * 输入: "aab"
 * 输出: 1
 * 解释: 进行一次分割就可将 s 分割成 ["aa","b"] 这样两个回文子串。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-partitioning-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _132_minCut {

    public static void main(String[] args) {
        _132_minCut minCut = new _132_minCut();
        System.out.println(minCut.minCut("aab"));
    }

    /**
     * 解题思路：
     * 要求最少分割次数，所以在一次分割中尽可能的形成较长的回文子串，当然你也可以将所有的可能性都列出来，取其中最少的（耗时）
     *
     * @param s
     * @return
     */
    public int minCut(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        int[] min = new int[s.length()];
        min[0] = 0;
        for (int i = 1; i < s.length(); i++) {
            int temp = Integer.MAX_VALUE;
            for (int j = 0; j <= i; j++) {
                if (s.charAt(j) == s.charAt(i) && (j + 1 > i - 1 || dp[j + 1][i - 1])) {
                    dp[j][i] = true;
                    if (j == 0) {
                        temp = 0;
                    } else {
                        temp = Math.min(temp, min[j - 1] + 1);
                    }
                }
            }
            min[i] = temp;

        }
        return min[s.length() - 1];

    }
}
