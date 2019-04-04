package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangpeng on 2019-01-29.
 * 438. 找到字符串中所有字母异位词
 * <p>
 * 给定一个字符串 s 和一个非空字符串 p，找到 s 中所有是 p 的字母异位词的子串，返回这些子串的起始索引。
 * <p>
 * 字符串只包含小写英文字母，并且字符串 s 和 p 的长度都不超过 20100。
 * <p>
 * 说明：
 * <p>
 * 字母异位词指字母相同，但排列不同的字符串。
 * 不考虑答案输出的顺序。
 * 示例 1:
 * <p>
 * 输入:
 * s: "cbaebabacd" p: "abc"
 * <p>
 * 输出:
 * [0, 6]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的字母异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的字母异位词。
 * 示例 2:
 * <p>
 * 输入:
 * s: "abab" p: "ab"
 * <p>
 * 输出:
 * [0, 1, 2]
 * <p>
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的字母异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的字母异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的字母异位词。
 *
 * @see <a href="https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/">find-leetcode-anagrams-in-a-string</a>
 */
public class _438_findAnagrams {

    public static void main(String[] args) {
        Util.printList(findAnagrams("cbaebabacd", "abc"));
        Util.printList(findAnagrams("abab", "ab"));
    }

    /**
     * 更优的解法
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        int l = 0, r = 0;
        int count = p.length();
        int[] freq = new int[26];
        for (int i = 0; i<p.length();i++) {
            freq[p.charAt(i)-'a']++;
        }
        List<Integer> res = new ArrayList<>();
        while (r < s.length()) {
            //当前元素次数减一
            freq[s.charAt(r)-'a']--;
            if (freq[s.charAt(r++)-'a'] >= 0) {
                count--;
            }
            while (count == 0) {
                if ((r - l) == p.length()) {
                    res.add(l);
                }
                if (freq[s.charAt(l)-'a'] == 0){
                    count++;
                }
                freq[s.charAt(l++)-'a']++;
            }

        }
        return res;
    }
}
