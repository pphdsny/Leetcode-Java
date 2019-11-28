package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-11-27.
 * 97. 交错字符串
 * <p>
 * 给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。
 * <p>
 * 示例 1:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
 * 输出: false
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/interleaving-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _97_isInterleave {

    public static void main(String[] args) {
        _97_isInterleave isInterleave = new _97_isInterleave();
        System.out.println(isInterleave.isInterleave("a", "bbbbbbb", "abbbbbba"));
        System.out.println(isInterleave.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave.isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(isInterleave.isInterleave("a", "", "c"));
        System.out.println(isInterleave.isInterleave("ab", "bc", "bbac"));
    }

    /**
     * 解题思路：循环比较，回溯算法
     * 先比较第i位+比较后面的位，如果第i位后面的都能匹配上，加上第i位也能匹配上，那么就能完全匹配。i从0开始
     *
     * 执行用时 :1362 ms, 在所有 java 提交中击败了5.10%的用户
     * 内存消耗 :34.7 MB, 在所有 java 提交中击败了42.29%的用户
     *
     * @param s1、
     * @param s2
     * @param s3
     * @return
     */
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        return isInterleave(s1, s2, s3, 0, 0, 0);
    }

    private boolean isInterleave(String s1, String s2, String s3, int i1, int i2, int i3) {
        if (i3 == s3.length()) return true;
        char c3 = s3.charAt(i3);
        if (((i1 < s1.length() && s1.charAt(i1) != c3) || "".equals(s1)) &&
                ((i2 < s2.length() && s2.charAt(i2) != c3) || "".equals(s2))) {
            return false;
        }
        if (i1 < s1.length() && s1.charAt(i1) == c3 && isInterleave(s1, s2, s3, i1 + 1, i2, i3 + 1)) {
            return true;
        }
        if (i2 < s2.length() && s2.charAt(i2) == c3 && isInterleave(s1, s2, s3, i1, i2 + 1, i3 + 1)) {
            return true;
        }
        return false;
    }
}
