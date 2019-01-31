package pp.arithmetic.easy;

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
 * @see <a href="https://leetcode-cn.com/problems/find-all-anagrams-in-a-string/">find-all-anagrams-in-a-string</a>
 */
public class _463_findAnagrams {

    public static void main(String[] args) {
        Util.printList(findAnagrams("cbaebabacd", "abc"));
        Util.printList(findAnagrams("abab", "ab"));
    }

    /**
     * 时间复杂度（O(m*n)）
     *
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> retList = new ArrayList<>();
        int[] pn = new int[26];
        int[] sn;
        for (int i = 0; i < p.length(); i++) {
            pn[p.charAt(i) - 'a']++;
        }

        for (int i = 0; i <= s.length() - p.length(); i++) {
            sn = new int[26];
            for (int j = 0; j < p.length(); j++) {
                sn[s.charAt(j + i) - 'a']++;
            }
            if (isSame(pn, sn)) {
                retList.add(i);
            }
        }

        return retList;
    }


    private static boolean isSame(int[] num1, int[] num2) {
        if (num1.length != num2.length) {
            return false;
        }
        for (int i = 0; i < num1.length && i < num2.length; i++) {
            if (num1[i] != num2[i]) {
                return false;
            }
        }
        return true;
    }
}
