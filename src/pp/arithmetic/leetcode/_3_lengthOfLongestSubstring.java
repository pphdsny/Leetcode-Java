package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/9/19.
 * 3.无重复字符的最长子串
 * <p>
 * 给定一个字符串，找出不含有重复字符的最长子串的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "abc"，其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 无重复字符的最长子串是 "b"，其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 无重复字符的最长子串是 "wke"，其长度为 3。
 * 请注意，答案必须是一个子串，"pwke" 是一个子序列 而不是子串。
 *
 * @see <a href="https://lingkou.com/problems/longest-substring-without-repeating-characters/description/">longest-substring-without-repeating-characters</a>
 */
public class _3_lengthOfLongestSubstring {
    public static void main(String[] args) {
        int max = lengthOfLongestSubstring("abcabcbb");
        System.out.println(max);
        int max1 = lengthOfLongestSubstring("bbbbb");
        System.out.println(max1);
        int max2 = lengthOfLongestSubstring("pwwkew");
        System.out.println(max2);
        int max3 = lengthOfLongestSubstring("aab");
        System.out.println(max3);
        int max4 = lengthOfLongestSubstring2("abcabcbb");
        System.out.println(max4);
    }

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] chars = s.toCharArray();
        int start = 0;
        int next = 0;
        int[] use = new int[128];
        int maxLength = 0;
        while (next < chars.length) {
            use[chars[next]] += 1;
            if (use[chars[next]] == 1) {
                if (next - start >= maxLength) {
                    maxLength = next - start + 1;
                }
            } else {
                while (start <= next && use[chars[next]] > 1) {
                    use[chars[start]] -= 1;
                    start++;
                }
            }
            next++;
        }
        return maxLength;
    }

    //官方，减少循环次数
    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}
