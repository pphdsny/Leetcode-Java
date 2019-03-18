package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/10/31.
 * 5. 最长回文子串
 * <p>
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba"也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-palindromic-substring/description/">longest-palindromic-substring</a>
 */
public class _5_longestPalindrome {
    public static void main(String[] args) {
        String maxStr = longestPalindrome2("");
        System.out.println(maxStr);
    }

    /**
     * 中心遍历，从一个点的中心和两个点的中心向左右两边遍历
     * 时间复杂度O(n^2)
     *
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s == null || s.length() == 0){
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            //一个点的中心
            int len1 = expandAroundCenter(s, i, i);
            //二个点的中心
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private static int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;
    }

    /**
     * 错误解法，暴力遍历所有起始位置和终止位置，情况复杂还容易漏，有问题
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        char[] chars = s.toCharArray();
        int start = 0, end = chars.length - 1;
        StringBuilder builder = new StringBuilder();
        String maxStr = "";
        int count = 0;
        while (start < chars.length) {
            int tempStart = start;
            while (start <= end) {
                if (chars[start] == chars[end]) {
                    if (start == end && builder.length() == 0) {
                        builder.append(chars[start]);
                        break;
                    }
                    builder.insert(count, chars[start]);
                    if (end > start) {
                        builder.insert(builder.length() - count, chars[end]);
                    }
                    start++;
                    end--;
                    count++;
                } else {
                    if (builder.length() == 0) {
                        //还未开始匹配
                        end--;
                    } else {
                        //已经开始匹配，中止
                        break;
                    }
                }
            }
            //下一次循环
            if (builder.length() > maxStr.length() && start >= end) {
                maxStr = builder.toString();
            }
            builder.delete(0, builder.length());
            count = 0;
            end = chars.length - 1;
            start = tempStart + 1;
        }
        if (builder.length() > maxStr.length()) {
            maxStr = builder.toString();
        }
        return maxStr;
    }
}
