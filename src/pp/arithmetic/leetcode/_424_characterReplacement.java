package pp.arithmetic.leetcode;

/**
 * Created by wangpeng on 2019-03-27.
 * 424. 替换后的最长重复字符
 * <p>
 * 给你一个仅由大写英文字母组成的字符串，你可以将任意位置上的字符替换成另外的字符，总共可最多替换 k 次。在执行上述操作后，找到包含重复字母的最长子串的长度。
 * <p>
 * 注意:
 * 字符串长度 和 k 不会超过 104。
 * <p>
 * 示例 1:
 * <p>
 * 输入:
 * s = "ABAB", k = 2
 * <p>
 * 输出:
 * 4
 * <p>
 * 解释:
 * 用两个'A'替换为两个'B',反之亦然。
 * 示例 2:
 * <p>
 * 输入:
 * s = "AABABBA", k = 1
 * <p>
 * 输出:
 * 4
 * <p>
 * 解释:
 * 将中间的一个'A'替换为'B',字符串变为 "AABBBBA"。
 * 子串 "BBBB" 有最长重复字母, 答案为 4。
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-repeating-character-replacement/">longest-repeating-character-replacement</a>
 */
public class _424_characterReplacement {
    public static void main(String[] args) {
        _424_characterReplacement replacement = new _424_characterReplacement();
        System.out.println(replacement.characterReplacement2("ABAB", 2));
        System.out.println(replacement.characterReplacement2("AABABBA", 1));
    }

    /**
     * 解法二：更优O（2n）
     * 优化方案：构建了窗口的一次移动规则，而不是需要从头开始遍历
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement2(String s, int k) {
        int max = 0, start = 0, end = 0, max_cur = 0;
        int[] count = new int[26];
        while (end < s.length()) {
            max_cur = Math.max(max_cur, ++count[s.charAt(end) - 'A']);
            while (end - start + 1 - max_cur > k)
                count[s.charAt(start++) - 'A']--;
            max = Math.max(max, end - start + 1);
            end++;
        }
        return max;
    }

    /**
     * 首先构造了一个移动窗口，因为有多个字母，所以需要把所有的字母都遍历一下
     * 时间复杂度O(n^2)=n+n-1+n-2+...+1
     *
     * @param s
     * @param k
     * @return
     */
    public int characterReplacement(String s, int k) {
        int[] cs = new int[26];
        int max = 0;
        int li, ri, ki;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (cs[c - 'A'] == 1) {
                //已经遍历过了
                continue;
            }
            //开始c的遍历
            cs[c - 'A'] = 1;
            li = ri = i;
            ki = k;
            while (ri < s.length()) {
                if (s.charAt(ri) != c) {
                    ki--;
                }
                while (ki < 0) {
                    if (s.charAt(li++) != c) ki++;
                }
                max = Math.max(max, ri - li + 1);
                ri++;
            }
            max = Math.max(max, ri - li + ki);
        }
        max = Math.min(max, s.length());

        return max;
    }
}
