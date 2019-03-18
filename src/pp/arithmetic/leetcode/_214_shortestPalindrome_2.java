package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/11/5.
 * 214. 最短回文串
 * <p>
 * 给定一个字符串 s，你可以通过在字符串前面添加字符将其转换为回文串。找到并返回可以用这种方式转换的最短回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "aacecaaa"
 * 输出: "aaacecaaa"
 * 示例 2:
 * <p>
 * 输入: "abcd"
 * 输出: "dcbabcd"
 *
 * @see <a href="https://leetcode-cn.com/problems/shortest-palindrome/description/">shortest-palindrome</a>
 */
public class _214_shortestPalindrome_2 {
    public static void main(String[] args) {
        String s1 = shortestPalindrome("aacecaaa");
        System.out.println(s1);
        String s2 = shortestPalindrome("abcd");
        System.out.println(s2);
        String s3 = shortestPalindrome("abbacd");
        System.out.println(s3);
        String s4 = shortestPalindrome("aaaabbaa");
        System.out.println(s4);
        String s5 = shortestPalindrome("abcdefba");
        System.out.println(s5);
    }

    /**
     * 实现，时间复杂度O()
     *
     * @param s
     * @return
     */
    public static String shortestPalindrome(String s) {
        int j = 0;

        // 找出s对于s.reverse的子串
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
        }

        // 递归边界: s本身就是回文子串
        if (j == s.length()) {
            return s;
        }


        String suffix = s.substring(j);

        return new StringBuffer(suffix).reverse().
                append(shortestPalindrome(s.substring(0, j))).
                append(suffix).
                toString();
    }
}
