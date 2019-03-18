package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2018/9/19.
 * 76. 最小覆盖子串
 * <p>
 * 给定一个字符串 S 和一个字符串 T，请在 S 中找出包含 T 所有字母的最小子串。
 * <p>
 * 示例：
 * <p>
 * 输入: S = "ADOBECODEBANC", T = "ABC"
 * 输出: "BANC"
 * 说明：
 * <p>
 * 如果 S 中不存这样的子串，则返回空字符串 ""。
 * 如果 S 中存在这样的子串，我们保证它是唯一的答案。
 *
 * @see <a href="https://lingkou.com/problems/minimum-window-substring/description/">minimum-window-substring</a>
 */
public class _76_minWindow {
    public static void main(String[] args) {
        String s = minWindow("ADOBECODEBANC", "ABC");
        System.out.println(s);
        String s1 = minWindow("aa", "aa");
        System.out.println(s1);
        String s2 = minWindow("abbbbbcdd", "abcdd");
        System.out.println(s2);
    }

    public static String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int[] map_s = new int[128];
        int[] map_t = new int[128];
        int begin = 0;
        char[] chars_s = s.toCharArray();
        char[] chars_t = t.toCharArray();
        for (int i = 0; i < t.length(); i++) {
            map_t[chars_t[i]] += 1;
        }
        String result = "";
        for (int next = 0; next < s.length(); next++) {
            map_s[chars_s[next]]++;
            while (begin < next) {
                if (map_t[chars_s[begin]] == 0) {
                    begin++;
                    //不是t中元素
                } else if (map_s[chars_s[begin]] > map_t[chars_s[begin]]) {
                    map_s[chars_s[begin]]--;
                    begin++;
                } else {
                    break;
                }
            }
            boolean windowOk = isWindowOk(map_s, map_t);
            if (windowOk) {
                String temp = s.substring(begin, next + 1);
                if (result.equals("") || result.length() > temp.length()) {
                    result = temp;
                }
            }
        }

        return result;
    }

    private static boolean isWindowOk(int[] chars_s, int[] chars_t) {
        for (int i = 0; i < chars_t.length; i++) {
            if (chars_t[i] > 0) {
                if (chars_s[i] < chars_t[i]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * 优秀解法
     *
     * @param s
     * @param t
     * @return
     */
    public static String minWindow2(String s, String t) {
        int[] cnt = new int[128];
        for (char c : t.toCharArray()) {
            cnt[c]++;
        }
        int from = 0;
        int total = t.length();
        int min = Integer.MAX_VALUE;
        for (int i = 0, j = 0; i < s.length(); i++) {
            if (cnt[s.charAt(i)]-- > 0)
                total--;
            while (total == 0) {
                if (i - j + 1 < min) {
                    min = i - j + 1;
                    from = j;
                }
                if (++cnt[s.charAt(j++)] > 0)
                    total++;
            }
        }
        return (min == Integer.MAX_VALUE) ? "" : s.substring(from, from + min);
    }
}
