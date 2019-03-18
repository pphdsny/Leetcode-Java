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
public class _214_shortestPalindrome {
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
     * 暴力匹配算法
     * 实现，时间复杂度O(n^2)
     * @param s
     * @return
     */
    public static String shortestPalindrome(String s) {
        if (s.length() == 0) {
            return "";
        }
        int len = s.length();
        int index = len / 2;
        int minRight = 0;

        while (index > 0) {
            //回文有两种：奇数和偶数
            int min1 = generate(s, index - 1, index + 1);
            int min2 = generate(s, index - 1, index);
            if (min1 != -1 || min2 != -2) {
                int tempRight = Math.max(min1, min2);
                minRight = Math.max(tempRight, minRight);
            }
            if (minRight == len - 1) {
                //本身正好是回文
                return s;
            }

            index--;
        }
        String s2 = s.substring(minRight + 1, len);
        String reverse = reverse(s2);

        return reverse + s;
    }

    private static int generate(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right <= len - 1) {
            if (s.charAt(left) != s.charAt(right)) {
                //不是回文
                break;
            }
            left--;
            right++;
        }
        if (++left == 0 && s.charAt(left) == s.charAt(--right)) {
            return right;
        }
        return -1;
    }

    private static String reverse(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            char temp = chars[end];
            chars[end] = chars[start];
            chars[start] = temp;
            start++;
            end--;
        }
        return new String(chars);
    }
}
