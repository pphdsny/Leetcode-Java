package pp.arithmetic.leetcode;

import java.util.Stack;

/**
 * Created by wangpeng on 2019-09-26.
 * 647. 回文子串
 * <p>
 * 给定一个字符串，你的任务是计算这个字符串中有多少个回文子串。
 * <p>
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被计为是不同的子串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abc"
 * 输出: 3
 * 解释: 三个回文子串: "a", "b", "c".
 * 示例 2:
 * <p>
 * 输入: "aaa"
 * 输出: 6
 * 说明: 6个回文子串: "a", "a", "a", "aa", "aa", "aaa".
 * 注意:
 * <p>
 * 输入的字符串长度不会超过1000。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindromic-substrings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _647_countSubstrings {
    public static void main(String[] args) {
        _647_countSubstrings countSubstrings = new _647_countSubstrings();
        //1
        System.out.println(countSubstrings.countSubstrings("abc"));
        System.out.println(countSubstrings.countSubstrings("aaa"));
        System.out.println(countSubstrings.countSubstrings("abab"));
        //2
        System.out.println(countSubstrings.countSubstrings2("abc"));
        System.out.println(countSubstrings.countSubstrings2("aaa"));
        System.out.println(countSubstrings.countSubstrings2("abab"));
        //3
        System.out.println(countSubstrings.countSubstrings3("abc"));
        System.out.println(countSubstrings.countSubstrings3("aaa"));
        System.out.println(countSubstrings.countSubstrings3("abab"));

    }

    /**
     * 解法一：
     * 定义两个指针，从0到len判断所有的回文可能性，性能不一定高，结果一定对（时间复杂度O(n^3)）
     * 执行用时 :116 ms, 在所有 Java 提交中击败了12.26%的用户
     * 内存消耗 :34.3 MB, 在所有 Java 提交中击败了92.34%的用户
     * <p>
     * 解法二：{@link _647_countSubstrings#countSubstrings2(String)}
     *
     * 解法三：{@link _647_countSubstrings#countSubstrings3(String)}
     *
     * @param s
     * @return
     */
    public int countSubstrings(String s) {
        int retCount = 0;
        int start, end;
        for (start = 0; start < s.length(); start++) {
            for (end = start; end < s.length(); end++) {
                if (isPalindrome(s, start, end)) {
                    retCount++;
                }
            }
        }

        return retCount;
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start <= end) {
            if (s.charAt(start) != s.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    /**
     * 解法二：中心扩展==>时间复杂度O(n^2)
     * 从中心（区分1个点的中心还是两个点的中心）向两边同时扩散，如左右两边都相等则是回文，继续扩散
     *
     * 执行用时 :2 ms , 在所有 Java 提交中击败了99.02%的用户
     * 内存消耗 :34 MB, 在所有 Java 提交中击败了92.79%的用户
     *
     * @param s
     * @return
     */
    public int countSubstrings2(String s) {
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            //分奇偶考虑
            res += countSegment(s, i, i);
            res += countSegment(s, i, i + 1);
        }
        return res;
    }

    //start往左边跑，end往右边跑, 判断s[start, end]是否为回文
    public int countSegment(String s, int start, int end) {
        int count = 0;
        while (start >= 0 && end < s.length() && s.charAt(start--) == s.charAt(end++))
            count++;
        return count;
    }

    /**
     * 解法三：动态规划==>时间复杂度O(n^2)
     * 基本原理同解法二，只是用动态规划的方式求解出来了
     *
     * 执行用时 :11 ms, 在所有 Java 提交中击败了47.66%的用户
     * 内存消耗 :35.6 MB, 在所有 Java 提交中击败了86.94%的用户
     * @param s
     * @return
     */
    public static int countSubstrings3(String s) {
        int result = 0;
        boolean[][] dp = new boolean[s.length()][s.length()];

        for (int i = s.length()-1; i >=0 ; i--) {
            for (int j = i; j < s.length(); j++) {
                if (i==j)
                    dp[i][j] = true;
                else
                    dp[i][j] = s.charAt(i)==s.charAt(j) && (j<=i+1 || dp[i+1][j-1]);
                if (dp[i][j]) result++;
            }
        }

        return result;
    }
}
