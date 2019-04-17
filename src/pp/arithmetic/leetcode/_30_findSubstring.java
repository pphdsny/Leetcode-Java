package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangpeng on 2019-04-17.
 * 30. 串联所有单词的子串
 * <p>
 * 给定一个字符串 s 和一些长度相同的单词 words。找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * s = "barfoothefoobarman",
 * words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoor" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：
 * s = "wordgoodgoodgoodbestword",
 * words = ["word","good","best","word"]
 * 输出：[]
 *
 * @see <a href="https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words/">substring-with-concatenation-of-all-words</a>
 */
public class _30_findSubstring {

    public static void main(String[] args) {
        _30_findSubstring findSubstring = new _30_findSubstring();
        List<Integer> list = findSubstring.findSubstring("barfoothefoobarman", new String[]{"foo", "bar"});
        Util.printList(list);
        List<Integer> list1 = findSubstring.findSubstring2("wordgoodgoodgoodbestword", new String[]{"word", "good", "best", "good"});
        Util.printList(list1);
    }

    /**
     * 解题思路：
     * 这是一道字符串中找单词的问题，需要找到单词的位置，不可能每次都循环遍历，由于是字符串的问题可以考虑用哈希表
     * 1、遍历单词，哈希表储存每个单词出现的次数
     * 2、遍历字符串，按单词长度跳跃，找到每个单词出现的次数
     * 3、次数超过对于单词或者未找到相应匹配，则跳过
     * <p>
     * 执行用时 : 242 ms, 在Substring with Concatenation of All Words的Java提交中击败了45.27% 的用户
     * 内存消耗 : 63.2 MB, 在Substring with Concatenation of All Words的Java提交中击败了33.24% 的用户
     * <p>
     * 通过提交leetcode来看，效率并不是很高，分析下时间复杂度在O(n^2)
     * <p>
     * 优化方案 {@link _30_findSubstring#findSubstring2(String, String[])}
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> retList = new ArrayList<>();
        if (words.length == 0) {
            return retList;
        }
        int wordLen = words[0].length();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], map.getOrDefault(words[i], 0) + 1);
        }
        int si = 0, ei;
        HashMap<String, Integer> iteMap = new HashMap<>();
        int forLen = s.length() - words.length * wordLen;
        while (si <= forLen) {
            ei = si;
            while (ei <= s.length() - wordLen) {
                String item = s.substring(ei, ei + wordLen);
                if (map.getOrDefault(item, 0) == 0) {
                    si++;
                    iteMap.clear();
                    break;
                }
                //遍历次数
                int iteCount = iteMap.getOrDefault(item, 0);
                iteMap.put(item, ++iteCount);
                if (iteCount > map.get(item)) {
                    //出现次数已超过
                    si++;
                    iteMap.clear();
                    break;
                }
                ei += wordLen;
                if (ei - si == words.length * wordLen) {
                    //找到满足条件的
                    retList.add(si);
                    si++;
                    iteMap.clear();
                    break;
                }
            }
        }

        return retList;
    }


    /**
     * 优化方案二：leetcode解题
     *
     * 执行用时 : 27 ms, 在Substring with Concatenation of All Words的Java提交中击败了93.31% 的用户
     * 内存消耗 : 44.8 MB, 在Substring with Concatenation of All Words的Java提交中击败了78.98% 的用户
     *
     * 大体思路和方案一一致，优化的点是减少了无效的循环次数fori，重复利用了forj不满足条件后的平移
     *
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        if (words.length == 0)
            return new ArrayList<>();
        int num = 0;
        List<Integer> res = new ArrayList<>();
        Map<String, Integer> wordsCount = new HashMap<>();
        Map<String, Integer> usedWords = new HashMap<>();
        for (String w : words)
            wordsCount.put(w, wordsCount.getOrDefault(w, 0) + 1);
        int wlen = words[0].length();
        for (int i = 0; i < wlen; i++, num = 0, usedWords.clear()) {
            for (int j = i; j + wlen <= s.length(); j += wlen) {
                String sub = s.substring(j, j + wlen);
                if (wordsCount.containsKey(sub)) {
                    num++;
                    usedWords.put(sub, usedWords.getOrDefault(sub, 0) + 1);
                    while (usedWords.get(sub) > wordsCount.get(sub)) {
                        String rem = s.substring(j - (num - 1) * wlen, j - (num - 2) * wlen);
                        usedWords.put(rem, usedWords.get(rem) - 1);
                        num--;
                    }
                } else {
                    num = 0;
                    usedWords.clear();
                }
                if (num == words.length) {
                    res.add(j - (num - 1) * wlen);
                }
            }
        }
        return res;
    }
}
