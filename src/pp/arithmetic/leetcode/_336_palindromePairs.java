package pp.arithmetic.leetcode;

import pp.arithmetic.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wangpeng on 2018/11/2.
 * 336. 回文对
 * <p>
 * 给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: ["abcd","dcba","lls","s","sssll"]
 * 输出: [[0,1],[1,0],[3,2],[2,4]]
 * 解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
 * 示例 2:
 * <p>
 * 输入: ["bat","tab","cat"]
 * 输出: [[0,1],[1,0]]
 * 解释: 可拼接成的回文串为 ["battab","tabbat"]
 *
 * @see <a href="https://leetcode-cn.com/problems/palindrome-pairs/description/">palindrome-pairs</a>
 */
public class _336_palindromePairs {
    public static void main(String[] args) {
        boolean aba = isPaind("aba");
        List<List<Integer>> lists = palindromePairsOptimize(new String[]{
                "abcd", "dcba", "lls", "s", "sssll"
        });
        for (int i = 0; i < lists.size(); i++) {
            Util.printList(lists.get(i));
        }

    }

    /**
     * 时间复杂度O(n*m^2),m为字符串的平均长度
     * LeetCode能提交成功，但是当每个字符串都特别长的时候，复杂度还是很高
     *
     * @param words
     * @return
     */
    private static List<List<Integer>> palindromePairsOptimize(String[] words) {
        List<List<Integer>> resultList = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            //自身就是回文，防止有""
            if (isPaind(word)) {
                Integer index = map.get("");
                if (index != null) {
                    addList(index, i, resultList);
                    addList(i, index, resultList);
                }
            }

            String reversed = new StringBuilder(word).reverse().toString();
            //是否直接匹配上
            Integer reversedIndex = map.get(reversed);
            if (reversedIndex != null && reversedIndex != i) {
                addList(reversedIndex, i, resultList);
            }
            for (int j = 1; j < reversed.length(); j++) {
                String left = reversed.substring(0, j);
                String right = reversed.substring(j, reversed.length());
                if (isPaind(left)) {
                    if (!right.equals("")) {
                        Integer index = map.get(right);
                        if (index != null && index != i) {
                            //回文
                            addList(i, index, resultList);
                        }
                    }
                }
                if (isPaind(right)) {
                    if (!left.equals("")) {
                        Integer index = map.get(left);
                        if (index != null && index != i) {
                            //回文
                            addList(index, i, resultList);
                        }
                    }
                }
            }
        }
        return resultList;
    }

    private static void addList(Integer fir, Integer sec, List<List<Integer>> resultList) {
        List<Integer> list = new ArrayList<>();
        list.add(fir);
        list.add(sec);
        resultList.add(list);
    }

    private static boolean isPaind(String string) {
        char[] chars = string.toCharArray();
        if (chars.length == 0) {
            return false;
        }
        for (int i = 0; i < chars.length / 2; i++) {
            if (chars[i] != chars[chars.length - i - 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 时间复杂度O(n^2*m),m为字符串的平均长度，太久了需优化
     *
     * @param words
     * @return
     */
    private static List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> resultList = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                if (i == j) {
                    continue;
                }
                String newString = words[i] + words[j];
                char[] chars = newString.toCharArray();
                int start = 0, end = chars.length - 1;
                boolean isPalin = true;
                while (start < end) {
                    if (chars[start] != chars[end]) {
                        isPalin = false;
                        break;
                    }
                    start++;
                    end--;
                }
                if (isPalin) {
                    List<Integer> list = new ArrayList<>();
                    list.add(i);
                    list.add(j);
                    resultList.add(list);
                }
            }
        }
        return resultList;
    }
}
